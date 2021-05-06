package com.lk.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆空间 内存溢出
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true){
            list.add(new OOMObject());
        }
    }
    static class OOMObject{

    }
}
