package com.lk.algorithms.limit.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class TokenBucketLimit extends BucketLimit {

    //private static Logger logger = LoggerFactory.getLogger(TokenBucketLimit.class);

    public TokenBucketLimit(int genNumber, int genTime, int maxNumber) {
        this(genNumber, genTime, TimeUnit.SECONDS, maxNumber);
    }

    public TokenBucketLimit(int genNumber, int genTime, TimeUnit timeUnit, int maxNumber) {
        super(maxNumber, timeUnit, genNumber, genTime);
        this.remainingNumber = new AtomicInteger(0);
        new Thread(new GenerateThread()).start();
    }

    public boolean tryAcquire() {
        while (true) {
            int currentStoredNumber = remainingNumber.get();
            if (currentStoredNumber == 0) {
                //logger.info("限流：{}", LocalDateTime.now().toString());
                return false;
            }
            if (remainingNumber.compareAndSet(currentStoredNumber, currentStoredNumber - 1)) {
                return true;
            }
        }
    }

    class GenerateThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                if (remainingNumber.get() == maxNumber) {
                    //logger.info("当前令牌数已满");
                    try { timeUnit.sleep(changeTime); }
                    catch (InterruptedException e) { e.printStackTrace(); }
                } else {
                    int old =  remainingNumber.get();
                    int newValue = old + changeNumber;
                    if (newValue > maxNumber)
                        newValue = maxNumber;
                    remainingNumber.compareAndSet(old, newValue);
                    //logger.info("生成令牌数：{}，当前令牌数：{}", changeNumber, newValue);
                    try { timeUnit.sleep(changeTime); }
                    catch (InterruptedException e) { e.printStackTrace(); }
                }
            }
        }
    }
}
