> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [blog.csdn.net](https://blog.csdn.net/v123411739/article/details/79561458)

**目录**

[概述](#t0)

[案例](#t1)

[CAS 是什么？](#t2)

[源码分析](#t3)

[intel 手册对 lock 前缀的说明如下：](#t4)

[CAS 的缺点：](#t5)

[循环时间长开销很大：](#t6)

[只能保证一个变量的原子操作：](#t7)

[什么是 ABA 问题？ABA 问题怎么解决？](#t8)

概述
==

CAS（Compare-and-Swap），即比较并替换，是一种实现并发算法时常用到的技术，Java 并发包中的很多类都使用了 CAS 技术。CAS 也是现在面试经常问的问题，本文将深入的介绍 CAS 的原理。

**我的最新面试题文章（面试系列持续更新中）：**[Java 集合框架高频面试题（2021 年最新版）](https://joonwhee.blog.csdn.net/article/details/115712641)

案例
==

介绍 CAS 之前，我们先来看一个例子。

```
/**
 * @author joonwhee
 * @date 2019/7/6
 */
public class VolatileTest {
    
    public static volatile int race = 0;
 
    private static final int THREADS_COUNT = 20;
    
    public static void increase() {
        race++;
    }
 
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }
 
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println(race);
    }
}
```

这个例子有些网友反馈会进入死循环，我后面也发现了，在 IDEA 的 RUN 模式下确实会陷入死循环，通过 Thread.currentThread().getThreadGroup().list(); 代码可以打印出当前的线程情况如下：

```
java.lang.ThreadGroup[name=main,maxpri=10]
    Thread[main,5,main]
    Thread[Monitor Ctrl-Break,5,main]
```

可以看到，除了 Main 方法线程后，还有一个 Monitor Ctrl-Break 线程，这个线程是 IDEA 用来监控 Ctrl-Break 中断信号的线程。

解决死循环的办法：如果是 IDEA，可以使用 DEBUG 模式运行就可以，或者使用下面这段代码。

```
import java.util.concurrent.CountDownLatch;
 
/**
 * @author joonwhee
 * @date 2019/7/6
 */
public class VolatileTest {
    
    public static volatile int race = 0;
 
    private static final int THREADS_COUNT = 20;
 
    private static CountDownLatch countDownLatch = new CountDownLatch(THREADS_COUNT);
 
    public static void increase() {
        race++;
    }
 
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        increase();
                    }
                    countDownLatch.countDown();
                }
            });
            threads[i].start();
        }
        countDownLatch.await();
        System.out.println(race);
    }
}
```

上面这个例子在 [volatile 关键字详解](http://blog.csdn.net/v123411739/article/details/79438066)文中用过，我们知道，运行完这段代码之后，并不会获得期望的结果，而且会发现每次运行程序，输出的结果都不一样，都是一个小于 200000 的数字。

通过分析字节码我们知道，这是因为 volatile 只能保证可见性，无法保证原子性，而自增操作并不是一个原子操作（如下图所示），在并发的情况下，putstatic 指令可能把较小的 race 值同步回主内存之中，导致我们每次都无法获得想要的结果。那么，应该怎么解决这个问题了？

![](https://img-blog.csdn.net/20180314222334362)

**解决方法：**

首先我们想到的是用 synchronized 来修饰 increase 方法。

![](https://img-blog.csdn.net/20180314222339188)

使用 synchronized 修饰后，increase 方法变成了一个原子操作，因此是肯定能得到正确的结果。但是，我们知道，每次自增都进行加锁，性能可能会稍微差了点，有更好的方案吗？

答案当然是有的，这个时候我们可以使用 Java 并发包原子操作类（Atomic 开头），例如以下代码。

![](https://img-blog.csdn.net/2018031422234680)

我们将例子中的代码稍做修改：race 改成使用 AtomicInteger 定义，“race++” 改成使用 “race.getAndIncrement()”，AtomicInteger.getAndIncrement() 是原子操作，因此我们可以确保每次都可以获得正确的结果，并且在性能上有不错的提升（针对本例子，在 JDK1.8.0_151 下运行）。

通过方法调用，我们可以发现，getAndIncrement 方法调用 getAndAddInt 方法，最后调用的是 compareAndSwapInt 方法，即本文的主角 CAS，接下来我们开始介绍 CAS。

![](https://img-blog.csdn.net/20180314222502733)

![](https://img-blog.csdn.net/20180314222428674)

getAndAddInt 方法解析：拿到内存位置的最新值 v，使用 CAS 尝试修将内存位置的值修改为目标值 v+delta，如果修改失败，则获取该内存位置的新值 v，然后继续尝试，直至修改成功。

CAS 是什么？
========

CAS 是英文单词 CompareAndSwap 的缩写，中文意思是：比较并替换。CAS 需要有 3 个操作数：内存地址 V，旧的预期值 A，即将要更新的目标值 B。

CAS 指令执行时，当且仅当内存地址 V 的值与预期值 A 相等时，将内存地址 V 的值修改为 B，否则就什么都不做。整个比较并替换的操作是一个原子操作。

源码分析
----

上面源码分析时，提到最后调用了 compareAndSwapInt 方法，接着继续深入探讨该方法，该方法在 Unsafe 中对应的源码如下。

![](https://img-blog.csdn.net/20180314222522710)

可以看到调用了 “Atomic::cmpxchg” 方法，“Atomic::cmpxchg” 方法在 linux_x86 和 windows_x86 的实现如下。

**linux_x86 的实现：**

![](https://img-blog.csdn.net/20180314222533843)

**windows_x86 的实现：**

![](https://img-blog.csdn.net/20180314222539318)

**Atomic::cmpxchg 方法解析：**

mp 是 “os::is_MP()” 的返回结果，“os::is_MP()” 是一个内联函数，用来判断当前系统是否为多处理器。

1.  如果当前系统是多处理器，该函数返回 1。
2.  否则，返回 0。

LOCK_IF_MP(mp) 会根据 mp 的值来决定是否为 cmpxchg 指令添加 lock 前缀。

1.  如果通过 mp 判断当前系统是多处理器（即 mp 值为 1），则为 cmpxchg 指令添加 lock 前缀。
2.  否则，不加 lock 前缀。

这是一种优化手段，认为单处理器的环境没有必要添加 lock 前缀，只有在多核情况下才会添加 lock 前缀，因为 lock 会导致性能下降。cmpxchg 是汇编指令，作用是比较并交换操作数。

intel 手册对 lock 前缀的说明如下：
-----------------------

1.  确保对内存的读 - 改 - 写操作原子执行。在 Pentium 及 Pentium 之前的处理器中，带有 lock 前缀的指令在执行期间会锁住总线，使得其他处理器暂时无法通过总线访问内存。很显然，这会带来昂贵的开销。从 Pentium 4，Intel Xeon 及 P6 处理器开始，intel 在原有总线锁的基础上做了一个很有意义的优化：如果要访问的内存区域（area of memory）在 lock 前缀指令执行期间已经在处理器内部的缓存中被锁定（即包含该内存区域的缓存行当前处于独占或以修改状态），并且该内存区域被完全包含在单个缓存行（cache line）中，那么处理器将直接执行该指令。由于在指令执行期间该缓存行会一直被锁定，其它处理器无法读 / 写该指令要访问的内存区域，因此能保证指令执行的原子性。这个操作过程叫做缓存锁定（cache locking），缓存锁定将大大降低 lock 前缀指令的执行开销，但是当多处理器之间的竞争程度很高或者指令访问的内存地址未对齐时，仍然会锁住总线。
2.  禁止该指令与之前和之后的读和写指令重排序。
3.  把写缓冲区中的所有数据刷新到内存中。

上面的第 1 点保证了 CAS 操作是一个原子操作，第 2 点和第 3 点所具有的内存屏障效果，保证了 CAS 同时具有 volatile 读和 volatile 写的内存语义。

CAS 的缺点：
========

CAS 虽然很高效的解决了原子操作问题，但是 CAS 仍然存在三大问题。

1.  循环时间长开销很大。
2.  只能保证一个变量的原子操作。
3.  ABA 问题。

循环时间长开销很大：
----------

CAS 通常是配合无限循环一起使用的，我们可以看到 getAndAddInt 方法执行时，如果 CAS 失败，会一直进行尝试。如果 CAS 长时间一直不成功，可能会给 CPU 带来很大的开销。

只能保证一个变量的原子操作：
--------------

当对一个变量执行操作时，我们可以使用循环 CAS 的方式来保证原子操作，但是对多个变量操作时，CAS 目前无法直接保证操作的原子性。但是我们可以通过以下两种办法来解决：1）使用互斥锁来保证原子性；2）将多个变量封装成对象，通过 AtomicReference 来保证原子性。

什么是 ABA 问题？ABA 问题怎么解决？
----------------------

CAS 的使用流程通常如下：1）首先从地址 V 读取值 A；2）根据 A 计算目标值 B；3）通过 CAS 以原子的方式将地址 V 中的值从 A 修改为 B。

但是在第 1 步中读取的值是 A，并且在第 3 步修改成功了，我们就能说它的值在第 1 步和第 3 步之间没有被其他线程改变过了吗？

如果在这段期间它的值曾经被改成了 B，后来又被改回为 A，那 CAS 操作就会误认为它从来没有被改变过。这个漏洞称为 CAS 操作的 “ABA” 问题。Java 并发包为了解决这个问题，提供了一个带有标记的原子引用类 “AtomicStampedReference”，它可以通过控制变量值的版本来保证 CAS 的正确性。因此，在使用 CAS 前要考虑清楚 “ABA” 问题是否会影响程序并发的正确性，如果需要解决 ABA 问题，改用传统的互斥同步可能会比原子类更高效。

推荐阅读
====

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

> 另外，我还准备了很多**大厂面试资料、0 基础自学教程**，由于不能放外链，所以有需要的小伙伴去公众号【**程序员囧辉**】回复【**资料**】自行获取好了。