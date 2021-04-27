package com.polo.thread.pool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

/**
 * @author polo
 */
public class PoolDemo {

    public static void main(String[] args) {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//        ExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(10);

        SynchronousQueue queue = new SynchronousQueue();

//        queue.
    }
}
