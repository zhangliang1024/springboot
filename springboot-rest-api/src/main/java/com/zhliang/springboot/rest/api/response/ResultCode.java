package com.zhliang.springboot.rest.api.response;
import	java.io.DataInputStream;


import lombok.Getter;

/**
 * @Author: colin
 * @Date: 2020/1/6 09:37
 * @Description:返回状态码
 * @Version: V1.0
 */
@Getter
public enum ResultCode {

    /*成功状态码*/
    SUCCESS(1,"成功"),
    /*参数错误：1001-1999*/
    PARAM_IS_INVALID(1001,"请求参数无效"),
    PARAM_IS_BLANK(1002,"请求参数为空"),
    PARAM_TYPE_BIND_ERROR(1003,"请求参数类型错误"),
    PARAM_NOT_COMPLETE(1004,"请求参数缺失"),
    /*用户错误：2001-2999*/
    USER_NOT_LOGGED_IN(2001,"用户未登录,访问的路径需要验证,请登录"),
    USER_LOGIN_ERROR(2002,"账号不存在或密码错误"),
    USER_ACCOUNT_FORBIDDEN(2003,"账号已被禁用"),
    USER_NOT_EXIST(2004,"用户不存在"),
    USER_HAS_EXISTED(2005,"用户已存在"),

    SYSTEM_ERROR(5001,"系统异常"),
    ;

    private Integer code;
    private String message;

    ResultCode (Integer code,String message){
        this.code = code;
        this.message = message;
    }

    public Integer code(){
        return this.code;
    }
    public String message(){
        return this.message;
    }

}
