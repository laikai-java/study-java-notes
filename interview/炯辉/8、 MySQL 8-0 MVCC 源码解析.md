> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [joonwhee.blog.csdn.net](https://joonwhee.blog.csdn.net/article/details/108379583)

之前在 [面试必问的 MySQL，你懂了吗？](https://blog.csdn.net/v123411739/article/details/106893197)中简单的介绍了 MVCC 的原理，掌握了这个原理其实在面试时是可以加分不少的。

因为现在很多人的理解还是停留在《高性能 MySQL》书中的版本，也就是通过**创建版本号**和**删除版本号**来判断。这个时候如果你能给出正确的理解，则会让面试官眼前一亮，这也是我们在面试中凸显出 “自己和其他候选者不一样的地方”，会更有利于在众多候选者中脱颖而出。

本文在此基础上，对 MVCC 展开详细的分析，同时修改了之前的一些不太准确的说法，希望可以助你在面试中更好的发（zhuang）挥（bi）。

PS：本文的源码基于 MySQL 8.0.16，对于现阶段生产环境常用的 5.7.* 版本，MVCC 部分的源码基本相同，因此可以放心参考。而 5.6.* 则有比较大的不同，主要是一些数据结构都改变了，但是究其核心原理还是基本一致的。

### 并发事务带来的问题（现象）

脏读：一个事务读取到另一个事务更新但还未提交的数据，如果另一个事务出现回滚或者进一步更新，则会出现问题。

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkZxZzc4MjI5eWppYlBuOE9iczN2U040Vlo3TEtYanpsM3E1WE9wSWtxUUFLcGFwUDhoY0pLVlhHM21LbWtsMEk0WG9pYVM3eVRrdndWdy82NDA?x-oss-process=image/format,png)

不可重复读：在一个事务中两次次读取同一个数据时，由于在两次读取之间，另一个事务修改了该数据，所以出现两次读取的结果不一致。

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkZxZzc4MjI5eWppYlBuOE9iczN2U040cWdRWWFrM043c29CRFZ3eVdKRHJpYU1ycVB2bE9zSXdURlpnVldxM1lNcUlTbUYxTkREWDVqZy82NDA?x-oss-process=image/format,png)

幻读：在一个事务中使用相同的 SQL 两次读取，第二次读取到了其他事务新插入的行。

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkZxZzc4MjI5eWppYlBuOE9iczN2U040WG1BSWpwRTRIVlpZaE5hTHZLNUNIeGNLWW4yaWJwZW9oVUxFbnE5ZThFSkNpYlZ0aWJZaGFzcW9nLzY0MA?x-oss-process=image/format,png)

要解决这些并发事务带来的问题，一个比较简单粗暴的方法是加锁，但是加锁必然会带来性能的降低，因此 MySQL 使用了 MVCC 来提升并发事务下的性能。

### MVCC 带来的好处？

试想，如果没有 MVCC，为了保证并发事务的安全，一个比较容易想到的办法就是加读写锁，实现：读读不冲突、读写冲突、写读冲突，写写冲突，在这种情况下，并发读写的性能必然会收到严重影响。

而通过 MVCC，我们可以做到读写之间不冲突，我们读的时候只需要将当前记录拷贝一份到内存中（ReadView），之后该事务的查询就只跟 ReadView 打交道，不影响其他事务对该记录的写操作。

### 事务隔离级别

读未提交（Read Uncommitted）：最低的隔离级别，会读取到其他事务还未提交的内容，存在脏读。

读已提交（Read Committed）：读取到的内容都是已经提交的，可以解决脏读，但是存在不可重复读。

可重复读（Repeatable Read）：在一个事务中多次读取时看到相同的内容，可以解决不可重复读，但是存在幻读。但是在 InnoDB 中不存在幻读问题，对于快照读，InnoDB 使用 MVCC 解决幻读，对于当前读，InnoDB 通过 gap locks 或 next-key locks 解决幻读。

串行化（Serializable）：最高的隔离级别，串行的执行事务，没有并发事务问题。

### 核心数据结构

trx_sys_t：事务系统中央存储器数据结构

```
struct trx_sys_t {
  TrxSysMutex mutex; /*! 互斥锁 */
​
  MVCC *mvcc;    /*!  mvcc */
​
  volatile trx_id_t max_trx_id; /*! 要分配给下一个事务的事务id*/
​
  std::atomic<trx_id_t> min_active_id; /*! 最小的活跃事务Id */
  
  // 省略...
​
  trx_id_t rw_max_trx_id; /*!< 最大读写事务Id */
​
  // 省略...
​
  trx_ids_t rw_trx_ids; /*! 当前活跃的读写事务Id列表 */
​
  Rsegs rsegs; /*!< 回滚段 */
​
  // 省略...
};
```

