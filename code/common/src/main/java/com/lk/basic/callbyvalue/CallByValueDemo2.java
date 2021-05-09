package com.lk.basic.callbyvalue;

/**
 * 实现⼀个改变对象参数状态的⽅法并不是⼀件难事。
 * 理由很简单，⽅法得到的是对象引⽤的拷⻉，对象引⽤及其他的拷⻉同时引⽤同⼀个对象。
 */
public class CallByValueDemo2 {
    public static void main(String[] args) {
       int[] array = new int[]{1,2,3,4,5,6};
       change(array);
        System.out.println(array[0]);
    }


    public static void change(int[] array) {
        //将数组的第一个元素变为0
        array[0] = 0;
    }
}
