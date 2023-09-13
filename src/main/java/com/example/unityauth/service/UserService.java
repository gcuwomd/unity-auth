package com.example.unityauth.service;

import com.example.unityauth.pojo.UnitySystemApi;
import com.example.unityauth.pojo.UnityUser;
import com.example.unityauth.utils.ResultUtil;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface UserService {

    ResultUtil getCode(String email);
   public ResultUtil searchUser(String username);
    ResultUtil register(UnityUser unityUser, String code, String email);

    boolean reset(String username, String password, String code);

    User searchUserInfo(String username);
    List<UnitySystemApi> getURL(String roelId);

    ResultUtil getCodeByMessage(String phone);

    ResultUtil registerByMessage(UnityUser unityUser, String code, String email);

    ResultUtil searchUserByMessage(String username);

    boolean resetByMessage(String username, String password, String code);
}