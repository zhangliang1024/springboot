package com.zhliang.springboot.api.mideng.utils;
import	java.util.Random;
import java.util.UUID;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.api.mideng.utils
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/6 15:10
 * @version：V1.0
 */
public class RandomUtil {

    public static String randomUUID() {
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
