package com.lk.spring.annotation;


public class ManUserserviceImpl implements UserService {

  @Override
  public void save() {
    System.out.println("man---save");
  }
}
