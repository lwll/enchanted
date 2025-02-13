package com.lwsmilence.enchanted.common;

import com.lwsmilence.enchanted.common.redis.RedissonUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
@Slf4j
public class RedisTests {

    @Autowired
    RedissonUtil redissonUtil;

    @Data
    static
    class user {
        private String name;
        private int age;
    }

    @Test
    public void testRBucket () {
        RBucket<user> bucket = redissonUtil.getRedisson().getBucket("redisson:test:bucket");

        user user = new user();
        user.setAge(1);
        user.setName("张三");
        bucket.set(user);

        log.info("current msg is : {}", bucket.get());
    }

    @Test
    public void testRList() {
        RList<String> list = redissonUtil.getRedisson().getList("redisson:test:list");

        list.clear();

        list.add("1");
        list.add("2");

        list.forEach(System.out::println);
    }

    @Test
    public void testRMap() {
        RMap<String, String> map = redissonUtil.getRedisson().getMap("redisson:test:map");

        map.clear();

        map.put("1", "1");
        map.put("2", "2");

        map.forEach((k, v) -> log.info("key: {}, value: {}", k, v));
    }

    @Test
    public void  testRLock() throws InterruptedException {
        RLock lock = redissonUtil.getRedisson().getLock("redisson:test:lock");

        int threads = 10;
        int turns = 1000;

        ExecutorService executorService = Executors.newFixedThreadPool(threads);

        CountDownLatch countDownLatch = new CountDownLatch(threads);

        final int[] count = {0};

        for (int i = 0; i < threads; i++) {
            executorService.submit(() -> {
                // 会在redis中生成一个临时的键值对。 key为资源名（锁名），value为map。此map中的key为UUID:threadId，如
                // a78e4d7b-eaf1-4dba-b062-c8b33c5bf3a6:117，value为重入值。此时为1
                lock.lock();
                // 可重入
                lock.lock();

                for (int j = 0; j < turns; j++) {
                    count[0]++;
                }

                countDownLatch.countDown();

                // unlock会使可重入值减1，为0时，会删除该key
                lock.unlock();
                lock.unlock();
            });
        }

        countDownLatch.await();

        log.info("count: {}", count[0]);
    }

    @Test
    public void  testRAtomicLong() throws InterruptedException {
        RAtomicLong atomicLong = redissonUtil.getRedisson().getAtomicLong("redisson:test:atomicLong");

        int threads = 10;
        int turns = 1000;

        ExecutorService executorService = Executors.newFixedThreadPool(threads);

        CountDownLatch countDownLatch = new CountDownLatch(threads);

        for (int i = 0; i < threads; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < turns; j++) {
                    atomicLong.incrementAndGet();
                }

                countDownLatch.countDown();
            });
        }

        countDownLatch.await();

        log.info("atomicLong: {}", atomicLong.get());
    }

    @Test
    public void  testRLongAdder() throws InterruptedException {
        RLongAdder longAdder = redissonUtil.getRedisson().getLongAdder("redisson:test:longAdder");

        int threads = 10;
        int turns = 1000;

        ExecutorService executorService = Executors.newFixedThreadPool(threads);

        CountDownLatch countDownLatch = new CountDownLatch(threads);

        for (int i = 0; i < threads; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < turns; j++) {
                    longAdder.increment();
                }

                countDownLatch.countDown();
            });
        }

        countDownLatch.await();

        log.info("longAddr: {}", longAdder.sum());
        longAdder.destroy();
    }

}
