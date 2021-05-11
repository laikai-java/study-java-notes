package com.lk.spring.annotation;


public class WomanUserserviceImpl implements UserService {

  @Override
  public void save() {
    System.out.println("woman---save");
  }
}
