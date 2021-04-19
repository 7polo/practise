package com.polo;

/**
 * @author baoqianyong
 * @date 2021/3/26
 */
public class GcDemo {

    public static void main(String[] args) {
        // 100M
        byte [] MAXOBJ = new byte [1024 * 1024 * 100];

        // 60M
        byte [] MAXOBJ2 = new byte [1024 * 1024 * 66];
        MAXOBJ = null;

        // 60M
        byte [] MAXOBJ3 = new byte [1024 * 1024 * 60];
    }
}
