package com.lk.basic.finallytest;


/**
 * finally 测试
 * finally一定会执行吗？
 * 不一定
 */
public class FinallyDemo {
    public static void main(String[] args) {
        daemonThread();
    }

    /**
     * try 之前return finally不会执行
     */
    public static void tryReturnBeforeFinally(int i){

        if (i == 0){
            return;
        }

        try {
            System.out.println("进入try");
        }finally {
            System.out.println("进入finally");
        }
    }

    /**
     * try 之前抛出异常 finally不会执行
     */
    public static void exceptionBeforeTryFinally(int i){

        int j = 1/i;

        try {
            System.out.println("进入try");
        }finally {
            System.out.println("进入finally");
        }
    }

    /**
     * try 之后 退出JVM finally不会执行
     */
    public static void exitJvmBetweenTryAndFinally(){

        try {
            System.out.println("进入try");
            System.exit(0);
        }finally {
            System.out.println("进入finally");
        }
    }

    /**
     * 将线程设置为守护线程 try finally 也可能不会执行
     */
    public static void daemonThread(){
        Thread thread = new Thread(new TryFinally());
        thread.setDaemon(true);
        thread.start();
    }

    public static class TryFinally implements Runnable{
        @Override
        public void run() {
            try {
                System.out.println("进入try");
            }finally {
                System.out.println("进入finally");
            }
        }
    }






}
