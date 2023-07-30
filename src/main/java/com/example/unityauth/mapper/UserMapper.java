package com.example.unityauth.mapper;

import com.example.unityauth.pojo.UnityUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper  {
   List searchUser(String username);
   boolean register(UnityUser unityUser);
   boolean reset(UnityUser unityUser);
   boolean forgetCheck(String username);
}
