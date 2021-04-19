package com.polo.thread.state;

/**
 * @author baoqianyong
 * @date 2021/3/31
 */
public class ThreadDemo {

    public static void main(String[] args) throws InterruptedException {
//        demo1();
//        demo2();
//        demo3();

        Thread.yield();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Thread me = Thread.currentThread();
                while (true && !me.isInterrupted()) {
                    try {
                        Thread.sleep(100);
                        me.interrupt();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }}).start();
    }

    public static void demo1() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread 执行中...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread 执行结束...");
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程结束");
    }

    public static void demo2() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread 执行中...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread 执行结束...");
            }
        });
        t.start();
        synchronized (t) {
            try {
                System.out.println("t wait");
                t.wait();
                System.out.println("唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("主线程结束");
    }

    public static void demo3() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread1 执行中...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread1 执行结束...");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread2 执行中...");
                try {
                    Thread.sleep(1000);
                    System.out.println("2：t join 前");
                    t.join();
                    System.out.println("2：t join 后");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread2 执行结束...");
            }
        });
        Thread c = Thread.currentThread();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true && !Thread.currentThread().isInterrupted()) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(c.getState()+", "+t2.getState());
                    if (c.getState() == Thread.State.TERMINATED) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }).start();

        try {
            t.start();
            t2.start();
            System.out.println("1：t join 前");
            synchronized (t) {
                Thread.sleep(2000);
            }
//            t.join();
            System.out.println("1：t join 后");
//            t2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("主线程结束");
    }
}
