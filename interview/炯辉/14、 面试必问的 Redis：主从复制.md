> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [blog.csdn.net](https://blog.csdn.net/v123411739/article/details/111903245)

**​前言**
=======

在分布式环境中，数据副本 (Replica) 和复制 (Replication) 作为提升系统可用性和读写性能的有效手段被大量应用在各种分布式系统中，Redis 也不例外。

虽说现在基本不会直接使用主从复制来作为 Redis 的高可用方案，但是无论是哨兵还是集群，都会使用到主从复制，因此，有必要先学习下主从复制的原理。

**正文**
======

主从复制实现原理
--------

在当前最新的 Redis 6.0 中，主从复制的完整过程如下：

**1、开启主从复制**

通常有以下三种方式：

1）在 slave 直接执行命令：slaveof <masterip> <masterport>

2）在 slave 配置文件中加入：slaveof <masterip> <masterport>

3）使用启动命令：--slaveof <masterip> <masterport>

注：在 Redis 5.0 之后，slaveof 相关命令和配置已经被替换成 replicaof，例如 replicaof <masterip> <masterport>。为了兼容旧版本，通过配置的方式仍然支持 slaveof，但是通过命令的方式则不行了。

**2、建立套接字（socket）连接**

slave 将根据指定的 IP 地址和端口，向 master 发起套接字（socket）连接，master 在接受（accept） slave 的套接字连接之后，为该套接字创建相应的客户端状态，此时连接建立完成。

**3、发送 PING 命令**

slave 向 master 发送一个 PING 命令，以检査套接字的读写状态是否正常、 master 能否正常处理命令请求。

如果 slave 收到 "PONG" 回复，那么表示 master 和 slave 之间的网络连接状态正常， 并且 master 可以正常处理命令请求。

如果是其他回复或者没有回复，表示 master 和 slave 之间的网络连接状态不佳或者 master 暂时没办法处理 slave 的命令请求，则 slave 进入 error 流程：slave 断开当前的连接，之后再进行重试。

**4、身份验证**

如果 master 和 slave 都没有设置密码，则无需验证。

如果 master 和 slave 都设置了密码，并且密码相同，则验证成功。

否则，master 和 slave 设置的密码不同、master 和 slave 一个设置密码一个没设置密码都会返回错误。

所有错误情况都会令 slave 进入 error 流程：slave 断开当前的连接，之后再进行重试。

**5、发送端口信息**

在身份验证通过后后， slave 将向 master 发送自己的监听端口号， master 收到后记录在 slave 所对应的客户端状态的 slave_listening_port 属性中。

**6、发送 IP 地址**

如果配置了 slave_announce_ip，则 slave 向 master 发送 slave_announce_ip 配置的 IP 地址， master 收到后记录在 slave 所对应的客户端状态的 slave_ip 属性。

该配置是用于解决服务器返回内网 IP 时，其他服务器无法访问的情况。可以通过该配置直接指定公网 IP。

**7、发送 CAPA**

CAPA 全称是 capabilities，这边表示的是同步复制的能力。

slave 会在这一阶段发送 capa 告诉 master 自己具备的（同步）复制能力， master 收到后记录在 slave 所对应的客户端状态的 slave_capa 属性。

CAPA 在最新的 Redis 6.0 版本中有两种值：eof 和 psync2。

eof 表示 slave 支持直接接收从 socket 发送过来的 RDB 数据流，也就是无盘加载（diskless_load）。

psync2 表示 slave 支持 Redis 4.0 引入的部分重同步 v2 版本，这个在下文会详细介绍。

**8、数据同步**

slave 将向 master 发送 PSYNC 命令， master 收到该命令后判断是进行部分重同步还是完整重同步，然后根据策略进行数据的同步。

1）如果是 slave 第一次执行复制，则向 master 发送 PSYNC ? -1， master 返回 +FULLRESYNC <replid> <offset> 执行完整重同步

2）如果不是第一次执行复制，则向 master 发送 PSYNC replid offset，其中 replid 是 master 的复制 ID，而 offset 是 slave 当前的复制偏移量。master 根据 replid 和 offset 来判断应该执行哪种同步操作。

如果是完整重同步，则返回 +FULLRESYNC <replid> <offset>；如果是部分重同步，则返回 +CONTINUE <replid>，此时 slave 只需等待 master 将自己缺少的数据发送过来就可以。

**9、命令传播**

当完成了同步之后，就会进人命令传播阶段，这时 master 只要一直将自己执行的写命令发送给 slave，而 slave 只要一直接收并执行 master 发来的写命令，就可以保证 master 和 slave 一直保持一致了。

在命令传播阶段， slave 默认会以每秒一次的频率，向 master 发送命令：REPLCONF ACK <reploff>，其中 reploff 是 slave 当前的复制偏移量。

发送 REPLCONF ACK 命令对于主从服务器有三个作用：

1）检测 master 和 slave 的网络连接状态。

2）汇报自己的复制偏移量，检测命令丢失，master 会对比复制偏移量，如果发现 slave 的复制偏移量小于自己，会向 slave 发送未同步的数据。

3）辅助实现 min-slaves 配置，用于防止 master 在不安全的情况下执行写命令。

例如以下配置表示：当延迟时间小于 10 秒的 slave 数量小于 3 个，则会拒绝执行写命令。而这边的延迟时间，就是以 slave 最近一次发送 ACK 时间和当前时间作对比。

```
min-slaves-to-write 3min-slaves-max-lag 10
```

以部分重同步为例，主从复制的核心步骤流程图如下：

![](https://img-blog.csdnimg.cn/img_convert/a6c2adcacf3c3ebebd2853021a02ca32.png)

相关源码在 replication.c，核心方法是：replicationSetMaster、connectWithMaster、syncWithMaster

旧版同步：SYNC
---------

Redis 2.8 之前的数据同步通过 SYNC 命令完成，完整流程如下：

1、slave 向 master 发送 SYNC 命令。

2、master 收到 SYNC 命令后执行 BGSAVE 命令，fork 子进程生成 RDB 文件，同时会使用一个缓冲区记录从现在开始执行的所有写命令。

Redis 在这边使用了 COW（copy-on-write）的特性，这边简单介绍一下。

fork 子进程时，一种比较 “愚蠢” 的做法是将父进程的整个地址空间拷贝一份给子进程，但这是非常耗时的，因此一般不会这么做。

另一种做法是，fork 之后，父子进程共用父进程已有的地址空间，只有当父子进程要进行写操作时，才将要修改的内容复制一份，再进行写操作，这也是 copy-on-write 名字的由来。

回到本文，这边当主进程 fork 出子进程时，因为 COW 的关系，可以认为在 fork 的这一刻，快照已经生成了，只是还没写到 RDB 文件。

那这边就有一个问题，RDB 文件是 fork 这一刻的数据，从 fork 这一刻到 master 将 RDB 文件发送给 slave 之间，主进程还在继续执行写命令，这期间的写命令 slave 怎么获得？

这就用到上面 “同时会使用一个缓冲区记录从现在开始执行的所有写命令”，这个缓冲区会记录 fork 之后的所有写命令。

后面当 master 将 RDB 文件发送给 slave 后，master 会继续将缓冲区中的写命令发送给 slave，也就是下面的第 4 步，从而保证 slave 的数据是完整的。

3、当 BGSAVE 命令执行完毕，master 会将生成的 RDB 文件发送给 slave。slave 接收 RDB 文件，并载入到内存，将数据库状态更新至 master 执行 BGSAVE 时的数据库状态。

这边发送 RDB 文件的方式有两种：1）socket：master 将 RDB 文件流通过 socket 直接发送到 slave；2）disk：master 将 RDB 文件先持久化到磁盘，再发送到 slave。

默认使用方式为 disk，可以通过以下配置来使用 socket 方式。

```
repl-diskless-sync yes
```

同时，相关的参数配置还有 diskless-sync-delay：该参数表示等待一定时长再开始复制，这样可以等待多个 slave 节点重新连接上来。

socket（无磁盘）方式适合磁盘读写速度慢但网络带宽非常高的环境。

另外，这边主进程检查子进程 BGSAVE 是否执行完毕是通过时间事件定时检查的。

4、master 将记录在缓冲区里面的所有写命令发送给 slave，slave 执行这些命令，将数据库状态更新至 master 当前所处的状态。

**SYNC 存在的问题**：slave 每次断线重连都需要使用完整重同步，效率低下。

新版同步：SYNC
---------

为了解决 slave 每次断线重连都需要使用完整重同步，redis 在 2.8 版本引入了 PSYNC，PSYNC 包含完整重同步和部分重同步。

1、完整重同步：和 SYNC 命令基本一致。

2、部分重同步：slave 只需要接收和同步断线期间丢失的写命令即可，不需要进行完整重同步。

为了实现部分重同步，Redis 引入了复制偏移量、复制积压缓冲区和运行 ID 三个概念。

**复制偏移量（offset）**

执行主从复制的双方都会分别维护一个复制偏移量，master 每次向 slave 传播 N 个字节，自己的复制偏移量就增加 N；同理 slave 接收 N 个字节，复制偏移量也增加 N。通过对比主从之间的复制偏移量就可以知道主从间的同步状态。

**复制积压缓冲区（replication backlog buffer）**

复制积压缓冲区是 master 维护的一个固定长度的 FIFO 队列，默认大小为 1MB。

当 master 进行命令传播时，不仅将写命令发给 slave 还会同时写进复制积压缓冲区，因此 master 的复制积压缓冲区会保存一部分最近传播的写命令。

当 slave 重连上 master 时会将自己的复制偏移量通过 PSYNC 命令发给 master，master 检查自己的复制积压缓冲区，如果发现这部分未同步的命令还在自己的复制积压缓冲区中的话就可以利用这些保存的命令进行部分同步，反之如果断线太久这部分命令已经不在复制缓冲区中了，那没办法只能进行全量同步。

**运行 ID（runid）**

每个 Redis server 都会有自己的运行 ID，由 40 个随机的十六进制字符组成。当 slave 初次复制 master 时，master 会将自己的运行 ID 发给 slave 进行保存，这样 slave 重连时再将这个运行 ID 发送给重连上的 master，master 会接受这个 ID 并与自身的运行 ID 比较进而判断是否是同一个 master。

**引入这三个概念后，数据同步过程如下：**

1）slave 通过 PSYNC runid offset 命令，将正在复制的 runid 和 offset 发送给 master。

2）master 判断 runid 和自己的 runid 相同，并且 offset 还在复制积压缓冲区，则进行部分重同步：通过复制积压缓冲区将 slave 缺失的命令发送给 slave，slave 执行命令，将数据库状态更新至 master 所处的状态。

3）否则，如果 master 判断 runid 不相同，或者 offset 已经不在复制积压缓冲区，则执行完整重同步。

PSYNC 的完整流程如下图：

![](https://img-blog.csdnimg.cn/img_convert/500e5717f89f34e2fdd4bc8ec4ee06ef.png)

**PSYNC 存在的问题**

通过上述流程，我们可以看出，PSYNC 执行部分重同步需要满足两个条件：1）master runid 不变；2）复制偏移量在 master 复制积压缓冲区中。一旦不满足这两个条件，则仍然需要进行完整重同步，例如以下场景。

1、slave 重启，缓存的 master runid 和 offset 都会丢失，slave 需进行完整重同步。

2、redis 发生故障切换，故障切换后 master runid 发生了变化，slave 需进行完整重同步。

slave 维护性重启、master 故障切换都是 redis 运维常见场景，因此，PSYNC 的这两个问题出现概率还是非常高的。

相关源码在 replication.c，核心方法是：syncCommand、readSyncBulkPayload、replicationFeedSlaves、backgroundSaveDoneHandler、slaveTryPartialResynchronization 等

PSYNC2
------

为了解决 PSYNC 在 slave 重启和故障切换导致完整重同步的问题，Redis 在 4.0 版本中对 PSYNC 进行了优化，我们称为 PSYNC2。

PSYNC2 进行了以下 2 个主要改动：

**1、引入两组 replid 和 offset 替换原来的 runid 和 offset**

第一组：replid 和 master_repl_offset

对于 master，表示为自己的复制 ID 和复制偏移量；

对于 slave，表示为自己正在同步的 master 的复制 ID 和复制偏移量。

可以认为这一组的两个字段就是对应原来的 runid 和 offset。

第二组：replid2 和 second_repl_offset

对于 master 和 slave，都表示自己的上一个 master 的复制 ID 和复制偏移量；主要用于故障切换时支持部分重同步。

值得注意的是，runid 并不是在引入 replid 之后就不存在了。在 4.0 之前，redis 使用 runid 来作为主从复制的标识，而在 4.0 后引入了 replid 来作为主从复制的标识，但是，runid 在 redis 中的功能不仅仅是作为主从复制的标识，runid 仍然有其他的功能，例如：用于作为 redis 服务器的唯一标识。

**2、slave 也会开启复制积压缓冲区**

slave 开启复制积压缓冲区，主要是用于故障切换后，当某个 slave 升级为 master，该 slave 仍然可以通过复制积压缓冲区继续支持部分重同步功能。

如果 slave 不开启复制积压缓冲区，当该 slave 升级为 master 后，复制积压缓冲区是空的，就没法支持部分重同步了。

接下来，让我们看看 Redis 是如何针对 PSYNC 的两个问题来进行优化。

**优化场景 1：slave 重启后导致完整同步**

产生该问题的根本原因是 slave 重启后，复制 ID（运行 ID） 和 复制偏移量丢失了。解决办法其实很简单，就是在关闭服务器前将这两个变量存下来即可。

Redis 的做法如下：slave 在正常关闭前会调用 rdbSaveInfoAuxFields 函数把当前的复制 ID（replid） 和复制偏移量（master_repl_offset）作为辅助字段保存到 RDB 文件中，后面该 slave 重启的时候，就可以从 RDB 文件中读取复制 ID 和复制偏移量，然后使用这两个变量来进行部分重同步。

**优化场景 2：master 故障切换后导致完整重同步**

产生该问题的根本原因是故障切换后出现了新的 master，而新 master 的复制 ID（运行 ID）发生改变导致没法进行部分重同步。

在正常同步的情况下，新 master 的数据跟老 master 理论上是完全一致的，包括复制积压缓冲区的数据。

因此理论上 slave 是可以进行部分重同步的，现在仅仅是因为复制 ID 变了而没法进行。所以，我们的目标就是想办法让新 master 和其他 slave 可以串联起来。

新 master 和其他没有晋升的 slave 的共同点是故障切换前的 master 是相同的，因此很容易想到的做法是：利用故障切换前的 master 来串联新 master 和剩余 slave。

Redis 的做法如下：当节点从 slave 晋升为 master 后，会将原来自己保存的第一组复制 ID 和复制偏移量（也就是老 master 的），移动到第二组复制 ID 和复制偏移量，然后将第一组复制 ID 重新生成一个新的，也就是属于自己的复制 ID。

相当于，slave 晋升为 master 后，replid 保存了自己的复制 ID，replid2 保存了老 master 的复制 ID。

这样，新 master 就可以通过 replid2 来判断 slave 是否之前跟自己从是从同一个 master 复制数据，如果是的话，则尝试使用部分重同步。

PSYNC2 的完整流程如下，可以看出和 PSYNC 很类似，主要区别在于紫色框部分。

![](https://img-blog.csdnimg.cn/img_convert/8703e2afaf7f6bcfc9c1a9d1080a8e3e.png)

相关源码基本同 PSYNC

主从复制的演变
-------

从 Redis 2.* 到现在，开发人员对主从复制流程进行逐步的优化，以下是演进过程：

1、2.8 版本之前 Redis 复制采用 SYNC 命令，无论是第一次复制还是断线重连后的复制都采用完整重同步，成本高。

2、2.8 ~ 4.0 之间复制采用 PSYNC 命令，主要优化了 Redis 在断线重连时候可通过 runid 和 offset 信息使用部分重同步。

3、4.0 版本之后对 PSYNC 进行了优化，通常称为 PSYNC2，主要优化了 PSYNC 在 slave 重启和故障切换时的完整重同步问题。

****最后****
==========

当你的才华还撑不起你的野心的时候，你就应该静下心来学习，愿你在我这里能有所收获。

如果你觉得本文写的还不错，对你有帮助，请通过**【点赞】**让我知道，支持我写出更好的文章。

****推荐阅读****
============

[面试必问的 Redis：RDB、AOF、混合持久化](https://joonwhee.blog.csdn.net/article/details/111569283)

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