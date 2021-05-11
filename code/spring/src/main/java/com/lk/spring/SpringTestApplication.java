package com.lk.spring;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringTestApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(SpringTestApplication.class);
    System.out.println("启动成功");
  }

}
