package com.polo;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author baoqianyong
 * @date 2021/3/5
 */
public class ObjectSizeDemo {

    public static void main(String[] args) {
//        -XX:-UseCompressedOops
//        System.out.println(ClassLayout.parseInstance(new Object()).toPrintable());
//        System.out.println(ClassLayout.parseInstance(new Object[]{new Object(), new Object()}).toPrintable());

        System.out.println(ClassLayout.parseInstance(new Object1()).toPrintable());
    }
}

class Object1 {
    private int a = 0;
}
