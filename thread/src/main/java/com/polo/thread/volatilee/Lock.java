package com.polo.thread.volatilee;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自定义简单的锁
 * @author baoqianyong
 * @date 2021/4/6
 */
public class Lock {

    private volatile int state = 0;

    private static final long offsset;

    private static final Unsafe unsafe;

    static {
        try {
            final PrivilegedExceptionAction<Unsafe> action = () -> {
                Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
                theUnsafe.setAccessible(true);
                return (Unsafe) theUnsafe.get(null);
            };
            unsafe = AccessController.doPrivileged(action);

            offsset = unsafe.objectFieldOffset(Lock.class.getDeclaredField("state"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public boolean lock() {
        do {
        } while (!unsafe.compareAndSwapInt(this, offsset, 0, 1));
        return true;
    }

    public void unlock() {
        while (!unsafe.compareAndSwapInt(this, offsset, 1, 0)){
        }
    }

    private static Integer count = 0;

    public static void main(String[] args) {
//        Lock lock = new Lock();
        ReentrantLock lock = new ReentrantLock();
        for (int i = 1; i <= 1000000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                    for (int j = 1; j <= 100; j++) {
                        count++;
                    }
                    System.out.println(Thread.currentThread().getName() + "  " + count);
                    lock.unlock();
                }
            }).start();
        }
    }
}
