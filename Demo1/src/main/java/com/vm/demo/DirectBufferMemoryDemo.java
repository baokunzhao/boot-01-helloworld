package com.vm.demo;

import sun.misc.VM;

import java.nio.ByteBuffer;

/**
 * @Auther: Admin
 * @Date: 2021/2/25 14:07
 * @Description:
 */
public class DirectBufferMemoryDemo {

    public static void main(String[] args) {

        System.out.println(VM.maxDirectMemory()/ 1024/1024);// 一般java程序默认可用的为这个os的1/4内存

        ByteBuffer.allocateDirect(6 * 1024 * 1024);

        // 抛出一下异常：

//        Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
//        at java.nio.Bits.reserveMemory(Bits.java:694)
//        at java.nio.DirectByteBuffer.<init>(DirectByteBuffer.java:123)
//        at java.nio.ByteBuffer.allocateDirect(ByteBuffer.java:311)
//        at demo.DirectBufferMemoryDemo.main(DirectBufferMemoryDemo.java:18)


    }
}
