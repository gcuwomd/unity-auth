package com.example.unityauth.pojo;

import lombok.Data;
import lombok.NonNull;

import java.net.URL;
@Data
public class UnityUser {
    @NonNull
    private String departmentId;
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String name;
    @NonNull
    private String  phone;
    @NonNull
    private String email;
    private String nickName;
    private String major;
    private  String grade;
    private String classNumber;
    private String dorm;
    private String politicsStatus;
    private URL avatar;
    private String position;


}
