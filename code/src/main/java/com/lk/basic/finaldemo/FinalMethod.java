package com.lk.basic.finaldemo;


/**
 * final 修饰方法 无法被重写
 */
public class FinalMethod {

  public final void say(){
    System.out.println("father");
  }

  public static void main(String[] args) {
    Son son = new Son();
    son.say();
  }


}
class Son extends FinalDemo{

  public  void say(){
    System.out.println("son");
  }
}
