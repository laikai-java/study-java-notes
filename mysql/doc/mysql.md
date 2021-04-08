# mysql架构

## 逻辑架构图



# ![](..\img\mysql逻辑架构图.jpg)



###  连接层

最上层是一些客户端和连接服务，包含本地 sock 通信和大多数基于客户端/服务端工具实现的类似于 tcp/ip 的
通信。主要完成一些类似于连接处理、授权认证、及相关的安全方案。在该层上引入了线程池的概念，为通过认证
安全接入的客户端提供线程。同样在该层上可以实现基于 SSL 的安全链接。服务器也会为安全接入的每个客户端验证它所具有的操作权限。

### 服务层

| ManagementServeices&Utilities | 系统管理和控制工具                                           |
| ----------------------------- | ------------------------------------------------------------ |
| SQLInterface                  | :SQL 接口。接受用户的 SQL 命令，并且返回用户需要查询的结果。比如 select from<br/>就是调用 SQLInterface |
| Parser 解析器                 | SQL 命令传递到解析器的时候会被解析器验证和解析               |
| Optimizer 查询优化器          | SQL 语句在查询之前会使用查询优化器对查询进行优化，比如有<br/>where 条件时，优化器来决定先投影还是先过滤。 |
| Cache 和 Buffer 查询缓存      | 如果查询缓存有命中的查询结果，查询语句就可以直接去查询缓存中取<br/>数据。这个缓存机制是由一系列小缓存组成的。比如表缓存，记录缓存，key 缓存，<br/>权限缓存等 |
|                               |                                                              |

### 引擎层

存储引擎层，存储引擎真正的负责了 MySQL 中数据的存储和提取，服务器通过 API 与存储引擎进行通信。不同
的存储引擎具有的功能不同，这样我们可以根据自己的实际需要进行选取。

### 存储层

数据存储层，主要是将数据存储在运行于裸设备的文件系统之上，并完成与存储引擎的交互。

## sql执行

![](..\img\sql语句.png)

![](..\img\sql执行顺序.png)

# 索引优化

## JOIN 连接查询

![](..\img\连接查询.png)

### 内连接

```sql
select <select_list> from A inner join B on a.key= b.key
```

### 左连接

```sql
selecet <select_list> from A left join B ON a.key = b.key
```

### 右连接

```sql
select <select_list> from A right join B on a.key = b.key
```

## 索引

### 定义

*是一种数据结构*

**排好序的快速查找数据结构**

### 索引分类

#### 单值索引

概念：即一个索引只包含单个列，一个表可以有多个单列索引

```sql
##表创建时进行创建 KEY(customer_name)
CREATE TABLE customer (id INT(10) UNSIGNED AUTO_INCREMENT ,customer_no VARCHAR(200),customer_name
VARCHAR(200),
PRIMARY KEY(id),
KEY(customer_name)
);

##单独建立索引  索引名 最好以idx开始  加上表名+列明
create index idx_customer_name on customer(customer_name);
```

#### 唯一索引

概念：索引列的值必须唯一，但允许有空值 

```sql
##随表一起创建 UNIQUE(customer_no)
CREATE TABLE customer (id INT(10) UNSIGNED AUTO_INCREMENT ,customer_no VARCHAR(200),customer_name
VARCHAR(200),
PRIMARY KEY(id),
KEY(customer_name),
UNIQUE(customer_no)
);
##单独建立唯一索引
CREATE UNIQUE INDEX idx_customer_no ON customer(customer_no);

```

#### 主键索引

概念：设定为主键后数据库会自动建立索引，innodb为聚簇索引 

```sql
## 随表一起建索引 PRIMARY KEY(id)
CREATE TABLE customer (
    id INT(10) UNSIGNED AUTO_INCREMENT ,
    customer_no VARCHAR(200),
    customer_name VARCHAR(200),
	PRIMARY KEY(id)
);

##单独建主键索引：
ALTER TABLE customer add PRIMARY KEY customer(customer_no);
##删除建主键索引：
ALTER TABLE customer drop PRIMARY KEY;
##修改建主键索引：必须先删除掉(drop)原索引，再新建(add)索引


```

#### 复合索引

概念：即一个索引包含多个列

```sql
##随表建立  KEY(customer_no,customer_name)
CREATE TABLE customer (
    id INT(10) UNSIGNED AUTO_INCREMENT ,
    customer_no VARCHAR(200),
    customer_name VARCHAR(200),
    PRIMARYKEY(id),
    KEY(customer_name),
    UNIQUE(customer_name),
    KEY(customer_no,customer_name)
);
##单独建立
CREATE INDEX idx_no_name ON customer(customer_no,customer_name);
```

### 索引的创建时机

####  适合创建索引的情况

1. 主键自动建立唯一索引；
2. 频繁作为查询条件的字段应该创建索引
3. 查询中与其它表关联的字段，外键关系建立索引 
4. 单键/组合索引的选择问题， 组合索引性价比更高
5. 查询中**排序**的字段，排序字段若通过索引去访问将大大提高排序速度

6. 查询中统计或者**分组字段**(需要orderby 字段建立索引)

#### 不适合创建索引的情况

1. 表记录太少
2. 经常增删改的表或者字段
3. Where 条件里用不到的字段不创建索引
4. 过滤性不好的不适合建索引

# Explain 性能分析

## 概念

使用 EXPLAIN 关键字可以模拟优化器执行 SQL 查询语句，从而知道 MySQL 是如何处理你的 SQL 语句的。分析你的查询语句或是表结构的性能瓶颈。
用法： **Explain+SQL** 语句。 Explain 执行后返回的信息：

## 能干嘛

![](..\img\explain用处.png)

表的读取顺序

数据读取操作的操作类型

哪些索引可以使用

哪些索引被实际使用

表之间的引用

每张表有多少行被优化器查询

## 怎么玩

```sql
explain + SQL   语句
```

### 数据准备

