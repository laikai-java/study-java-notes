package com.lk.basic.construct;

/**
 * ava 程序在执行子类的构造方法之前，如果没有用 super() 来调用父类特定的构造方法，则会调 用父类中“没有参数的构造方法”。
 * 因此，如果父类中只定义了有参数的构造方法，而在子类的构 造方法中又没有用 super() 来调用父类中特定的构造方法，
 * 则编译时将发生错误，因为 Java 程 序在父类中找不到没有参数的构造方法可供执行。
 * 解决办法是在父类里加上一个不做事且没有参 数的构造方法。
 */
public class ConstructDemo {

  public static class Human{

    private String name;
    public String say(){
      return name + ":say";
    }

    public Human(String name){
      this.name = name;
    }
  }

  public static class Man extends Human{
    public Man(String name){
      super(name);
    }
  }

}
