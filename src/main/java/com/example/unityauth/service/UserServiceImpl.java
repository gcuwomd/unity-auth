package com.example.unityauth.service;

import com.example.unityauth.mapper.UserMapper;
import com.example.unityauth.pojo.RoleUser;
import com.example.unityauth.pojo.UnitySystemApi;
import com.example.unityauth.pojo.UnityUser;
import com.example.unityauth.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    EmailUtil emailUtil;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    MessageUtil messageUtil;
    String code;
    @Override
    public ResultUtil getCode(String email) {
        long time=redisUtil.getExpire(email,TimeUnit.SECONDS);
        if(time!=-2){
            return new ResultUtil(200,time+"s后重试",null);
        }
        code= CodeUtil.createCode(); ;
        emailUtil.sendEmail(email,"验证码",code);
        redisUtil.set(email,code);
        redisUtil.setExpire(email,300,TimeUnit.SECONDS);
        return  new ResultUtil(200,"发送成功",null);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultUtil register(UnityUser unityUser, String code, String email) {
         if((!userMapper.existDepartment(unityUser.getDepartmentId())) ) {
             return new ResultUtil(403,"无此部门编号", null) ;
         }
        if (redisUtil.get(email)==(null)){
            return new ResultUtil(403,"验证码过期", null) ;
        }
        if(redisUtil.get(email).equals(code)){
            redisUtil.del(email);
            unityUser.setPassword(passwordEncoder.encode(unityUser.getPassword()));
            if(!userMapper.register(unityUser))
             return  new ResultUtil(403,"用户已存在",null);
        }
        userMapper.setDefaultRole(unityUser.getUsername());
        return new ResultUtil(200,"注册成功",null);
    }
@Override
    public ResultUtil searchUser(String username){
    long time=redisUtil.getExpire("R"+username,TimeUnit.SECONDS);
    if(time!=-2){
        return new ResultUtil(200,time+"s后重试",null);
    }
        UnityUser user=userMapper.userExist(username);
        if(user!=null){
            code=CodeUtil.createCode();
            emailUtil.sendEmail(user.getEmail(),"验证码",code);
            redisUtil.set("R"+username,code);
            redisUtil.setExpire("R"+username,300,TimeUnit.SECONDS);
        }


        return  new ResultUtil(200,"发送成功",null);
    }
    @Override
    public boolean reset(String username, String password, String code) {
        if (redisUtil.get("R"+username)==null) return false;
        if(!redisUtil.get("R"+username).equals(code)) return  false;
        password=passwordEncoder.encode(password);
        return   userMapper.reset(username,password);
    }

    @Override
    public User searchUserInfo(String username) {
       String password= userMapper.userInfoPassword(username).getPassword();
        List list=userMapper.userInfoRole(username);
        ArrayList<String>authority =new ArrayList<>();
        for (Object item : list) {
            // 对每个元素执行操作
            authority.add(((RoleUser)item).getRoleId());
        }

        return new User(username,password,AuthorityUtils.createAuthorityList(authority));
    }

    @Override
    public List<UnitySystemApi> getURL(String roelId) {
        {
            return  userMapper.userInfoUrl(roelId);
        }
    }
/**
 * 注册时发送验证码
 * @param phone 注册的手机号码
 *
 * */
    @Override
    public ResultUtil getCodeByMessage(String phone) {
        String code = CodeUtil.createCode();
        messageUtil.sendRegisterMessage(phone,code);
        redisUtil.set(phone,code);
        return ResultUtil.sucess();
    }

    @Override
    public ResultUtil registerByMessage(UnityUser unityUser, String code, String phone) {
        if((!userMapper.existDepartment(unityUser.getDepartmentId())) ) {
            return new ResultUtil(403,"无此部门编号", null) ;
        }
        if (redisUtil.get(phone)==(null)){
            return new ResultUtil(403,"验证码过期", null) ;
        }
        if(redisUtil.get(phone).equals(code)){
            redisUtil.del(phone);
            unityUser.setPassword(passwordEncoder.encode(unityUser.getPassword()));
            if(!userMapper.register(unityUser))
                return  new ResultUtil(403,"用户已存在",null);
        }
        userMapper.setDefaultRole(unityUser.getUsername());
        return new ResultUtil(200,"注册成功",null);
    }

    @Override
    public ResultUtil searchUserByMessage(String username) {
        long time=redisUtil.getExpire("R"+username,TimeUnit.SECONDS);
        if(time!=-2){
            return new ResultUtil(200,time+"s后重试",null);
        }
        UnityUser user=userMapper.userExist(username);
        if(user!=null){
            code=CodeUtil.createCode();
            messageUtil.sendForgetPaasswordMessage(user.getPhone(),code);
            redisUtil.set("R"+username,code);
            redisUtil.setExpire("R"+username,300,TimeUnit.SECONDS);
        }


        return  new ResultUtil(200,"发送  ·去为3erweqrrrrrrrrrrrrrrrr成功",null);
    }

    @Override
    public boolean resetByMessage(String username, String password, String code) {
        return false;
    }

}
