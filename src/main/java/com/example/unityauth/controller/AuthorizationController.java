package com.example.unityauth.controller;

import com.example.unityauth.pojo.UnityUser;
import com.example.unityauth.service.UserService;
import com.example.unityauth.utils.ResultUtil;
import jakarta.servlet.http.HttpServletResponse;
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
    @GetMapping(value = "/oauth2/consent")
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
   return  userimpl.getCode(email);
    }
    @PostMapping("/user/register")
    @ResponseBody
    ResultUtil register(@RequestBody UnityUser unityUser, @RequestParam String code,String email){

            return userimpl.register(unityUser,code,email);
    }
    @GetMapping("/user/reset/code")
    @ResponseBody
    ResultUtil resetCode(@RequestParam  String username){

        return  userimpl.searchUser(username);
    }
    /**
     * 兼容短信的邮箱发送
     *
     *
     * */
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
    @GetMapping("/user/register/message/code")
    @ResponseBody
    ResultUtil GetMessageCode(@RequestParam  String phone){
        return  userimpl.getCodeByMessage(phone);
    }
    @PostMapping("/user/message/register")
    @ResponseBody
    ResultUtil registerByMessage(@RequestBody UnityUser unityUser, @RequestParam String code,String phone){

        return userimpl.registerByMessage(unityUser,code,phone);
    }
    @GetMapping("/user/reset/message/code")
    @ResponseBody
    ResultUtil resetCodeByMessage(@RequestParam String username){

        return  userimpl.searchUserByMessage(username);
    }

}