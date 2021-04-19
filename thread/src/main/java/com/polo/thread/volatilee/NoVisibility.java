package com.polo.thread.volatilee;

/**
 * @author baoqianyong
 * @date 2021/4/6
 */
public class NoVisibility {

//    private static boolean ready = false;
     private static volatile boolean ready = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (!ready) {
                // 此处不能使用 System.out.println 打印
                // 下面会解释
            }
            System.out.println("接收到 ready 值变化");
        }).start();

        Thread.sleep(500);
        ready = true;
        System.out.println("ready 值发生变更");
    }
}
