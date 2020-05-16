package com.zhliang.springboot.rest.api.common.exception;

import com.zhliang.springboot.rest.api.common.enums.IResponseEnum;
import lombok.Getter;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.rest.api.common.core.exception
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/15 13:48
 * @version：V1.0
 */
@Getter
public class BaseException extends RuntimeException {

    /**
     * 返回码
     */
    protected IResponseEnum responseEnum;

    /**
     * 异常参数消息
     */
    protected Object[] args;


    public BaseException(IResponseEnum responseEnum) {
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

    public BaseException(IResponseEnum responseEnum,Object[] args,String msg){
        super(msg);
        this.responseEnum = responseEnum;
        this.args = args;
    }

    public BaseException(IResponseEnum responseEnum,Object[] args,String msg,Throwable cause){
        super(msg,cause);
        this.responseEnum = responseEnum;
        this.args = args;
    }


}
