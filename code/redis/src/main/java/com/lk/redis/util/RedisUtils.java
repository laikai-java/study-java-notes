package com.lk.redis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtils {
    private static JedisPool jedisPool;

    static {
        JedisPoolConfig jpc = new JedisPoolConfig();
        jpc.setMaxTotal(20);
        jpc.setMaxIdle(10);
        jedisPool = new JedisPool(jpc,"127.0.0.1",6379);
    }

    public static Jedis getJedis() throws Exception{
        if(jedisPool == null)
            throw new NullPointerException("JedisPool is not OK.");
        return jedisPool.getResource();
    }
}
