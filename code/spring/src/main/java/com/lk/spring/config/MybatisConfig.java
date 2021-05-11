package com.lk.spring.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.lk.generator.mapper")
public class MybatisConfig {
}
