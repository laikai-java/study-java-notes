package com.lk.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * java 实现cas
 *
 * 问题1
 * ABA 问题  使用带有版本的 AtomicStampedReference
 *
 * 问题2
 * 循环时间长 开销大
 *
 * 问题3
 * 只能保证一个共享变量的原子操作
 * 使用AtomicReference
 */
public class CASDemo {

    public static void main(String[] args) {
        final Counter counter = new Counter();
        List<Thread> threads = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.safeCount();
                    counter.count();
                }
            });
            threads.add(thread);
        }


        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                //依次执行完毕
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("counter i :" + counter.i);
        System.out.println("counter atomicInteger :" + counter.atomicInteger.get());
        System.out.println("time :" + (System.currentTimeMillis() - start));

    }


    public static class Counter {
        private AtomicInteger atomicInteger = new AtomicInteger(0);
        private int i = 0;

        /**
         * 线程安全计数器
         */
        public void safeCount() {
            //cas 循环
            for (; ; ) {
                int i = atomicInteger.get();
                boolean b = atomicInteger.compareAndSet(i, ++i);
                if (b) {
                    break;
                }
            }
        }

        /**
         * 非线程安全 计数器
         */
        public void count() {
            i++;
        }
    }
}
