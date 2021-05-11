package com.lk.spring.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {


  @GetMapping("/demo/{id}")
  public Object demo(@PathVariable("id") String id){
    if ("1".equals(id)){
      throw new RuntimeException("id错误");
    }
    return "hello world";
  }

}
