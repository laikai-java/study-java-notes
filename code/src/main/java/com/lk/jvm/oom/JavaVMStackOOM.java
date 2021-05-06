package com.lk.jvm.oom;

public class JavaVMStackOOM {
    private void dontStop() {
        while (true) {
        }
    }
    public void stackLeakByThread(){
        while(true){
            new Thread(this::dontStop).start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        try {
            oom.stackLeakByThread();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
