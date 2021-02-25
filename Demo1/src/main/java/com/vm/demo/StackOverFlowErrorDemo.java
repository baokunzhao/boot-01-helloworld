package com.vm.demo;

/**
 * @Auther: Admin
 * @Date: 2021/2/25 11:14
 * @Description:
 */
public class StackOverFlowErrorDemo {

    public static void main(String[] args) {


        test();


    }

    private static void test() {// 不断的压栈和出栈

        test();

    }

}
