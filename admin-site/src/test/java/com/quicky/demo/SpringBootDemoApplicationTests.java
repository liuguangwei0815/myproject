package com.quicky.demo;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(SpringBootDemoApplicationTests.class);


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisCacheTemplate;


    @Test
    public void redisTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        IntStream.range(0, 1000).forEach(i ->
                executorService.execute(() -> stringRedisTemplate.opsForValue().increment("kk", 1))
        );
    }

    @Test
    public void redisTestString() {
        stringRedisTemplate.opsForValue().set("carry", "chan");
        final String v1 = stringRedisTemplate.opsForValue().get("carry");
        log.info("[字符缓存结果] - [{}]", v1);
    }

    @Test
    public void redisTestObject() {
        //以下只演示整合，具体Redis命令可以参考官方文档，Spring Data Redis 只是改了个名字而已，Redis支持的命令它都支持
//        String key = "carry:user:1";
//        ValueOperations<String, Object> valueOperations = redisCacheTemplate.opsForValue();
//        valueOperations.set(key, );
//        //对应 String（字符串）
//        final User user = (User) valueOperations.get(key);
//        log.info("[对象缓存结果] - [{}]", user);
    }

    @Test
    public void redisTestList() {
        ListOperations<String, Object> listOperations = redisCacheTemplate.opsForList();
        listOperations.trim("key1", 1, 0);
        List<Object> list = listOperations.range("key1", 0, -1);
        log.info("[对象缓存结果] - {}", list);
        listOperations.leftPush("key1", 1);
        listOperations.leftPush("key1", 2);
        listOperations.rightPush("key1", 3);
        list = listOperations.range("key1", 0, -1);
        log.info("[对象缓存结果] - {}", list);
    }

    @Test
    public void redisTestHash() {
        HashOperations<String, Object, Object> hashOperations = redisCacheTemplate.opsForHash();
        hashOperations.put("hkey", "k1", "v1");
        hashOperations.put("hkey", "k2", "v2");
        hashOperations.put("hkey", "k3", "v3");
        Map<Object, Object> map = hashOperations.entries("hkey");
        log.info("[对象缓存结果] - [{}]", map);
    }

    @Test
    public void redisTestSet() {
        SetOperations<String, Object> setOperations = redisCacheTemplate.opsForSet();
        setOperations.add("skey", "v1", "v2", "v3", "v4");
        Cursor<Object> cursor = setOperations.scan("skey", ScanOptions.NONE);
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
        log.info("[对象缓存结果] - [{}]", cursor);
    }

    @Test
    public void redisTestZSet() {
        ZSetOperations<String, Object> zSetOperations = redisCacheTemplate.opsForZSet();
        ZSetOperations.TypedTuple<Object> tuple1 = new DefaultTypedTuple<>("v1", 9.6);
        ZSetOperations.TypedTuple<Object> tuple2 = new DefaultTypedTuple<>("v2", 9.9);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<>();
        tuples.add(tuple1);
        tuples.add(tuple2);
        zSetOperations.add("zkey", tuples);
        Set<Object> set = zSetOperations.range("zkey", 0, -1);
        log.info("[对象缓存结果] - {}", set);
    }
}