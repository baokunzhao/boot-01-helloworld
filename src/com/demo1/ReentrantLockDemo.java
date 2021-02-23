package com.demo1;

/**
 * @Created on 2021/2/21.
 * @Author by zbk
 */
public class ReentrantLockDemo {

    public synchronized void method1() {

        System.out.println(Thread.currentThread().getName() + ",m1");

        // 进入method2方法自动获取锁
        this.method2();
    }

    public synchronized void method2() {

        System.out.println(Thread.currentThread().getName() + ",m2");

        String n = new String();

    }


    public static void main(String[] args) {


        ReentrantLockDemo demo = new ReentrantLockDemo();

        new Thread(() -> {
            demo.method1();
        }, "t1").start();

        new Thread(() -> {
            demo.method1();
        }, "t2").start();

    }

}
