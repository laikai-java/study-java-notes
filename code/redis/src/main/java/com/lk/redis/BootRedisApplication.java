package com.lk.redis;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootRedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootRedisApplication.class,args);
        System.out.println("启动成功");
    }
}
