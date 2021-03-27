package com.jareven.foreverlove;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @ClassName Test
 * @Author zjw
 * @Date 2021/3/11 11:05
 * 简介：
 */
class Test {


    public static void main(String[] args) {

        AppLifecycleCallbacks a = new AppLifecycleCallbacks();

        AppLifecycleCallbacks b = new AppLifecycleCallbacks();

        HashSet<AppLifecycleCallbacks> hashSet = new HashSet<>();

        boolean ab = hashSet.add(a);

        boolean bb = hashSet.add(b);

//        System.out.print("a=" + a.hashCode());
//        System.out.print("\n");
//        System.out.print("b=" + b.hashCode());
//        System.out.print("\n");
//        System.out.print("ab=" + ab);
//        System.out.print("\n");
//        System.out.print("bb=" + bb);
//        System.out.print("\n");


        List<String> list = new ArrayList<>();
        System.out.print(list.hashCode());
        System.out.print("\n");
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        System.out.print(list.hashCode());
        System.out.print("\n");
        if (list.size() > 4) {
            list = list.subList(0, 4);
        }

        System.out.print(list.hashCode());
        System.out.print("\n");
        System.out.print(list);


    }

    private static void swap() {

        //找出b中多出的字符
        String a = "aabbcc";
        String b = "abjcabc";


        char[] chars = (a + b).toCharArray();

        int i = 0;
        for (char aChar : chars) {
            i = i ^ aChar;
        }

        System.out.print((char) i);


    }


}
