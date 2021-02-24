package com.juc.demo;

/**
 * @Auther: Admin
 * @Date: 2021/2/24 13:32
 * @Description:
 */
public class JVMDemo {


    // vm中添加参数：-XX:+PrintGCDetails 打印cg cmd中使用 jinfo -flag PrintGCDetails 进程号
    // -XX:MetaspaceSize=50m
    public static void main(String[] args) throws Exception {

        System.out.println("hello");
//        Thread.sleep(Integer.MAX_VALUE);

    }
}
