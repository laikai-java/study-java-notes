package com.lk.concurrent.multithread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * AQS 源码详解
 */
public class AQSDemo {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        new Thread(() -> {
            lock.lock();
            try {
                try {
                    TimeUnit.MINUTES.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.unlock();
            }
        }, "thread A").start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread() + " coming in");
            } finally {
                lock.unlock();
            }
        }, "thread B").start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread() + " coming in");
            } finally {
                lock.unlock();
            }
        }, "thread C").start();
    }
}
