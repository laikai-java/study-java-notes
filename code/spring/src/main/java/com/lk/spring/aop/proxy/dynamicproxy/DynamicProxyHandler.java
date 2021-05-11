package com.lk.spring.aop.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxyHandler implements InvocationHandler {

  Object object;

  public DynamicProxyHandler(Object object) {
    this.object = object;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println("代理 买房前----");
    Object result = method.invoke(object,args);
    System.out.println("代理 买房后----");
    return result;
  }
}
