> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [blog.csdn.net](https://blog.csdn.net/v123411739/article/details/111569283)

**前言**
======

本来说 Redis 分 3 篇，但是上周写持久化时发现持久化的内容还越多的，于是持久化就单拆一篇了。

我估计后面的主从复制、哨兵、集群内容也是不少，所以说实话，我也不知道之前说的 3 篇会拆成几篇了。

![](https://img-blog.csdnimg.cn/img_convert/0dc77ed56680e911f818a731eed85a6b.png)

持久化机制的内容大纲其实很早就有了，但是实际写的时候断断续续写了有两周。

主要细节还是挺多的，在翻源码的过程中，会遇到一些疑惑点，也发现一些自己以前不知道的知识点，所以自己也要花点时间去搞清楚。

慢工出细活吧，本文还是有很多非常细节的内容的，如果能掌握，让大厂面试官眼前一亮还是问题不大的。

![](https://img-blog.csdnimg.cn/img_convert/e1689af342c68b4e68dbc8e1999f51cc.png)

**正文**
======

**Redis 核心主流程**
---------------

AOF 和 RDB 的持久化过程中，有不少操作是在时间事件 serverCron 中被触发的。所以，这边有必要先了解下 Redis 中的事件核心流程。

Redis 的服务器进程就是一个事件循环，最重要的有两个事件：文件事件和时间事件。Redis 在服务器初始化后，会无限循环，处理产生的文件事件和时间事件。

文件事件常见的有：接受连接（accept）、读取（read）、写入（write）、关闭连接（close）等。

时间事件中常见的就是 serverCron，redis 核心流程中通常也只有这个时间事件。serverCron 默认配置下每 100ms 会被触发一次，在该时间事件中，会执行很多操作：清理过期键、AOF 后台重写、RDB 的 save point 的检查、将 aof_buf 内容写到磁盘上（flushAppendOnlyFile 函数）等等。

Redis 的核心主流程如下图：

![](https://img-blog.csdnimg.cn/img_convert/6991d64c12d7450c33900ae085542d4c.png)

相关源码在 server.c、ae.c，核心方法是：main、aeProcessEvents

Redis 的持久化机制有哪几种
----------------

RDB、AOF、混合持久化（redis4.0 引入）

RDB 的实现原理、优缺点
-------------

描述：类似于快照。在某个时间点，将 Redis 在内存中的数据库状态（数据库的键值对等信息）保存到磁盘里面。RDB 持久化功能生成的 RDB 文件是经过压缩的二进制文件。

命令：有两个 Redis 命令可以用于生成 RDB 文件，一个是 SAVE，另一个是 BGSAVE。

开启：使用 save point 配置，满足 save point 条件后会触发 BGSAVE 来存储一次快照，这边的 save point 检查就是在上文提到的 serverCron 中进行。

save point 格式：save <seconds> <changes>，含义是 Redis 如果在 seconds 秒内数据发生了 changes 次改变，就保存快照文件。例如 Redis 默认就配置了以下 3 个：

```
// 当前AOF文件比上次重写后的AOF文件大小的增长比例超过100
auto-aof-rewrite-percentage 100 
// 当前AOF文件的文件大小大于64MB
auto-aof-rewrite-min-size 64mb
```

关闭：1）注释掉所有 save point 配置可以关闭 RDB 持久化。2）在所有 save point 配置后增加：save ""，该配置可以删除所有之前配置的 save point。

```
save ""
```

SAVE：生成 RDB 快照文件，但是会阻塞主进程，服务器将无法处理客户端发来的命令请求，所以通常不会直接使用该命令。

BGSAVE：fork 子进程来生成 RDB 快照文件，阻塞只会发生在 fork 子进程的时候，之后主进程可以正常处理请求，详细过程如下图：

![](https://img-blog.csdnimg.cn/img_convert/4408a7a0979fc085e55a00517ca18fc6.png)

fork：在 Linux 系统中，调用 fork() 时，会创建出一个新进程，称为子进程，子进程会拷贝父进程的 page table。如果进程占用的内存越大，进程的 page table 也会越大，那么 fork 也会占用更多的时间。如果 Redis 占用的内存很大，那么在 fork 子进程时，则会出现明显的停顿现象。

RDB 的优点：

1）RDB 文件是是经过压缩的二进制文件，占用空间很小，它保存了 Redis 某个时间点的数据集，很适合用于做备份。 比如说，你可以在最近的 24 小时内，每小时备份一次 RDB 文件，并且在每个月的每一天，也备份一个 RDB 文件。这样的话，即使遇上问题，也可以随时将数据集还原到不同的版本。

2）RDB 非常适用于灾难恢复（disaster recovery）：它只有一个文件，并且内容都非常紧凑，可以（在加密后）将它传送到别的数据中心。

3）RDB 可以最大化 redis 的性能。父进程在保存 RDB 文件时唯一要做的就是 fork 出一个子进程，然后这个子进程就会处理接下来的所有保存工作，父进程无须执行任何磁盘 I/O 操作。

4）RDB 在恢复大数据集时的速度比 AOF 的恢复速度要快。

RDB 的缺点：

1）RDB 在服务器故障时容易造成数据的丢失。RDB 允许我们通过修改 save point 配置来控制持久化的频率。但是，因为 RDB 文件需要保存整个数据集的状态， 所以它是一个比较重的操作，如果频率太频繁，可能会对 Redis 性能产生影响。所以通常可能设置至少 5 分钟才保存一次快照，这时如果 Redis 出现宕机等情况，则意味着最多可能丢失 5 分钟数据。

2）RDB 保存时使用 fork 子进程进行数据的持久化，如果数据比较大的话，fork 可能会非常耗时，造成 Redis 停止处理服务 N 毫秒。如果数据集很大且 CPU 比较繁忙的时候，停止服务的时间甚至会到一秒。

3）Linux fork 子进程采用的是 copy-on-write 的方式。在 Redis 执行 RDB 持久化期间，如果 client 写入数据很频繁，那么将增加 Redis 占用的内存，最坏情况下，内存的占用将达到原先的 2 倍。刚 fork 时，主进程和子进程共享内存，但是随着主进程需要处理写操作，主进程需要将修改的页面拷贝一份出来，然后进行修改。极端情况下，如果所有的页面都被修改，则此时的内存占用是原先的 2 倍。

相关源码在 rdb.c，核心方法是：rdbSaveBackground、rdbSave

AOF 的实现原理、优缺点
-------------

描述：保存 Redis 服务器所执行的所有写操作命令来记录数据库状态，并在服务器启动时，通过重新执行这些命令来还原数据集。

开启：AOF 持久化默认是关闭的，可以通过配置：appendonly yes 开启。

关闭：使用配置 appendonly no 可以关闭 AOF 持久化。

AOF 持久化功能的实现可以分为三个步骤：命令追加、文件写入、文件同步。

命令追加：当 AOF 持久化功能打开时，服务器在执行完一个写命令之后，会将被执行的写命令追加到服务器状态的 aof 缓冲区（aof_buf）的末尾。

文件写入与文件同步：可能有人不明白为什么将 aof_buf 的内容写到磁盘上需要两步操作，这边简单解释一下。

Linux 操作系统中为了提升性能，使用了页缓存（page cache）。当我们将 aof_buf 的内容写到磁盘上时，此时数据并没有真正的落盘，而是在 page cache 中，为了将 page cache 中的数据真正落盘，需要执行 fsync / fdatasync 命令来强制刷盘。这边的文件同步做的就是刷盘操作，或者叫文件刷盘可能更容易理解一些。

在文章开头，我们提过 serverCron 时间事件中会触发 flushAppendOnlyFile 函数，该函数会根据服务器配置的 appendfsync 参数值，来决定是否将 aof_buf 缓冲区的内容写入和保存到 AOF 文件。

appendfsync 参数有三个选项：

1）always：每处理一个命令都将 aof_buf 缓冲区中的所有内容写入并同步到 AOF 文件，即每个命令都刷盘。

2）everysec：将 aof_buf 缓冲区中的所有内容写入到 AOF 文件，如果上次同步 AOF 文件的时间距离现在超过一秒钟， 那么再次对 AOF 文件进行同步， 并且这个同步操作是异步的，由一个后台线程专门负责执行，即每秒刷盘 1 次。

3）no：将 aof_buf 缓冲区中的所有内容写入到 AOF 文件， 但并不对 AOF 文件进行同步， 何时同步由操作系统来决定。即不执行刷盘，让操作系统自己执行刷盘。

AOF 的优点

1）AOF 比 RDB 可靠。你可以设置不同的 fsync 策略：no、everysec 和 always。默认是 everysec，在这种配置下，redis 仍然可以保持良好的性能，并且就算发生故障停机，也最多只会丢失一秒钟的数据。

2）AOF 文件是一个纯追加的日志文件。即使日志因为某些原因而包含了未写入完整的命令（比如写入时磁盘已满，写入中途停机等等）， 我们也可以使用 redis-check-aof 工具也可以轻易地修复这种问题。

3）当 AOF 文件太大时，Redis 会自动在后台进行重写：重写后的新 AOF 文件包含了恢复当前数据集所需的最小命令集合。整个重写是绝对安全，因为重写是在一个新的文件上进行，同时 Redis 会继续往旧的文件追加数据。当新文件重写完毕，Redis 会把新旧文件进行切换，然后开始把数据写到新文件上。

4）AOF 文件有序地保存了对数据库执行的所有写入操作以 Redis 协议的格式保存， 因此 AOF 文件的内容非常容易被人读懂， 对文件进行分析（parse）也很轻松。如果你不小心执行了 FLUSHALL 命令把所有数据刷掉了，但只要 AOF 文件没有被重写，那么只要停止服务器， 移除 AOF 文件末尾的 FLUSHALL 命令， 并重启 Redis ， 就可以将数据集恢复到 FLUSHALL 执行之前的状态。

AOF 的缺点

1）对于相同的数据集，AOF 文件的大小一般会比 RDB 文件大。

2）根据所使用的 fsync 策略，AOF 的速度可能会比 RDB 慢。通常 fsync 设置为每秒一次就能获得比较高的性能，而关闭 fsync 可以让 AOF 的速度和 RDB 一样快。

3）AOF 在过去曾经发生过这样的 bug ：因为个别命令的原因，导致 AOF 文件在重新载入时，无法将数据集恢复成保存时的原样。（举个例子，阻塞命令 BRPOPLPUSH 就曾经引起过这样的 bug ） 。虽然这种 bug 在 AOF 文件中并不常见， 但是相较而言， RDB 几乎是不可能出现这种 bug 的。

相关源码在 aof.c，核心方法是：feedAppendOnlyFile、flushAppendOnlyFile

混合持久化的实现原理、优缺点
--------------

描述：混合持久化并不是一种全新的持久化方式，而是对已有方式的优化。混合持久化只发生于 AOF 重写过程。使用了混合持久化，重写后的新 AOF 文件前半段是 RDB 格式的全量数据，后半段是 AOF 格式的增量数据。

整体格式为：[RDB file][AOF tail]

开启：混合持久化的配置参数为 aof-use-rdb-preamble，配置为 yes 时开启混合持久化，在 redis 4 刚引入时，默认是关闭混合持久化的，但是在 redis 5 中默认已经打开了。

关闭：使用 aof-use-rdb-preamble no 配置即可关闭混合持久化。

混合持久化本质是通过 AOF 后台重写（bgrewriteaof 命令）完成的，不同的是当开启混合持久化时，fork 出的子进程先将当前全量数据以 RDB 方式写入新的 AOF 文件，然后再将 AOF 重写缓冲区（aof_rewrite_buf_blocks）的增量命令以 AOF 方式写入到文件，写入完成后通知主进程将新的含有 RDB 格式和 AOF 格式的 AOF 文件替换旧的的 AOF 文件。

优点：结合 RDB 和 AOF 的优点, 更快的重写和恢复。

缺点：AOF 文件里面的 RDB 部分不再是 AOF 格式，可读性差。

相关源码在 aof.c，核心方法是：rewriteAppendOnlyFile

为什么需要 AOF 重写
------------

AOF 持久化是通过保存被执行的写命令来记录数据库状态的，随着写入命令的不断增加，AOF 文件中的内容会越来越多，文件的体积也会越来越大。

如果不加以控制，体积过大的 AOF 文件可能会对 Redis 服务器、甚至整个宿主机造成影响，并且 AOF 文件的体积越大，使用 AOF 文件来进行数据还原所需的时间就越多。

举个例子， 如果你对一个计数器调用了 100 次 INCR ， 那么仅仅是为了保存这个计数器的当前值， AOF 文件就需要使用 100 条记录。

然而在实际上， 只使用一条 SET 命令已经足以保存计数器的当前值了， 其余 99 条记录实际上都是多余的。

为了处理这种情况， Redis 引入了 AOF 重写：可以在不打断服务端处理请求的情况下， 对 AOF 文件进行重建（rebuild）。

AOF 重写
------

描述：Redis 生成新的 AOF 文件来代替旧 AOF 文件，这个新的 AOF 文件包含重建当前数据集所需的最少命令。具体过程是遍历所有数据库的所有键，从数据库读取键现在的值，然后用一条命令去记录键值对，代替之前记录这个键值对的多条命令。

命令：有两个 Redis 命令可以用于触发 AOF 重写，一个是 BGREWRITEAOF 、另一个是  REWRITEAOF 命令；

开启：AOF 重写由两个参数共同控制，auto-aof-rewrite-percentage 和 auto-aof-rewrite-min-size，同时满足这两个条件，则触发 AOF 后台重写 BGREWRITEAOF。

```
// 当前AOF文件比上次重写后的AOF文件大小的增长比例超过100
auto-aof-rewrite-percentage 100 
// 当前AOF文件的文件大小大于64MB
auto-aof-rewrite-min-size 64mb
```

关闭：auto-aof-rewrite-percentage 0，指定 0 的百分比，以禁用自动 AOF 重写功能。

```
auto-aof-rewrite-percentage 0
```

REWRITEAOF：进行 AOF 重写，但是会阻塞主进程，服务器将无法处理客户端发来的命令请求，通常不会直接使用该命令。

BGREWRITEAOF：fork 子进程来进行 AOF 重写，阻塞只会发生在 fork 子进程的时候，之后主进程可以正常处理请求。

REWRITEAOF 和 BGREWRITEAOF 的关系与 SAVE 和 BGSAVE 的关系类似。

相关源码在 aof.c，核心方法是：rewriteAppendOnlyFile

AOF 后台重写存在的问题
-------------

AOF 后台重写使用子进程进行从写，解决了主进程阻塞的问题，但是仍然存在另一个问题：子进程在进行 AOF 重写期间，服务器主进程还需要继续处理命令请求，新的命令可能会对现有的数据库状态进行修改，从而使得当前的数据库状态和重写后的 AOF 文件保存的数据库状态不一致。

如何解决 AOF 后台重写存在的数据不一致问题
-----------------------

为了解决上述问题，Redis 引入了 AOF 重写缓冲区（aof_rewrite_buf_blocks），这个缓冲区在服务器创建子进程之后开始使用，当 Redis 服务器执行完一个写命令之后，它会同时将这个写命令追加到 AOF 缓冲区和 AOF 重写缓冲区。

这样一来可以保证：

1、现有 AOF 文件的处理工作会如常进行。这样即使在重写的中途发生停机，现有的 AOF 文件也还是安全的。

2、从创建子进程开始，也就是 AOF 重写开始，服务器执行的所有写命令会被记录到 AOF 重写缓冲区里面。

这样，当子进程完成 AOF 重写工作后，父进程会在 serverCron 中检测到子进程已经重写结束，则会执行以下工作：

1、将 AOF 重写缓冲区中的所有内容写入到新 AOF 文件中，这时新 AOF 文件所保存的数据库状态将和服务器当前的数据库状态一致。

2、对新的 AOF 文件进行改名，原子的覆盖现有的 AOF 文件，完成新旧两个 AOF 文件的替换。

之后，父进程就可以继续像往常一样接受命令请求了。

相关源码在 aof.c，核心方法是：rewriteAppendOnlyFileBackground

AOF 重写缓冲区内容过多怎么办
----------------

将 AOF 重写缓冲区的内容追加到新 AOF 文件的工作是由主进程完成的，所以这一过程会导致主进程无法处理请求，如果内容过多，可能会使得阻塞时间过长，显然是无法接受的。

Redis 中已经针对这种情况进行了优化：

1、在进行 AOF 后台重写时，Redis 会创建一组用于父子进程间通信的管道，同时会新增一个文件事件，该文件事件会将写入 AOF 重写缓冲区的内容通过该管道发送到子进程。

2、在重写结束后，子进程会通过该管道尽量从父进程读取更多的数据，每次等待可读取事件 1ms，如果一直能读取到数据，则这个过程最多执行 1000 次，也就是 1 秒。如果连续 20 次没有读取到数据，则结束这个过程。

通过这些优化，Redis 尽量让 AOF 重写缓冲区的内容更少，以减少主进程阻塞的时间。

到此，AOF 后台重写的核心内容基本告一段落，通过一张图来看下其完整流程。

![](https://img-blog.csdnimg.cn/img_convert/6cdfe810231bacdb4c58e3b566976337.png)

相关源码在 aof.c，核心方法是：aofCreatePipes、aofChildWriteDiffData、rewriteAppendOnlyFile

RDB、AOF、混合持久，我应该用哪一个？
---------------------

一般来说， 如果想尽量保证数据安全性， 你应该同时使用 RDB 和 AOF 持久化功能，同时可以开启混合持久化。

如果你非常关心你的数据， 但仍然可以承受数分钟以内的数据丢失， 那么你可以只使用 RDB 持久化。

如果你的数据是可以丢失的，则可以关闭持久化功能，在这种情况下，Redis 的性能是最高的。

使用 Redis 通常都是为了提升性能，而如果为了不丢失数据而将 appendfsync  设置为 always 级别时，对 Redis 的性能影响是很大的，在这种不能接受数据丢失的场景，其实可以考虑直接选择 MySQL 等类似的数据库。

服务启动时如何加载持久化数据
--------------

简单来说，如果同时启用了 AOF 和 RDB，Redis 重新启动时，会使用 AOF 文件来重建数据集，因为通常来说， AOF 的数据会更完整。

而在引入了混合持久化之后，使用 AOF 重建数据集时，会通过文件开头是否为 “REDIS” 来判断是否为混合持久化。

完整流程如下图所示：

![](https://img-blog.csdnimg.cn/img_convert/c1618be1f307ae6abe68549b5831ee7f.png)

相关源码在 server.c，核心方法是：loadDataFromDisk

​

****最后****
==========

当你的才华还撑不起你的野心的时候，你就应该静下心来学习，愿你在我这里能有所收获。

如果你觉得本文写的还不错，对你有帮助，请通过**【点赞】**让我知道，支持我写出更好的文章。

****推荐阅读****
============

[面试必问的 Redis：数据结构和基础概念](https://joonwhee.blog.csdn.net/article/details/110847333)

[面试必问的 Spring，你懂了吗？](https://joonwhee.blog.csdn.net/article/details/110009966)

[如何写一份让 HR 眼前一亮的简历（附模板）](https://joonwhee.blog.csdn.net/article/details/109709619)

[字节、美团、快手核心部门面试总结（真题解析）](https://joonwhee.blog.csdn.net/article/details/109588711)

[面试阿里，HashMap 这一篇就够了](https://joonwhee.blog.csdn.net/article/details/106324537)

[面试必问的 MySQL，你懂了吗？](https://joonwhee.blog.csdn.net/article/details/106893197)

[面试必问的线程池，你懂了吗？](https://joonwhee.blog.csdn.net/article/details/106609583)

[4 年 Java 经验，阿里网易拼多多面试总结、心得体会](https://joonwhee.blog.csdn.net/article/details/99708892)

[跳槽，如何选择一家公司](https://joonwhee.blog.csdn.net/article/details/109171171)

[如何准备好一场大厂面试](https://joonwhee.blog.csdn.net/article/details/108702592)

[MySQL 8.0 MVCC 核心原理解析（核心源码）](https://joonwhee.blog.csdn.net/article/details/108379583)

[921 天，咸鱼到阿里的修仙之路](https://joonwhee.blog.csdn.net/article/details/106182747)

[复习 2 个月拿下美团 offer，我都做了些啥](https://joonwhee.blog.csdn.net/article/details/106463578)