package com.lk.spring.circulardependencies;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClientCircularDependencies {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        A a = context.getBean("a",A.class);
        B b= context.getBean("b",B.class);

    }
}
