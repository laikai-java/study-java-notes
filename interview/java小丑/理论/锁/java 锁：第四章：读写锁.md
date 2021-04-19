> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [blog.csdn.net](https://blog.csdn.net/java_wxid/article/details/99165717)

### 理论：

![](https://img-blog.csdnimg.cn/2019081108203492.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

![](https://img-blog.csdnimg.cn/20190811082113852.png)

未使用读写锁的代码：

```
package com.javaliao.backstage;
 
import java.util.HashMap;
import java.util.Map;
 
class Data{
    private volatile Map map = new HashMap<String,Object>();
 
    //写
    public void put(String key,Object value){
        System.out.println(Thread.currentThread().getName()+"\t 正在写入："+key);
        try {
            Thread.sleep(300);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t 写入完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //读
    public void get(String key){
        System.out.println(Thread.currentThread().getName()+"\t 正在读取");
        try {
            Thread.sleep(300);
            Object value = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读取完成:"+value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
 
public class Demo {
 
    public static void main(String[] args) {
        Data data = new Data();
        //五个写的线程
        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(()->{
                data.put(tempInt+"",tempInt+"");
            },String.valueOf(i)).start();
        }
        //五个读的线程
        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(()->{
                data.get(tempInt+"");
            },String.valueOf(i)).start();
        }
    }
}
```

控制台：

![](https://img-blog.csdnimg.cn/20190811085502117.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

可以看到写的操作原子性和独占性没有得到保证，0 线程正在写入共享资源的时候，其他线程有写入和读取的共享资源操作，导致数据不一致。

### 是否可以添加 Lock 锁解决原子性和独占性的问题？

不可以，因为添加

```
package com.javaliao.backstage;
 
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
 
class Data{
    private volatile Map map = new HashMap<String,Object>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public void put(String key,Object value){
        //写锁
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在写入："+key);
            Thread.sleep(300);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t 写入完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.writeLock().unlock();
        }
    }
 
    public void get(String key){
        //读锁
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在读取");
            Thread.sleep(300);
            Object value = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读取完成:"+value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.readLock().unlock();
        }
    }
}
 
 
public class Demo {
 
    public static void main(String[] args) {
        Data data = new Data();
        //五个写的线程
        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(()->{
                data.put(tempInt+"",tempInt+"");
            },String.valueOf(i)).start();
        }
        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(()->{
                data.get(tempInt+"");
            },String.valueOf(i)).start();
        }
    }
}
```

只能保证一个线程读，不能让多个线程同时读取，不符合实际需求。

### 使用 ReentrantReadWriteLock 解决原子性和独占性，可以很好的解决并发性和数据的一致性

读写锁的代码：

```
package com.javaliao.backstage;
 
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
 
class Data{
    private volatile Map map = new HashMap<String,Object>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public void put(String key,Object value){
        //写锁
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在写入："+key);
            Thread.sleep(300);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t 写入完成 ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.writeLock().unlock();
        }
    }
 
    public void get(String key){
        //读锁
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在读取 ");
            Thread.sleep(300);
            Object value = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读取完成:"+value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.readLock().unlock();
        }
    }
}
 
 
public class Demo {
 
    public static void main(String[] args) {
        Data data = new Data();
        //五个写的线程
        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(()->{
                data.put(tempInt+"",tempInt+"");
            },String.valueOf(i)).start();
        }
        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(()->{
                data.get(tempInt+"");
            },String.valueOf(i)).start();
        }
    }
}
```

控制台：

![](https://img-blog.csdnimg.cn/20190811090615156.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

比较：

![](https://img-blog.csdnimg.cn/20190811090910829.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)