package com.polo.distribute.lock.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author baoqianyong
 * @date 2021/5/11
 */
public class RedisTool {

    private static JedisPool pool = null;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(1000);
        config.setMaxIdle(100);
        config.setMinIdle(50);
        config.setBlockWhenExhausted(true);
        pool = new JedisPool(config, "172.22.5.100", 6379, 5000);
    }

    public static Jedis getRedis() {
        return pool.getResource();
    }
}
