package com.juc.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: Admin
 * @Date: 2021/2/22 16:40
 * @Description: 传统生产者和消费者模式
 */
public class ProductConsumerDemo {


    public static void main(String[] args) {

        System.out.println(Runtime.getRuntime().availableProcessors());

        Resource r = new Resource();

        new Thread(() -> {// 生产5个

            for (int i = 0; i < 5; i++) {

                try {
                    r.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }, "aa").start();


        new Thread(() -> {// 消费5个

            for (int i = 0; i < 5; i++) {
                try {
                    r.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }, "bb").start();


    }

}


// 1. 线程要操作资源类

class Resource {


    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    private volatile int number = 0;

    public void increment() throws Exception {


        lock.lock();
        try {
            // 先判断
            while (number != 0) {// 一定要用while防止虚假唤醒
                condition.await();
            }

            // do some thing
            number++;
            System.out.println(Thread.currentThread().getName() + " number:" + number);
            // 唤醒
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    public void decrement() throws Exception {

        lock.lock();
        try {
            // 先判断
            while (number == 0) {// 一定要用while防止虚假唤醒
                condition.await();
            }

            // do some thing
            number--;
            System.out.println(Thread.currentThread().getName() + " number:" + number);
            // 唤醒
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }




}