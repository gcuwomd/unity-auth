package com.example.unityauth.service;

import com.example.unityauth.pojo.UnityUser;

import java.util.List;

public interface UserService {

    boolean getCode(String email);
   public boolean searchUser(String username);
    String register(UnityUser unityUser, String code, String email);
     boolean reset(String email,String password);
}