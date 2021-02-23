package com.juc.demo;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁demo
 * @Created on 2021/2/21.
 * @Author by zbk
 */
public class SpinLockDemo {

    private AtomicReference<Thread> ref = new AtomicReference<>();

    public void lock(){

        Thread thread = Thread.currentThread();

        while (!ref.compareAndSet(null, thread)){

        }

        System.out.println(thread.getName() + ", lock");
    }


    public void unlock(){

        Thread thread = Thread.currentThread();
        ref.compareAndSet(thread, null);

        System.out.println(Thread.currentThread().getName() + ", unlock");
    }

    public static void main(String[] args) throws InterruptedException {


        SpinLockDemo demo = new SpinLockDemo();

        new Thread(() -> {

            demo.lock();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            demo.unlock();

        }, "t1").start();


        Thread.sleep(1000);


        new Thread(() -> {

            demo.lock();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            demo.unlock();

        }, "t2").start();

    }

}
