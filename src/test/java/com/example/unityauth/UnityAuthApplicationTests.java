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
		userService.searchUser("202110098171");
		System.out.println(userService.reset("202110098171","202110098171","1234"));
	}

	@Test
	void user(){
		userService.searchUserInfo("202010089000");
	}

	@Test
	void pa(){
		System.out.println(passwordEncoder.encode("admin123"));
		;
	}


}
