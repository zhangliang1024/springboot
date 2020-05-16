package com.zhliang.springboot.rest.api.common.enums;

/**
 * @Author: zhliang
 * @Date: 2020/5/15 13:51
 * @Description:异常码返回枚举类接口
 * @Version: V1.0
 */
public interface IResponseEnum {

    /**
     * 获取返回码
     */
     int getCode();

    /**
     * 获取返回消息
     */
     String getMessage();

}
