package com.polo.thread.question;


import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author baoqianyong
 * @date 2021/4/18
 */
public class TurnedPrint {

    public static void main(String[] args) {
        // 1. 基于 volatile 的实现, 利用可见性
        volatilePrint();

        // 2. 基于 公平锁的实现
//        fairLockPrint();

        // 3. sync 的实现
//        onlySyncPrint();
    }

    private static volatile Integer state = 0;
    private static Integer value = 1;

    public static void volatilePrint() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                while (true) {
                    if (value > 10000) {
                        state = 1;
                        break;
                    }
                    if (state == 0) {
                        System.out.println("线程1：" + value++);
                        state = 1;
                    }
                }
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                while (true) {
                    if (value > 10000) {
                        state = 0;
                        break;
                    }
                    if (state == 1) {
                        System.out.println("线程2：" + value++);
                        state = 0;
                    }
                }
            }
        });
        t2.start();
    }

    public static void fairLockPrint() {

        Lock lock = new ReentrantLock(true);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    lock.lock();
                    if (value > 1000) {
                        lock.unlock();
                        break;
                    }
                    System.out.println("线程1：" + value++);
                    lock.unlock();
                }
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    lock.lock();
                    if (value > 1000) {
                        lock.unlock();
                        break;
                    }
                    System.out.println("线程2：" + value++);
                    lock.unlock();
                }
            }
        });
        t2.start();
    }

    private static Object syncLock = new Object();

    public static void onlySyncPrint() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (syncLock) {
                        if (value > 1000) {
                            syncLock.notify();
                            break;
                        }
                        if (value % 2 != 0) {
                            System.out.println("线程1：" + value++);
                            syncLock.notify();
                        } else {
                            try {
                                syncLock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (syncLock) {
                        if (value > 1000) {
                            syncLock.notify();
                            break;
                        }
                        if (value % 2 == 0) {
                            System.out.println("线程2：" + value++);
                            syncLock.notify();
                        } else {
                            try {
                                syncLock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        t2.start();
    }
}
