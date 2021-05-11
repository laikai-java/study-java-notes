package com.lk.spring.annotation;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Human {

  @Autowired
  @Qualifier("womanUserserviceImpl")
  UserService woman;

  @Resource
  UserService manUserserviceImpl;

  @PostConstruct
  public void init(){
    woman.save();
    manUserserviceImpl.save();
  }

}
