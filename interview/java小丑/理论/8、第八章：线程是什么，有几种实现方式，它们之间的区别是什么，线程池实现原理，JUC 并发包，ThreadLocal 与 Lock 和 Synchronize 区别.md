> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [blog.csdn.net](https://blog.csdn.net/java_wxid/article/details/106901449)

什么是线程？讲个故事给你听，让你没法去背这个题，地址：[https://blog.csdn.net/java_wxid/article/details/94131223](https://blog.csdn.net/java_wxid/article/details/94131223)

有几种实现方式？

1.  继承 Thread 类
2.  实现 Runnable 接口
3.  实现 Callable 接口
4.  线程池方式

优缺点

1. 继承 Thread 类

*   优点、代码简单。
*   缺点、该类无法集成别的类。

2. 实现 Runnable 接口

*   优点、继承其他类。 同一实现该接口的实例可以共享资源。
*   缺点、代码复杂

3. 实现 Callable

*   优点、可以获得异步任务的返回值

4. 线程池、实现自动化装配，易于管理，循环利用资源。

代码实现案例：

```
继承Thread类，并重写里面的run方法
class A extends Thread{
    public void run(){
        for(int i=1;i<=100;i++){
            System.out.println("-----------------"+i);
        }
    }
}
A a = new A();
a.start();
 
实现Runnable接口，并实现里面的run方法
class B implements Runnable{
    public void run(){
        for(int i=1;i<=100;i++){
            System.out.println("-----------------"+i);
        }
    }
}
B b = new B();
Thread t = new Thread(b);
t.start();
实现Callable
class A implements Callable<String>{
    public String call() throws Exception{
        //...
    }
}
FutureTask<String> ft = new FutureTask<>(new A());
new Thread(ft).start();
线程池
ExcutorService es = Executors.newFixedThreadPool(10);
es.submit(new Runnable(){//任务});
es.submit(new Runnable(){//任务});
...
es.shutdown();
```

问题扩展

在 Java 中 Lock 接口比 synchronized 块的优势是什么？你需要实现一个高效的缓存，它允许多个用户读，但只允许一个用户写，以此来保持它的完整性，你会怎样去实现它？

整体上来说 Lock 是 synchronized 的扩展版，Lock 提供了无条件的、可轮询的 (tryLock 方法)、定时的 (tryLock 带参方法)、可中断的 (lockInterruptibly)、可多条件队列的 (newCondition 方法) 锁操作。另外 Lock 的实现类基本都支持非公平锁 (默认) 和公平锁，synchronized 只支持非公平锁，当然，在大部分情况下，非公平锁是高效的选择。

**线程池的实现原理**：[https://blog.csdn.net/java_wxid/article/details/101844786](https://blog.csdn.net/java_wxid/article/details/101844786)

**JUC 并发包：**

*   volatile 的三大特性：[https://blog.csdn.net/java_wxid/article/details/97611028](https://blog.csdn.net/java_wxid/article/details/97611028)
*   CompareAndSwap 底层原理：[https://blog.csdn.net/java_wxid/article/details/97611037](https://blog.csdn.net/java_wxid/article/details/97611037)
*   AtomicReference 原子引用：[https://blog.csdn.net/java_wxid/article/details/97611046](https://blog.csdn.net/java_wxid/article/details/97611046)
*   CountDownLatch 倒计时器：[https://blog.csdn.net/java_wxid/article/details/99168098](https://blog.csdn.net/java_wxid/article/details/99168098)
*   CyclicBarrier 循环栅栏：[https://blog.csdn.net/java_wxid/article/details/99171155](https://blog.csdn.net/java_wxid/article/details/99171155)
*   Semaphore 信号灯：[https://blog.csdn.net/java_wxid/article/details/99174538](https://blog.csdn.net/java_wxid/article/details/99174538)

**ThreadLocal 与 Lock 和 Synchronize 区别**

ThreadLocal 与 Lock 和 Synchronize 区别  
ThreadLocal 为每一个线程都提供了变量的副本，使得每个线程在某一时间访问到的并不是同一个对象，这样就隔离了多个线程对数据的数据共享。ThreadLocal 采用了 “以空间换时间” 的方式，为每一个线程都提供了一份变量，因此可以同时访问而互不影响。  
synchronized 是利用锁的机制，使变量或代码块在某一时该只能被一个线程访问。同步机制采用了 “以时间换空间” 的方式，仅提供一份变量，让不同的线程排队访问。  
如果一个代码块被 synchronized 关键字修饰，当一个线程获取了对应的锁，并执行该代码块时，其他线程便只能一直等待直至占有锁的线程释放锁。事实上，占有锁的线程释放锁一般会是以下三种情况之一：  
占有锁的线程执行完了该代码块，然后释放对锁的占有；  
占有锁线程执行发生异常，此时 JVM 会让线程自动释放锁；  
占有锁线程进入 WAITING 状态从而释放锁，例如在该线程中调用 wait() 方法等。  
synchronized 是 Java 语言的内置特性，可以轻松实现对临界资源的同步互斥访问。那么，为什么还会出现 Lock 呢？试考虑以下三种情况：  
Case 1 ：  
在使用 synchronized 关键字的情形下，假如占有锁的线程由于要等待 IO 或者其他原因（比如调用 sleep 方法）被阻塞了，但是又没有释放锁，那么其他线程就只能一直等待，别无他法。这会极大影响程序执行效率。因此，就需要有一种机制可以不让等待的线程一直无期限地等待下去（比如只等待一定的时间 (解决方案：tryLock(long time, TimeUnit unit)) 或者 能够响应中断 (解决方案：lockInterruptibly())），这种情况可以通过 Lock 解决。  
Case 2 ：  
我们知道，当多个线程读写文件时，读操作和写操作会发生冲突现象，写操作和写操作也会发生冲突现象，但是读操作和读操作不会发生冲突现象。但是如果采用 synchronized 关键字实现同步的话，就会导致一个问题，即当多个线程都只是进行读操作时，也只有一个线程在可以进行读操作，其他线程只能等待锁的释放而无法进行读操作。因此，需要一种机制来使得当多个线程都只是进行读操作时，线程之间不会发生冲突。同样地，Lock 也可以解决这种情况 (解决方案：ReentrantReadWriteLock)。  
Case 3 ：  
我们可以通过 Lock 得知线程有没有成功获取到锁 (解决方案：ReentrantLock)，但这个是 synchronized 无法办到的。  
上面提到的三种情形，我们都可以通过 Lock 来解决，但 synchronized 关键字却无能为力。事实上，Lock 是 java.util.concurrent.locks 包 下的接口，Lock 实现提供了比 synchronized 关键字 更广泛的锁操作，它能以更优雅的方式处理线程同步问题。也就是说，Lock 提供了比 synchronized 更多的功能。但是要注意以下几点：  
1）synchronized 是 Java 的关键字，因此是 Java 的内置特性，是基于 JVM 层面实现的。而 Lock 是一个 Java 接口，是基于 JDK 层面实现的，通过这个接口可以实现同步访问；  
2）采用 synchronized 方式不需要用户去手动释放锁，当 synchronized 方法或者 synchronized 代码块执行完之后，系统会自动让线程释放对锁的占用；而 Lock 则必须要用户去手动释放锁，如果没有主动释放锁，就有可能导致死锁现象。

关于读写锁：[https://blog.csdn.net/java_wxid/article/details/99165717](https://blog.csdn.net/java_wxid/article/details/99165717)