package com.example.unityauth.controller;

import com.example.unityauth.pojo.UnityUser;
import com.example.unityauth.service.UserService;
import com.example.unityauth.utils.ResultUtil;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;


/**
 * 认证服务器相关自定接口
 *
 * @author vains
 */
@Controller
@RequiredArgsConstructor
public class AuthorizationController {
@Autowired
UserService userimpl;

//login登录页
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    //授权页面
    @GetMapping("/oauth2/consent")
    public String consent(@RequestParam String scope, @RequestParam String client_id, @RequestParam String state, Model model,Principal principal) {
        model.addAttribute("principalName",principal.getName());
        model.addAttribute("scopes", scope.split(" "));
        model.addAttribute("clientId", client_id);
        model.addAttribute("state", state);
        return "consent";
    }
    @GetMapping("/register")
    @ResponseBody
    String test(){

        return "email";
    }
    @GetMapping("/getCode")
    @ResponseBody
    ResultUtil GetCode(@RequestParam   @Email(message = "请输入有效的邮箱地址") String email){
       HashMap claim=new HashMap<>();
        if(userimpl.getCode(email)){

            return ResultUtil.sucess();
        }
        return ResultUtil.error();
    }
    @PostMapping("/user/register")
    @ResponseBody
    ResultUtil register(@RequestBody UnityUser unityUser, @RequestParam String code,String email){



            return ResultUtil.sucess();
    }
    @GetMapping("/user/forget")
    @ResponseBody
    HashMap forget(@RequestParam  String username){
        HashMap claim=new HashMap<>();

        return claim;
    }
    @PostMapping("/user/reset")
    @ResponseBody
    HashMap register(@RequestBody Map maps){
        HashMap map=new HashMap();



        return map;
    }
}