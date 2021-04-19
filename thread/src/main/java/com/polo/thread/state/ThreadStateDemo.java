package com.polo.thread.state;

/**
 * @author baoqianyong
 * @date 2021/3/30
 */
public class ThreadStateDemo {


    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    int i = 0;
                    while (i < 1000) {
                        i++;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("1." + t.getState());
        t.start();
        System.out.println("2." + t.getState());

        Thread.sleep(500);
        System.out.println("3." + t.getState());

        Thread.sleep(501);
        System.out.println("4." + t.getState());

        Thread.sleep(500);
        System.out.println("5." + t.getState());
    }
}
