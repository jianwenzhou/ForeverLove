package com.jareven.foreverlove;

import java.util.HashSet;

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

        System.out.print("a=" + a.hashCode());
        System.out.print("\n");
        System.out.print("b=" + b.hashCode());
        System.out.print("\n");
        System.out.print("ab=" + ab);
        System.out.print("\n");
        System.out.print("bb=" + bb);


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
