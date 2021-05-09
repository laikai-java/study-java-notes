package com.lk.basic.callbyvalue;

/**
 * ⼀个⽅法不能修改⼀个基本数据类型的参数
 */
public class CallByValueDemo1 {
    public static void main(String[] args) {
        int num1 = 10;
        int num2 = 20;

        swap(num1,num2);
        System.out.println("main中 num1= " + num1);
        System.out.println("main中 num2= " + num2);
    }


    public static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
        System.out.println("swap中 a= " + a);
        System.out.println("swap中 b= " + b);
    }
}
