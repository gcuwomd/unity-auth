package com.example.unityauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class userDetailServiceimp implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private  UserServiceImpl userService ;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return  userService.searchUserInfo(username);
    }
}
