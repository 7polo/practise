package com.polo.thread.volatilee;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author baoqianyong
 * @date 2021/4/6
 */
public class VisibilityAtomic {

    private volatile static Integer count = 0;

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 1; j <= 100; j++) {
                        count++;
                    }
                    System.out.println(count);
                }
            }).start();
        }
    }
}
