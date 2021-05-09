package com.lk.concurrent.thread;

import java.util.concurrent.locks.ReentrantLock;

public class MonitorDemo {
}

class MonitorExample {
    int a = 0;

    public synchronized void writer() { // 1
        a++;                            // 2
    }                                   // 3

    public synchronized void reader() { // 4
        int i = a;                      // 5
    }                                   // 6
}

class ReentrantLockExample {
    int a = 0;
    ReentrantLock lock = new ReentrantLock();

    public void writer() {
        lock.lock(); // 获取锁
        try {
            a++;
        } finally {
            lock.unlock(); // 释放锁
        }
    }

    public void reader() {
        lock.lock(); // 获取锁
        try {
            int i = a;
        } finally {
            lock.unlock(); // 释放锁
        }
    }
}


