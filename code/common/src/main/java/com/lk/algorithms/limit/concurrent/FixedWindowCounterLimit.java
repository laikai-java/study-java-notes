package com.lk.algorithms.limit.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 固定窗口计数器限流
 */
public class FixedWindowCounterLimit extends CounterLimit {

    /**
     * 计数器
     */
    private AtomicInteger counter = new AtomicInteger(0);

    public FixedWindowCounterLimit(int limitCount, long limitTime) {
        this(limitCount, limitTime, TimeUnit.SECONDS);
    }

    public FixedWindowCounterLimit(int changeNumber, long changeTime, TimeUnit timeUnit) {
        super(changeNumber, changeTime, timeUnit);
    }


    /**
     * 固定窗口计数:
     * 如果请求数 达到了阈值 限制返回false
     * 如果没有达到   cas 计数器增加一
     */
    @Override
    public boolean tryAcquire() {
        while (true) {
            if (limited) {
                return false;
            } else {
                int currentCount = counter.get();
                if (currentCount == changeNumber) {
                    limited = true;
                    return false;
                }
                if (counter.compareAndSet(currentCount, currentCount + 1)) {
                    return true;
                }
            }
        }
    }

    /**
     * 重置计数器线程
     */
    class CounterResetThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    timeUnit.sleep(changeTime);
                    counter.compareAndSet(changeNumber, 0); // 计数器清零
                    limited = false; // 修改当前状态为不受限
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
