package com.juc.demo;

/**
 * @Auther: Admin
 * @Date: 2021/2/23 16:36
 * @Description: 死锁
 */
public class DeadLockDemo {


    public static void main(String[] args) {



        // 多个以上线程操作资源类
        new Thread(new HoldLock("lockA", "lockB"),"a").start();
        new Thread(new HoldLock("lockB", "lockA"),"b").start();

    }



}


class HoldLock implements Runnable{

    private String lock1 = "lock1";
    private String lock2 = "lock2";


    public HoldLock(String lock1, String lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {


        synchronized (this.lock1){// 手上已经有一个锁，在syn还没有结束的时候，尝试获取另一个锁，

            System.out.println("持有" + lock1 + "，尝试获取" + lock2);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (this.lock2){


                System.out.println("持有" + lock2 + "，尝试获取" + lock1);

            }

        }


    }
}