```sql
CREATE TABLE `t_dept` (
`id` INT ( 11 ) NOT NULL AUTO_INCREMENT,
`deptName` VARCHAR ( 30 ) DEFAULT NULL,
`address` VARCHAR ( 40 ) DEFAULT NULL,
PRIMARY KEY ( `id` ) 
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;
CREATE TABLE `t_emp` (
`id` INT ( 11 ) NOT NULL AUTO_INCREMENT,
`name` VARCHAR ( 20 ) DEFAULT NULL,
`age` INT ( 3 ) DEFAULT NULL,
`deptId` INT ( 11 ) DEFAULT NULL,
empno INT NOT NULL,
PRIMARY KEY ( `id` ),
KEY `idx_dept_id` ( `deptId` ) 
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;
INSERT INTO t_dept ( deptName, address )
VALUES
	( '华山', '华山' );
INSERT INTO t_dept ( deptName, address )
VALUES
	( '丐帮', '洛阳' );
INSERT INTO t_dept ( deptName, address )
VALUES
	( '峨眉', '峨眉山' );
INSERT INTOt_dept ( deptName, address )
VALUES
	( '武当', '武当山' );
INSERT INTO t_dept ( deptName, address )
VALUES
	( '明教', '光明顶' );
INSERT INTO t_dept ( deptName, address )
VALUES
	( '少林', '少林寺' );
INSERT INTO t_emp ( NAME, age, deptId, empno )
VALUES
	( '风清扬', 90, 1, 100001 );
INSERT INTO t_emp ( NAME, age, deptId, empno )
VALUES
	( '岳不群', 50, 1, 100002 );
INSERT INTO t_emp ( NAME, age, deptId, empno )
VALUES
	( '令狐冲', 24, 1, 100003 );
INSERT INTO t_emp ( NAME, age, deptId, empno )
VALUES
	( '洪七公', 70, 2, 100004 );
INSERT INTO t_emp ( NAME, age, deptId, empno )
VALUES
	( '乔峰', 35, 2, 100005 );
INSERT INTO t_emp ( NAME, age, deptId, empno )
VALUES
	( '灭绝师太', 70, 3, 100006 );
INSERT INTO t_emp ( NAME, age, deptId, empno )
VALUES
	( '周芷若', 20, 3, 100007 );
INSERT INTO t_emp ( NAME, age, deptId, empno )
VALUES
	( '张三丰', 100, 4, 100008 );
INSERT INTO t_emp ( NAME, age, deptId, empno )
VALUES
	( '张无忌', 25, 5, 100009 );
INSERT INTO t_emp ( NAME, age, deptId, empno )
VALUES
	( '韦小宝', 18, NULL, 100010 );









CREATE TABLE t1(id INT(10) AUTO_INCREMENT,content VARCHAR(100) NULL, PRIMARY KEY(id));
CREATE TABLE t2(id INT(10) AUTO_INCREMENT,content VARCHAR(100) NULL, PRIMARY KEY(id));  
CREATE TABLE t3(id INT(10) AUTO_INCREMENT,content VARCHAR(100) NULL, PRIMARY KEY(id)); 
CREATE TABLE t4(id INT(10) AUTO_INCREMENT,content VARCHAR(100) NULL, PRIMARY KEY(id));
INSERT INTO t1(content) VALUES   ( CONCAT('t1_',FLOOR(1+RAND()*1000))); INSERT INTO t2(content)VALUES(CONCAT('t2_',FLOOR(1+RAND()*1000))); INSERT INTO t3(content)VALUES(CONCAT('t3_',FLOOR(1+RAND()*1000))); INSERT INTO t4(content)VALUES(CONCAT('t4_',FLOOR(1+RAND()*1000)));

```



### 执行计划包含的内容

![](..\img\explain内容.png)



#### id

select 查询的序列号,包含一组数字，表示查询中执行 select 子句或操作表的顺序

反应表的读取顺序

##### id相同

```sql
select * from t1,t2,t3 where t1.id=t2.id and t2.id = t3.id
```

![](..\img\执行计划  id 相同.png)

id  相同 执行顺序由上至下

##### id不同



如果是子查询 id的序号会递增 

 id值越大  优先级越高 越先被执行

##### id相同不同 又相同

```sql
EXPLAIN select t2.* from t2,(select * from t3 where t3.content = '') as s3  where s3.id = t2.id;
```

![](..\img\执行计划  ID 相同又不同.png)

id 如果相同，可以认为是一组，从上往下顺序执行；

在所有组中， id 值越大，优先级越高，越先执行

衍生 =DERIVED

在 `MySQL 5.7` 中，会对衍生表进行合并优化，如果要直观的查看 `select_type` 的值，需要临时关闭该功能（默认是打开的），**下面的介绍中凡是涉及到衍生表的都需要该操作**。

```sql
# 关闭衍生表的合并优化（只对该会话有效）
set session optimizer_switch='derived_merge=off'; 

# 打开衍生表的合并优化（只对该会话有效）
set session optimizer_switch='derived_merge=on';
```

#### select_type

##### 有哪些

##### 查询的类型

主要区分普通查询 联合查询 子查询等复杂查询

###### SIMPLE

简单的select查询 查询中不包括子查询和联合查询

![](..\img\simple单表查询.png)

###### PRIMARY

查询中若**包括**任何复杂的**子部分**   **最外层查询**责备标记为PRIMARY

![](..\img\primary查询.png)

######  SUBQUERY

在select或where列表中包含了子查询

![](..\img\subquery 子查询.png)

![](..\img\where后面 in和=的区别.png)

###### DERIVED

在 FROM 列表中包含的子查询被标记为 DERIVED(衍生)
MySQL 会递归执行这些子查询, 把结果放在临时表里。



###### UNION

若第二个SELECT出现在UNION之后，则被标记为UNION

 若UNION包含在FROM子句的子查询中,外层SELECT将被标记为：DERIVED 

![](..\img\执行计划  union.png)

