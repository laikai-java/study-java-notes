package com.lk.spring.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LogAspect {


  @Pointcut("execution(* com.lk.spring.controller.DemoController.*(..))")
  public void pointCut(){
  }

  @Before("pointCut()")
  public void before(JoinPoint joinPoint){
    log.info("前置通知-----");
  }

  @AfterReturning("pointCut()")
  public void afterReturning(JoinPoint joinPoint){
    log.info("返回通知-----");
  }

  @AfterThrowing("pointCut()")
  public void afterThrowing(JoinPoint joinPoint){
    log.info("异常通知-----");
  }

  @After("pointCut()")
  public void after(JoinPoint joinPoint){
    log.info("后置通知-----");
  }

  @Around("pointCut()")
  public Object around(ProceedingJoinPoint joinPoint){
    Object proceed = null;
    try {
      log.info("环绕通知里面的前置通知");
      proceed = joinPoint.proceed();
      log.info("环绕通知里面的后置通知");
    } catch (Throwable throwable) {
      throwable.printStackTrace();
      log.info("环绕通知里面的异常通知");
    }
    return proceed;
  }


}
