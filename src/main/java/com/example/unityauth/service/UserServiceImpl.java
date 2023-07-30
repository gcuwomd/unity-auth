package com.example.unityauth.service;

import com.example.unityauth.mapper.UserMapper;
import com.example.unityauth.pojo.UnityUser;
import com.example.unityauth.utils.CodeUtil;
import com.example.unityauth.utils.EmailUtil;
import com.example.unityauth.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
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

   public List searchUser(String username){
     return   userMapper.searchUser(username);
    }

    @Override
    public String register(UnityUser unityUser, String code, String email) {
        if (redisUtil.get(email).equals(null)){
            return "验证码失效";
        }
        if(redisUtil.get(email).equals(code)){
            redisUtil.del(email);
            userMapper.register(unityUser);
            return "注册成功";
        }


        return "验证码错误";
    }

    @Override
    public boolean forget() {
        return false;
    }
}
