package com.lk.basic.equalsandhashcode;

public class EqualsTest2 {

  public static void main(String[] args) {
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
