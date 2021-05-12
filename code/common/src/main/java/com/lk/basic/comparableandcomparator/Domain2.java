package com.lk.basic.comparableandcomparator;

/**
 * Comparable可以认为是一个内比较器，实现了Comparable接口的类有一个特点，就是这些类是可以和自己比较的，
 *  * 至于具体和另一个实现了Comparable接口的类如何比较，则依赖compareTo方法的实现。
 *  *
 *  * 如果add进入一个Collection的对象想要Collections的sort方法帮你自动进行排序的话，
 *  * 那么这个对象必须实现Comparable接口。compareTo方法的返回值是int，有三种情况：
 *  *    比较者大于被比较者，返回正整数
 *  *    比较者等于被比较者，返回0
 *  *    比较者小于被比较者，返回负整数
 */
public class Domain2 implements Comparable<String> {

  private String str;

  public Domain2(String str) {
    this.str = str;
  }

  @Override
  public int compareTo(String str) {
    if (this.str.compareTo(str) > 0) {
      return 1;
    } else if (this.str.compareTo(str) == 0) {
      return 0;
    } else {
      return -1;
    }
  }

  public String getStr() {
    return str;
  }

  @Override
  public String toString() {
    return str;
  }
}