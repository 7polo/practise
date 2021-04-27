package com.polo.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * @author polo
 */
public class LockSupportDemo {

    public static void main(String[] args) {

        Thread t = new Thread(new Runnable() {
            public void run() {
                System.out.println("running1");
                LockSupport.park();
                while (true) {
                    System.out.println("running...");
                }
            }
        });
        t.start();

        try {
            Thread.sleep(2000);
            LockSupport.unpark(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
