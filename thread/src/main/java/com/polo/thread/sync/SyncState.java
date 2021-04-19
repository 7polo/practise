package com.polo.thread.sync;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @author baoqianyong
 * @date 2021/4/12
 */
public class SyncState {

    public static void main(String[] args) throws InterruptedException {

        TimeUnit.SECONDS.sleep(5);

        Object obj2 = new Object();
        System.out.println(ClassLayout.parseInstance(obj2).toPrintable());
        System.out.println(obj2.hashCode());
        synchronized (obj2) {
            System.out.println(ClassLayout.parseInstance(obj2).toPrintable());
        }
    }
}
