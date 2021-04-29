package com.lk.concurrent.thread;

/**
 * 双重校验锁实现对象的单例
 */
public class Singleton {

    /**
     * 第一步构造器私有化
     */
    private Singleton(){

    }

    /**
     * 第二步 创建对象
     */
    private volatile static Singleton instance;

    /**
     * 第三步创建对外暴露获取该实例的方法
     */
    public static Singleton getInstance(){
        if (instance == null){
            synchronized (Singleton.class){
                if (instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
