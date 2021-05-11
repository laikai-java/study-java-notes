package com.lk.spring.life;



import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * bean的生命周期
 * 实例化
 * 填充属性
 * 1 如果实现了xxxxAware 接口 执行setXxxx方法
 * 2 如果实现了 BeanPostProcessor接口 执行postProcessBeforeInitialization()
 * 3 如果实现了InitializingBean接口 执行afterPropertiesSet方法
 * 4 如果执行了init-method方法  执行
 * 5 如果实现了 BeanPostProcessor接口 执行postProcessAfterInitialization()
 *
 *销毁时shutdown
 *
 * 1 实现了 DestructionAwareBeanPostProcessor  接口  postProcessBeforeDestruction
 * 2 实现了 DisposableBean destroy
 * 3 自定义的destroy-method
 *
 *
 *
 * --------C 构造器使用---------------
 * --------实现了xxxxAware---------------
 * name： c
 * c----BeanPostProcessor接口 执行postProcessBeforeInitialization()-----
 * -----InitializingBean afterPropertiesSet------
 * ---------init-method star----------
 * c----BeanPostProcessor接口 执行postProcessAfterInitialization()-----
 *
 *
 * c--DestructionAwareBeanPostProcessor--销毁了
 * c----DisposableBean destroy（）--- 销毁
 * c 自定义的destroy-method
 *
 */

public class C implements BeanNameAware, InitializingBean, DisposableBean {

  public C(){
    System.out.println("--------C 构造器使用---------------");
  }

  public void init(){
    System.out.println("---------init-method star----------");
  }

  public void myDestroy(){
    System.out.println("c 自定义的destroy-method ");
  }

  @Override
  public void setBeanName(String name) {
    System.out.println("--------实现了xxxxAware---------------");
    System.out.println("name： " + name);
  }



  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println("-----InitializingBean afterPropertiesSet------");
  }

  @Override
  public void destroy() throws Exception {
    System.out.println("c----DisposableBean destroy（）--- 销毁");
  }
}
