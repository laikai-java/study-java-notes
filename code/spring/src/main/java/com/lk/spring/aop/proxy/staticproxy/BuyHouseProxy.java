package com.lk.spring.aop.proxy.staticproxy;

public class BuyHouseProxy implements BuyHouse{

  BuyHouse buyHouse;

  public BuyHouseProxy(BuyHouse buyHouse){
    this.buyHouse = buyHouse;
  }

  @Override
  public void buyHosue() {
    System.out.println("代理商----开始代理");
    buyHouse.buyHosue();
    System.out.println("代理商----结束代理");
  }
}
