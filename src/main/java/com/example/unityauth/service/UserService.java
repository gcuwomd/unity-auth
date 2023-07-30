package com.example.unityauth.service;

import com.example.unityauth.pojo.UnityUser;

import java.util.List;

public interface UserService {

    boolean getCode(String email);
    List searchUser(String username);
    String register(UnityUser unityUser, String code, String email);
     boolean forget();
}