package com.vm.demo;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @Auther: Admin
 * @Date: 2021/2/25 10:54
 * @Description:
 */
public class PhantomReferenceDemo {


    public static void main(String[] args) {



        Object obj1 =  new Object();

        ReferenceQueue<Object> objectReferenceQueue = new ReferenceQueue<>();

        PhantomReference<Object> phantomReference = new PhantomReference<>(obj1, objectReferenceQueue);



        obj1 = null;

        System.gc();

        System.out.println(obj1);
        System.out.println(phantomReference.get());
        System.out.println(objectReferenceQueue.poll());// 被回收的obj存入到 referencequeue中保存




    }

}
