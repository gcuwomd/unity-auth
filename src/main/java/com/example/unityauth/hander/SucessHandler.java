package com.example.unityauth.hander;

import com.example.unityauth.mapper.UserMapper;
import com.example.unityauth.pojo.RoleUser;
import com.example.unityauth.pojo.UnitySystemApi;
import com.example.unityauth.service.UserServiceImpl;
import com.example.unityauth.utils.RedisUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
@Component
public class SucessHandler implements AuthenticationSuccessHandler {
    private  static  RedisUtil redisUtil;
    private static UserMapper userMapper;
    @Resource
    public  void  setRedisUtil(RedisUtil redisUtil){
        SucessHandler.redisUtil=redisUtil;
    }
    @Resource
    public  void  setUserMapper(UserMapper userMapper){
        SucessHandler.userMapper=userMapper;
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //获取用户权限，数据，需要遍历
        Object[] authority=authentication.getAuthorities().toArray();
        for(Object item:authority){
            System.out.println(item.toString());
            redisUtil.pushValue(authentication.getName(),item.toString());
        }

        response.sendRedirect("/oauth2/authorize?response_type=code&client_id=messaging-client&scope=message.read&redirect_uri=https://nav.bamdev.space");
    }
}
