package com.example.unityauth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


@SpringBootTest
class UnityAuthApplicationTests {

	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate redisTemplate;

	@Test
	void contextLoads() {
		redisTemplate.opsForValue().set("spring","hello spring");
		System.out.println(redisTemplate.opsForValue().get("spring"));
	}

}
