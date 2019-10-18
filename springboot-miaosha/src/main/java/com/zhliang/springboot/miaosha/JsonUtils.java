package com.zhliang.springboot.miaosha;

import com.alibaba.fastjson.JSON;

/**
 * @Author: colin
 * @Date: 2019/9/5 16:59
 * @Description:
 * @Version: V1.0
 */
public class JsonUtils {


    public static String beanToString(Object obj){
        return JSON.toJSONString(obj);
    }


    public static <T> T StingToBean(String json,Class<T> clazz){
        return JSON.parseObject(json, clazz);
    }
}
