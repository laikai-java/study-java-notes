package com.lk.basic.callbyvalue;

/**
 * 很多程序设计语⾔（特别是，C++和 Pascal)提供了两种参数传递的⽅式：值调⽤和引⽤调⽤。
 * 有些程序员（甚⾄本书的作者）认为 Java 程序设计语⾔对对象采⽤的是引⽤调⽤，实际上，这
 * 种理解是不对的。由于这种误解具有⼀定的普遍性，所以下⾯给出⼀个反例来详细地阐述⼀下这
 * 个问题。
 *
 * ⼀个⽅法不能让对象参数引⽤⼀个新的对象。
 */
public class CallByValueDemo3 {
    public static void main(String[] args) {
        Student s1 = new Student("小张");
        Student s2 = new Student("小李");

        swap(s1,s2);
        System.out.println("s1:" + s1.getName());//小张
        System.out.println("s2:" + s2.getName());//小李
    }

    public static void swap( Student x, Student y){
        Student temp = x;
        x = y;
        y = temp;
        System.out.println("x:" + x.getName());//小李
        System.out.println("y:" + y.getName());//小张
    }


    public static class Student{
        private String name;

        public String getName() {
            return name;
        }

        public Student(String name) {
            this.name = name;
        }
    }
}