###### UNION RESULT

 从UNION表获取结果的SELECT

#### TABLE

这个数据是基于哪张表的。

#### TYPE

type 是查询的访问类型。是较为重要的一个指标，结果值从最好到最坏依次是：

```SQL
system>const>eq_ref>ref>fulltext>ref_or_null>index_merge>unique_subquery>index_subquery>range>index>ALL 
```

常用

```sql
system > const > eq_ref > ref > range > index > ALL
```

一般来说，得保证查询至少达到 range 级别，最好能达到 ref。

##### system

表只有一行记录(等于系统表),这是const类型的特列,平时不会出现

##### const

表示通过索引一次就找到了,const 用于比较 primarykey 或者 unique 索引。因为只匹配一行数据，所以很快。

 如将主键置于 where 列表中，MySQL 就能将该查询转换为一个常量。

```sql
explain select * from (select * from t1 where t1.id = 1) s;
```

![](..\img\const.png)

##### eq_ref

唯一性索引扫描，对于每个索引键，表中只有一条记录与之匹配。常见于主键或唯一索引扫描。

```sql
explain select * from t1,t2 where t1.id = t2.id;
```

![](..\img\eq_ref.png)

##### ref

非唯一性索引扫描，返回匹配某个单独值的所有行.本质上也是一种索引访问，它返回所有匹配某个单独值的行，
然而，它可能会找到多个符合条件的行，所以他应该属于查找和扫描的混合体。

没用索引前：

![](..\img\ref-没用索引前.png)

用了索引后:

```sql
create index idx_t2_content on t2(content);
```

![](..\img\ref-用了索引后.png)

##### range

只检索给定范围的行,使用一个索引来选择行。

key 列显示使用了哪个索引一般就是在你的 where 语句中出现 了 between、<、>、in 等的查询这种范围扫描索引扫描比全表扫描要好，因为它只需要开始于索引的某一点，而结束于另一点，不用扫描全部索引。

```sql
explain select * from t1 where t1.id < 5;
explain select * from t1 where t1.id in (1,2,5,7);
```

![](..\img\range.png)

##### index

出现index是sql使用了索引

但是没用通过索引进行过滤，

一般是使用了覆盖索引或者是利用索引进行了排序分组。

```sql
##全表扫描 All
explain select * from t1 ;

##覆盖索引
explain select id from t1;
```

![](..\img\index.png)

##### all

FullTableScan，将遍历全表以找到匹配的行。

![](..\img\all 全表扫描.png)

#### possible_keys

显示可能应用在这张表中的索引，一个或多个。查询涉及到的字段上若存在索引，则该索引将被列出，但不一定被查询实际使用。

#### key

实际使用的索引。如果为NULL，则没有使用索引

#### key_len

表示索引中使用的字节数，可通过该列计算查询中使用的索引的长度。 key_len 字段能够帮你检查是否充分的 利用上了索引。ken_len 越长，说明索引使用的越充分。



#### ref

显示索引的哪一列被使用了，如果可能的话，是一个常数。

哪些列或常量被用于查找索引列上的值。



![](..\img\ref.png)

#### rows

根据表统计信息和索引使用情况 大致估算所需记录需要读取的行数

rows 列显示 MySQL 认为它执行查询时必须检查的行数。越少越好！

#### Extra

其他的额外重要的信息。

##### Usingfilesort

说明 mysql 会对数据使用一个外部的索引排序，而不是按照表内的索引顺序进行读取。MySQL 中无法利用索引 完成的排序操作称为“文件排序”。
出现 filesort 的情况：

```sql
explain select id ,empno,name from t_emp where deptId=100 order by name limit 10;
```

使用索引前

![](..\img\using filesort.png)

使用索引后

![](..\img\useingfilesort-使用索引.png)

查询中排序的字段，排序字段若通过索引去访问将大大提高排序速度。

##### Usingtemporary

使了用临时表保存中间结果,MySQL 在对查询结果排序时使用临时表。常见于排序 orderby 和分组查询 group by。

![](..\img\useing temory.png)





##### Usingindex

Usingindex 代表表示相应的 select 操作中使用了覆盖索引(CoveringIndex)，避免访问了表的数据行，效率不错！ 如果同时出现 using where，表明索引被用来执行索引键值的查找;如果没有同时出现 using where，表明索引只是
用来读取数据而非利用索引执行查找。
利用索引进行了排序或分组。

##### Usingwhere

表明使用了 where 过滤。

# 优化

## 索引分析

### 单表

#### 模拟数据

```sql
CREATE TABLE IF NOT EXISTS `article`(
`id` INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
`author_id` INT (10) UNSIGNED NOT NULL,
`category_id` INT(10) UNSIGNED NOT NULL , 
`views` INT(10) UNSIGNED NOT NULL , 
`comments` INT(10) UNSIGNED NOT NULL,
`title` VARBINARY(255) NOT NULL,
`content` TEXT NOT NULL
);
INSERT INTO `article`(`author_id`,`category_id` ,`views` ,`comments` ,`title` ,`content` )VALUES
(1,1,1,1,'1','1'),
(2,2,2,2,'2','2'),
(3,3,3,3,'3','3');
```

#### 模拟查询

> 查询category_id = 1 且comments > 1 ,观看数量最多的文章

```sql
select id,author_id from article where category_id  = 1 and
comments > 1 order by views desc limit 1;
```

##### 当未加任何索引时

![](..\img\单表查询 未加索引.png)

type: All  全表扫描,情况不容乐观

useing filesort: 文件内排序 ,更不乐观

##### 如何优化

```sql
##查看索引
show index from article;
```

![](..\img\单表未加索引前的article的索引.png)

##### 新建索引

```sql
##第一种方式
alter table article add index idx_article_ccv (category_id,comments,views);

##第二种方式
create index idx_article_ccv on article(category_id,comments,views);
```

再次查看执行计划

