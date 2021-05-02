package com.lk.concurrent.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * 简单使用ThreadLocal
 */
public class ThreadLocalDemo {
    public static void main(String[] args) {
        ThreadLocalExample thread = new ThreadLocalExample();
        for (int i = 0; i < 5; i++) {
            new Thread(thread, String.valueOf(i)).start();
        }
    }
}

class ThreadLocalExample implements Runnable{
    private static final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(
            () -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));


    @Override
    public void run() {
        System.out.println("Thread Name= "+Thread.currentThread().getName()+" default Formatter = "+formatter.get().toPattern());
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //formatter pattern is changed here by thread, but it won't reflect to other threads
        formatter.set(new SimpleDateFormat());
        System.out.println("Thread Name= "+Thread.currentThread().getName()+" formatter = "+formatter.get().toPattern());
    }
}
