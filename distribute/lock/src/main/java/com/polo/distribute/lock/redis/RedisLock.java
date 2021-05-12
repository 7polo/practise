package com.polo.distribute.lock.redis;

import com.polo.distribute.lock.Lock;
import redis.clients.jedis.Jedis;

/**
 * @author baoqianyong
 * @date 2021/5/11
 */
public class RedisLock implements Lock {

    private static final String DISTRIBUTE_LOCK = "DISTRIBUTE_LOCK";
    private static final String LOCK_SUCCESS = "OK";

    @Override
    public boolean lock(String requestId) {
        Jedis jedis = RedisTool.getRedis();
        String result = jedis.set(DISTRIBUTE_LOCK, requestId, "NX", "PX", 50000);
        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    @Override
    public void unlock(String requestId) {

    }
}
