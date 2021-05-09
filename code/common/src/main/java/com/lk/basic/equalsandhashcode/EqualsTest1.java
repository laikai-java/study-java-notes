package com.lk.basic.equalsandhashcode;

/**
 * 下面根据“类是否覆盖equals()方法”，将它分为2类。
 * (01) 若某个类没有覆盖equals()方法，当它的通过equals()比较两个对象时，实际上是比较两个对象是不是同一个对象。
 *      这时，等价于通过“==”去比较这两个对象。
 * (02) 我们可以覆盖类的equals()方法，来让equals()通过其它方式比较两个对象是否相等。
 *      通常的做法是：若两个对象的内容相等，则equals()方法返回true；否则，返回fasle。
 */
public class EqualsTest1 {

  public static void main(String[] args) {
    // 新建2个相同内容的Person对象，
    // 再用equals比较它们是否相等
    Person p1 = new Person("eee", 100);
    Person p2 = new Person("eee", 100);
    System.out.printf("%s\n", p1.equals(p2));
  }

  private static class Person {
    int age;
    String name;

    public Person(String name, int age) {
      this.name = name;
      this.age = age;
    }

    @Override
    public String toString() {
      return name + " - " +age;
    }
  }
}