![](..\img\单表查询建立索引 在三个参数都加索引.png)

![](..\img\单表查询三个参数索引结论.png)

因为comments >1是一个范围值   使用后 无法对后面的views部分进行索引;

就是说 range 类型查询字段后面的索引实效了;

- 全表扫描已解决，但是文件排序依然存在
- 索引不合适

##### 删除并重建索引

```sql
drop index idx_article_ccv on article;

##只在确定值得列建立索引
create index idx_article_cv on article(category_id,views);
```

##### 再次查看执行计划

![](..\img\单表查询正确索引.png)

### 两表

#### 模拟数据

```sql
CREATE TABLE IF NOT EXISTS `class`(
`id` INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
`card` INT (10) UNSIGNED NOT NULL
);
CREATE TABLE IF NOT EXISTS `book`(
`bookid` INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
`card` INT (10) UNSIGNED NOT NULL
);
INSERT INTO class(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card)VALUES(FLOOR(1+(RAND()*20)));
 
INSERT INTO book(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card)VALUES(FLOOR(1+(RAND()*20)));
```

#### 模拟查询

###### 查看查询计划

```sql
explain select * from class left join book on class.card = book.card;
```

![](..\img\双表未加索引前的执行计划.png)

可以看到 两张表全是全表扫描 并且使用了using join buffer

尝试进行建立索引

###### 先对左表建立索引

```sql
create index idx_card on class(card);
```

![](..\img\双表left join 索引加在左表.png)

可以看到 虽然使用了索引 但是rows 还是跟未加索引时  是一样的

###### 删除索引 对右表建立索引

```sql
##删除索引
drop index idx_card from class;
##最右表建立索引
create index idx_card on book(card);
```

![](..\img\双表查询 left join 对右表进行索引建立.png)

**结果：type变为ref，rows只扫描了一行。**
**结论：这是由于LEFT JOIN特性决定的，由于左表数据全都有，所以关键在于如何从右表进行搜索，所以右表一定要添加索引。**

### 三表

#### 模拟数据

- 在双表的基础上新增一张phone表

```sql
CREATE TABLE IF NOT EXISTS `phone`(
`phoneid` INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
`card` INT (10) UNSIGNED NOT NULL
)ENGINE = INNODB;

INSERT INTO phone(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card)VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card)VALUES(FLOOR(1+(RAND()*20)));

```

##### 三表均没有新建索引

模拟查询

```sql
explain select * from class left join book on class.card = book.card left join phone on class.card = phone.card;
```

![](..\img\三表left join 查询 未加索引前执行计划.png)

**结论： 全表扫描，且使用了连接缓存**

##### 在phone和book表新增索引

```sql
create index idx_phone_card on phone(card);

create index idx_book_card on book(card);
```

![](..\img\三表left join 查询 对右表进行建立索引.png)

### 总结

- 语句优化应尽可能减少join语句中NestedLoop的循环总次数，即“**`永远用小结果集驱动大结果集`**”。
- 优先优化NestedLoop的内层循环。
- 尽量保证join语句中被驱动表的条件字段添加了索引（即LEFT JOIN在右表上添加，反之亦然）。
- 当无法保证被驱动表的条件字段添加索引时，且内存资源充足的前提下，不妨调整join buffer以达到性能优化的目的。

### 索引失效(应该避免)

![](..\img\索引失效.png)

模拟数据

```sql
CREATE TABLE staffs(
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(24) DEFAULT NULL COMMENT'姓名',
`age` INT NOT NULL DEFAULT 0 COMMENT'年龄',
`pos` VARCHAR(20) NOT NULL DEFAULT'' COMMENT'职位',
`add_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT'入职时间'
)CHARSET utf8 COMMENT'员工记录表';

INSERT INTO staffs(`name`,`age`,`pos`,`add_time`) VALUES('z3',22,'manager',NOW());
INSERT INTO staffs(`name`,`age`,`pos`,`add_time`) VALUES('July',23,'dev',NOW());
INSERT INTO staffs(`name`,`age`,`pos`,`add_time`) VALUES('2000',23,'dev',NOW());
INSERT INTO staffs(`name`,`age`,`pos`,`add_time`) VALUES(NULL,23,'test',NOW());

ALTER TABLE staffs ADD INDEX index_staffs_nameAgePos(`name`,`age`,`pos`);

```

查看索引顺序

```sql
show index from staffs;
```

![](..\img\索引优化-模拟数据-查看索引顺序.png)

#### 最佳左前缀原则

**查询时从索引的最左列开始并且*不跳过索引中的列***（带头大哥不能死，中间兄弟不能断。）

带头大哥不能死，中间兄弟不能断。

过滤条件要使用索引必须按照索引建立的顺序，依次满足，一旦跳过某个字段，索引后面的字段都无法被使用。

多列索引是先按照第一列进行排序，然后在第一列排好序的基础上再对第二列排序，如果没有第一列的话，直接访问第二列，那第二列肯定是无序的，直接访问后面的列就用不到索引。

#### 全值匹配我最爱

```sql
explain select * from staffs where name = 'July' and age = 23 and pos = 'dev';
```

![](..\img\全值匹配.png)

#### 不在索引列上做任何操作

计算 、 函数、 ( 自动/手动 ) 类型转换,会导致索引失效而转成全表扫描;

```sql

##
select * from staffs where name = 'July';

