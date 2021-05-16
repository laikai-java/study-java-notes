package com.lk.basic.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 一边遍历 一边删除
 *  不能在foreach中删除 因为使用iterator.next 获取下一个元素 且使用了list.remove方法删除元素 报错CME
 *
 *
 *  正确做法
 *      使用Iterator的remove()方法
 *
 *      使用for循环正序遍历
 *
 *      使用for循环倒序遍历
 */
public class SafeDeleteForeachListDemo {
    public static void main(String[] args) {
        forNegativeRemove();
    }
    /**
     * 使用for循环倒序遍历
     */
    public static void forNegativeRemove(){
        List<String> platformList = new ArrayList<>();
        platformList.add("博客园");
        platformList.add("博客园");
        platformList.add("CSDN");
        platformList.add("掘金");
        for (int i = platformList.size() - 1; i >= 0; i--) {
            final String s = platformList.get(i);
            if ("博客园".equals(s)){
                platformList.remove(i);
            }
        }

        System.out.println(platformList);
    }

    /**
     * 使用for循环正序遍历
     */
    public static void forPositiveRemove(){
        List<String> platformList = new ArrayList<>();
        platformList.add("博客园");
        platformList.add("博客园");
        platformList.add("CSDN");
        platformList.add("掘金");
        for (int i = 0; i < platformList.size(); i++) {
            final String s = platformList.get(i);
            if ("博客园".equals(s)){
                platformList.remove(i);
                //进行下标修正
                i--;
            }
        }

        System.out.println(platformList);
    }

    public static void iteratorRemove(){
        List<String> platformList = new ArrayList<>();
        platformList.add("博客园");
        platformList.add("博客园");
        platformList.add("CSDN");
        platformList.add("掘金");

        Iterator<String> iterator = platformList.iterator();
        while(iterator.hasNext()){
            String next = iterator.next();
            if ("博客园".equals(next)){
                iterator.remove();
            }
        }
        System.out.println(platformList);
    }
    public static void foreachRemove(){
        List<String> platformList = new ArrayList<>();
        platformList.add("博客园");
        platformList.add("CSDN");
        platformList.add("掘金");

        /**
         * 不行!!!!!!!
         */
        for (String platform : platformList) {
            if (platform.equals("博客园")) {
                platformList.remove(platform);
            }
        }

        System.out.println(platformList);
    }
}
