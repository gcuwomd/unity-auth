package com.example.unityauth.mapper;

import com.example.unityauth.pojo.UnityUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper  {
   boolean userExist(String username);
   boolean register(UnityUser unityUser);
   boolean reset(String email,String password);

}
