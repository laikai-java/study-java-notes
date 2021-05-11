package com.lk.spring;

import com.lk.spring.circulardependencies.A;
import com.lk.spring.circulardependencies.B;
import com.lk.spring.life.C;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClientCircularDependencies {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        A a = context.getBean("a",A.class);
        B b= context.getBean("b",B.class);
        C c= context.getBean("c", C.class);

    }
}