MVCC：MVCC 读取视图管理器

```
class MVCC {
 public:
  // 省略...
​
  /** 创建一个视图 */
  void view_open(ReadView *&view, trx_t *trx);
​
  /** 关闭一个视图 */
  void view_close(ReadView *&view, bool own_mutex);
​
  /** 释放一个视图 */
  void view_release(ReadView *&view);
​
 // 省略...
​
  /** 判断视图是否处于活动和有效状态 */
  static bool is_view_active(ReadView *view) {
    ut_a(view != reinterpret_cast<ReadView *>(0x1));
​
    return (view != NULL && !(intptr_t(view) & 0x1));
  }
​
 // 省略...
​
 private:
  typedef UT_LIST_BASE_NODE_T(ReadView) view_list_t;
​
  /** 空闲可以被重用的视图*/
  view_list_t m_free;
​
  /**  活跃或者已经关闭的 Read View 的链表 */
  view_list_t m_views;
};
```

ReadView：视图，某一时刻的一个事务快照

```
class ReadView {
​
  // 省略...
​
 private:
  /** 高水位，大于等于这个ID的事务均不可见*/
  trx_id_t m_low_limit_id;
​
  /** 低水位：小于这个ID的事务均可见 */
  trx_id_t m_up_limit_id;
​
  /** 创建该 Read View 的事务ID*/
  trx_id_t m_creator_trx_id;
​
  /** 创建视图时的活跃事务id列表*/
  ids_t m_ids;
​
  /** 配合purge，标识该视图不需要小于m_low_limit_no的UNDO LOG，
   * 如果其他视图也不需要，则可以删除小于m_low_limit_no的UNDO LOG*/
  trx_id_t m_low_limit_no;
​
  /** 标记视图是否被关闭*/
  bool m_closed;
​
  // 省略...
};
```

### 增加隐藏字段

为了实现 MVCC，InnoDB 会向数据库中的每行记录增加三个字段：

DB_ROW_ID：行 ID，6 字节，随着插入新行而单调递增，如果有主键，则不会包含该列。

DB_TRX_ID：事务 ID，6 字节，记录插入或更新该行的最后一个事务的事务标识，也就是事务 ID。

DB_ROLL_PTR：回滚指针，7 字节，指向写入回滚段的 undo log 记录。每次对某条记录进行更新时，会通过 undo log 记录更新前的行内容，更新后的行记录会通过 DB_ROLL_PTR 指向该 undo log 。当某条记录被多次修改时，该行记录会存在多个版本，通过 DB_ROLL_PTR 链接形成一个类似版本链的概念，大致如下图所示。

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkd4RkRTUXhrbEc0cEZQaWJvWHppY3ViSzBBc2Y5YnFROVZZZDdMN0djY3NPeXZmdkNzOU5yaWFacnBLMmljaWJnS2FHczZQdXZTSnJSbUdHZy82NDA?x-oss-process=image/format,png)

**源码分析**

在源码中，添加这 3 个字段的方法在：/storage/innobase/dict/dict0dict.cc 的 dict_table_add_system_columns 方法中，核心部分如下图。

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkd4RkRTUXhrbEc0cEZQaWJvWHppY3ViS0hLWXNNTjVZWHVCUFp3SEI4UFFBeTRnY2RxUjZGNDd5dDdBZThkencyT0x3dmY1S2lhWG1uQUEvNjQw?x-oss-process=image/format,png)

### 增删改的底层操作

当我们更新一条数据，InnoDB 会进行如下操作：

1.  加锁：对要更新的行记录加排他锁
    
2.  写 undo log：将更新前的记录写入 undo log，并构建指向该 undo log 的回滚指针 roll_ptr
    
3.  更新行记录：更新行记录的 DB_TRX_ID 属性为当前的事务 Id，更新 DB_ROLL_PTR 属性为步骤 2 生成的回滚指针，将此次要更新的属性列更新为目标值
    
4.  写 redo log：DB_ROLL_PTR 使用步骤 2 生成的回滚指针，DB_TRX_ID 使用当前的事务 Id，并填充更新后的属性值
    
5.  处理结束，释放排他锁
    

删除操作：在底层实现中是使用更新来实现的，逻辑基本和更新操作一样，几个需要注意的点：1）写 undo log 中，会通过 type_cmpl 来标识是删除还是更新，并且不记录列的旧值；2）这边不会直接删除，只会给行记录的 info_bits 打上删除标识（REC_INFO_DELETED_FLAG），之后会由专门的 purge 线程来执行真正的删除操作。

