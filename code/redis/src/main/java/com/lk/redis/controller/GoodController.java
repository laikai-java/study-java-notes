package com.lk.redis.controller;


import com.lk.redis.lock.DistributedLock;
import com.lk.redis.util.RedisUtils;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Resource;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

@RestController
public class GoodController {
    private final ReentrantLock lock = new ReentrantLock();
    private static final String REDIS_LOCK = "redis:lock";
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    Redisson redisson;

    @Value("${server.port}")
    String serverPort;

    @Autowired
    DistributedLock distributedLock;


    @GetMapping("/distributedLock")
    public Object distributedLock() throws InterruptedException {
        String key = "add_information_lock";
        String value = UUID.randomUUID().toString();
        long expireTime = 5L;

        boolean lock = distributedLock.lock(key, value, expireTime);
        String threadName = Thread.currentThread().getName();
        if (!lock){
            System.out.println(threadName + " 未获取到锁...............................");
            return "未获取到锁";
        }

        System.out.println(threadName + " 获得锁...............................");
        Thread.sleep(1000*10);
        distributedLock.unLock(key, value);
        System.out.println(threadName + " 解锁了...............................");


        return "成功";
    }

    /**
     * jvm 单机锁
     * 在高并发下 情况下 当前的对存库的操作 不具有原子性
     */
    @GetMapping("/jvmlock/buy_goods")
    public String jvmlock_buy_goods() {

        lock.lock();
        try {
            //看看库存 量是否足够
            String result = stringRedisTemplate.opsForValue().get("goods:001");
            int goodNumber = result == null ? 0 : Integer.parseInt(result);
            if (goodNumber > 0) {
                int realNumber = goodNumber - 1;
                stringRedisTemplate.opsForValue().set("goods:001", String.valueOf(realNumber));
                System.out.println("成功买到商品，库存还剩下: " + realNumber + " 件" + "\t服务提供端口" + serverPort);
                return "成功买到商品，库存还剩下:" + realNumber + " 件" + "\t服务提供端口" + serverPort;
            } else {
                System.out.println("商品已经售完/活动结束/调用超时,欢迎下次光临" + "\t服务提供端口" + serverPort);
            }
            return "商品已经售完/活动结束/调用超时,欢迎下次光临" + "\t服务提供端口" + serverPort;
        } finally {
            lock.unlock();
        }
    }

    @GetMapping("/redislock_bad/buy_goods")
    public String redislock_bad_buy_goods() {

        String value = UUID.randomUUID().toString() + Thread.currentThread().getName();
        //分布式锁  setnx
        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(REDIS_LOCK, value);
        if (!flag) {
            return "";
        }

        //看看库存 量是否足够
        String result = stringRedisTemplate.opsForValue().get("goods:001");
        int goodNumber = result == null ? 0 : Integer.parseInt(result);
        if (goodNumber > 0) {
            int realNumber = goodNumber - 1;
            stringRedisTemplate.opsForValue().set("goods:001", String.valueOf(realNumber));
            //发生异常??? 没有释放锁
            stringRedisTemplate.delete(REDIS_LOCK);
            System.out.println("成功买到商品，库存还剩下: " + realNumber + " 件" + "\t服务提供端口" + serverPort);
            return "成功买到商品，库存还剩下:" + realNumber + " 件" + "\t服务提供端口" + serverPort;
        } else {
            System.out.println("商品已经售完/活动结束/调用超时,欢迎下次光临" + "\t服务提供端口" + serverPort);
        }
        return "商品已经售完/活动结束/调用超时,欢迎下次光临" + "\t服务提供端口" + serverPort;
    }

