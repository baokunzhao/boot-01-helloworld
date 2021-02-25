package com.vm.demo;

import net.sf.cglib.core.AbstractClassGenerator;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Auther: Admin
 * @Date: 2021/2/25 15:11
 * @Description: 元空间溢出
 */
public class MetaSpaceDemo {


    static class Test{}


    // -XX:MetaspaceSize=5m  -XX:MaxMetaspaceSize=5m
    public static void main(String[] args) {


        // 不断的创建static类，放入metaspace中模拟
        while (true){

            Enhancer enhancer = new Enhancer();
            enhancer.setUseCache(false);
            enhancer.setSuperclass(Test.class);

            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                    return proxy.invoke(obj, args);
                }
            });

            enhancer.create();

        }

//        Exception in thread "main" java.lang.OutOfMemoryError: Metaspace
//        at java.lang.Class.getDeclaredMethods0(Native Method)
//        at java.lang.Class.privateGetDeclaredMethods(Class.java:2701)
//        at java.lang.Class.getDeclaredMethod(Class.java:2128)
//        at net.sf.cglib.proxy.Enhancer.getCallbacksSetter(Enhancer.java:630)
//        at net.sf.cglib.proxy.Enhancer.setCallbacksHelper(Enhancer.java:618)
//        at net.sf.cglib.proxy.Enhancer.setThreadCallbacks(Enhancer.java:612)
//        at net.sf.cglib.proxy.Enhancer.createUsingReflection(Enhancer.java:634)
//        at net.sf.cglib.proxy.Enhancer.firstInstance(Enhancer.java:538)
//        at net.sf.cglib.core.AbstractClassGenerator.create(AbstractClassGenerator.java:225)
//        at net.sf.cglib.proxy.Enhancer.createHelper(Enhancer.java:377)
//        at net.sf.cglib.proxy.Enhancer.create(Enhancer.java:285)
//        at com.vm.demo.MetaSpaceDemo.main(MetaSpaceDemo.java:38)



    }

}
