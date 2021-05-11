package com.lk.spring.aop.proxy.dynamicproxy;


import com.lk.spring.aop.proxy.staticproxy.BuyHouse;
import com.lk.spring.aop.proxy.staticproxy.BuyHouseImpl;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * jdk
 * cglib
 */
public class DynamicProxyClient {

  public static void main(String[] args) {

    BuyHouse buyHouse = new BuyHouseImpl();

    BuyHouse proxyBuyHouse = (BuyHouse) Proxy.newProxyInstance(BuyHouse.class.getClassLoader(), new
                  Class[]{BuyHouse.class}, new DynamicProxyHandler(buyHouse));
       proxyBuyHouse.buyHosue();

  }

}
