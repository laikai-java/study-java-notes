package com.lk.concurrent.multithread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

/**
 * 线程间通信
 * sync Object#wait Object#notify notifyAll
 * Lock Condition#await Condition#signal signalAll
 * LockSupport park unPark
 *
 */
public class ThreadInformationDemo {
    public static void main(String[] args) {
        //serialRun();
        runDAfterABC();
    }

    /**
     * 如何让两个线程依次执行？
     * Thread.join()
     */
    public static void serialRun(){
        Thread A = new Thread(() -> {
            printNumber();
        },"A");

        Thread B = new Thread(() -> {
            try {
                System.out.println("B等待A执行完毕");
                A.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            printNumber();
        },"B");

        A.start();
        B.start();
    }

    /**
     * 那如何让 两个线程按照指定方式有序交叉运行呢？
     * 希望 A 在打印完 1 后，再让 B 打印 1, 2, 3，最后再回到 A 继续打印 2, 3
     * 使用synchronized 搭配Object的wait 和 notify
     */
    public static void crossRun(){
        Object lock = new Object();
        Thread A = new Thread(() -> {
            synchronized(lock){
                System.out.println("A1");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("A2");
                System.out.println("A3");
            }
        },"A");

        Thread B = new Thread(() -> {
           synchronized (lock){
               printNumber();
               lock.notify();
           }
        },"B");

        A.start();
        B.start();
    }

    /**
     * 交替执行
     */
    public static void crossOneByOne(){
        Object lock = new Object();

        final int[] count = new int[]{0};
        Thread A = new Thread(() -> {
            synchronized (lock){
                while (count[0] < 100){
                    if (count[0] % 2 == 0){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + ": print " + count[0]);
                    count[0]++;
                    lock.notify();
                }
            }
        },"print 奇数");

        Thread B = new Thread(() -> {
            synchronized (lock){
                while (count[0] < 100){
                    if (count[0] % 2 == 1){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + ": print " + count[0]);
                    count[0]++;
                    lock.notify();
                }
            }
        },"print 偶数");

        A.start();
        B.start();
    }

    /**
     * 四个线程 A B C D，其中 D 要等到 A B C 全执行完毕后才执行，而且 A B C 是同步运行的
     * A B C 三个线程同时运行，各自独立运行完后通知 D；
     * 对 D 而言，只要 A B C 都运行完了，D 再开始运行。
     * CountdownLatch
     */
    public static void runDAfterABC() {
        int worker = 3;
        CountDownLatch countDownLatch = new CountDownLatch(worker);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("D is waiting for other three threads");
                try {
                    countDownLatch.await();
                    System.out.println("All done, D starts working");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        for (char threadName = 'A'; threadName <= 'C'; threadName++) {
            final String tN = String.valueOf(threadName);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(tN + " is working");
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(tN + " finished");
                    countDownLatch.countDown();
                }
            }).start();


        }
    }
    private static void printNumber() {
        int i=0;
        while (i++ < 3) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " print: " + i);
        }
    }
}
