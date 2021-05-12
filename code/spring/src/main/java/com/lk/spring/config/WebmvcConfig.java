package com.lk.spring.config;


import com.lk.spring.interceptor.InterceptorDemo1;
import com.lk.spring.interceptor.InterceptorDemo2;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebmvcConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new InterceptorDemo1()).addPathPatterns("/**");
    registry.addInterceptor(new InterceptorDemo2()).addPathPatterns("/**");
  }
}