    @GetMapping("/redislock_exception/buy_goods")
    public String redislock_exception_buy_goods() throws Exception {

        String value = UUID.randomUUID().toString() + Thread.currentThread().getName();
        try {

            //分布式锁  setnx
            // 还需要超时  万一程序宕机 没有释放锁!!!  超时与setnx需要具有原子性
            Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(REDIS_LOCK, value, 10L, TimeUnit.SECONDS);
            //设定时间
            //stringRedisTemplate.expire(REDIS_LOCK, 10L, TimeUnit.SECONDS);
            if (!flag) {
                return "";
            }

            //看看库存 量是否足够
            String result = stringRedisTemplate.opsForValue().get("goods:001");
            int goodNumber = result == null ? 0 : Integer.parseInt(result);
            if (goodNumber > 0) {
                int realNumber = goodNumber - 1;
                stringRedisTemplate.opsForValue().set("goods:001", String.valueOf(realNumber));
                System.out.println("成功买到商品，库存还剩下: " + realNumber + " 件" + "\t服务提供端口" + serverPort);
                return "成功买到商品，库存还剩下:" + realNumber + " 件" + "\t服务提供端口" + serverPort;
            } else {
                System.out.println("商品已经售完/活动结束/调用超时,欢迎下次光临" + "\t服务提供端口" + serverPort);
            }
            return "商品已经售完/活动结束/调用超时,欢迎下次光临" + "\t服务提供端口" + serverPort;
        } finally {
            //发生异常释放锁
            //加锁必有解锁
            //只能删除自己的锁  但是不是原子操作  -->使用lua脚本 或者 redis事务
           /* if (value.equals(stringRedisTemplate.opsForValue().get(REDIS_LOCK))) {
                stringRedisTemplate.delete(REDIS_LOCK);
            }*/

            //使用事务解决finally中获取与删除 原子性问题
           /* while(true){
                //监听该锁
                stringRedisTemplate.watch(REDIS_LOCK);
                //如果是自己的锁 循环删除
                if (stringRedisTemplate.opsForValue().get(REDIS_LOCK).equals(value)){
                    stringRedisTemplate.multi();
                    stringRedisTemplate.delete(REDIS_LOCK);
                    List<Object> list = stringRedisTemplate.exec();
                    if (CollectionUtils.isEmpty(list)){
                        continue;
                    }
                }
                stringRedisTemplate.unwatch();
                break;
            }*/

            //Redis 调用 Lua 脚本通过 eval 命令保证代码执行的原子性
            Jedis jedis = RedisUtils.getJedis();
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] "
                    + "then "
                    + "    return redis.call('del', KEYS[1]) "
                    + "else "
                    + "    return 0 "
                    + "end";
            try {
                Object eval = jedis.eval(script, Collections.singletonList(REDIS_LOCK), Collections.singletonList(value));
                if ("1".equals(eval.toString())){
                    System.out.println("删除锁成功");
                }else{
                    System.out.println("删除锁失败");
                }
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }

        }
    }

    @GetMapping("/redisson/buy_goods")
    public String redisson()  {

        String value = UUID.randomUUID().toString() + Thread.currentThread().getName();
        RLock lock = redisson.getLock(REDIS_LOCK);
        lock.lock();
        try {
            //看看库存 量是否足够
            String result = stringRedisTemplate.opsForValue().get("goods:001");
            int goodNumber = result == null ? 0 : Integer.parseInt(result);
            if (goodNumber > 0) {
                int realNumber = goodNumber - 1;
                stringRedisTemplate.opsForValue().set("goods:001", String.valueOf(realNumber));
                System.out.println("成功买到商品，库存还剩下: " + realNumber + " 件" + "\t服务提供端口" + serverPort);
                return "成功买到商品，库存还剩下:" + realNumber + " 件" + "\t服务提供端口" + serverPort;
            } else {
                System.out.println("商品已经售完/活动结束/调用超时,欢迎下次光临" + "\t服务提供端口" + serverPort);
            }
            return "商品已经售完/活动结束/调用超时,欢迎下次光临" + "\t服务提供端口" + serverPort;
        } finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread())
            lock.unlock();
        }
    }
}
