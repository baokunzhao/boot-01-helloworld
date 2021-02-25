package com.juc.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Created on 2021/2/22.
 * @Author by zbk
 * @Description: 使用BlockingQueue模拟生产者和消费者，不需要lock和unlock synchronized来控制，自己做控制
 */
public class ProdConsumerBlockingQueueDemo {


    public static void main(String[] args) throws Exception {

        MyResource resource = new MyResource(new ArrayBlockingQueue<>(10));

        new Thread(() -> {
            try {
                resource.pord();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "prod").start();

        new Thread(() -> {
            try {
                resource.consumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "consumer").start();

        Thread.sleep(5000L);

        resource.stopProd();
        System.out.println("5s后停止生产");



    }

}


class MyResource{

    private volatile boolean Flag = true;// 控制是否进行生产，必须是多线程可见的，使用volatile关键字

    private AtomicInteger number = new AtomicInteger();// 用来记录生产的次数，保证原子性，不能使用c++

    private BlockingQueue<Integer> queue = null;

    // 使用构造器注入，所需要的具体实现类
    public MyResource(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public void pord() throws Exception {

        Integer data;
        boolean result;
        while (Flag){// 循环插入
            data = number.incrementAndGet();
            result = queue.offer(data, 2L, TimeUnit.SECONDS);// 插入满了后阻塞
            if(result){
                System.out.println(Thread.currentThread().getName() + ", 插入队列" + data + "成功");
            }else{
                System.out.println(Thread.currentThread().getName() + ", 插入队列" + data + "失败");
            }

            Thread.sleep(10000L);

        }

    }


    public void consumer() throws Exception{

        Integer data;
        while (Flag){
            data = queue.poll(2L, TimeUnit.SECONDS);// 空了后阻塞，2s超时时间
            if(data == null || data == 0){
                Flag = false;
                System.out.println(Thread.currentThread().getName() + "消费超时，并退出");
                return;
            }
            System.out.println(Thread.currentThread().getName() + ", 移出队列" + data + "成功");
        }


    }




    public void stopProd(){

        this.Flag = false;

    }



}
