/**
 * 限流
 * 其次，应对大流量的一些常见手段是什么？
 * 缓存：说白了，就是让数据尽早进入缓存，离程序近一点，不要大量频繁的访问DB。
 *
 * 降级：如果不是核心链路，那么就把这个服务降级掉。打个比喻，现在的APP都讲究千人千面，拿到数据后，做个性化排序展示，
 * 如果在大流量下，这个排序就可以降级掉！
 *
 * 限流：大家都知道，北京地铁早高峰，地铁站都会做一件事情，就是限流了！
 * 想法很直接，就是想在一定时间内把请求限制在一定范围内，保证系统不被冲垮，同时尽可能提升系统的吞吐量。
 *
 * 限流的常用处理手段有：计数器、滑动窗口、漏桶、令牌。
 */
package com.lk.algorithms.limit;