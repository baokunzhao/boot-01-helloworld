package com.juc.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁demo、为了保证并发的效率同时也要兼顾数据的一致性，读共享，写保证原子性
 *
 * @Created on 2021/2/21.
 * @Author by zbk
 */
public class ReadWriteLockDemo {


    public static void main(String[] args) {

        MyCache c = new MyCache();

        // 模拟写的操作
        for (int i = 0; i < 5; i++) {

            int finalI = i;
            new Thread(() -> {

                c.put("" + finalI, "" + finalI);


            }, "" + i).start();

        }


        // 模拟读的操作
        for (int i = 0; i < 5; i++) {

            int finalI = i;
            new Thread(() -> {

                c.get("" + finalI);

            }, "" + i).start();

        }


    }

}

// 模拟缓存类
class MyCache {

    private volatile Map<String, Object> map = new HashMap<>();

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {

        lock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + " 开始写入, key=" + key);
            TimeUnit.MILLISECONDS.sleep(400);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + " 写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }

    }


    public void get(String key) {

        lock.readLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + " 开始读取");
            map.get(key);
            System.out.println(Thread.currentThread().getName() + " 读取完成, key=" + key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }


    }


}