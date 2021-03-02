package com.redis.demo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * @Created on 2021/3/2.
 * @Author by zbk
 * @Description:
 */
public class JRedisDemo {


    // 简单api调用
    public static void test1() {


        Jedis jedis = new Jedis("127.0.0.1", 6379);

        jedis.set("k1", "v1");
        jedis.set("k2", "v2");
        jedis.set("k3", "v3");
        System.out.println(jedis.get("k3"));

        jedis.hset("hset1", "username", "zbk");

        jedis.lpush("list1", "v1");

        List<String> list1 = jedis.lrange("list1", 0, -1);
        System.out.println(list1);


        jedis.zadd("zset1", 10, "v1");
        jedis.zadd("zset1", 20, "v2");
        jedis.zadd("zset1", 30, "v3");

        jedis.mset("str1", "v1", "str2", "v2", "str3", "v3");


    }


    public static void main(String[] args) {

//        test1();

        test2();

        test3();


    }

    private static void test3() {

        Jedis jedis = new Jedis("127.0.0.1", 6379);

        jedis.slaveof("xx.xx.xx.xx", 6379);//一般在配置文件中指定


    }

    private static void test2() {

        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.watch("k1");

        Transaction transaction = jedis.multi();

        transaction.set("k8", "v8");
        transaction.set("k9", "v9");
        transaction.set("k10", "v10");

        // exec和discard后系统会unwatch，无需手动
        transaction.exec();
//        transaction.discard();


        System.out.println("事务修改成功!");

    }
}
