package com.polo.distribute.lock.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author baoqianyong
 * @date 2021/5/11
 */
public class RedisTool {

    public static Jedis getRedis() {
        JedisPool pool = new JedisPool("127.0.0.1", 6379);
        Jedis jedis = pool.getResource();
        return jedis;
    }
}
