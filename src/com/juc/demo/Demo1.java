package com.juc.demo;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;


public class Demo1 {

    public String str = "1221";
    private static final List list = new ArrayList();


    // test
    public static void main(String[] args) {

        System.out.println("args = " + Arrays.deepToString(args));
        System.out.println("args = " + Arrays.deepToString(args));

        test1();

        test();

        System.out.println(10 >> 1);



    }

    private static void test1() {
        try {


            Date date = new Date();

            System.out.println("date = " + date);


        } catch (Exception e) {
            e.printStackTrace();
        }



    }


    /**
     * 我是注释
     */
    public static void test() {

//        List<String> list = new ArrayList<>();
        List<String> list = Collections.synchronizedList(new ArrayList<>());// 线程安全类
        List<String> list2 = new CopyOnWriteArrayList<>();

        list.add("a");
        list.add("b");
        list.add("c");

        list.forEach(System.out::println);




    }

}
