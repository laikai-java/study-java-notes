package com.lk.concurrent.thread;

import java.util.concurrent.TimeUnit;

/**
 * 死锁 产生条件
 *
 * 1. 互斥条件：该资源任意⼀个时刻只由⼀个线程占⽤。
 * 2. 请求与保持条件：⼀个进程因请求资源⽽阻塞时，对已获得的资源保持不放。
 * 3. 不剥夺条件:线程已获得的资源在末使⽤完之前不能被其他线程强⾏剥夺，只有⾃⼰使⽤完毕
 * 后才释放资源。
 * 4. 循环等待条件:若⼲进程之间形成⼀种头尾相接的循环等待资源关系。
 */
public class DeadLockDemo {

    public static void main(String[] args) {

        Object objA = new Object();
        Object objB = new Object();
        new Thread(()->{
            synchronized (objA){
                System.out.println("线程A持有objA");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //并且想要持有objB
                synchronized (objB){
                    System.out.println("想要持有objB");
                }
            }
        },"线程A").start();


        new Thread(()->{
            synchronized (objB){
                System.out.println("线程B持有objB");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //并且想要持有objB
                synchronized (objA){
                    System.out.println("想要持有objA");
                }
            }
        },"线程B").start();
    }

}
