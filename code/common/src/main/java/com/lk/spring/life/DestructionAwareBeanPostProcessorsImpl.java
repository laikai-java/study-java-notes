package com.lk.spring.life;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;

public class DestructionAwareBeanPostProcessorsImpl implements DestructionAwareBeanPostProcessor {

  @Override
  public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
    System.out.println(beanName + "--DestructionAwareBeanPostProcessor--销毁了"  );
  }
}
