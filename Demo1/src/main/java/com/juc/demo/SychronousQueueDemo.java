package com.juc.demo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @Auther: Admin
 * @Date: 2021/2/22 15:59
 * @Description:不存储任何元素，生产一个消费一个
 */
public class SychronousQueueDemo {


    public static void main(String[] args) {


        BlockingQueue blockingQueue = new SynchronousQueue();// 队列中只有一个空的对象

        new Thread(() ->{

            try {
                blockingQueue.put("1");
                System.out.println("put 1");
                blockingQueue.put("2");
                System.out.println("put 2");
                blockingQueue.put("3");
                System.out.println("put 3");


            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }, "aaa").start();


        new Thread(() ->{


            try {
                Thread.sleep(5000);
                blockingQueue.take();
                System.out.println("take 1");

                Thread.sleep(5000);
                blockingQueue.take();
                System.out.println("take 2");

                Thread.sleep(5000);
                blockingQueue.take();
                System.out.println("take 3");


            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }, "aaa").start();



    }

}
