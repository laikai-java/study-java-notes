package com.lk.basic.exception;

public class ExcepetionDemo {

  public static void main(String[] args) {
    final int f = f(1);
    System.out.println(f);

  }

  public static void finallyTest() {
    try {
      System.out.println("try");
      System.exit(1);
    } finally {
      System.out.println("finally ----");
    }
  }


  public static int f(int value) {

    try {
      return value * value ;
    } finally {
      if (value == 2) {
        return 0;
      }

    }

  }
}
