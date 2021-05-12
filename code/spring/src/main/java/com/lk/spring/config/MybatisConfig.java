package com.lk.spring.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.lk.spring.business.mapper")
public class MybatisConfig {
}
