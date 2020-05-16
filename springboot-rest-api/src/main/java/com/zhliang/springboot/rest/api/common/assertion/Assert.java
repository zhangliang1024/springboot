package com.zhliang.springboot.rest.api.common.assertion;

import com.zhliang.springboot.rest.api.common.exception.BaseException;

import javax.validation.constraints.NotNull;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.rest.api.common.core
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/15 13:46
 * @version：V1.0
 */
public interface Assert {

    /**
     * 创建异常
     */
    BaseException newException(Object... args);

    /**
     * 创建异常
     */
    BaseException newException(Throwable cause,Object... args);


    /**
     * 断言对象obj非空，如果为空则抛出异常
     */
    default void assertNotNnull(@NotNull Object obj){
        if(obj == null){
            throw newException();
        }
    }

    /**
     * 断言对象obj非空，如果为空则抛出异常
     * 异常信息args支持传递参数方式，避免在判断之前进行字符串拼接操作
     */
    default void assertNotNnull(@NotNull Object obj,Object... args){
        if(obj == null){
            throw newException(args);
        }
    }

    /**
     * 断言字符串不为空串（长度为0）为空串，则抛出异常
     */
    default void assertNotEmpty(@NotNull String str){
        if(null  == str || "".equals(str.trim())){
            throw newException();
        }
    }

    /**
     * 断言字符串不为空串（长度为0）为空串，则抛出异常
     * 异常信息args支持传递参数方式，避免在判断之前进行字符串拼接操作
     */
    default void assertNotEmpty(@NotNull String str,Object... args){
        if(null  == str || "".equals(str.trim())){
            throw newException(args);
        }
    }
}
