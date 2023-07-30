package com.example.unityauth.service;

import com.example.unityauth.mapper.UserMapper;
import com.example.unityauth.pojo.UnityUser;
import com.example.unityauth.utils.CodeUtil;
import com.example.unityauth.utils.EmailUtil;
import com.example.unityauth.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    EmailUtil emailUtil;
    private String code;
    @Autowired
    RedisUtil redisUtil;


    @Override
    public boolean getCode(String email) {
        code= CodeUtil.createCode();
        emailUtil.sendEmail(email,"验证码",code);
        redisUtil.set(email,code);
        redisUtil.setExpire(email,300,TimeUnit.SECONDS);
        return true;
    }

   public boolean searchUser(String username){
     return   userMapper.userExist(username);
    }

    @Override
    public String register(UnityUser unityUser, String code, String email) {
        if (redisUtil.get(email)==(null)){
            return "验证码失效";
        }
        if(redisUtil.get(email).equals(code)){
            redisUtil.del(email);
            unityUser.setPassword(passwordEncoder.encode(unityUser.getPassword()));
            userMapper.register(unityUser);
            return "注册成功";
        }


        return "验证码错误";
    }

    @Override
    public boolean reset(String email,String password) {
        if (redisUtil.get(email)==null) return false;
        password=passwordEncoder.encode(password);
        return   userMapper.reset(email,password);
    }
}
