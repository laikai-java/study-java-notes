package com.lk.concurrent.multithread;

import java.util.concurrent.TimeUnit;

/**
 * 守护线程  当程序中没有非守护线程时 JVM 就会退出
 * Daemon属性需要在启动线程之前设置，不能在启动线程之后设置
 * Daemon线程被用作完成支持性工作，但是在Java虚拟机退出时Daemon线程中的finally块并不一定会执行
 */
public class DaemonDemo {
    public static void main(String[] args) {

        Thread thread = new Thread(new DaemonRunner());

        thread.setDaemon(true);
        thread.start();
    }

    public static class DaemonRunner implements Runnable{

        @Override
        public void run() {
            System.out.println("进入");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println("守护线程finally 执行");
            }
        }
    }
}