##
select * from staffs where left(name,4)= 'July';
```

![](..\img\索引列不进行任何操作.png)

#### 存储引擎不能使用索引中范围条件右边的列

范围之后全失效

#### 尽量使用覆盖索引



只访问索引的查询 (索引列和查询列一致),减少select *



#### mysql在使用不等式(!= 或者<>)的时候无法使用索引会导致全表扫描



#### is null,is not null 也无法使用索引

如果**允许字段为空**，则

- IS NULL 不会导致索引失效
- IS NOT NULL 会导致索引失效

is null 

is not null



#### like通配符 以('%abc...') mysql 索引失效 会变成全表扫描的操作

百分like加右边



解决like '%字符串%'时索引不被使用的方法

使用覆盖索引解决两边

查询的字段  为 建立的索引中的列

#### 字符串不加单引号 索引失效

#### 少用or,用它来连接时会索引失效



 



### 一般性建议

定值 范围 还是排序  一般order by 是给定一个范围

分组必排序

【优化总结口诀】
全值匹配我最爱，最左前缀要遵守；
带头大哥不能死，中间兄弟不能断；
索引列上少计算，范围之后全失效；
Like百分写最右，覆盖索引不写星；
不等空值还有or，索引失效要少用；
VAR引号不可丢，SQL高级也不难！

![](..\img\一般性建议.png)

![](..\img\case小总结.png)





## 查询截取分析



1 explain

----分析----

1观察 至少跑一天 ，看看生产的慢SQL情况

2 开启慢查询日志，设置阈值 比如超过五秒的就是慢SQL 并将它抓取出来

3 explain + 慢SQL分析

4 show profile

5 运维/DBA 进行SQL数据库服务器的参数调优

==总结

1 慢查询的开启并捕获

2 explain + 慢SQL分析

3 show profile 查询SQL在MySQL 服务器里面的执行细节和生命周期情况

4 SQL数据库服务器的参数调优

### 查询优化

#### 永远小表驱动大表

![](..\img\小表驱动大表.png)

```sql

##原理RBO
select * from A where id in (select id from B)
##等价于
for select id from B
for select * from A where A.id = B.id

```

当B表的数据集小于A表时,使用in 优于exists
因为减少最外层的数据交互(即IO操作)



```sql
select * from A where exists (select 1 from B where B.id=A.id)

##等价于 将主表查询的数据 放到子查询做验证
for select * from A
from select * from B where B.id=A.id
```

当B表的数据库大于A表时 使用exists 优于in

#### order by 关键字优化



##### 模拟数据

```sql
create table tblA(
#id int primary key not null auto_increment,
age int,
birth timestamp not null
);

insert into tblA(age, birth) values(22, now());
insert into tblA(age, birth) values(23, now());
insert into tblA(age, birth) values(24, now());

create index idx_A_ageBirth on tblA(age, birth);
```

##### 查看执行计划

###### 案例A

```sql
##order by 按照索引顺序
EXPLAIN SELECT * FROM tblA WHERE age > 20 order by age;

##order by 按照索引顺序
EXPLAIN SELECT * FROM tblA WHERE age > 20 order by age,birth;

##order by 没有按照索引顺序
EXPLAIN SELECT * FROM tblA WHERE age > 20 order by birth;

##order by 没有按照索引顺序
EXPLAIN SELECT * FROM tblA WHERE  age > 20 order by birth,age;

##order by 没有按照索引顺序
EXPLAIN SELECT * FROM tblA WHERE birth > '2021-04-08 19:45:00' ORDER BY birth;

