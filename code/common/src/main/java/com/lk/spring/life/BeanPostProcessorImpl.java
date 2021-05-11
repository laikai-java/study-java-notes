package com.lk.spring.life;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;


public class BeanPostProcessorImpl  implements BeanPostProcessor {
  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName)
      throws BeansException {
    if (beanName.equals("c")){
    System.out.println(beanName + "----BeanPostProcessor接口 执行postProcessBeforeInitialization()-----");}
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    if (beanName.equals("c")){
    System.out.println(beanName + "----BeanPostProcessor接口 执行postProcessAfterInitialization()-----");}
    return bean;
  }
}
