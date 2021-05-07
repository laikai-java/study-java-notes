package com.lk.spring.circulardependencies;

public class B {
    A a;

    public void setA(A a) {
        this.a = a;
        System.out.println("B call setA.");
    }
}
