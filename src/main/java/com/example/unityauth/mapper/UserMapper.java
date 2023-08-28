package com.example.unityauth.mapper;

import com.example.unityauth.pojo.RoleUser;
import com.example.unityauth.pojo.UnitySystemApi;
import com.example.unityauth.pojo.UnityUser;
import com.example.unityauth.pojo.UrlWR;
import org.apache.ibatis.annotations.Mapper;

import javax.management.relation.Role;
import java.util.List;

@Mapper
public interface UserMapper  {
   UnityUser userExist(String username);
   boolean register(UnityUser unityUser);
   boolean reset(String username,String password);
   UnityUser userInfoPassword(String username);
   List<RoleUser> userInfoRole(String username);
   List<UnitySystemApi> userInfoUrl(String roleId);
    List<UrlWR>  userMsg();
    boolean existDepartment(String departmentId);

    boolean setDefaultRole(String username);
}