插入操作：相比于更新操作比较简单，就是新增一条记录，DB_TRX_ID 使用当前的事务 Id，同样会有 undo log 和 redo log。

**源码分析**

更新行记录的核心源码在：/storage/innobase/btr/btr0cur.cc/btr_cur_update_in_place 方法，核心部分如下图。

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkZxZzc4MjI5eWppYlBuOE9iczN2U040Yk9MdzJYeDhaTDdabEFpYWFRM0tveEhZOVhOcnppYUN5Z2JpYUpsYjFsNFdNUGdKR3JHWjRCR1VBLzY0MA?x-oss-process=image/format,png)

### 构建一致性读取视图（ReadView）

当我们的隔离级别为 RR 时：每开启一个事务，系统会给该事务会分配一个事务 Id，在该事务执行第一个 select 语句的时候，会生成一个当前时间点的事务快照 ReadView，核心属性如下：

*   m_ids：创建 ReadView 时当前系统中活跃的事务 Id 列表，可以理解为生成 ReadView 那一刻还未执行提交的事务，并且该列表是个升序列表。
    
*   m_up_limit_id：低水位，取 m_ids 列表的第一个节点，因为 m_ids 是升序列表，因此也就是 m_ids 中事务 Id 最小的那个。
    
*   m_low_limit_id：高水位，生成 ReadView 时系统将要分配给下一个事务的 Id 值。
    
*   m_creator_trx_id：创建该 ReadView 的事务的事务 Id。
    

**源码分析**

MVCC 模式下的普通查询主方法入口在：/storage/innobase/row/row0sel.cc 的 row_search_mvcc 方法中，之后的所有源码分析基本都在该方法内。

具体创建视图的方法在 ReadView::prepare，调用链如下：

row_search_mvcc -> trx_assign_read_view -> MVCC::view_open -> 

ReadView::prepare，源码如下：

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkd4RkRTUXhrbEc0cEZQaWJvWHppY3ViS051NHlGZmliRjBuNzRIUm4waFFaYThnOE5IdDM5RXo5elJpYWlhTFJIV2ljeWljSHZwRTJUUlIyVDJBLzY0MA?x-oss-process=image/format,png)

最后，会将这个创建的 ReadView 添加到 MVCC 的 m_views 中。

视图可见性判断：SQL 查询走聚簇索引

有了这个 ReadView，这样在访问某条记录时，只需要按照下边的步骤判断记录的某个版本是否可见：

1.  如果被访问版本的 trx_id 与 ReadView 中的 m_creator_trx_id 值相同，意味着当前事务在访问它自己修改过的记录，所以该版本可以被当前事务访问。
    
2.  如果被访问版本的 trx_id 小于 ReadView 中的 m_up_limit_id（低水位），表明被访问版本的事务在当前事务生成 ReadView 前已经提交，所以该版本可以被当前事务访问。
    
3.  如果被访问版本的 trx_id 大于等于 ReadView 中的 m_low_limit_id（高水位），表明被访问版本的事务在当前事务生成 ReadView 后才开启，所以该版本不可以被当前事务访问。
    
4.  如果被访问版本的 trx_id 属性值在 ReadView 的 m_up_limit_id 和 m_low_limit_id 之间，那就需要判断 trx_id 属性值是不是在 m_ids 列表中，这边会通过二分法查找。如果在，说明创建 ReadView 时生成该版本的事务还是活跃的，该版本不可以被访问；如果不在，说明创建 ReadView 时生成该版本的事务已经被提交，该版本可以被访问。
    

在进行判断时，首先会拿记录的最新版本来比较，如果该版本无法被当前事务看到，则通过记录的 DB_ROLL_PTR 找到上一个版本，重新进行比较，直到找到一个能被当前事务看到的版本。

而对于删除，其实就是一种特殊的更新，InnoDB 在 info_bits 中用一个标记位 delete_flag 标识是否删除。当我们在进行判断时，会检查下 delete_flag 是否被标记，如果是，则会根据情况进行处理：1）如果索引是聚簇索引，并且具有唯一特性（主键、唯一索引等），则返回 DB_RECORD_NOT_FOUND；2）否则，会寻找下一条记录继续流程。

其实很容易理解，如果是唯一索引查询，必然只有一条记录，如果被删除了则直接返回空，而如果是普通索引，可能存在多个相同值的行记录，该行不存在，则继续查找下一条。

