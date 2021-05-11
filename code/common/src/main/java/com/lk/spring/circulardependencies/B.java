package com.lk.spring.circulardependencies;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class B {

    A a2;

    @Resource
    A a;
    @Autowired
    public void setA(A a) {
        this.a2 = a;
        System.out.println("B call setA.");
    }

    public B(){
        System.out.println("b  start");
    }
}
