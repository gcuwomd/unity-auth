package com.example.unityauth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class pageController {


    @RequestMapping("/login")
    public String loginPage() {

        return "login";


    }
}