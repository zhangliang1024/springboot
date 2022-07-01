package com.zhliang.springboot.elasticsearch.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.elasticsearch.utils
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/12/23 17:35
 * @version：V1.0
 */
public class ObjToMap {

    /**
     * 将Object对象里面的属性和值转化成Map对象
     */
    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            map.put(fieldName, value);
        }
        return map;
    }

}
