package com.lk.spring.circulardependencies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class A {
    @Autowired
    B b;

    public void setB(B b) {
        this.b = b;
        System.out.println("A call setB.");
    }
}
