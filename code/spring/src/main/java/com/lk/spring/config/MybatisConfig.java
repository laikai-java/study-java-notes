package com.lk.spring.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {"com.lk.spring.business.mapper","com.lk.spring.generator.mapper"})
public class MybatisConfig {
}
