package com.example.unityauth.mapper;

import com.example.unityauth.pojo.UnityUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper  {
   UnityUser userExist(String username);
   boolean register(UnityUser unityUser);
   boolean reset(String username,String password);

}
