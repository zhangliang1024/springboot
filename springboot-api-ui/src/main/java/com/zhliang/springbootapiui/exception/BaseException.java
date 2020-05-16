package com.zhliang.springbootapiui.exception;


import lombok.Getter;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springbootapiui.exception
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/6 09:47
 * @version：V1.0
 */
@Getter
public class BaseException extends RuntimeException {

    /**
     * 返回码
     */
    protected IResponseEnum responseEnum;
    /**
     * 异常消息参数
     */
    protected Object[] args;

    public BaseException(IResponseEnum responseEnum){
        super(responseEnum.getMessage());
        this.responseEnum = responseEnum;
    }

    public BaseException(int code,String msg){
        super(msg);
        this.responseEnum = new IResponseEnum() {
            @Override
            public int getCode() {
                return code;
            }

            @Override
            public String getMessage() {
                return msg;
            }
        };
    }

    public BaseException(IResponseEnum responseEnum,Object[] args,String message){
        super(message);
        this.responseEnum = responseEnum;
        this.args =args;
    }

    public BaseException(IResponseEnum responseEnum, Object[] args, String message, Throwable cause){
        super(message,cause);
        this.responseEnum = responseEnum;
        this.args =args;
    }
}
