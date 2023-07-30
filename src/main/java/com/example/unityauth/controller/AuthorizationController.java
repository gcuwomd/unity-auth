package com.example.unityauth.controller;

import com.example.unityauth.pojo.UnityUser;
import com.example.unityauth.service.UserService;
import com.example.unityauth.utils.ResultUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    @GetMapping("/user/register/code")
    @ResponseBody
    ResultUtil GetCode(@RequestParam  String email){
        if(userimpl.getCode(email)){
    //邮箱验证
            return new ResultUtil(1,"发送成功",null);
        }
        return new ResultUtil(0,"发送失败",null);
    }
    @PostMapping("/user/register")
    @ResponseBody
    ResultUtil register(@RequestBody UnityUser unityUser, @RequestParam String code,String email){

            return ResultUtil.sucess(userimpl.register(unityUser,code,email));
    }
    @GetMapping("/user/reset/code")
    @ResponseBody
    ResultUtil resetCode(@RequestParam  String username){
            if (userimpl.searchUser(username))
                return ResultUtil.sucess();


        return ResultUtil.error();
    }
    @PostMapping("/user/reset")
    @ResponseBody
    ResultUtil reset(@RequestBody Map<String,String> map){
        String code=map.get("code");
        String username=map.get("username");
        String password=map.get("password");
       if( userimpl.reset( username,password,code))
           return  ResultUtil.sucess();
        return ResultUtil.error();
    }
}