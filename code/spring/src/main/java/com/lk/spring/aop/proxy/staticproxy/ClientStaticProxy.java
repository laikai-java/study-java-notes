package com.lk.spring.aop.proxy.staticproxy;

/**
 * 静态代理
 */
public class ClientStaticProxy {

  public static void main(String[] args) {
    //目标对象
    BuyHouse buyHouse = new BuyHouseImpl();

    //代理商
    BuyHouseProxy buyHouseProxy = new BuyHouseProxy(buyHouse);
    buyHouseProxy.buyHosue();
  }
}
