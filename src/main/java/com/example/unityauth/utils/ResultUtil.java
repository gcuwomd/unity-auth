package com.example.unityauth.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class ResultUtil {
    private Integer code;
    private String msg;
    private Object data;

    public ResultUtil(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public  static ResultUtil sucess(Object data){
    return  new ResultUtil(1,"sucess",data);
}
    public  static ResultUtil sucess(){
        return  new ResultUtil(1,"sucess",null);
    }
    public  static ResultUtil error(){
        return  new ResultUtil(0,"error",null);
    }

    @Override
    public String toString() {
        return "ResultUtil{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
