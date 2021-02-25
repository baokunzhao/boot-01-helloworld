package com.juc.demo;

import java.lang.ref.SoftReference;

/**
 * @Created on 2021/2/24.
 * @Author by zbk
 * @Description:
 */
public class SoftReferenceDemo {

    public static void main(String[] args) {

        Object obj1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(obj1);

        // -Xms:10m -Xms=10m 设置后，当内存不够时，会被gc
        System.out.println(softReference.get());

    }
}
