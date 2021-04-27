package com.lk.basic.interfaceandabstract;

public class InterfaceAndAbstractDemo {

  interface MyInterface {

    int a = 0;

    int b = 1;

    void say();

    default void tell() {
      System.out.println("default ");
    }

    static void staticTell() {
      System.out.println(a+"static Tell " + b);
    }

  }

  public abstract class MyAbstract {

    abstract void say();
  }

  public static void main(String[] args) {
    MyInterface.staticTell();
  }


}
