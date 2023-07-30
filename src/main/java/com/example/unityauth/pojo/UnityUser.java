package com.example.unityauth.pojo;

import lombok.Data;

import java.net.URL;
@Data
public class UnityUser {

    private String username;
    private String password;
    private String name;
    private String  phone;
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
