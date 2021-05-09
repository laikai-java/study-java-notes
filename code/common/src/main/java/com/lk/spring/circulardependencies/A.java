package com.lk.spring.circulardependencies;

public class A {
    B b;

    public void setB(B b) {
        this.b = b;
        System.out.println("A call setB.");
    }
}
