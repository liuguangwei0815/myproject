package com.quicky.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.quicky.demo.service.RedisLockBiz;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillRediseLock {
	
	@Autowired
	RedisLockBiz redisLockBiz;
	@Test
	public void testSkill() {
		
		for (int i = 0; i < 50; i++) {
			System.out.println("开始执行线程:");
			new Thread() {
				@Override
				public void run() {
					redisLockBiz.seckilling(String.valueOf(88));
				}
			}.start();;
		}
		
	}

}
