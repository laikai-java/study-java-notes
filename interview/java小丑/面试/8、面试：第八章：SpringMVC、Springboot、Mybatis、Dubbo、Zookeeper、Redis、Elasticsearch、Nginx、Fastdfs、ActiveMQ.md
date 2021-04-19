> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [liaozhiwei.blog.csdn.net](https://liaozhiwei.blog.csdn.net/article/details/88706119)

### **SpringMVC:**

**简单的介绍一下 Spring Mvc 的工作原理？**

![](https://img-blog.csdnimg.cn/20190220164317913.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

1、用户向服务器发送请求，请求被 SpringMVC 的前端控制器 DispatcherServlet 截获。

2、DispatcherServlet 对请求的 URL（统一资源定位符）进行解析，得到 URI(请求资源标识符)，然后根据该 URI，调用 HandlerMapping 获得该 Handler 配置的所有相关的对象，包括 Handler 对象以及 Handler 对象对应的拦截器，这些对象都会被封装到一个 HandlerExecutionChain 对象当中返回。

3、DispatcherServlet 根据获得的 Handler，选择一个合适的 HandlerAdapter。HandlerAdapter 的设计符合面向对象中的单一职责原则，代码结构清晰，便于维护，最为重要的是，代码的可复制性高。HandlerAdapter 会被用于处理多种 Handler，调用 Handler 实际处理请求的方法。

4、提取请求中的模型数据，开始执行 Handler(Controller)。在填充 Handler 的入参过程中，根据配置，spring 将帮助做一些额外的工作消息转换：将请求的消息，如 json、xml 等数据转换成一个对象，将对象转换为指定的响应信息。数据转换：对请求消息进行数据转换，如 String 转换成 Integer、Double 等。 数据格式化：对请求的消息进行数据格式化，如将字符串转换为格式化数字或格式化日期等。数据验证：验证数据的有效性如长度、格式等，验证结果存储到 BindingResult 或 Error 中。

5、Handler 执行完成后，向 DispatcherServlet 返回一个 ModelAndView 对象，ModelAndView 对象中应该包含视图名或视图模型。

6、根据返回的 ModelAndView 对象，选择一个合适的 ViewResolver(视图解析器) 返回给 DispatcherServlet。

7、ViewResolver 结合 Model 和 View 来渲染视图。

8、将视图渲染结果返回给客户端。

url 和 uri 的区别？

**URI 包括 URL 和 URN 两个类别，**个人的身份证号就是 URN，个人的家庭地址就是 URL，URN 可以唯一标识一个人，而 URL 可以告诉邮递员怎么把货送到你手里。

### **Springboot:**

**简单的介绍一下 Springboot？**

**Springboot** 用来简化 spring 应用的初始搭建以及开发过程 使用特定的方式来进行配置（properties 或 yml 文件）   
可以创建独立的 spring 引用程序 main 方法运行   
**Springboot** 嵌入的 Tomcat 无需部署 war 文件   
简化 maven 配置

starters 自动依赖与版本控制

### **Mybatis:**

1、什么是 mybatis？

（1）mybatis 是一个优秀的基于 java 的持久层框架，它内部封装了 jdbc，使开发者只需要关注 sql 语句本身，而不需要花费精力去处理加载驱动、创建连接、创建 statement 等繁杂的过程。

（2）mybatis 通过 xml 或注解的方式将要执行的各种 statement 配置起来，并通过 java 对象和 statement 中 sql 的动态参数进行映射生成最终执行的 sql 语句，最后由 mybatis 框架执行 sql 并将结果映射为 java 对象并返回。

（3）MyBatis 支持定制化 SQL、存储过程以及高级映射。MyBatis 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集。MyBatis 可以使用简单的 XML 或注解来配置和映射原生信息，将接口和 Java 的 POJO 映射成数据库中的记录。

2、Mybait 的优点：

（1）简单易学，容易上手（相比于 Hibernate） —- 基于 SQL 编程；

（2）JDBC 相比，减少了 50% 以上的代码量，消除了 JDBC 大量冗余的代码，不需要手动开关连接；

（3）很好的与各种数据库兼容（因为 MyBatis 使用 JDBC 来连接数据库，所以只要 JDBC 支持的数据库 MyBatis 都支持，而 JDBC 提供了可扩展性，所以只要这个数据库有针对 Java 的 jar 包就可以就可以与 MyBatis 兼容），开发人员不需要考虑数据库的差异性。

（4）提供了很多第三方插件（分页插件 / 逆向工程）；

（5）能够与 Spring 很好的集成；

（6）MyBatis 相当灵活，不会对应用程序或者数据库的现有设计强加任何影响，SQL 写在 XML 里，从程序代码中彻底分离，解除 sql 与程序代码的耦合，便于统一管理和优化，并可重用。

（7）提供 XML 标签，支持编写动态 SQL 语句。

（8） 提供映射标签，支持对象与数据库的 ORM 字段关系映射。

（9）提供对象关系映射标签，支持对象关系组建维护。

3、MyBatis 框架的缺点：

（1）SQL 语句的编写工作量较大，尤其是字段多、关联表多时，更是如此，对开发人员编写 SQL 语句的功底有一定要求。

（2）SQL 语句依赖于数据库，导致数据库移植性差，不能随意更换数据库。

4、MyBatis 框架适用场合：

（1）MyBatis 专注于 SQL 本身，是一个足够灵活的 DAO 层解决方案。

（2）对性能的要求很高，或者需求变化较多的项目，如互联网项目，MyBatis 将是不错的选择。

5、#{} 和 ${} 的区别是什么？

#{} 是预编译处理，${} 是字符串替换。

Mybatis 在处理 #{} 时，会将 sql 中的 #{} 替换为? 号，调用 PreparedStatement 的 set 方法来赋值；

Mybatis 在处理 ${} 时，就是把 ${} 替换成变量的值。

使用 #{} 可以有效的防止 SQL 注入，提高系统安全性。  


### **Dubbo:**

**简单的介绍一下 Dubbo？(Dubbo 是什么)**

dubbo 就是个服务调用的东东。

**为什么怎么说呢？**

因为 Dubbo 是由阿里开源的一个 RPC 分布式框架

**那么 RPC 是什么呢？**

就是不同的应用部署到不同的服务器上，应用之间想要调用没有办法直接调用，因为不在一个内存空间，需要通过网络通讯来调用，或者传达调用的数据。而且 RPC 会将远程调用的细节隐藏起来，让调用远程服务像调用本地服务一样简单。

**dubbo 有哪些组件？**

主要有五个角色 / 核心组件，分为是 Container（容器）、Provider（服务的提供方）、Registry（注册中心）、Consumer（服务的消费方）、Monitor（监控中心）。

**容器：**主要负责****启动、加载、运行服务提供者****；

****注册中心：****注册中心只负责**地址的注册和查找**

****监控中心：****监控中心负责统计各****服务调用次数、调用时间****

****Dubbo 支持什么协议？****

****Dubbo 协议****：缺省协议、采用了****单一长连接和 NIO**** ****异步通讯、使用线程池并发处理请求，能减少握手和加大并发效率****

### **Zookeeper:**

**Zookeeper 的实现原理？（工作原理）**

Zookeeper 会维护一个类似于标准的文件系统的具有层次关系的数据结构。这个文件系统中每个子目录项都被称为 znode 节点，这个 znode 节点也可以有子节点，每个节点都可以存储数据，客户端也可以对这些 node 节点进行 getChildren，getData,exists 方法，同时也可以在 znode tree 路径上设置 watch（类似于监听），当 watch 路径上发生节点 create、delete、update 的时候，会通知到 client。client 可以得到通知后，再获取数据，执行业务逻辑操作。Zookeeper 的作用主要是用来维护和监控存储的 node 节点上这些数据的状态变化，通过监控这些数据状态的变化，从而达到基于数据的集群管理。

**为什么要用 zookeeper 作为 dubbo 的注册中心？能选择其他的吗？**

Zookeeper 的数据模型是由一系列的 Znode 数据节点组成，和文件系统类似。zookeeper 的数据全部存储在内存中，性能高；zookeeper 也支持集群，实现了高可用；同时基于 zookeeper 的特性，也支持事件监听（服务的暴露方发生变化，可以进行推送），所以 zookeeper 适合作为 dubbo 的注册中心区使用。redis、Simple 也可以作为 dubbo 的注册中心来使用。

****项目中主要用 zookeeper 做了什么？（作用）****

作为注册中心用；主要是在服务器上搭建 zookeeper，其次在 spring 管理的 dubbo 的配置文件中配置（暴露方和消费方都需要配置）

### **Redis:**

******简单介绍一个 redis？******

redis 是内存中的数据结构存储系统，一个 key-value 类型的非关系型数据库，可持久化的数据库，相对于关系型数据库（数据主要存在硬盘中），性能高，因此我们一般用 redis 来做缓存使用；并且 redis 支持丰富的数据类型，比较容易解决各种问题，因此 redis 可以用来作为注册中心，​数据库、缓存和消息中间件。Redis 的 Value 支持 5 种数据类型，string、hash、list、set、zset（sorted set）;

**String 类型：**一个 key 对应一个 value

**Hash 类型：**它的 key 是 string 类型，value 又是一个 map（key-value），适合存储对象。

**List 类型：**按照插入顺序的字符串链表（双向链表），主要命令是 LPUSH 和 RPUSH，能够支持反向查找和遍历

**Set 类型：**用哈希表类型的字符串序列，没有顺序，集合成员是唯一的，没有重复数据，底层主要是由一个 value 永远为 null 的 hashmap 来实现的。

**zset 类型：**和 set 类型基本一致，不过它会给每个元素关联一个 ****double**** 类型的分数（score），这样就可以为成员排序，并且插入是有序的。

**你还用过其他的缓存吗？这些缓存有什么区别？都在什么场景下去用？**

对于缓存了解过 redis 和 memcache

**Memcache 和 redis 的区别：**

数据支持的类型：redis 不仅仅支持简单的 k/v 类型的数据，同时还支持 list、set、zset、hash 等数据结构的存储；memcache 只支持简单的 k/v 类型的数据，key 和 value 都是 string 类型

可靠性：memcache 不支持数据持久化，断电或重启后数据消失，但其稳定性是有保证的；redis 支持数据持久化和数据恢复，允许单点故障，但是同时也会付出性能的代价

性能上：对于存储大数据，memcache 的性能要高于 redis

**应用场景：**

Memcache：适合多读少写，大数据量的情况（一些官网的文章信息等）

Redis：适用于对读写效率要求高、数据处理业务复杂、安全性要求较高的系统

**案例：**分布式系统，存在 session 之间的共享问题，因此在做单点登录的时候，我们利用 redis 来模拟了 session 的共享，来存储用户的信息，实现不同系统的 session 共享；

**对 redis 的持久化了解不？**

redis 的持久化方式有两种：

**RDB（半持久化方式）：**按照配置不定期的通过异步的方式、快照的形式直接把内存中的数据持久化到磁盘的一个 dump.rdb 文件（二进制的临时文件）中，redis 默认的持久化方式，它在配置文件（redis.conf）中。

优点：只包含一个文件，将一个单独的文件转移到其他存储媒介上，对于文件备份、灾难恢复而言，比较实用。

缺点：系统一旦在持久化策略之前出现宕机现象，此前没有来得及持久化的数据将会产生丢失

**RDB 持久化配置：**

Redis 会将数据集的快照 dump 到 dump.rdb 文件中。此外，我们也可以通过配置文件来修改 Redis 服务器 dump 快照的频率，在打开 6379.conf 文件之后，我们搜索 save，可以看到下面的配置信息：

save 900 1              #在 900 秒 (15 分钟) 之后，如果至少有 1 个 key 发生变化，则 dump 内存快照。

save 300 10            #在 300 秒 (5 分钟) 之后，如果至少有 10 个 key 发生变化，则 dump 内存快照。

save 60 10000        #在 60 秒 (1 分钟) 之后，如果至少有 10000 个 key 发生变化，则 dump 内存快照。

**AOF（全持久化的方式）：**把每一次数据变化都通过 write() 函数将你所执行的命令追加到一个 appendonly.aof 文件里面，Redis 默认是不支持这种全持久化方式的，需要在配置文件（redis.conf）中将 appendonly no 改成 appendonly yes

优点：数据安全性高，对日志文件的写入操作采用的是 append 模式，因此在写入过程中即使出现宕机问题，也不会破坏日志文件中已经存在的内容；

缺点：对于数量相同的数据集来说，aof 文件通常要比 rdb 文件大，因此 rdb 在恢复大数据集时的速度大于 AOF；

**AOF 持久化配置:**

在 Redis 的配置文件中存在三种同步方式，它们分别是：

appendfsync always     #**每次有数据修改**发生时都会都调用 fsync 刷新到 aof 文件，非常慢，但是安全；

appendfsync everysec  #**每秒钟**都调用 fsync 刷新到 aof 文件中，很快，但是可能丢失一秒内的数据，推荐使用，兼顾了速度和安全；

appendfsync no          #**不会自动同步**到磁盘上，需要依靠 OS（操作系统）进行刷新，效率快，但是安全性就比较差；

**二种持久化方式区别：**

AOF 在运行效率上往往慢于 RDB，每秒同步策略的效率是比较高的，同步禁用策略的效率和 RDB 一样高效；

如果缓存数据安全性要求比较高的话，用 aof 这种持久化方式（比如项目中的购物车）；

如果对于大数据集要求效率高的话，就可以使用默认的。而且这两种持久化方式可以同时使用。  

**做过 redis 的集群吗？你们做集群的时候搭建了几台，都是怎么搭建的？**

Redis 的数据是存放在内存中的，不适合存储大数据，大数据存储一般公司常用 hadoop 中的 Hbase 或者 MogoDB。redis 主要用来处理高并发的，用我们的项目来说，电商项目如果并发大的话，一台单独的 redis 是不能足够支持我们的并发，这就需要我们扩展多台设备协同合作，即用到集群。

Redis 搭建集群的方式有多种，例如：客户端分片、Twemproxy、Codis 等，但是 redis3.0 之后就支持 redis-cluster 集群，这种方式采用的是无中心结构，每个节点保存数据和整个集群的状态，每个节点都和其他所有节点连接。如果使用的话就用 redis-cluster 集群。集群这块是公司运维搭建的，具体怎么搭建不是太了解。

我们项目中 redis 集群主要搭建了 6 台，3 主（为了保证 redis 的投票机制）3 从（高可用），每个主服务器都有一个从服务器，作为备份机。所有的节点都通过 PING-PONG 机制彼此互相连接；客户端与 redis 集群连接，只需要连接集群中的任何一个节点即可；Redis-cluster 中内置了 16384 个哈希槽，Redis-cluster 把所有的物理节点映射到【0-16383】slot 上，负责维护。

******redis 有事务吗？******

Redis 是有事务的，redis 中的事务是一组命令的集合，这组命令要么都执行，要不都不执行，保证一个事务中的命令依次执行而不被其他命令插入。redis 的事务是不支持回滚操作的。redis 事务的实现，需要用到 MULTI（事务的开始）和 EXEC（事务的结束）命令 ;

**缓存穿透**

缓存查询一般都是通过 key 去查找 value，如果不存在对应的 value，就要去数据库中查找。如果这个 key 对应的 value 在数据库中也不存在，并且对该 key 并发请求很大，就会对数据库产生很大的压力，这就叫缓存穿透

解决方案：

1. 对所有可能查询的参数以 hash 形式存储，在控制层先进行校验，不符合则丢弃。

2. 将所有可能存在的数据哈希到一个足够大的 bitmap 中，一个一定不存在的数据会被这个 bitmap 拦截掉，从而避免了对底层存储系统的查询压力。

3. 如果一个查询返回的数据为空（不管是数 据不存在，还是系统故障），我们仍然把这个空结果进行缓存，但它的过期时间会很短，最长不超过五分钟。

**缓存雪崩**

当缓存服务器重启或者大量缓存集中在一段时间内失效，发生大量的缓存穿透，这样在失效的瞬间对数据库的访问压力就比较大，所有的查询都落在数据库上，造成了缓存雪崩。 这个没有完美解决办法，但可以分析用户行为，尽量让失效时间点均匀分布。大多数系统设计者考虑用加锁或者队列的方式保证缓存的单线程（进程）写，从而避免失效时大量的并发请求落到底层存储系统上。

解决方案：

1. 在缓存失效后，通过加锁或者队列来控制读数据库写缓存的线程数量。比如对某个 key 只允许一个线程查询数据和写缓存，其他线程等待。  
2. 可以通过缓存 reload 机制，预先去更新缓存，再即将发生大并发访问前手动触发加载缓存  
3. 不同的 key，设置不同的过期时间，让缓存失效的时间点尽量均匀  
4. 做二级缓存，或者双缓存策略。A1 为原始缓存，A2 为拷贝缓存，A1 失效时，可以访问 A2，A1 缓存失效时间设置为短期，A2 设置为长期。

**redis 的安全机制（你们公司 redis 的安全这方面怎么考虑的？）**

漏洞介绍：redis 默认情况下，会绑定在 bind 0.0.0.0:6379，这样就会将 redis 的服务暴露到公网上，如果在没有开启认证的情况下，可以导致任意用户在访问目标服务器的情况下，未授权就可访问 redis 以及读取 redis 的数据，攻击者就可以在未授权访问 redis 的情况下可以利用 redis 的相关方法，成功在 redis 服务器上写入公钥，进而可以直接使用私钥进行直接登录目标主机；

解决方案：

1.  禁止一些高危命令。修改 redis.conf 文件，用来禁止远程修改 DB 文件地址，比如 rename-command FLUSHALL ""、rename-command CONFIG""、rename-command EVAL “” 等；
2.  以低权限运行 redis 服务。为 redis 服务创建单独的用户和根目录，并且配置禁止登录；
3.  为 redis 添加密码验证。修改 redis.conf 文件，添加 requirepass mypassword；
4.  禁止外网访问 redis。修改 redis.conf 文件，添加或修改 bind 127.0.0.1，使得 redis 服务只在当前主机使用；
5.  做 log 监控，及时发现攻击；

1.  redis 的哨兵机制（redis2.6 以后出现的）

**哨兵机制：**

监控：监控主数据库和从数据库是否正常运行；

提醒：当被监控的某个 redis 出现问题的时候，哨兵可以通过 API 向管理员或者其他应用程序发送通知；

自动故障迁移：主数据库出现故障时，可以自动将从数据库转化为主数据库，实现自动切换；

具体的配置步骤参考的网上的文档。要注意的是，如果 master 主服务器设置了密码，记得在哨兵的配置文件（sentinel.conf）里面配置访问密码

**redis 中对于生存时间的应用**

  Redis 中可以使用 expire 命令设置一个键的生存时间，到时间后 redis 会自动删除；

  应用场景：

1.  设置限制的优惠活动的信息；
2.  一些及时需要更新的数据，积分排行榜；
3.  手机验证码的时间；
4.  限制网站访客访问频率；

### **Elasticsearch:**

******简单介绍一个**** Elasticsearch？**

ElasticSearch 是一个基于 Lucene 的搜索服务器。通过 HTTP 使用 JSON 进行数据索引，用于分布式全文检索，解决人们对于搜索的众多要求。

******lucene 与 elasticsearch（solr）有什么区别？******

lucene 只是一个提供全文搜索功能类库的核心工具包，而真正使用它还需要一个完善的服务框架搭建起来的应用。好比 lucene 是类似于 jdk，而搜索引擎软件就是 tomcat 的。elasticsearch 和 solr, 这两款都是基于 lucene 的搭建的，可以独立部署启动的搜索引擎服务软件。

**基本概念：**

| 

cluster 集群

 | 

整个 elasticsearch 默认就是集群状态，整个集群是一份完整、互备的数据。

 |
| 

node 节点

 | 

集群中的一个节点，一般只一个进程就是一个 node

 |
| 

shard 分片

 | 

分片，即使是一个节点中的数据也会通过 hash 算法，分成多个片存放，默认是 5 片。

 |
| 

index 逻辑数据库

 | 

相当于 rdbms 的 database, 对于用户来说是一个逻辑数据库，虽然物理上会被分多个 shard 存放，也可能存放在多个 node 中。

 |
| 

type

 | 

类似于 rdbms 的 table，但是与其说像 table，其实更像面向对象中的 class , 同一 Json 的格式的数据集合。

 |
| 

document

 | 

类似于 rdbms 的 row、面向对象里的 object

 |
| 

field

 | 

相当于字段、属性

 |

### 与 MySQL 对比

![](https://img-blog.csdnimg.cn/2019022010294597.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

### ******利用 ki********b********ana 学习 elasti********csearch restful api (DSL)******

Kibana 是一个开源分析和可视化平台，可视化操作 Elasticsearch。Kibana 可以用来搜索，查看和与存储在 Elasticsearch 索引中的数据进行交互。可以轻松地进行高级数据分析，并可在各种图表，表格和地图中显示数据。

ES 提供了基于 JSON 的 query **DSL** 查询语言

### ******es 中保存的数据结构******

public class  Movie {

String id;

String name;

}

这两个对象如果放在关系型数据库保存，会被拆成 2 张表，但是 elasticsearch 是用一个 json 来表示一个 document。

所以它保存到 es 中应该是：

{

  “id”:”1”,

  “name”:”operation red sea”,

  “doubanScore”:”8.5”,

  “actorList”:[  

{“id”:”1”,”name”:”zhangyi”},

{“id”:”2”,”name”:”haiqing”},

{“id”:”3”,”name”:”zhanghanyu”}

]

}

### ******es**** ****的 java 客户端的选择******

目前市面上有两类客户端

一类是 TransportClient 为代表的 ES 原生客户端，不能执行原生 dsl 语句必须使用它的 Java api 方法。

另外一种是以 Rest Api 为主的 missing client，最典型的就是 jest。 这种客户端可以直接使用 dsl 语句拼成的字符串，直接传给服务端，然后返回 json 字符串再解析。

两种方式各有优劣，但是最近 elasticsearch 官网，宣布计划在 7.0 以后的版本中废除 TransportClient。以 RestClient 为主。在官方的 RestClient 基础上，进行了简单包装的 Jest 客户端，就成了首选，而且该客户端也与 springboot 完美集成。

### ******中文分词******

elasticsearch 本身自带的中文分词，就是单纯把中文一个字一个字的分开，根本没有词汇的概念。

### ******问题：******

1、 es 大量的写操作会影响 es 性能，因为 es 需要更新索引，而且 es 不是内存数据库，会做相应的 io 操作。

2、而且修改某一个值，在高并发情况下会有冲突，造成更新丢失，需要加锁，而 es 的乐观锁会恶化性能问题。

### ******解决思路：******

用 redis 做精确计数器，redis 是内存数据库读写性能都非常快，利用 redis 的原子性的自增可以解决并发写操作。

 redis 每计 100 次数（可以被 100 整除）我们就更新一次 es，这样写操作就被稀释了 100 倍，这个倍数可以根据业务情况灵活设定。

### ******增量同步索引库******

推荐使用 MQ（RabbitMQ）

原理：使用 MQ 做增量同步，即当修改数据之后就将此数据发送至 MQ，由 MQ 将此数据同步到 ES 上

### **Nginx:**

1、请解释一下什么是 Nginx?

nginx 本是一个 web 服务器和反向代理服务器，但由于丰富的负载均衡策略，常常被用于客户端可真实的服务器之间，作为负载均衡的实现。用于 HTTP、HTTPS、SMTP、POP3 和 IMAP 协议。

2、请列举 Nginx 的一些特性？

1）反向代理 / L7 负载均衡器

2）嵌入式 Perl 解释器

3）动态二进制升级

4）可用于重新编写 URL，具有非常好的 PCRE 支持

3、nginx 和 apache 的区别？

1）轻量级，同样起 web 服务，比 apache 占用更少的内存及资源

2）抗并发，nginx 处理请求是异步非阻塞的，而 apache 则是阻塞型的，在高并发下 nginx 能保持低资源低消耗高性能

3）高度模块化的设计，编写模块相对简单

4）最核心的区别在于 apache 是同步多进程模型，一个连接对应一个进程；nginx 是异步的，多个连接（万级别）可以对应一个进程

4.nginx 是如何实现高并发的？

一个主进程，多个工作进程，每个工作进程可以处理多个请求，每进来一个 request，会有一个 worker 进程去处理。但不是全程的处理，处理到可能发生阻塞的地方，比如向上游（后端）服务器转发 request，并等待请求返回。那么，这个处理的 worker 继续处理其他请求，而一旦上游服务器返回了，就会触发这个事件，worker 才会来接手，这个 request 才会接着往下走。由于 web server 的工作性质决定了每个 request 的大部份生命都是在网络传输中，实际上花费在 server 机器上的时间片不多。这是几个进程就解决高并发的秘密所在。即 @skoo 所说的 webserver 刚好属于网络 io 密集型应用，不算是计算密集型。

5、请解释 Nginx 如何处理 HTTP 请求？

Nginx 使用反应器模式。主事件循环等待操作系统发出准备事件的信号，这样数据就可以从套接字读取，在该实例中读取到缓冲区并进行处理。单个线程可以提供数万个并发连接。

6、在 Nginx 中，如何使用未定义的服务器名称来阻止处理请求?

只需将请求删除的服务器就可以定义为：Server {listen 80; server_name “ “ ;return 444;}这里，服务器名被保留为一个空字符串，它将在没有 “主机” 头字段的情况下匹配请求，而一个特殊的 Nginx 的非标准代码 444 被返回，从而终止连接。7、 使用 “反向代理服务器” 的优点是什么? 答：反向代理服务器可以隐藏源服务器的存在和特征。它充当互联网云和 web 服务器之间的中间层。这对于安全方面来说是很好的，特别是当您使用 web 托管服务时。

8、请列举 Nginx 服务器的最佳用途?

Nginx 服务器的最佳用法是在网络上部署动态 HTTP 内容，使用 SCGI、WSGI 应用程序服务器、用于脚本的 FastCGI 处理程序。它还可以作为负载均衡器。

9、请解释 Nginx 服务器上的 Master 和 Worker 进程分别是什么?

Master 进程：读取及评估配置和维持 Worker 进程：处理请求

10、请解释你如何通过不同于 80 的端口开启 Nginx?

为了通过一个不同的端口开启 Nginx，你必须进入 / etc/Nginx/sites-enabled/，如果这是默认文件，那么你必须打开名为 “default” 的文件。编辑文件，并放置在你想要的端口：Like server {listen 81;}

11、请解释是否有可能将 Nginx 的错误替换为 502 错误、503?

502 = 错误网关 503 = 服务器超载 有可能，但是您可以确保 fastcgi_intercept_errors 被设置为 ON，并使用错误页面指令。Location / {fastcgi_pass 127.0.01:9001; fastcgi_intercept_errors on; error_page 502 =503/error_page.html; #…}

12、在 Nginx 中，解释如何在 URL 中保留双斜线? 答：要在 URL 中保留双斜线，就必须使用 merge_slashes_off; 语法: merge_slashes [on/off] 默认值: merge_slashes on 环境: http，server

13、请解释 ngx_http_upstream_module 的作用是什么?

ngx_http_upstream_module 用于定义可通过 fastcgi 传递、proxy 传递、uwsgi 传递、memcached 传递和 scgi 传递指令来引用的服务器组。

14、请解释什么是 C10K 问题? C10K 问题是指无法同时处理大量客户端 (10,000) 的网络套接字。

15、请陈述 stub_status 和 sub_filter 指令的作用是什么?

1）Stub_status 指令：该指令用于了解 Nginx 当前状态的当前状态，如当前的活动连接，接受和处理当前读 / 写 / 等待连接的总数   2）Sub_filter 指令：它用于搜索和替换响应中的内容，并快速修复陈旧的数据

16、解释 Nginx 是否支持将请求压缩到上游?

您可以使用 Nginx 模块 gunzip 将请求压缩到上游。gunzip 模块是一个过滤器，它可以对不支持 “gzip” 编码方法的客户机或服务器使用 “内容编码: gzip” 来解压缩响应。

17、解释如何在 Nginx 中获得当前的时间? 要获得 Nginx 的当前时间，必须使用 SSI 模块、$date_gmt 和 $date_local 的变量。Proxy_set_header THE-TIME $date_gmt;

18、用 Nginx 服务器解释 - s 的目的是什么? 用于运行 Nginx -s 参数的可执行文件。

19、解释如何在 Nginx 服务器上添加模块? 在编译过程中，必须选择 Nginx 模块，因为 Nginx 不支持模块的运行时间选择。

20、什么是反向代理和正向代理?

正向代理：被代理的是客户端，比如通过 XX 代理访问国外的某些网站，实际上客户端没有权限访问国外的网站，客户端请求 XX 代理服务器，XX 代理服务器访问国外网站，将国外网站返回的内容传给真正的用户。用户对于服务器是隐藏的，服务器并不知道真实的用户。

反向代理：被代理的是服务器，也就是客户端访问了一个所谓的服务器，服务器会将请求转发给后台真实的服务器，真实的服务器做出响应，通过代理服务器将结果返给客户端。服务器对于用户来说是隐藏的，用户不知道真实的服务器是哪个。

关于正向代理和反向代理，听起来比较绕，仔细理解，体会也不难明白到底是什么意思。

用 nginx 做实现服务的高可用，nginx 本身可能成为单点，遇见的两种解决方案，一种是公司搭建自己的 DNS，将请求解析到不同的 NGINX，另一只是配合 keepalive 实现服务的存活检测。

### **Fastdfs:**

******简单介绍一下 FastDFS？******

1. 开源的分布式文件系统，主要对文件进行存储、同步、上传、下载，有自己的容灾备份、负载均衡、线性扩容机制；

2.FastDFS 架构主要包含 Tracker（跟踪） server 和 Storage（组，卷） server。客户端请求 Tracker server 进行文件上传、下载的时候，通过 Tracker server 调度最终由 Storage server 完成文件上传和下载。

3.Tracker server：跟踪器或者调度器，主要起负载均衡和调度作用。通过 Tracker server 在文件上传时可以根据一些策略找到 Storage server 提供文件上传服务。

Storage server：存储服务器，作用主要是文件存储，完成文件管理的所有功能。客户端上传的文件主要保存在 Storage server 上，Storage server 没有实现自己的文件系统而是利用操作系统的文件系统去管理文件。

存储服务器采用了分组 / 分卷的组织方式。

整个系统由一个组或者多个组组成；

组与组之间的文件是相互独立的；

所有组的文件容量累加就是整个存储系统的文件容量；

一个组可以由多台存储服务器组成，一个组下的存储服务器中的文件都是相同的，组中的多台存储服务器起到了冗余备份和负载均衡的作用；

在组内增加服务器时，如果需要同步数据，则由系统本身完成，同步完成之后，系统自动将新增的服务器切换到线上提供使用；

当存储空间不足或者耗尽时，可以动态的添加组。只需要增加一台服务器，并为他们配置一个新的组，即扩大了存储系统的容量。

我们在项目中主要使用 fastdfs 来存储整个项目的图片。

******为什么要使用 FastDFS 作为你们的图片服务器？******

首先基于 fastDFS 的特点：存储空间可扩展、提供了统一的访问方式、访问效率高、容灾性好  等特点，再结合我们项目中图片的容量大、并发大等特点，因此我们选择了 FastDFS 作为我们的图片服务器；

Nginx 也可以作为一台图片服务器来使用，因为 nginx 可以作为一台 http 服务器来使用，作为网页静态服务器，通过 location 标签配置；在公司中有的时候也用 ftp 作为图片服务器来使用。

******FastDFS 中文件上传下载的具体流程？******

![](https://img-blog.csdnimg.cn/20190220105352312.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

客户端上传文件后生成一个 file_id，返回给客户端，客户端利用这个 file_id 结合 ip 地址，生成一个完成图片的 url，保存在数据库中。生成的那个 file_id 用于以后访问该文件的索引信息。

FastDFS 文件下载的流程

![](https://img-blog.csdnimg.cn/20190220105402815.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

### **ActiveMQ:**

****什么是**** ****activemq****

activeMQ 是一种开源的，面向消息的中间件，用来系统之间进行通信的

****activemq**** ****的原理****

 原理就是生产者生产消息， 把消息发送给 activemq。 Activemq 接收到消息， 然后查看有多少个消费者， 然后把消息转发给消费者， 此过程中生产者无需参与。 消费者接收到消息后做相应的处理和生产者没有任何关系

******对比 RabbitMQ******

RabbitMQ 的协议是 AMQP，而 ActiveMQ 使用的是 JMS 协议。顾名思义 JMS 是针对 Java 体系的传输协议，队列两端必须有 JVM，所以如果开发环境都是 java 的话推荐使用 ActiveMQ，可以用 Java 的一些对象进行传递比如 Map、Blob（二进制大数据）、Stream 等。而 AMQP 通用行较强，非 java 环境经常使用，传输内容就是标准字符串。

另外一点就是 RabbitMQ 用 Erlang 开发，安装前要装 Erlang 环境，比较麻烦。ActiveMQ 解压即可用不用任何安装。

******对比 KafKa******

Kafka 性能超过 ActiveMQ 等传统 MQ 工具，集群扩展性好。

弊端是： 

在传输过程中可能会出现消息重复的情况，

不保证发送顺序

一些传统 MQ 的功能没有，比如消息的事务功能。

 所以通常用 Kafka 处理大数据日志。

******对比 Redis******

其实 Redis 本身利用 List 可以实现消息队列的功能，但是功能很少，而且队列体积较大时性能会急剧下降。对于数据量不大、业务简单的场景可以使用。

****如何解决消息重复问题****

所谓消息重复, 就是消费者接收到了重复的消息, 一般来说我们对于这个问题的处理要把握下面几点,

①. 消息不丢失 (上面已经处理了)

②. 消息不重复执行

一般来说我们可以在业务段加一张表, 用来存放消息是否执行成功, 每次业务事物 commit 之后, 告知服务端, 已经处理过该消息,

这样即使你消息重发了, 也不会导致重复处理

大致流程如下:

        业务端的表记录已经处理消息的 id, 每次一个消息进来之前先判断该消息是否执行过, 如果执行过就放弃, 如果没有执行就开始执行消息, 消息执行完成之后存入这个消息的 id

******关于事务控制******

获取 session 链接的时候 设置参数 默认不开启

| 

****producer 提交时的事务****

 | 

****事务开启****

 | 

****只执行 send 并不会提交到队列中，只有当执行 session.commit() 时，消息才被真正的提交到队列中。****

 |
| 

****事务不开启****

 | 

****只要执行 send，就进入到队列中。****

 |
| 

****consumer 接收时的事务****

 | 

****事务开启，签收必须写****

Session.**_**_SESSION_TRANSACTED_**_**

 | 

****收到消息后，消息并没有真正的被消费。消息只是被锁住。一旦出现该线程死掉、抛异常，或者程序执行了 session.rollback() 那么消息会释放，重新回到队列中被别的消费端再次消费。****

 |
| 

****事务不开启，签收方式选择****

Session.AUTO_ACKNOWLEDGE

 | 

只要调用 comsumer.receive 方法，自动确认。

 |
| 

****事务不开启，签收方式选择****

Session.CLIENT_ACKNOWLEDGE

 | 

需要客户端执行 message.acknowledge(), 否则视为未提交状态，线程结束后，其他线程还可以接收到。

  这种方式跟事务模式很像，区别是不能手动回滚, 而且可以单独确认某个消息。

****手动签收****

 |
| 

****事务不开启，签收方式选择****

Session.DUPS_OK_ACKNOWLEDGE

 | 

在 Topic 模式下做批量签收时用的，可以提高性能。但是某些情况消息可能会被重复提交，使用这种模式的 consumer 要可以处理重复提交的问题。

 |

******持久化与非持久化******

****通过**** producer.setDeliveryMode(DeliveryMode.**_**_PERSISTENT_**_**) 进行设置

持久化的好处就是当 activemq 宕机的话，消息队列中的消息不会丢失。非持久化会丢失。但是会消耗一定的性能。