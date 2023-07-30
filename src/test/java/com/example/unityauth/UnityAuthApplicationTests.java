package com.example.unityauth;

import com.example.unityauth.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@MapperScan("com.example.unityauth.mapper")
@SpringBootTest
class UnityAuthApplicationTests {
	@Autowired
	UserMapper userMapper;

	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate redisTemplate;

	@Test
	void contextLoads() {
		redisTemplate.opsForValue().set("spring","hello spring");
		System.out.println(redisTemplate.opsForValue().get("spring"));
	}

}
