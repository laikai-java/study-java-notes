package com.lk.basic.finaldemo;

import java.util.HashMap;
import java.util.Map;

/**
 * final 修饰变量
 * 基本数据类型 值不能改变
 * 引用数据类型 引用不可变 引用对象的属性可以变
 */
public class FinalVariable {

  public static void main(String[] args) {
    //修饰变量
    final int a = 1;
    //a=20;


    //修饰引用类型
    final Integer integer1 = 20;
    Integer integer2 = 30;

    //integer1 = integer2;


    //修饰map
    final Map<String,String> map = new HashMap<>();
    map.put("1","1");


  }

}
