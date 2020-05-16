package com.zhliang.springboot.api.mideng.enums;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.api.mideng.enums
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/6 15:15
 * @version：V1.0
 */
public enum  ResponseCode {
    ILLEGAL_ARGUMENT(100,"illegal_argument"),
    REPETITIVE_OPERATION(100,"repetitive_operation")
    ;

    private int code;
    private String msg;

    ResponseCode(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
