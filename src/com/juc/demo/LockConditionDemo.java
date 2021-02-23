package com.juc.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Created on 2021/2/22.
 * @Author by zbk
 * @Description 使用lock的condition对条件做精确判断, 线程a先打印，然后b，最后c
 */
public class LockConditionDemo {


    public static void main(String[] args) {

        Print p = new Print();

        for (int i = 0; i < 5; i++) {

            new Thread(() -> {
                try {
                    p.print10();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "a").start();

        }


        for (int i = 0; i < 5; i++) {

            new Thread(() -> {
                try {
                    p.print20();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "b").start();

        }

        for (int i = 0; i < 5; i++) {

            new Thread(() -> {
                try {
                    p.print30();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "c").start();


        }


    }


}


// 资源类
class Print {

    private Lock lock = new ReentrantLock();

    private int mark = 1;// 作为多线程判断条件

    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();
    Condition c3 = lock.newCondition();


    public void print10() throws Exception {

        lock.lock();
        try {
            while (mark != 1) {
                c1.await();
            }
            System.out.println(Thread.currentThread().getName() + ", 打印10次");
            mark = 2;
            c2.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    public void print20() throws Exception {

        lock.lock();
        try {
            while (mark != 2) {
                c2.await();
            }
            System.out.println(Thread.currentThread().getName() + ", 打印20次");
            mark = 3;
            c3.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }


    public void print30() throws Exception {

        lock.lock();
        try {
            while (mark != 3) {
                c3.await();
            }
            System.out.println(Thread.currentThread().getName() + ", 打印30次");
            System.out.println("==============");
            mark = 1;
            c1.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }


}