package com.lk.jvm.oom;

/**
 * StackOverFlowError
 * -Xss: stack start  减少栈内存容量
 */
public class JavaVMStackSOF2 {
    private int stackLength = 1;

    public void stackLeak() {
        long useused1, useused2, useused3, useused4, useused5, useused6, useused7, useused8, useused9,
                useused10, useused12, useused13, useused14, useused15, useused16, useused17, useused18, useused19,
                useused20, useused22, useused23, useused24, useused25, useused26, useused27, useused28, useused29,
                useused30, useused32, useused33, useused34, useused35, useused36, useused37, useused38, useused39;
        stackLength++;
        stackLeak();
        useused1 = useused2 = useused3 = useused4 = useused5 = useused6 = useused7 = useused8 = useused9 =
                useused10 = useused12 = useused13 = useused14 = useused15 = useused16 = useused17 = useused18 = useused19 =
                        useused20 = useused22 = useused23 = useused24 = useused25 = useused26 = useused27 = useused28 = useused29 =
                                useused30 = useused32 = useused33 = useused34 = useused35 = useused36 = useused37 = useused38 = useused39 = 0;
    }

    public static void main(String[] args) {
        JavaVMStackSOF2 oom = new JavaVMStackSOF2();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length :" + oom.stackLength);
            throw e;
        }

    }
}
