package com.lk.concurrent.thread;


public class VolatileDemo{
    public static void main(String[] args) {
        VolatileExample volatileExample = new VolatileExample();
        new Thread(() -> {
            volatileExample.writer();
        }, "A").start();

        new Thread(() -> {
            volatileExample.reader();
        }, "B").start();
    }
}

class VolatileFeaturesExample {

    volatile long vl = 0L; // 使用volatile声明64位的long型变量

    public void set(long l) {
        vl = l; // 单个volatile变量的写
    }

    public void getAndIncrement() {
        vl++; // 复合（多个）volatile变量的读/写
    }

    public long get() {
        return vl; // 单个volatile变量的读
    }

}


class VolatileFeaturesExample2 {

    long vl = 0L; // 64位的long型普通变量

    public synchronized void set(long l) { // 对单个的普通变量的写用同一个锁同步
        vl = l;
    }

    public void getAndIncrement() { // 普通方法调用
        long temp = get(); // 调用已同步的读方法
        temp += 1L; // 普通写操作
        set(temp); // 调用已同步的写方法
    }

    public synchronized long get() { // 对单个的普通变量的读用同一个锁同步
        return vl;
    }
}

/**
 * 线程A写一个volatile变量，随后线程B读这个volatile变量，这个过程实质上是线程A通过主内存向线程B发送消息。
 * 在读线程B读一个volatile变量后，写线程A在写这个volatile变量之前所有可见的共享变量的值都将立即变得对读线程B可见。
 */
class VolatileExample {
    int a = 0;
    volatile boolean flag = false;

    public void writer() {
        a = 1;               // 1
        flag = true;         // 2
    }

    public void reader() {
        if (flag) {             // 3
            int i = a;          // 4
            System.out.println(i);
        }
    }
}


