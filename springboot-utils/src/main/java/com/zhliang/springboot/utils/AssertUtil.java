package com.zhliang.springboot.utils;

import org.springframework.lang.Nullable;

/**
 * @创建人：zhiang
 * @创建时间：2020/5/15 10:51
 * @version：V1.0
 */
public class AssertUtil {

    public static void isNull(@Nullable Object object, String message) {
        if (object == null) {
            throw new RuntimeException(message);
        }
    }
}
