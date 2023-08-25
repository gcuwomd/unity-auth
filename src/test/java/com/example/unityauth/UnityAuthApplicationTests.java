package com.example.unityauth;

import com.example.unityauth.mapper.UserMapper;
import com.example.unityauth.service.UserServiceImpl;
import com.example.unityauth.utils.OssUtil;
import com.example.unityauth.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@MapperScan("com.example.unityauth.mapper")
@SpringBootTest
class UnityAuthApplicationTests {
	@Autowired
	RedisUtil redisUtil;
	@Autowired
	OssUtil ossUtil;
	@Autowired
	UserServiceImpl userService;
@Autowired
PasswordEncoder passwordEncoder;
	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate redisTemplate;

	@Test
	void contextLoads() {
		redisUtil.del("2428015329@qq.com");
		System.out.println(userService.getCode("2428015329@qq.com"));
		;
	}

	@Test
	void oss(){
		System.out.println(passwordEncoder.encode("admin123"));
	}





}
