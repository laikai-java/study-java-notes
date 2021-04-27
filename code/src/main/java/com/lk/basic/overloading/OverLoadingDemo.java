package com.lk.basic.overloading;

/**
 * 重写
 * 发生在同一个类中，方法名必须相同，参数类型不同、个数不同、顺序不同，方法返回值和访问
 * 修饰符可以不同。
 * 方法签名： 方法名加参数类型
 */
public class OverLoadingDemo {

  private void method(){

  }

  private void method(int a){

  }

  private void method(int a,int b){

  }

  public int method(String a){
    return 0;
  }
}
