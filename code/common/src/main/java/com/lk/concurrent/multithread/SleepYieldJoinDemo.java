package com.lk.concurrent.multithread;

import java.util.concurrent.TimeUnit;

/**
 * sleep() sleep方法的作用是让当前线程暂停指定的时间（毫秒） 不会释放锁
 * yield yield方法的作用是暂停当前线程，以便其他线程有机会执行，不过不能指定暂停的时间，并且也不能保证当前线程马上停止。
 * join
 */
public class SleepYieldJoinDemo {

    public static void main(String[] args) {
        SleepYieldJoinDemo sleepYieldJoinDemo = new SleepYieldJoinDemo();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                sleepYieldJoinDemo.sleepMethod();
            }, String.valueOf(i)).start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                sleepYieldJoinDemo.waitMethod();
            }, String.valueOf(i)).start();
        }

        new Thread(new YieldTest(),"A").start();
        new Thread(new YieldTest(),"B").start();



    }

    public synchronized void sleepMethod(){
        System.out.println(Thread.currentThread().getName()+" ---Sleep start-----");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" ---Sleep end-----");
    }

    public synchronized void waitMethod(){
        System.out.println(Thread.currentThread().getName()+"Wait start-----");
        synchronized (this){
            try {
                wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"Wait end-----");
    }


    public static class YieldTest implements Runnable{

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
                Thread.yield();
            }
        }
    }

    public static class JoinTest implements Runnable{
        @Override
        public void run() {

            try {
                System.out.println(Thread.currentThread().getName() + " start-----");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " end------");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) {
            for (int i=0;i<5;i++) {
                Thread test = new Thread(new JoinTest());
                test.start();
                try {
                    test.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Finished~~~");
        }
    }
}
