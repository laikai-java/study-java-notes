package com.lk.spring.config;


import com.lk.spring.annotation.ManUserserviceImpl;
import com.lk.spring.annotation.UserService;
import com.lk.spring.annotation.WomanUserserviceImpl;
import com.lk.spring.life.BeanPostProcessorImpl;
import com.lk.spring.life.C;
import com.lk.spring.life.DestructionAwareBeanPostProcessorsImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

  @Bean(initMethod = "init",destroyMethod = "myDestroy")
  public C c(){
    return new C();
  }

  @Bean
  public BeanPostProcessorImpl beanPostProcessor2(){
    return new BeanPostProcessorImpl();
  }

  @Bean
  public DestructionAwareBeanPostProcessorsImpl destructionAwareBeanPostProcessors(){
    return new DestructionAwareBeanPostProcessorsImpl();
  }

  @Bean
  public UserService manUserserviceImpl(){
    return new ManUserserviceImpl();
  }

  @Bean
  public UserService womanUserserviceImpl(){
    return new WomanUserserviceImpl();
  }


}
