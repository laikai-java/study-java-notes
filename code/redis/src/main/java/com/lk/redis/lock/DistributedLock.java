package com.lk.redis.lock;

import com.lk.redis.util.RedisUtils;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;


@Component
public class DistributedLock {

  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  private static final Long RELEASE_SUCCESS = 1L;

  private static final Long POSTPONE_SUCCESS = 1L;

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

  // 延时脚本
  private static final String POSTPONE_LOCK_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] "
      + "then"
      + " return redis.call('expire', KEYS[1], ARGV[2]) "
      + "else "
      + "return '0' "
      + "end";


  /**
   * 分布式锁
   *
   * @param key
   * @param value
   * @param expireTime 单位: 秒
   * @return
   */
  public Boolean lock(String key, String value, long expireTime) {
    Boolean locked = stringRedisTemplate.opsForValue()
        .setIfAbsent(key, value, expireTime, TimeUnit.SECONDS);

    if (locked) {
      // 加锁成功, 启动一个延时线程, 防止业务逻辑未执行完毕就因锁超时而使锁释放
      PostponeTask postponeTask = new PostponeTask(key, value, expireTime, this);
      Thread thread = new Thread(postponeTask);
      thread.setDaemon(Boolean.TRUE);
      thread.start();
    }
    return locked;
  }

  /**
   * 解锁
   *
   * @param key
   * @param value
   * @return
   */
  public Boolean unLock(String key, String value) {
    Boolean flag = Boolean.FALSE;
    //Redis 调用 Lua 脚本通过 eval 命令保证代码执行的原子性
    Jedis jedis = RedisUtils.getJedis();
    try {
      Object eval = jedis.eval(RELEASE_LOCK_SCRIPT, Collections.singletonList(key),
          Collections.singletonList(value));
      if (RELEASE_SUCCESS.equals(eval)) {
        return Boolean.TRUE;
      }
    } finally {
      if (jedis != null) {
        jedis.close();
      }
    }
    return flag;
  }

  /**
   * 锁延时
   *
   * @param key
   * @param value
   * @param expireTime
   * @return
   */
  public Boolean postpone(String key, String value, long expireTime) {
    Boolean flag = Boolean.FALSE;
    //Redis 调用 Lua 脚本通过 eval 命令保证代码执行的原子性
    Jedis jedis = RedisUtils.getJedis();
    try {
      Object eval = jedis.eval(POSTPONE_LOCK_SCRIPT, Collections.singletonList(key),
          Arrays.asList(value, String.valueOf(expireTime)));
      if (RELEASE_SUCCESS.equals(eval)) {
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
