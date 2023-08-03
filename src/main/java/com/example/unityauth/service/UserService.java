package com.example.unityauth.service;

import com.example.unityauth.pojo.UnitySystemApi;
import com.example.unityauth.pojo.UnityUser;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface UserService {

    boolean getCode(String email);
   public boolean searchUser(String username);
    String register(UnityUser unityUser, String code, String email);

    boolean reset(String username, String password, String code);

    User searchUserInfo(String username);
    List<UnitySystemApi> getURL(String roelId);
}