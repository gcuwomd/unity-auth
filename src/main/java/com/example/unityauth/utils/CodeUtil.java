package com.example.unityauth.utils;

import java.util.Random;

public class CodeUtil {
     public  static  String createCode(){
         Random random = new Random();
         int code = random.nextInt(9000) + 1000;
         return String.valueOf(code);
    }
}
