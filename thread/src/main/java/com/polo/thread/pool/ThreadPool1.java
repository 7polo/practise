package com.polo.thread.pool;

import java.util.concurrent.*;

/**
 * @author baoqianyong
 * @date 2021/4/14
 */
public class ThreadPool1 {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1));
        System.out.println(executor.getActiveCount());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("hahaha1");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println(executor.getActiveCount());

        executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("hahaha1");
            }
        });

        System.out.println(executor.getActiveCount());
        executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("hahaha1");
            }
        });
        System.out.println(executor.getActiveCount());
    }
}
