package com.lk.concurrent.threadpool;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 线程池创建的方式
 * 使用Executors 提供的静态方法创建
 * 使用ThreadPoolExecutor 自定义创建
 */
public class ThreadPoolCreateDemo {

    public static void main(String[] args) {
        //创建只有一个线程的线程池   但其中的阻塞队列为Integer.MAX_VALUE的有界链表
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        //创建固定长度线程的线程池   但其中的阻塞队列为Integer.MAX_VALUE的有界链表
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);

        //创建可变线程数量的线程池  但是最大线程数为Integer.MAX_VALUE 阻塞队列为SynchronousQueue 不存储元素
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        //创建执行周期性任务的线程池 但是最大线程数为Integer.MAX_VALUE 阻塞队列为DelayedWorkQueue
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);

    }
}
