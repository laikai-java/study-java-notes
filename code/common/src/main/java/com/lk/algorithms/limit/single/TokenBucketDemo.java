package com.lk.algorithms.limit.single;

public class TokenBucketDemo {
    /**
     * 时间刻度
     */
    private static long time = System.currentTimeMillis();

    /**
     * 创建token的速率
     */
    private static int createTokenRate = 3;
    /**
     * 桶的大小
     */
    private static int size = 10;
    /**
     * 当前令牌数
     */
    private static int tokens = 0;

    public static boolean grant() {
        long now = System.currentTimeMillis();
        //在这段时间需要产生的令牌数量
        int in = (int) ((now - time) / 50 * createTokenRate);
        tokens = Math.min(size,tokens + in);
        time = now;
        if (tokens > 0){
            --tokens;
            return true;
        }

        return false;
    }
}
