package com.lk.basic.override;

import java.util.concurrent.CompletionException;

/**
 * 重写
 * “两同”即方法名相同、形参列表相同;
 *
 * “两小”指的是子类方法返回值类型应比父类方法返回值类型更小或相等，
 * 子类方法声明抛出 的异常类应比父类方法声明抛出的异常类更小或相等;
 *
 * “一大”指的是子类方法的访问权限应比父类方法的访问权限更大或相等。
 */
public class OverRideDemo {

  public static class Hero {

    protected String name() throws RuntimeException{
      return "超级英雄";
    }

    public Hero hero(){
      return new Hero();
    }
  }

  public static class SuperMan extends Hero {

    @Override
    public String name() throws CompletionException {
      return "超人";
    }

    @Override
    public Hero hero() {
      return new SuperMan();
    }
  }

}
