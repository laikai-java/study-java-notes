> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [blog.csdn.net](https://blog.csdn.net/java_wxid/article/details/97611665)

### 可重入锁是什么？

![](https://img-blog.csdnimg.cn/20190728100736800.png)

可以防止死锁，是同一把锁

代码：

```
package com.javaliao.backstage;
 
class Phone{
 
    public synchronized void sendSMS(){
        System.out.println(Thread.currentThread().getName()+"\t 发短信");
        sendEmail();
    }
    public synchronized void sendEmail(){
        System.out.println(Thread.currentThread().getName()+"\t 发邮件");
    }
}
public class Demo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{
            phone.sendSMS();
        },"t1").start();
        new Thread(()->{
            phone.sendSMS();
        },"t2").start();
    }
}
```

控制台：

![](https://img-blog.csdnimg.cn/20190728102425673.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

生活案例：

家里的大门有一把锁，厕所没有上锁。我进了大门了，就不用在厕所上锁了。

ReentrantLock 就是把可重入锁

```
package com.javaliao.backstage;
 
 
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
 
class Phone implements Runnable{
 
    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        sendSMS();
    }
 
    public void sendSMS(){
        //加几把就要解锁几次
        lock.lock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 发短信");
            sendEmail();
        } finally {
            lock.unlock();
            lock.unlock();
        }
    }
    public void sendEmail(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 发邮件");
        } finally {
            lock.unlock();
        }
    }
 
 
}
public class Demo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        Thread t1 = new Thread(phone,"t1");
        Thread t2 = new Thread(phone,"t2");
        t1.start();
        t2.start();
    }
}
```

![](https://img-blog.csdnimg.cn/20190728104911870.png)