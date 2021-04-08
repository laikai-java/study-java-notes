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





#### group by 关键字优化

### 慢查询日志

 

### 批量数据脚本

### show profile

### 全局查询日志





# MySQL锁机制

