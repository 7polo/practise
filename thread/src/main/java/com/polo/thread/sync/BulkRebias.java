package com.polo.thread.sync;

import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author baoqianyong
 * @date 2021/4/14
 */
public class BulkRebias {

    private static Thread t1, t2;

    public static void main(String[] args) throws InterruptedException {
        ClassLayout.parseInstance(new Object()).toPrintable();

        TimeUnit.SECONDS.sleep(5);
        List<Object> list = new ArrayList<>();
        t1 = new Thread(() -> {
            System.out.println("---------------线程1");
            for (int i = 0; i < 100; i++) {
                Object a = new Object();
                list.add(a);
                synchronized (a) {
                    if (i == 18 || i == 19 || i == 20) {
                        System.out.println(i + ":" + ClassLayout.parseInstance(a).toPrintable());
                    }
                }
            }
        });

        t1.start();
        t1.join();

        t2 = new Thread(() -> {
            System.out.println("---------------线程2");
            for (int i = 0; i <= 21 && i < list.size(); i++) {
                Object a = list.get(i);
                synchronized (a) {
                    if (i == 18 || i == 19) {
                        System.out.println(i + ":" + ClassLayout.parseInstance(a).toPrintable());
                    }
                }
            }
        });

        t2.start();
        t2.join();

        System.out.println("--------------- 主线程");
        System.out.println("18:" + ClassLayout.parseInstance(list.get(18)).toPrintable());
        System.out.println("19:" + ClassLayout.parseInstance(list.get(19)).toPrintable());
        System.out.println("20:" + ClassLayout.parseInstance(list.get(20)).toPrintable());
        System.out.println("30:" + ClassLayout.parseInstance(list.get(30)).toPrintable());
        System.out.println("44:" + ClassLayout.parseInstance(list.get(30)).toPrintable());
    }
}
