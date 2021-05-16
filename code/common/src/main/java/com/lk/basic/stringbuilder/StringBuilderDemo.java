package com.lk.basic.stringbuilder;

import java.util.concurrent.TimeUnit;

/**
 * StringBuilder 为什么线程不安全
 */
public class StringBuilderDemo {
    public static void main(String[] args) {

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j=0; j < 1000;j++){
                    stringBuilder.append("a");
                }
            }, String.valueOf(i)).start();
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(stringBuilder.length());
    }
}
