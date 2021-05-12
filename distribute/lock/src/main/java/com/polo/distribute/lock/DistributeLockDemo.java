package com.polo.distribute.lock;

import com.polo.distribute.lock.redis.RedisLock;

/**
 * @author polo
 */
public class DistributeLockDemo {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            final int index = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Lock lock = new RedisLock();
                    boolean result = lock.lock("" + index);
                    System.out.println("加锁:" + (result ? "成功" : "失败"));
                }
            }).start();
        }
    }
}
