package com.polo;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author baoqianyong
 * @date 2021/3/12
 */
public class FinalDemo {

    public static void main(String[] args) {
//        MyString str = new MyString("a");
//
//        HashMap<MyString, Boolean> map = new HashMap<>();
//        map.put(str, Boolean.FALSE);
//
//        str.setValue("bc");
//        System.out.println(map.get(str));

//        Integer a = 100;
//        HashMap<Integer, Boolean> intMap = new HashMap<>();
//        intMap.put(a, Boolean.TRUE);
//        a = 100;
//        System.out.println(intMap.get(a));


        Integer a = new Integer(1000);
        System.out.println(a.hashCode());
        HashMap<Integer, Boolean> intMap = new HashMap<>();
        intMap.put(a, Boolean.TRUE);
        a = new Integer(1000);
        System.out.println(a.hashCode());
        System.out.println(intMap.get(a));
    }
}

final class MyString {

    private char[] value = null;

    public MyString(String str) {
        this.setValue(str);
    }

    public void setValue(String value) {
        if (value == null) {
            value = null;
        }
        this.value = value.toCharArray();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MyString myString = (MyString) o;
        return Arrays.equals(value, myString.value);
    }

    @Override
    public int hashCode() {
        if (value == null) {
            return 0;
        }
        return value.length % 2 == 0 ? 2 : 1;
    }
}
