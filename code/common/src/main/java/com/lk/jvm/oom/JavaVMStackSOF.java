package com.lk.jvm.oom;

/**
 * StackOverFlowError
 * -Xss: stack start  减少栈内存容量
 */
public class JavaVMStackSOF {
    private int stackLength=1;
    public void stackLeak(){
        stackLength++;
        stackLeak();
    }
    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length :" + oom.stackLength);
            throw e;
        }

    }
}
