package com.juc.demo;

/**
 * @Created on 2021/2/24.
 * @Author by zbk
 * @Description:
 */
public class StrongReferenceDemo {

    public static void main(String[] args) {

        Object obj1 = new Object();
        Object obj2 = obj1;
        obj1 = null;
        System.gc();;
//        System.out.println(obj1);
        System.out.println(obj2);// 因为是强引用不会被回收

    }
}
