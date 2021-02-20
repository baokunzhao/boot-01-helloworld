package com.demo1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Demo1 {

    public String str = "1221";
    private static final List list = new ArrayList();


    // test
    public static void main(String[] args) {

        System.out.println("args = " + Arrays.deepToString(args));
        System.out.println("args = " + Arrays.deepToString(args));

        test1();

        test();

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


    }

}
