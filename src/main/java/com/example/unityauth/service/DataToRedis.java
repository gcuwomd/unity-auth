package com.example.unityauth.service;

import com.example.unityauth.mapper.UserMapper;
import com.example.unityauth.pojo.UrlWR;
import com.example.unityauth.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataToRedis {
            @Autowired
    RedisUtil redisUtil;
            @Autowired
    UserMapper userMapper;


        @Scheduled(fixedDelay = 60000*60*2) //2小时执行一次
        public void execute() {
            // 执行数据库查询，比如获取所有用户
            List list=userMapper.userMsg();
            if(list!=null){
                for(Object item:list){
                    redisUtil.hashSet(((UrlWR) item).getWebsiteId(),((UrlWR) item).getRoleId(),((UrlWR) item).getUrl());
                }
            }



        }


}