##order by 按照索引顺序
EXPLAIN SELECT * FROM tblA WHERE birth > '2021-04-08 19:45:00' ORDER BY age;
```

![](..\img\索引优化-order by 顺序.png)

###### 案例B

```sql
EXPLAIN SELECT * FROM tblA ORDER BY age ASC,birth DESC;
```

![](..\img\索引优化-order by 排序不一致.png)

##### 结论

- MySQL支持两种方式的排序，index和filesort。index效率高，它是指扫描索引本身完成排序，filesort效率低。
- ORDER BY子句，尽量使用Index方式排序，避免filesort方式排序。
- ORDER BY子句满足两种情况，会使用index排序，一是ORDER BY子句采用遵照最佳左前缀法则，二是where条件字段和ORDER BY子句组合起来，满足最佳左前缀法则
  排序分组优化
- 

#### MySQL的排序算法

当发生 Using filesort 时，MySQL会根据自己的算法对查询结果进行排序

##### 双路排序

- MySQL 4.1 之前是使用双路排序,字面意思就是两次扫描磁盘，最终得到数据，读取行指针和 order by 列，对他们进行排序，然后扫描已经排序好的列表，按照列表中的值重新从列表中读取对应的数据输出.
- 从磁盘取排序字段，在 buffer 进行排序，再从磁盘取其他字段
  简单来说，取一批数据，要对磁盘进行了两次扫描，众所周知，I\O 是很耗时的，所以在 mysql4.1 之后，出现了第二种改进的算法，就是单路排序

##### 单路排序

- 从磁盘读取查询需要的所有列，按照 order by 列在 buffer 对它们进行排序，然后扫描排序后的列表进行输出， 它的效率更快一些，避免了第二次读取数据。并且把随机 IO 变成了顺序 IO,但是它会使用更多的空间， 因为它把每一行都保存在内存中了

##### 存在的问题

- 在 sort_buffer 中，方法 B 比方法 A 要多占用很多空间，因为方法 B 是把所有字段都取出, 所以有可能取出的数据的总大小超出了 sort_buffer 的容量，导致每次只能取 sort_buffer 容量大小的数据，进行排序（创建 tmp 文件，多 路合并），排完再取 sort_buffer 容量大小，再排……从而多次 I/O。也就是本来想省一次 I/O 操作，反而导致了大量的 I/O 操作，反而得不偿失

##### 如何优化

- **增大`sort_buffer_size`参数的设置**

  不管用哪种算法，提高这个参数都会提高效率，当然，要根据系统的能力去提高，因为这个参数是针对每个进程的 1M-8M 之间调整

- **增大`max_length_for_sort_data`参数的设置**

  mysql 使用单路排序的前提是排序的字段大小要小于 **max_length_for_sort_data**， 提高这个参数，会增加使用改进算法的概率。

  但是如果设的太高，数据总容量超出 sort_buffer_size 的概率反而会增大， 就会出现**高频磁盘 I/O** 和**低的处理器使用率**。（1024-8192 之间调整）

- **减少 select 后面的查询的字段**（少用select *）

  查询的字段减少，缓冲就能容纳更多的内容，也就相当于间接增大了**sort_buffer_size**

##### 总结A

![](..\img\order by 总结A.png)

##### 总结B

![](..\img\order by 总结B.png)

#### group by 关键字优化

GROUP BY 优化和ORDER BY大致类似



先排序后进行分组 遵循最佳左前缀

![](..\img\group by优化.png)

#### 小总结

- 要想在排序时使用索引，避免 Using filesort，可以采用索引覆盖
- ORDER BY /GROUP BY后面字段的顺序要和复合索引的顺序完全一致
- ORDER BY /GROUP BY后面的索引必须按照顺序出现，排在后面的可以不出现
- 要进行升序或者降序时，字段的排序顺序必须一致。不能一部分升序，一部分降序，可以都升序或者都降序
- 如果复合索引前面的字段作为常量出现在过滤条件中，排序字段可以为紧跟其后的字段

### 慢查询日志

####  慢查询日志定义

- 慢查询日志是MySQL提供的一种日志记录，用来记录响应时间超过阀值的SQL语句。
- 如果某条SQL语句运行时间超过**long_query_time**设定的值，就会被记录到慢查询日志中。
- long_query_time的默认值为 10（10秒）
- 由它来查看哪些SQL超出了我们的最大忍耐时间值，比如一条sql执行超过5秒钟，我们就算慢SQL，收集超过5秒的sql，结合之前explain进行全面分析

#### 使用

**`默认情况下，MySQL 数据库没有开启慢查询日志`**，需要我们手动来设置这个参数

**`如果不是调优需要的话，一般不建议启动该参数`**，因为开启慢查询日志会将SQL语句写入日志，因此或多或少带来一定的性能影响。



|                 SQL语句                 | 说明                              |
| :-------------------------------------: | --------------------------------- |
| SHOW VARIABLES LIKE '%slow_query_log%'; | 查看慢查询日志是否开启（默认OFF） |
|      set global slow_query_log=1;       | 开启慢查询日志                    |
|      set global slow_query_log=0;       | 关闭慢查询日志                    |
| SHOW VARIABLES LIKE 'long_query_time%'; | 查看慢查询设定阈值（默认10秒）    |
|          set long_query_time=5          | 设定慢查询阈值为5秒 （单位：秒 ） |



#### 注意

- set global slow_query_log=1开启慢查询日志，仅对当前数据库生效，MySQL重启后失效。
- 如果需要永久生效，则需要修改`my.ini`配置文件，在`[mysqld]`下增加 `slow_query_log = 1`、`slow_query_log_file =user-slow.log`、`long_query_time = 5`和`log_output = FILE`（和数据库查询的一致）

#### 永久配置

```sql
[mysqld]
#日志输出为文件
log-output=FILE
# 配置慢查询，5.7版本默认为1
#开启慢查询
slow-query-log=1
#日志路径
slow_query_log_file="user-slow.log"
#设置慢查询阈值为5秒
long_query_time=5
```

#### 日志分析工具

生产环境中手工查找，分析日志，非常的耗费时间，因此MySQL提供了日志分析工具`mysqldumpslow`

##### 帮助信息

![](..\img\mysqldumpslow命令参数.png)

```sql
--获取返回集最多的10条SQL
mysqldumpslow -s r -t 10 user-slow.log
--获取访问次数最多的10条SQL
mysqldumpslow -s c -t 10 user-slow.log
--获取按时间排序的前10条含有LEFT JOIN的SQL语句
mysqldumpslow -s t -t 10 -g "LEFT JOIN" user-slow.log
--结合|more使用，否则有可能会爆屏
mysqldumpslow -s r -t 10 user-slow.log |more
```



### 批量数据脚本

#### 插入数据

##### 建表语句

```sql
CREATE TABLE `dept`( 
 `id` INT(11) NOT NULL AUTO_INCREMENT, 
 `deptName` VARCHAR(30) DEFAULT NULL,
  `address` VARCHAR(40) DEFAULT NULL, 
  ceo INT NULL,
  PRIMARY KEY(`id`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `emp`( 
`id` INT(11) NOT NULL AUTO_INCREMENT,
`empno` INT NOT NULL,
`name` VARCHAR(20) DEFAULT NULL, 
`age` INT(3) DEFAULT NULL, 
`deptId` INT(11) DEFAULT NULL,
PRIMARY KEY(`id`) 
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```

##### 设置参数

在执行创建函数之前，首先请保证 log_bin_trust_function_creators 参数为 1，即 on 开启状态。
否则会报错：

查询：

```sql
show variables like 'log_bin_trust_function_creators'; 
```

设置：

```sql
set global log_bin_trust_function_creators=1;
```

当然，如上设置只存在于当前操作，想要永久生效，需要写入到配置文件中：
在[mysqld]中加上 ***log_bin_trust_function_creators***=1

##### 编写随机函数

创建函数,保证每条数据不同

###### 随机产生字符串

```sql
DELIMITER $$
CREATE FUNCTION rand_string(n INT) RETURNS VARCHAR(255)
BEGIN
DECLARE chars_str VARCHAR(100) DEFAULT  'abcdefghijklmnopqrstuvwxyzABCDEFJHIJKLMNOPQRSTUVWXYZ';
DECLARE return_str VARCHAR(255) DEFAULT '';
DECLARE i INT DEFAULT 0;
WHILE i< n DO
SET return_str=CONCAT(return_str,SUBSTRING(chars_str,FLOOR(1+RAND()*52),1));
SET i=i+1;
END WHILE;
RETURN return_str;
END $$
```

###### 随机产生部门编号

```sql
DELIMITER $$
CREATE FUNCTION rand_num(from_num INT,to_num INT) RETURNS INT(11) 
BEGIN DECLARE i INT DEFAULT 0; 
SET i=FLOOR(from_num+RAND()*(to_num-from_num+1)) ;
RETURN i; 
END $$

```

##### 创建存储过程

###### 创建往 emp 表中插入数据的存储过程

```sql
DELIMITER $$
CREATE PROCEDURE insert_emp(START INT, MAX_NUM INT )
BEGIN 
DECLARE i INT DEFAULT 0;
SET autocommit = 0;
REPEAT
SET i = i+1;
INSERT INTO emp (empno, NAME ,age ,deptid ) VALUES ((START+i) ,rand_string(6) , rand_num(30,50),rand_num(1,10000)); 
UNTIL i=max_num 
END REPEAT;
COMMIT;
END $$
```

###### 创建往 dept 表中插入数据的存储过程

```sql
DELIMITER $$ 
CREATE PROCEDURE `insert_dept`(max_num INT)
BEGIN DECLARE i INT DEFAULT 0; 
SET autocommit=0; 
REPEAT SET i=i+1; 
INSERT  INTO dept(deptname,address,ceo)VALUES(rand_string(8),rand_string(10),rand_num(1,500000)); UNTIL i=max_num
END REPEAT;
COMMIT; 
END $$
```

##### 调用存储过程

###### 添加数据到部门表

```sql
#执行存储过程，往 dept 表添加 10 万条数据 
DELIMITER ; 
CALL insert_dept(100000);

```

######  添加数据到员工表

```sql
#执行存储过程，往 emp 表添加 50 万条数据
DELIMITER ; 
CALL insert_emp(600000,500000);
```



### show profile

#### 定义

- Show Profiles是MySQL提供，可以分析SQL语句执行的资源消耗情况，可用于SQL调优。
- 通过配置profiling参数启用SQL剖析，该参数可以在全局和session级别来设置。
- 全局级别作用于整个MySQL实例，而session级别只影响当前回话。
- 该参数开启后，后续执行的SQL语句都将记录其资源开销，诸如IO，上下文切换，CPU，Memory等
- Show profiles是5.0.37之后添加的，要想使用此功能，要确保MySQL版本 > 5.0.37。

#### 如何使用

```sql
#查看数据库版本
select version();

#查看是否开启 profiling
show variables like '%profiling%';

##开启
set profiling = 1;

#查看最近的SQL 语句
show profiles;

```

#### 帮助文档

```sql
#查看帮助信息
help profile;
Name: 'SHOW PROFILE'
Description:
Syntax:
SHOW PROFILE [type [, type] ... ]
    [FOR QUERY n]
    [LIMIT row_count [OFFSET offset]]

type: {
    ALL
  | BLOCK IO
  | CONTEXT SWITCHES
  | CPU
  | IPC
  | MEMORY
  | PAGE FAULTS
  | SOURCE
  | SWAPS
}

The SHOW PROFILE and SHOW PROFILES statements display profiling
information that indicates resource usage for statements executed
during the course of the current session.

*Note*:

The SHOW PROFILE and SHOW PROFILES statements are deprecated and will
be removed in a future MySQL release. Use the Performance Schema
instead; see
https://dev.mysql.com/doc/refman/5.7/en/performance-schema-query-profil
ing.html.

To control profiling, use the profiling session variable, which has a
default value of 0 (OFF). Enable profiling by setting profiling to 1 or
ON:

mysql> SET profiling = 1;

SHOW PROFILES displays a list of the most recent statements sent to the
server. The size of the list is controlled by the
profiling_history_size session variable, which has a default value of
15. The maximum value is 100. Setting the value to 0 has the practical
effect of disabling profiling.

All statements are profiled except SHOW PROFILE and SHOW PROFILES, so
you will find neither of those statements in the profile list.
Malformed statements are profiled. For example, SHOW PROFILING is an
illegal statement, and a syntax error occurs if you try to execute it,
but it will show up in the profiling list.

SHOW PROFILE displays detailed information about a single statement.
Without the FOR QUERY n clause, the output pertains to the most
recently executed statement. If FOR QUERY n is included, SHOW PROFILE
displays information for statement n. The values of n correspond to the
Query_ID values displayed by SHOW PROFILES.

The LIMIT row_count clause may be given to limit the output to
row_count rows. If LIMIT is given, OFFSET offset may be added to begin
the output offset rows into the full set of rows.

By default, SHOW PROFILE displays Status and Duration columns. The
Status values are like the State values displayed by SHOW PROCESSLIST,
although there might be some minor differences in interpretion for the
two statements for some status values (see
https://dev.mysql.com/doc/refman/5.7/en/thread-information.html).

Optional type values may be specified to display specific additional
types of information:

o ALL displays all information

o BLOCK IO displays counts for block input and output operations

o CONTEXT SWITCHES displays counts for voluntary and involuntary
  context switches

o CPU displays user and system CPU usage times

o IPC displays counts for messages sent and received

o MEMORY is not currently implemented

o PAGE FAULTS displays counts for major and minor page faults

o SOURCE displays the names of functions from the source code, together
  with the name and line number of the file in which the function
  occurs

o SWAPS displays swap counts

Profiling is enabled per session. When a session ends, its profiling
information is lost.

URL: https://dev.mysql.com/doc/refman/5.7/en/show-profile.html

Examples:
mysql> SELECT @@profiling;
+-------------+
| @@profiling |
+-------------+
|           0 |
+-------------+
1 row in set (0.00 sec)

mysql> SET profiling = 1;
Query OK, 0 rows affected (0.00 sec)

mysql> DROP TABLE IF EXISTS t1;
Query OK, 0 rows affected, 1 warning (0.00 sec)

mysql> CREATE TABLE T1 (id INT);
Query OK, 0 rows affected (0.01 sec)

mysql> SHOW PROFILES;
+----------+----------+--------------------------+
| Query_ID | Duration | Query                    |
+----------+----------+--------------------------+
|        0 | 0.000088 | SET PROFILING = 1        |
|        1 | 0.000136 | DROP TABLE IF EXISTS t1  |
|        2 | 0.011947 | CREATE TABLE t1 (id INT) |
+----------+----------+--------------------------+
3 rows in set (0.00 sec)

mysql> SHOW PROFILE;
+----------------------+----------+
| Status               | Duration |
+----------------------+----------+
| checking permissions | 0.000040 |
| creating table       | 0.000056 |
| After create         | 0.011363 |
| query end            | 0.000375 |
| freeing items        | 0.000089 |
| logging slow query   | 0.000019 |
| cleaning up          | 0.000005 |
+----------------------+----------+
7 rows in set (0.00 sec)

mysql> SHOW PROFILE FOR QUERY 1;
+--------------------+----------+
| Status             | Duration |
+--------------------+----------+
| query end          | 0.000107 |
| freeing items      | 0.000008 |
| logging slow query | 0.000015 |
| cleaning up        | 0.000006 |
+--------------------+----------+
4 rows in set (0.00 sec)

mysql> SHOW PROFILE CPU FOR QUERY 2;
+----------------------+----------+----------+------------+
| Status               | Duration | CPU_user | CPU_system |
+----------------------+----------+----------+------------+
| checking permissions | 0.000040 | 0.000038 |   0.000002 |
| creating table       | 0.000056 | 0.000028 |   0.000028 |
| After create         | 0.011363 | 0.000217 |   0.001571 |
| query end            | 0.000375 | 0.000013 |   0.000028 |
| freeing items        | 0.000089 | 0.000010 |   0.000014 |
| logging slow query   | 0.000019 | 0.000009 |   0.000010 |
| cleaning up          | 0.000005 | 0.000003 |   0.000002 |
+----------------------+----------+----------+------------+
7 rows in set (0.00 sec)
```

##### 官网

Performance Schema说明文档：https://dev.mysql.com/doc/refman/8.0/en/performance-schema-query-profiling.html
SHOW PROFILE说明文档：https://dev.mysql.com/doc/refman/8.0/en/show-profile.html

##### 使用

```sql
show profile for query 167;  -- 获取指定查询(Query_ID = 167)的开销
-- 查看特定部分的开销，如下为CPU部分的开销  
show profile cpu for query 167 ;
-- 如下为MEMORY部分的开销  
show profile memory for query 167 ; 
-- 同时查看io和cpu
show profile block io,cpu for query 167;  
-- 下面的SQL语句用于查询query_id为2的SQL开销，且按最大耗用时间倒序排列  
set @query_id=167;
```

#### 如果Status出现以下情况，需要进行SQL优化



![](..\img\show profile 优化.png)

1. converting HEAP to MyISAM 查询结果太大 内存都不够用了往磁盘上搬了。
2. Creating tmp table 创建临时表：拷贝数据到临时表；用完还要删除
3. Copying to tmp table on disk：把内存中的临时表数据复制到磁盘  危险！！！！！
4. locked：锁住



### 全局查询日志

- **千万不要在`生产环境`上开启此功能**
- **千万不要在`生产环境`上开启此功能**
- **千万不要在`生产环境`上开启此功能**



#### 配置启用

```sql
[mysqld]
# 是否开启sql执行结果记录，必须要设置general_log_file参数，日志的路径地址
# 即日志跟踪，1为开启，0为关闭
general-log=0
general_log_file="execute_sql_result.log"
```

#### SQL 命令启用

```sql
set global general_log= 1 ;
set global log_output='TABLE';
```





# MySQL锁机制

## 锁的定义

锁是计算机协调多个进程或线程并发访问某一资源的机制。

- 在数据库中，除传统的计算资源（如CPU、RAM、I/O等）的争用以外，数据也是一种共享资源，如何保证数据并发访问的一致性、有效性是所有数据库必须解决的一个问题。
- 锁冲突是影响数据库并发访问性能的一个重要因素，从这个角度来说，锁对数据库而言显得尤为重要，而且也更加复杂

## 锁的分类

从对数据的操作类型分为：**读锁**（共享锁）和 **写锁**（排他锁）

读锁：针对同一份数据,对该数据的读操作可以同时进行且不受影响。

写锁：写操作未完成前，会阻断其他的读操作和写操作

从对数据的操作粒度可以分为：**表锁**和**行锁**

### 表锁

#### 表锁特点

- MyISAM引擎使用表锁 开销小，加锁快，无死锁，锁定力度大，发生锁冲突的概率最高（？？？）
- 并发度最低
- 不支持事务

#### 模拟数据

```sql
create table mylock (
id int not null primary key auto_increment,
name varchar(20) default ''
) engine myisam;

insert into mylock(name) values('a');
insert into mylock(name) values('b');
insert into mylock(name) values('c');
insert into mylock(name) values('d');
insert into mylock(name) values('e');

select * from mylock;
```

#### 查看数据库锁

```sql
#查看数据库test中的表是否加锁
show open tables in test;
#book表加读锁  phone表加写锁
lock table book read,phone write;
#全部解锁
unlock tables;
```

#### 表锁（读锁）

**主机A给表加上表锁（读锁）**

- 主机A和其他主机都可以读取该表的信息
- 主机A不能读取其他表的信息，但其他主机可以读取库中其他表的信息
- 主机A不能对锁住的表进行修改
- 其他主机对表进行修改，会被阻塞，直到表锁（读锁）被释放

#### 表锁（写锁)

**主机A给表加上表锁（写锁）**

- 主机A可以读取该表信息，但其他主机读取时，会进入阻塞状态，直到表锁（写锁）被释放
- 主机A不能读取其他表的信息，但其他主机不能读取该表信息，但可以读取其他表的信息
- 主机A可以对该表进行修改
- 其他主机不能对该表进行修改，会被阻塞，直到表锁（写锁）被释放

#### 总结

![](..\img\表锁总结.png)

**读锁不会阻塞读，只会阻塞写。但是写锁会阻塞读和写。**