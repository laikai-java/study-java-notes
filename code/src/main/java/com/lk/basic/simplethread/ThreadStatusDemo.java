package com.lk.basic.simplethread;


/**
 * 线程的状态 NEW 创建一个线程 还未开启 RUNNABLE  可运行线程的线程状态。 处于可运行状态的线程正在Java虚拟机中执行，但它可能正在等待来自操作系统（例如处理器）的其他资源
 * BLOCKED   阻塞中 等待锁的释放 WAITING   等待 TIME_WAITING  超时等待 TERMINATED  终止
 */
public class ThreadStatusDemo {

  public static void main(String[] args) {
    new Thread(new TimeWaiting(), "TimeWaitingThread").start();
    new Thread(new Waiting(), "WaitingThread").start();
    // 使用两个Blocked线程，一个获取锁成功，另一个被阻塞
    new Thread(new Blocked(), "BlockedThread-1").start();
    new Thread(new Blocked(), "BlockedThread-2").start();
  }

  /**
   * 该线程不断地进行睡眠
   */
  public static class TimeWaiting implements Runnable {

    @Override
    public void run() {
      while (true) {
        SleepUtils.second(100);
      }
    }
  }

  /**
   * 该线程在Waiting.class实例上等待
   */
  public static class Waiting implements Runnable {

    @Override
    public void run() {
      while (true) {
        synchronized (Waiting.class) {
          try {
            Waiting.class.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }

  /**
   * 该线程在Blocked.class实例上加锁后，不会释放该锁
   */
  public static class Blocked implements Runnable {

    @Override
    public void run() {
      synchronized (Blocked.class) {
        SleepUtils.second(100);
      }
    }
  }

}
