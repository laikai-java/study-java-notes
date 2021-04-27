package com.lk.basic.equalsandhashcode;

/**
 * == : 它的作用是判断两个对象的地址是不是相等。即，判断两个对象是不试同一个对象。
 *
 * equals() : 它的作用也是判断两个对象是否相等。但它一般有两种使用情况：
*               情况1，类没有覆盖equals()方法。则通过equals()比较该类的两个对象时，等价于通过“==”比较这两个对象。
 *              情况2，类覆盖了equals()方法。一般，我们都覆盖equals()方法来两个对象的内容相等；
 *              若它们的内容相等，则返回true(即，认为这两个对象相等)。
 */
public class EqualsTest3 {

  public static void main(String[] args) {
    // 新建2个相同内容的Person对象，
    // 再用equals比较它们是否相等
    Person p1 = new Person("eee", 100);
    Person p2 = new Person("eee", 100);
    System.out.printf("p1.equals(p2) : %s\n", p1.equals(p2));
    System.out.printf("p1==p2 : %s\n", p1==p2);
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

    @Override
    public boolean equals(Object obj) {
      if (obj==null){
        return false;
      }

      if (this == obj){
        return true;
      }

      if (this.getClass() != obj.getClass()){
        return false;
      }

      Person person = (Person) obj;
      return this.age == person.age && this.name.equals(person.name);
    }
  }
}
