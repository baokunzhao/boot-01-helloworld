package com.demo1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Created on 2021/2/22.
 * @Author by zbk
 * @Description: 另一种创建线程的方式
 */
public class FutureTaskDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        // 构造方法传入callable接口，同时futuretask实现了runnable接口
        FutureTask<Integer> task = new FutureTask<Integer>(() -> {
            return 1;
        });

        new Thread(task, "aa").start();

        System.out.println(task.get());

    }
}
