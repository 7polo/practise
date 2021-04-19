package com.polo.thread.volatilee;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;

/**
 * 自定义简单的原子类
 *
 * @author baoqianyong
 * @date 2021/4/6
 */
public class AtomicI {

    private volatile int value;

    private static final long offsset;

    private static final Unsafe unsafe;

    static {
        try {
            final PrivilegedExceptionAction<Unsafe> action = () -> {
                Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
                theUnsafe.setAccessible(true);
                return (Unsafe) theUnsafe.get(null);
            };

            // 获取 unsafe 实例
            unsafe = AccessController.doPrivileged(action);

            // 获取变量偏移值
            offsset = unsafe.objectFieldOffset(AtomicI.class.getDeclaredField("value"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public void increment() {
        int val;
        do {
            val = unsafe.getIntVolatile(this, offsset);
        } while (!unsafe.compareAndSwapInt(this, offsset, val, val + 1));
    }

    public int get() {
        return unsafe.getIntVolatile(this, offsset);
    }

    public static void main(String[] args) {
        AtomicI v = new AtomicI();
        for (int i = 1; i <= 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 1; j <= 100; j++) {
                        v.increment();
                        System.out.println(v.get());
                    }

                }
            }).start();
        }
    }
}
