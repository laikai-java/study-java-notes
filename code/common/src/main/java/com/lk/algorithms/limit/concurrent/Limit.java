package com.lk.algorithms.limit.concurrent;

/**
 * 限流接口
 */
public interface Limit {

    /**
     * 尝试将计数器加1，返回为true表示能够正常访问接口，false表示访问受限
     */
    boolean tryAcquire();
}
