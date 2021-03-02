package com.vm.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Created on 2021/2/25.
 * @Author by zbk
 * @Description:
 */
public class GCDemo {



    // 1. -Xms1m -Xmx1m -XX:MaxDirectMemorySize=5m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC
    // [GC (Allocation Failure) [DefNew: 1189K->1189K(1856K), 0.0000281 secs]
    // [Tenured: 3491K->2334K(4096K), 0.0021465 secs] 4680K->2334K(5952K),
    // [Metaspace: 3226K->3226K(1056768K)], 0.0022283 secs] [Times: user=0.02 sys=0.00, real=0.02 secs]

//    Heap
//    【def new generation】  total 576K, used 83K [0x00000000ffe00000, 0x00000000ffea0000, 0x00000000ffea0000)
//    eden space 512K,  16% used [0x00000000ffe00000, 0x00000000ffe14e80, 0x00000000ffe80000)
//    from space 64K,   0% used [0x00000000ffe80000, 0x00000000ffe80000, 0x00000000ffe90000)
//    to   space 64K,   0% used [0x00000000ffe90000, 0x00000000ffe90000, 0x00000000ffea0000)
//    【tenured generation 】  total 1408K, used 1106K [0x00000000ffea0000, 0x0000000100000000, 0x0000000100000000)
//    the space 1408K,  78% used [0x00000000ffea0000, 0x00000000fffb4b38, 0x00000000fffb4c00, 0x0000000100000000)
//    Metaspace       used 3252K, capacity 4496K, committed 4864K, reserved 1056768K
//    class space    used 352K, capacity 388K, committed 512K, reserved 1048576K


    // 2. -Xms1m -Xmx1m -XX:MaxDirectMemorySize=5m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC
    // 3. -Xms1m -Xmx1m -XX:MaxDirectMemorySize=5m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC
    // 4. -Xms1m -Xmx1m -XX:MaxDirectMemorySize=5m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelOldGC
    // 3和4互相激活

//    5. -Xms10m -Xmx10m -XX:MaxDirectMemorySize=5m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC
//    运行如下：
//    [GC (CMS Initial Mark) [1 CMS-initial-mark: 4736K(6848K)] 4736K(9920K), 0.0001754 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
//            [CMS-concurrent-mark-start]
//            [CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
//            [CMS-concurrent-preclean-start]
//            [CMS-concurrent-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
//            [GC (CMS Final Remark) [YG occupancy: 54 K (3072 K)][Rescan (parallel) , 0.0003050 secs][weak refs processing, 0.0000092 secs][class unloading, 0.0002872 secs][scrub symbol table, 0.0005430 secs][scrub string table, 0.0001653 secs][1 CMS-remark: 4736K(6848K)] 4790K(9920K), 0.0013773 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
//            [CMS-concurrent-sweep-start]
//            [CMS-concurrent-sweep: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
//            [CMS-concurrent-reset-start]
//            [CMS-concurrent-reset: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
//    Heap
//    par new generation   total 3072K, used 109K [0x00000000ff600000, 0x00000000ff950000, 0x00000000ff950000)
//    eden space 2752K,   3% used [0x00000000ff600000, 0x00000000ff61b640, 0x00000000ff8b0000)
//    from space 320K,   0% used [0x00000000ff8b0000, 0x00000000ff8b0000, 0x00000000ff900000)
//    to   space 320K,   0% used [0x00000000ff900000, 0x00000000ff900000, 0x00000000ff950000)
//    concurrent mark-sweep generation total 6848K, used 4736K [0x00000000ff950000, 0x0000000100000000, 0x0000000100000000)
//    Metaspace       used 3252K, capacity 4496K, committed 4864K, reserved 1056768K
//    class space    used 352K, capacity 388K, committed 512K, reserved 1048576K


//  6.   -Xms10m -Xmx10m -XX:MaxDirectMemorySize=5m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialOldGC
    // 已经不用了
//    Error: Could not create the Java Virtual Machine.
//            Error: A fatal exception has occurred. Program will exit.
//    Unrecognized VM option 'UseSerialOldGC'
//    Did you mean '(+/-)UseSerialGC'?


//    6.   -Xms10m -Xmx10m -XX:MaxDirectMemorySize=5m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC




    public static void main(String[] args) throws InterruptedException {

        String str = "test";

        List<String> list = new ArrayList<>();
        try {
            while (true){

                str += str +  new Random().nextInt(1111111111) + new Random().nextInt(999999999);
                str.intern();
                //list.add(str);


            }
        } catch (Throwable e) {
            e.printStackTrace();
        }


    }
}
