package com.polo.distribute.lock.redis;

import com.polo.distribute.lock.Lock;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.Random;
import java.util.Timer;

/**
 * @author baoqianyong
 * @date 2021/5/11
 */
public class RedisLock implements Lock {

    private static final String DISTRIBUTE_LOCK = "DISTRIBUTE_LOCK";
    private static final String LOCK_SUCCESS = "OK";
    private static final Long RELEASE_SUCCESS = 1L;

    public RedisLock() {
        new WatchThread().start();
    }

    @Override
    public boolean lock(String requestId) {
        // 睡眠毫秒， 避免同一时间太多的竞争
        try {
            Thread.sleep(new Random().nextInt(10) * 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Jedis jedis = RedisTool.getRedis();
        // 通过一个操作完成 NX 和 PX
        // 即当该 key 不存在时才插入并设置过期时间， 保证了原子性
        String result = jedis.set(DISTRIBUTE_LOCK, requestId, "NX", "PX", 10 * 1000);
        jedis.close();
        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

//    @Override
//    public boolean lock(String requestId) {
//        Jedis jedis = RedisTool.getRedis();
//        Long result = jedis.setnx(DISTRIBUTE_LOCK, requestId);
//        if (LOCK_SUCCESS.equals(result)) {
//            jedis.expire(DISTRIBUTE_LOCK, 5000);
//            return true;
//        }
//        return false;
//    }

    @Override
    public boolean unlock(String requestId) {
        Jedis jedis = RedisTool.getRedis();
        // 这里是通过 lua 脚本的方式， 用一个命令完成两步操作： 1. 判断key是否存在 2. 删除该 key
        // redis 执行 lua 是原子性
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Arrays.asList(DISTRIBUTE_LOCK), Arrays.asList(requestId));
        jedis.close();
        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    /**
     * 续时
     */
    private class WatchThread extends Thread {
        public WatchThread() {
            this.setDaemon(true);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(8 * 1000);
                    Jedis jedis = RedisTool.getRedis();
                    if (jedis.exists(DISTRIBUTE_LOCK)) {
                        String lockClient = jedis.get(DISTRIBUTE_LOCK);
                        if (lockClient != null) {
                            jedis.expire(DISTRIBUTE_LOCK, 10);
                            System.out.println(lockClient + " 续时. 【" + (System.currentTimeMillis() / 1000) + "]");
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
