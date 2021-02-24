package com.juc.demo;

import java.lang.ref.WeakReference;

/**
 * @Created on 2021/2/24.
 * @Author by zbk
 * @Description:
 */
public class WeakReferenceDemo {

    public static void main(String[] args) {


        Object obj1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(obj1);

        System.out.println(obj1);
        System.out.println(weakReference.get());

        obj1 = null;

        System.gc();

        System.out.println(obj1);
        System.out.println(weakReference.get());// 此时内存够的，但是还是被回收



    }
}
