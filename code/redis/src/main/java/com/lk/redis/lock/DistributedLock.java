package com.lk.redis.lock;

import com.lk.redis.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.io.Serializable;
import java.util.Collections;
import java.util.concurrent.TimeUnit;


@Component
public class DistributedLock {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final Long RELEASE_SUCCESS = 1L;

    private static final String LOCK_SUCCESS = "OK";

    private static final String SET_IF_NOT_EXIST = "NX";

    private static final String SET_WITH_EXPIRE_TIME = "EX";

    // 解锁脚本(lua)
    private static final String RELEASE_LOCK_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] "
            + "then "
            + "    return redis.call('del', KEYS[1]) "
            + "else "
            + "    return 0 "
            + "end";


    /**
     * 分布式锁
     * @param key
     * @param value
     * @param expireTime 单位: 秒
     * @return
     */
    public Boolean lock(String key, String value, long expireTime) {
        return stringRedisTemplate.opsForValue().setIfAbsent(key, value, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 解锁
     * @param key
     * @param value
     * @return
     */
    public Boolean unLock(String key, String value) {
        Boolean flag = Boolean.FALSE;
        //Redis 调用 Lua 脚本通过 eval 命令保证代码执行的原子性
        Jedis jedis = RedisUtils.getJedis();
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] "
                + "then "
                + "    return redis.call('del', KEYS[1]) "
                + "else "
                + "    return 0 "
                + "end";
        try {
            Object eval = jedis.eval(script, Collections.singletonList(key), Collections.singletonList(value));
            if ("1".equals(eval.toString())){
               return Boolean.TRUE;
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return flag;
    }



}
