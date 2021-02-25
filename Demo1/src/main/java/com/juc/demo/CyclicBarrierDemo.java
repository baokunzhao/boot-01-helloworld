package com.juc.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;



public class CyclicBarrierDemo {


    public static void main(String[] args) {

        CyclicBarrier cb = new CyclicBarrier(7,() ->{
            System.out.println("工作结束");
        });


        for (int i = 0; i < 7; i++) {

            int finalI = i;
            new Thread(() -> {

                System.out.println(finalI + "开始工作");
                try {
                    cb.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }, "" + i).start();

        }


    }

}
