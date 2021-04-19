package com.polo.thread.sync;

/**
 * @author baoqianyong
 * @date 2021/4/12
 */
public class SyncDemo {

    /**
     * 静态同步方法
     */
    public static synchronized void syncStaticMethod() {
        System.out.println("syncStaticMethod");
    }

    /**
     * 同步方法
     */
    public synchronized void syncMethod() {
        System.out.println("syncMethod");
    }

    /**
     * 同步代码块
     */
    public void syncBlock() {
        synchronized (this) {
            System.out.println("syncBlock");
        }
    }
}