以上内容是对于 RR 级别来说，而对于 RC 级别，其实整个过程几乎一样，唯一不同的是生成 ReadView 的时机，RR 级别只在事务第一次 select 时生成一次，之后一直使用该 ReadView。而 RC 级别则在每次 select 时，都会生成一个 ReadView。

**源码分析**

走聚簇索引的核心流程在 row_search_mvcc 方法，如下：

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkVyWWduc3N4MVFrNTd4RHVwbVMzaDdkZVprYkpaSW93NEphcUtWNFpKU3drcW55OWRYQmtkZ2JiZk1Nakw0NDh1ZHJiVnJIQzRJOFEvNjQw?x-oss-process=image/format,png)

视图可见性判断在方法：changes_visible，调用链如下：

row_search_mvcc -> lock_clust_rec_cons_read_sees -> 

changes_visible，源码如下：

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkVyWWduc3N4MVFrNTd4RHVwbVMzaDdwWXBzQjVZbGNKUjQ1VVB2b2JNMzlCbTFQaDVEOEpXTTdhU2FtUkxrQkRnRTRhM1VOYjBEancvNjQw?x-oss-process=image/format,png)

判断记录是否被打上 delete_flag 标的方法在：/storage/innobase/include/rem0rec.ic 的 rec_get_deleted_flag 方法中，如下图。

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkZxZzc4MjI5eWppYlBuOE9iczN2U040T1RWckswOHJ3eG5UaWMySGdZaWFpY1NobTA4NE9oNTZCOGlhSDhJQmlibWIzYzdHbVIzSkh4VXRnOFEvNjQw?x-oss-process=image/format,png)

获取记录的上一个版本

获取记录的上一个版本，主要是通过 DB_ROLL_PTR 来实现，核心流程如下：

1.  获取记录的回滚指针 DB_ROLL_PTR、获取记录的事务 Id 
    
2.  通过回滚指针拿到对应的 undo log
    
3.  解析 undo log，并使用 undo log 构建用于更新向量 UPDATE
    
4.  构建记录的上一个版本：先用记录的当前版本填充，然后使用 UPDATE（undo log）进行覆盖。
    

**源码解析**

构建记录的上一个版本：trx_undo_prev_version_build，调用链如下：

row_search_mvcc -> row_sel_build_prev_vers_for_mysql -> row_vers_build_for_consistent_read -> trx_undo_prev_version_build，源码如下：

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkZxZzc4MjI5eWppYlBuOE9iczN2U040YXVjMzRieGx5ZEx2aFNXaFJ6UU1ocnFTclVXS0FmVHdKOFRWaDViUXJyMDBJeGRlZDBHV3pBLzY0MA?x-oss-process=image/format,png)

### 视图可见性判断：SQL 查询走普通（二级）索引

[面试必问的 MySQL，你懂了吗？](https://blog.csdn.net/v123411739/article/details/106893197) 只分析了走聚簇索引的情况，本文简单的介绍下走普通（二级）索引的情况。

当走普通索引时，判断逻辑如下：

1.  判断被访问索引记录所在页的最大事务 Id 是否小于 ReadView 中的 m_up_limit_id（低水位），如果是则代表该页的最后一次修改事务 Id 在 ReadView 创建前以前已经提交，则必然可以访问；如果不是，并不代表一定不可以访问，道理跟走聚簇索引一样，事务 Id 大的也可能提交比较早，所以需要做进一步判断，见步骤 2。
    
2.  使用 ICP（Index Condition Pushdown）根据索引信息来判断搜索条件是否满足，这边主要是在使用聚簇索引判断前先进行过滤，这边有三种情况：a）ICP 判断不满足条件但没有超出扫描范围，则获取下一条记录继续查找；b）如果不满足条件并且超出扫描返回，则返回 DB_RECORD_NOT_FOUND；c）如果 ICP 判断符合条件，则会获取对应的聚簇索引来进行可见性判断。
    

**源码分析**

普通（非聚簇）索引的视图可见性判断在方法：lock_sec_rec_cons_read_sees，调用链如下：

row_search_mvcc -> lock_sec_rec_cons_read_sees，源码如下：

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkVyWWduc3N4MVFrNTd4RHVwbVMzaDdCYmZITUxEdElBdmhFTGxneVpRSUNzYWliTmljMFBYUW1KU3VYSVgyRUJlYlQ4QjlhWksyb2ljMmcvNjQw?x-oss-process=image/format,png)

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkVyWWduc3N4MVFrNTd4RHVwbVMzaDdYNDZTMWdZNTVqQjFjVDY5dXk4MU1yNEhtYW1QNmpURHJuMmJLUzdSVXVxOExEUEtGSjVISkEvNjQw?x-oss-process=image/format,png)

