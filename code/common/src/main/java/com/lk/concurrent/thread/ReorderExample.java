package com.lk.concurrent.thread;


/**
 * 重排序对多线程的影响
 * <p>
 * 数据依赖性：写后读 写后写 读后写
 * as-if-serial语义: 不管怎样重排序 单线程程序的执行结果都不会改变 如果操作之间不存在数据依赖关系 会发生重排序
 * 程序顺序规则: happens-before A hb B  B hb C ---> A hb C
 */
public class ReorderExample {
    int a = 0;
    boolean flag = false;

    /**
     * 操作1和2 无数据依赖 可能发生重排序
     */
    public void writer() {
        a = 1;       // 1
        flag = true; // 2
    }

    /**
     * 操作3和4 存在控制依赖关系 编译器和处理器采取 猜测 执行
     * 重排序缓冲
     */
    public void reader() {
        if (flag) {   // 3
            int i = a * a;// 4
            System.out.println(i);
        }
    }
}

class SynchronizedExample {
    int a = 0;
    boolean flag = false;

    public synchronized void writer() { // 获取锁
        a = 1;
        flag = true;
    } // 释放锁

    public synchronized void reader() { // 获取锁
        if (flag) {
            int i = a;

        } // 释放锁
    }
}

