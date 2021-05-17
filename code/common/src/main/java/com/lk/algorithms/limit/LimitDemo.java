package com.lk.algorithms.limit;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 使用guava中RateLimiter 进行限流
 */
public class LimitDemo {

    public static ConcurrentHashMap<String, RateLimiter> resourceRateLimiter = new ConcurrentHashMap<>();


    static{
        createResourceRateLimiter("order",50);
    }

    public static void createResourceRateLimiter(String resource,double qps){
        if (resourceRateLimiter.contains(resource)){
                resourceRateLimiter.get(resource).setRate(qps);
        }else{
            RateLimiter rateLimiter = RateLimiter.create(qps);
            resourceRateLimiter.putIfAbsent(resource,rateLimiter);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5000; i++) {
            new Thread(() -> {
                if (resourceRateLimiter.get("order").tryAcquire(10, TimeUnit.MILLISECONDS)){
                    System.out.println("执行业务逻辑");
                }else{
                    System.out.println("限流");
                }
            }, String.valueOf(i)).start();
        }
    }
}