### ICP（Index Condition Pushdown）

ICP 是 MySQL 5.6 引入的一个优化，根据官方的说法：ICP 可以减少存储引擎访问基表的次数 和 MySQL 访问存储引擎的次数，这边涉及到 MySQL 底层的处理逻辑，不是本文重点，这边不进行细讲。

这边用官方的例子简单介绍下，我们有张 people 表，索引定义为：INDEX (zipcode, lastname, firstname)，对于以下这个 SQL：

```
SELECT * FROM people
  WHERE zipcode='95054'
  AND lastname LIKE '%etrunia%'
  AND address LIKE '%Main Street%';
```

当没有使用 ICP 时：此查询会使用该索引，但是必须扫描 people 表所有符合 zipcode='95054' 条件的记录。

当使用 ICP 时：不仅会使用 zipcode 的条件来进行过滤，还会使用 （lastname LIKE '%etrunia%'）来进行过滤，这样可以避免扫描符合 zipcode 条件而不符合 lastname 条件匹配的记录行 。

ICP 的官方文档：https://dev.mysql.com/doc/refman/8.0/en/index-condition-pushdown-optimization.html

### 当前读和快照读

当前读：官方叫做 Locking Reads（锁定读取），读取数据的最新版本。常见的 update/insert/delete、还有 select ... for update、select ... lock in share mode 都是当前读。

官方文档：https://dev.mysql.com/doc/refman/8.0/en/innodb-locking-reads.html

快照读：官方叫做 Consistent Nonlocking Reads（一致性非锁定读取，也叫一致性读取），读取快照版本，也就是 MVCC 生成的 ReadView。用于普通的 select 的语句。

官方文档：https://dev.mysql.com/doc/refman/8.0/en/innodb-consistent-read.html

### MVCC 解决了幻读了没有？

MVCC 解决了部分幻读，但并没有完全解决幻读。

对于快照读，MVCC 因为因为从 ReadView 读取，所以必然不会看到新插入的行，所以天然就解决了幻读的问题。

而对于当前读的幻读，MVCC 是无法解决的。需要使用 Gap Lock 或 Next-Key Lock（Gap Lock + Record Lock）来解决。

其实原理也很简单，用上面的例子稍微修改下以触发当前读：select * from user where id < 10 for update，当使用了 Gap Lock 时，Gap 锁会锁住 id < 10 的整个范围，因此其他事务无法插入 id < 10 的数据，从而防止了幻读。

### Repeatable Read 解决了幻读是什么情况？

SQL 标准中规定的 RR 并不能消除幻读，但是 MySQL InnoDB 的 RR 可以，靠的就是 Gap 锁。在 RR 级别下，Gap 锁是默认开启的，而在 RC 级别下，Gap 锁是关闭的。

### 例子 1：RR（RC） 真正生成 ReadView 的时机

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkZxZzc4MjI5eWppYlBuOE9iczN2U040cTh6TkhuMkVXb1R5V0YySmVkRUUwc0hXcko5aWNFNkdXZDJSR0k0THA2TEx2eUFraWJBUlFwWmcvNjQw?x-oss-process=image/format,png)

解析：RR 生成 ReadView 的时机是事务第一个 select 的时候，而不是事务开始的时候。右边的例子中，事务 1 在事务 2 提交了修改后才执行第一个 select，因此生成的 ReadView 中，a 的是 100 而不是事务 1 刚开始时的 50。

### 例子 2：RR 和 RC 生成 ReadView 的区别

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkZxZzc4MjI5eWppYlBuOE9iczN2U040U2VGdkVFdnZRdWJhaWNySlVCa2tiMzN1ZGZFbDdoZ082bzJlV21oNGNreWJRRXRsWXZrMmdPQS82NDA?x-oss-process=image/format,png)

解析：RR 级别只在事务第一次 select 时生成一次，之后一直使用该 ReadView。而 RC 级别则在每次 select 时，都会生成一个 ReadView，所以 在第二次 select 时，读取到了事务 2 对于 a 的修改值。

MySQL 的源码主要是 c++ 写的，因此自己看起来比较吃力，花了挺多时间学习整理的。如果你能掌握本文的内容，面试 Java 岗位，无论是哪个公司，相信都能让面试官眼前一亮。

现在互联网的竞争越来越激烈，如果很多东西都只停留在表面，很难取得面试官的 “芳心”，只有在适当的时候亮出自己的 “长剑”，才能在众多候选人中凸显出自己的与众不同。你需要向面试官证明，为什么是你而不是其他人。