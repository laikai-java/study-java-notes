package com.lk.concurrent.thread;

import java.util.concurrent.TimeUnit;

/**
 * 使用 synchronized
 * 1.修饰实例⽅法: 作⽤于当前对象实例加锁，进⼊同步代码前要获得 当前对象实例的锁
 * <p>
 * 2.修饰静态⽅法: 也就是给当前类加锁，会作⽤于类的所有对象实例 ，进⼊同步代码前要获得 当
 * 前 class 的锁。因为静态成员不属于任何⼀个实例对象，是类成员（ static 表明这是该类的⼀个
 * 静态资源，不管 new 了多少个对象，只有⼀份）。所以，如果⼀个线程 A 调⽤⼀个实例对象的
 * ⾮静态 synchronized ⽅法，⽽线程 B 需要调⽤这个实例对象所属类的静态 synchronized ⽅法，
 * 是允许的，不会发⽣互斥现象，因为访问静态 synchronized ⽅法占⽤的锁是当前类的锁，⽽访
 * 问⾮静态 synchronized ⽅法占⽤的锁是当前实例对象锁。
 * <p>
 * 3.修饰代码块 ：指定加锁对象，对给定对象/类加锁。 synchronized(this|object) 表示进⼊同步代码
 * 库前要获得给定对象的锁。 synchronized(类.class) 表示进⼊同步代码前要获得 当前 class 的锁
 *
 *
 * 1、静态方法的锁和实例方法的锁，默认是不同的对象锁
 *
 * 2、静态方法加锁，能和该类中所有用synchronized修饰静态方法的相互互斥，和未用synchronized修饰的静态方法不互斥
 *
 * 3、静态方法锁实际是对类对象加锁，实例方法加锁实际是对实例对象加锁
 *
 * 底层原理
 *
 * 在执⾏ monitorenter 时，会尝试获取对象的锁，如果锁的计数器为 0 则表示锁可以被获取，获取
 * 后将锁计数器设为 1 也就是加 1。
 * 在执⾏ monitorexit 指令后，将锁计数器设为 0，表明锁被释放。如果获取对象锁失败，那当前
 * 线程就要阻塞等待，直到锁被另外⼀个线程释放为⽌。
 *
 * synchronized 同步语句块的实现使⽤的是 monitorenter 和 monitorexit 指令，其中
 * monitorenter 指令指向同步代码块的开始位置， monitorexit 指令则指明同步代码块的结束位置。
 * synchronized 修饰的⽅法并没有 monitorenter 指令和 monitorexit 指令，取得代之的确实是
 * ACC_SYNCHRONIZED 标识，该标识指明了该⽅法是⼀个同步⽅法。
 * 不过两者的本质都是对对象监视器 monitor 的获取。
 */
public class SynchronizedDemo {

    public static void main(String[] args) {
        Sys sys1 = new Sys();
        Sys sys2 = new Sys();

       /* System.out.println("调用同一对象的同步方法 会堵塞获取锁");
        new Thread(() -> {
            sys1.method();
        }, "A调用sys1的method").start();
        new Thread(() -> {
            sys1.method();
        }, "B调用sys1的method").start();*/

        /*System.out.println("调用不同对象的同一方法 不会堵塞");
        new Thread(() -> {
            sys1.method();
        }, "调用sys1的method").start();
        new Thread(() -> {
            sys2.method();
        }, "调用sys2的method").start();*/

       /* new Thread(() -> {
            Sys.method2();
        }, "调用静态方法method2").start();
        new Thread(() -> {
            Sys.method3();
        }, "调用静态方法method3").start();*/

       /* new Thread(() -> {
            Sys.method2();
        }, "调用静态方法method2").start();
        new Thread(() -> {
            sys1.method();
        }, "调用对象方法method").start();*/

        /*new Thread(() -> {
          synchronized (sys1){
              System.out.println(Thread.currentThread().getName() + "获取sys1对象锁");
              try {
                  TimeUnit.SECONDS.sleep(3);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              System.out.println(Thread.currentThread().getName() + "释放sys1对象锁");
          }
        }, "对sys1加锁 并sleep").start();
        new Thread(() -> {
            synchronized (sys1){
                System.out.println(Thread.currentThread().getName() + "获取sys1对象锁");
                System.out.println(Thread.currentThread().getName() + "释放sys1对象锁");
            }
        }, "对sys1加锁").start();
*/


        new Thread(() -> {
            synchronized (Sys.class){
                System.out.println(Thread.currentThread().getName() + "获取sys1对象锁");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "释放sys1对象锁");
            }
        }, "对Sys类加锁 并sleep").start();
        new Thread(() -> {
            synchronized (Sys.class){
                System.out.println(Thread.currentThread().getName() + "获取sys1对象锁");
                System.out.println(Thread.currentThread().getName() + "释放sys1对象锁");
            }
        }, "对Sys类加锁").start();






    }

    public static class Sys {
        /**
         * 修饰实例⽅法
         */
        public synchronized void method() {
            System.out.println(Thread.currentThread().getName()  + "获取锁");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()  + "释放锁");
        }

        /**
         * 修饰静态⽅法
         */
        public static synchronized void method2() {
            System.out.println(Thread.currentThread().getName()  + "获取锁");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()  + "释放锁");
        }
        /**
         * 修饰静态⽅法
         */
        public static synchronized void method3() {
            System.out.println(Thread.currentThread().getName()  + "获取锁");
            System.out.println(Thread.currentThread().getName()  + "释放锁");
        }

        /**
         * 修饰代码块
         */
        public void method4(){
            synchronized (this){
                System.out.println("代码块");
            }
        }



    }

}
