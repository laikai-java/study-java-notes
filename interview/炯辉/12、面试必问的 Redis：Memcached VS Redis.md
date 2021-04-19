> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [blog.csdn.net](https://blog.csdn.net/v123411739/article/details/114242333)

**前言**
======

老态龙钟的 Memcached 似乎已经无力阻挡 Redis 一统江湖了，但是在面试中，关于两者的比较还是频频出现，因此有必要了解下两者的区别。

**正文**
======

### 数据结构

Memcached：主要支持简单的 key-value 数据结构，类似于 Redis 里的 String。

Redis：总共有 9 种，常见的 5 种，高级的 4 种：

*   String：字符串，最基础的数据类型。
    
*   List：列表。
    
*   Hash：哈希对象。
    
*   Set：集合。
    
*   Sorted Set：有序集合，Set 的基础上加了个分值。
    
*   HyperLogLog：通常用于基数统计。使用少量固定大小的内存，来统计集合中唯一元素的数量。统计结果不是精确值，而是一个带有 0.81% 标准差（standard error）的近似值。所以，HyperLogLog 适用于一些对于统计结果精确度要求不是特别高的场景，例如网站的 UV 统计。
    
*   Geo：redis 3.2 版本的新特性。可以将用户给定的地理位置信息储存起来， 并对这些信息进行操作：获取 2 个位置的距离、根据给定地理位置坐标获取指定范围内的地理位置集合。
    
*   Bitmap：位图。
    
*   Stream：主要用于消息队列，类似于 kafka，可以认为是 pub/sub 的改进版。提供了消息的持久化和主备复制功能，可以让任何客户端访问任何时刻的数据，并且能记住每一个客户端的访问位置，还能保证消息不丢失。
    

### 数据存储

Memcached：数据全部存在内存中，重启实例会导致数据全部丢失

Redis：通常全部存在内存中，同时支持持久化到磁盘上

关于 Redis 的数据存储（内存管理），网上经常见到一种说法：当物理内存用完时，Redis 可以将一些很久没用到的 value 交换到磁盘，同时在内存中清除。这种特性使得 Redis 可以保持超过其机器本身内存大小的数据。

这个说法用在现在，其实已经不准确了。上述的这个说法是 Redis 里的虚拟内存（Virtual Memory）功能，该功能在 Redis 2.0 被引入，但是在 Redis 2.4 中被默认关闭，并标记为废弃，而在后续版中被完全移除。也就是说，大部分人用的版本根本就没有这个功能了。

所以在面试中最好不要提到这个说法，或者需要自行补充说明该功能现在已经不存在了。

### 持久化

Memcached：不支持

Redis：AOF、RDB、混合持久化

### 灾难恢复

Memcached：实例挂掉后，数据不可恢复

Redis：实例挂掉后可以通过 RDB、AOF 恢复 ，但是还是会有数据丢失问题

### 事件处理（事件库）

Memcached：使用 Libevent 库

Redis：自己封装了简易事件库 AeEvent

### 过期键删除策略

常见的有以下三种：

定时删除：在设置键的过期时间的同时，创建一个定时器，让定时器在键的过期时间来临时，立即执行对键的删除操作。对内存最友好，对 CPU 时间最不友好。

惰性删除：放任键过期不管，但是每次获取键时，都检査键是否过期，如果过期的话，就删除该键；如果没有过期，就返回该键。对 CPU 时间最优化，对内存最不友好。

定期删除：每隔一段时间，默认 100ms，程序就对数据库进行一次检査，删除里面的过期键。至 于要删除多少过期键，以及要检査多少个数据库，则由算法决定。前两种策略的折中，对 CPU 时间和内存的友好程度较平衡。

Memcached：惰性删除

Redis：惰性删除 + 定期删除

###   
内存驱逐（淘汰）策略

当内存空间已经用满时，服务实例将将根据配置的驱逐策略，进行相应的动作。

memcached：主要为 LRU 算法

redis：当前总共有以下 8 种：

*   noeviction：默认策略，不淘汰任何 key，直接返回错误
    
*   allkeys-lru：在所有的 key 中，使用 LRU 算法淘汰部分 key
    
*   allkeys-lfu：在所有的 key 中，使用 LFU 算法淘汰部分 key
    
*   allkeys-random：在所有的 key 中，随机淘汰部分 key
    
*   volatile-lru：在设置了过期时间的 key 中，使用 LRU 算法淘汰部分 key
    
*   volatile-lfu：在设置了过期时间的 key 中，使用 LFU 算法淘汰部分 key
    
*   volatile-random：在设置了过期时间的 key 中，随机淘汰部分 key
    
*   volatile-ttl：在设置了过期时间的 key 中，挑选 TTL（time to live，剩余时间）短的 key 淘汰
    

### 性能

我自己对 “高性能” 这个话题有很强的兴趣，我相信也有不少同学跟我一样，对高并发、高性能有特殊的向往，所以这个话题会多讲两句。

首先，影响性能比较的因素有很多，网络带宽、CPU、内存等等，所以其实很多测试并不能完全说明问题，可能在这个条件下 Redis 快，而在另一个条件下是 Memcached 快。

所以两者的性能比较其实可以算一个比较开放的话题，在面试中，你只要能够自圆其说，说服面试官，那就是 OK 的。

Redis 作者 antirez 在 12 年左右在 Stack Overflow 上谈过两者的性能问题，他是这么说的：由于 Redis 只使用单核，而 Memcached 可以使用多核，所以在比较上：在处理小数据时，平均每一个核上 Redis 比 Memcached 性能更高，而在 100k 左右的大数据时， Memcached 性能要高于 Redis。

antirez 毕竟是大牛，所以他的这个说法，大部分人都是认同的，所以在面试中这么回答是可以的。

antirez 的这个说法是按 “CPU 单核” 维度来比较，但是我们在实际的使用中，肯定是按 “实例” 维度来使用，所以接下来我们探讨下对于两者在 “实例” 维度的比较。

按 “实例” 维度进行比较时，个人认为由于 Memcached 多线程的特性，在 Redis 6.0 之前，通常情况下 Memcached 性能是要高于 Redis 的，同时实例的 CPU 核数越多，Memcached 的性能优势越大。

而在 Redis 6.0 支持 I/O 多线程后，当 Redis 关闭持久化后，两者在性能上可能会比较接近。

### 技术选型、如何选择

看完上面的比较，其实不难做出选择，99% 的人、场景，或者说 Redis 能支持的场景，使用 Redis 基本不会有问题。

而且就最近几年的发展来看，Redis 可谓风光无限，而 Memcached 则是已经逐渐跟不上 Redis 脚步了，这也侧面反映了当前大家的选择都是趋向于使用 Redis。

而关于使用 Memcached 的场景，我自己了解到的一些线上真实使用场景都是对于性能有非常高的要求。

Redis 6.0 支持的 I/O 阶段多线程目前根据官方说法至少能提升性能 1 倍，随着 Redis 在性能上的不断优化，可能后续 Memcached 的使用场景会越来越少了。

****最后****
==========

当你的才华还撑不起你的野心的时候，你就应该静下心来学习，愿你在我这里能有所收获。

原创不易，如果你觉得本文写的还不错，对你有帮助，请通过**【点赞】**让我知道，支持我写出更好的文章。

推荐阅读
====

[两年 Java 开发工作经验面试总结](https://joonwhee.blog.csdn.net/article/details/71437307)

[4 年 Java 经验，阿里网易拼多多面试总结、心得体会](https://joonwhee.blog.csdn.net/article/details/99708892)

[5 年 Java 经验，字节、美团、快手核心部门面试总结（真题解析）](https://joonwhee.blog.csdn.net/article/details/109588711)

[921 天，咸鱼到阿里的修仙之路](https://joonwhee.blog.csdn.net/article/details/106182747)

[复习 2 个月拿下美团 offer，我都做了些啥](https://joonwhee.blog.csdn.net/article/details/106463578)

[如何写一份让 HR 眼前一亮的简历（附模板）](https://joonwhee.blog.csdn.net/article/details/109709619)

[面试阿里，HashMap 这一篇就够了](https://joonwhee.blog.csdn.net/article/details/106324537)

[面试必问的 MySQL，你懂了吗？](https://joonwhee.blog.csdn.net/article/details/106893197)

[面试必问的线程池，你懂了吗？](https://joonwhee.blog.csdn.net/article/details/106609583)

[跳槽，如何选择一家公司](https://joonwhee.blog.csdn.net/article/details/109171171)

[如何准备好一场大厂面试](https://joonwhee.blog.csdn.net/article/details/108702592)

[MySQL 8.0 MVCC 核心原理解析（核心源码）](https://joonwhee.blog.csdn.net/article/details/108379583)