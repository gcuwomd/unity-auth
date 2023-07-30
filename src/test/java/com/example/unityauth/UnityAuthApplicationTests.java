package com.example.unityauth;

import com.example.unityauth.mapper.UserMapper;
import com.example.unityauth.service.UserServiceImpl;
import com.example.unityauth.utils.OssUtil;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@MapperScan("com.example.unityauth.mapper")
@SpringBootTest
class UnityAuthApplicationTests {
	@Autowired
	OssUtil ossUtil;
	@Autowired
	UserServiceImpl userService;

	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate redisTemplate;

	@Test
	void contextLoads() {
		redisTemplate.opsForValue().set("spring","hello spring");
		System.out.println(redisTemplate.opsForValue().get("spring"));
	}

	@Test
	void oss(){
	}

	@Test
	void forget(){
		System.out.println(userService.searchUser("202110098171"));
	}
	@Test
	void reset(){
		System.out.println(userService.reset("2428015329@qq.com","202110098171"));
	}

}
