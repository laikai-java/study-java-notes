package com.lk.spring.aop.proxy.staticproxy;

public class BuyHouseImpl  implements BuyHouse{

  @Override
  public void buyHosue() {
    System.out.println("买房子");
  }
}
