package com.lk.basic.comparableandcomparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Comparable接口的类是可以支持和自己比较的，但是其实代码里面Comparable的泛型未必就一定要是Domain，
 * 将泛型指定为String或者指定为其他任何任何类型都可以，只要开发者指定了具体的比较算法就行。
 */
public class ComparableClient {

  public static void main(String[] args) {
    Domain d1 = new Domain("c");
    Domain d2 = new Domain("c");
    Domain d3 = new Domain("b");
    Domain d4 = new Domain("d");
    System.out.println(d1.compareTo(d2));
    System.out.println(d1.compareTo(d3));
    System.out.println(d1.compareTo(d4));

    List<Domain> data = new ArrayList<>();
    data.add(d1);
    data.add(d2);
    data.add(d3);
    data.add(d4);
    System.out.println("排序前"+data);
    Collections.sort(data);
    System.out.println("排序后"+data);

    Domain2 domain2 = new Domain2("a");
    System.out.println(domain2.compareTo("b"));
  }

}
