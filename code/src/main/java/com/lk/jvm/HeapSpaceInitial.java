package com.lk.jvm;

/**
 * -Xms 用来设置堆空间（年轻代+老年代）的初始内存大小
 * -X：是jvm运行参数
 * ms：memory start
 * -Xmx：用来设置堆空间（年轻代+老年代）的最大内存大小
 * mx：memory max
 *
 * -XX:+PrintGCDetails 打印gc信息
 */
public class HeapSpaceInitial {
    public static void main(String[] args) {
        // 返回Java虚拟机中的堆内存总量
        // 因为eden 区 与 old区比例 1:5
        // from to 1 1
        //所以 from : to : old = 1:1:8
        //因为from和to只能同时存在一个 所以 可用堆内存总量为9/10总-Xmx
        long initialMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        // 返回Java虚拟机试图使用的最大堆内存
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        System.out.println("-Xms:" + initialMemory + "M");
        System.out.println("-Xmx:" + maxMemory + "M");

    }
}
