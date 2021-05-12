package com.lk.spring.config;


import com.lk.spring.filter.FilterDemo1;
import com.lk.spring.filter.FilterDemo2;
import java.util.Arrays;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {


  @Bean
  public FilterRegistrationBean<FilterDemo1> filterDemo1(){
    FilterRegistrationBean<FilterDemo1> filterRegistrationBean = new FilterRegistrationBean<>();
    filterRegistrationBean.setFilter(new FilterDemo1());
    filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
    return filterRegistrationBean;
  }

  @Bean
  public FilterRegistrationBean<FilterDemo2> filterDemo2(){
    FilterRegistrationBean<FilterDemo2> filterRegistrationBean = new FilterRegistrationBean<>();
    filterRegistrationBean.setFilter(new FilterDemo2());
    filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
    return filterRegistrationBean;
  }


}
