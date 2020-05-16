package com.zhliang.springboot.api.mideng.utils;

import org.apache.commons.lang.StringUtils;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.api.mideng.utils
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/6 15:13
 * @version：V1.0
 */
public class StrUtil {

    public static String EMPTY = "";

    public static boolean isNotEmpty(String toString) {
        return StringUtils.isNotEmpty(toString);
    }

    public static boolean isBlank(String token) {
        return StringUtils.isBlank(token);
    }
}
