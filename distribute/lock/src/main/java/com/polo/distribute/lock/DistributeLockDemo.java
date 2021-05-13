package com.polo.distribute.lock;

import com.polo.distribute.lock.redis.RedisLock;

import java.util.Random;

/**
 * @author polo
 */
public class DistributeLockDemo {

    public static void main(String[] args) {
        Lock lock = new RedisLock();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    Thread client = Thread.currentThread();
                    new Thread().setDaemon(true);
                    while (true) {
                        if (lock.lock(client.getName())) {
                            System.out.println();
                            System.out.println(client.getName() + " get lock ");
                            try {
                                doSomething();
                            } finally {
                                System.out.println(client.getName() + " unlock ");
                                lock.unlock(client.getName());
                            }
                            break;
                        }
                    }
                }
            });
            t.setName("client" + i);
            t.start();
        }
    }

    public static void doSomething() {
        System.out.println(Thread.currentThread().getName() + " do something");
        Random random = new Random();
        long s = 1000 * random.nextInt(4);
        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " do something success 耗时:" + s + "ms");
    }
}
