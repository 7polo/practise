package com.polo.thread.lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author baoqianyong
 * @date 2021/4/17
 */
public class Lock1 implements Lock {

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

            offsset = unsafe.objectFieldOffset(com.polo.thread.volatilee.Lock.class.getDeclaredField("state"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }


    @Override
    public void lock() {
        while (!cas(0, 1)) {
        }
    }

    @Override
    public void unlock() {
        if (state != 0) {
            while (!cas(1, 0)) {
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return cas(0, 1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        if (time <= 0) {
            return tryLock();
        } else {
            long deadline = unit.toNanos(time) + System.nanoTime();
            while (!tryLock()) {
                time = deadline - System.nanoTime();
                if (time <= 0) {
                    return false;
                }
            }
            return true;
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    private boolean cas(int except, int update) {
        return unsafe.compareAndSwapInt(this, offsset, except, update);
    }
}
