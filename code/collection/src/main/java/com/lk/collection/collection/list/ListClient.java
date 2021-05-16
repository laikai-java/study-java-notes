package com.lk.collection.collection.list;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * list 测试
 */
public class ListClient {
    public static void main(String[] args) {
        testGrow();

    }

    public static void testGrow(){
        /**
         * 当初始化大小为奇数时 扩容时大约为1.5倍
         */
        ArrayList<Integer> list = new ArrayList<>(11);
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }

    }

    public static ArrayList<String> getArrayList(){
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(""+i);
        }
        return list;
    }

    public static LinkedList<String> getLinkedList(){
        LinkedList<String> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(""+i);
        }
        return list;
    }


    public static void testIterator(){

        ArrayList<String> list = getArrayList();
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        LinkedList<String> linkedList = getLinkedList();
        Iterator<String> iterator1 = linkedList.iterator();
        while (iterator1.hasNext()) {
            System.out.println(iterator1.next());
        }
        /**
         * 内部使用迭代器进行循环
         * 不能一边循环 一边删除
         */
        for(String i : list){
            list.remove(i);
        }
    }


    public static void testRemove(){
        ArrayList<String> list = getArrayList();
        list.remove(0);
    }

    public static void testAdd(){
        ArrayList<String> list = getArrayList();
        list.add("a");
        list.add(0,"a");

    }
}
