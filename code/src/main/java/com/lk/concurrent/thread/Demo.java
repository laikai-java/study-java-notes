package com.lk.concurrent.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 线程的创建
 * Thread类和Runnable接口
 *
 * Callable、Future与FutureTask 有返回值
 */
public class Demo {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
//    Thread thread = new MyThread();
//    thread.start();
//
//    MyRunnable myRunnable = new MyRunnable();
//    Thread runnable = new Thread(myRunnable);
//    runnable.start();
//
//    new Thread(()->{
//      System.out.println("my lambdas runnable");
//    }).start();;

    // callable
    MyCallable myCallable = new MyCallable();
    FutureTask<Integer> futureTask = new FutureTask<>(myCallable);
    futureTask.run();
    final Integer integer = futureTask.get();
    System.out.println(integer);

  }

  /**
   * 我们在程序里面调用了start()方法后，虚拟机会先为我们创建一个线程，然后等到这个线程第一次得到时间片时再调用run()方法。
   * 注意不可多次调用start()方法。在第一次调用start()方法后，再次调用start()方法会抛出IllegalThreadStateException异常。
   */
  public static class MyThread extends  Thread{
    @Override
    public void run() {
      System.out.println("my thread");
    }
  }

  public static class MyRunnable implements Runnable{
    @Override
    public void run() {
      System.out.println("my Runnable");
    }
  }

  public static class MyCallable implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
      System.out.println("my callable");
      return 1;
    }
  }
}
