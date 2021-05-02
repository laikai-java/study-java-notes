package com.lk.concurrent.threadpool;

import java.util.concurrent.*;

public class CreateThreadDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Thread(new MyThread()).start();

        new Thread(new MyRunnable()).start();

        MyCallable myCallable = new MyCallable();
        FutureTask<String> futureTask = new FutureTask<>(myCallable);
        futureTask.run();
        String s = futureTask.get();
        System.out.println(s);

        MyCallable myCallable2 = new MyCallable();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> submit = executorService.submit(myCallable2);
        String s1 = submit.get();
        System.out.println(s1);
        executorService.shutdown();
    }
}

class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": my thread 继承Thread 重写run方法");
    }
}

/**
 * 实现Runnable  实现run方法  无返回值  无异常
 */
class MyRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": MyRunnable实现Runnable接口 重写run方法");
    }
}
/**
 * 实现Callable  实现call方法  有返回值 有异常
 */
class MyCallable implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + ": MyCallable实现Callable接口 重写call方法");
        return "test";
    }
}


