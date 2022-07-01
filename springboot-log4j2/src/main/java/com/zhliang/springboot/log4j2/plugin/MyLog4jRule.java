package com.zhliang.springboot.log4j2.plugin;

import java.util.HashMap;
import java.util.Map;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.log4j2.plugin
 * @类描述：
 * 现在拦截加密的日志有三类:
 * 1，身份证
 * 2，姓名
 * 3，身份证号
 * @创建人：zhiang
 * @创建时间：2021/3/14 15:55
 * @version：V1.0
 */


public class MyLog4jRule {

    /**
     * 正则匹配 关键词 类别
     */
    public static Map<String, String> regularMap = new HashMap<>();
    /**
     * TODO  可配置
     * 此项可以后期放在配置项中
     */
    public static final String USER_NAME_STR = "Name,name,联系人,姓名";
    public static final String USER_IDCARD_STR = "empCard,idCard,身份证,证件号";
    public static final String USER_PHONE_STR = "mobile,Phone,phone,电话,手机";

    /**
     * "(\\d{6}\\d{4}(([0][1-9])|([1][0-2]))(([0][1-9])|([1][0-9])|([2][0-9])|([3][0-1]))[0-9]{3}[xX0-9]) | (\\d{6}\\d{2}(([0][1-9])|([1][0-2]))(([0][1-9])|([1][0-9])|([2][0-9])|([3][0-1]))[0-9]{3})";
     */
    private static String IDCARD_REGEXP = "(\\d{17}[0-9Xx]|\\d{14}[0-9Xx])";
    private static String USERNAME_REGEXP = "[\\u4e00-\\u9fa5]{2,4}";
    private static String PHONE_REGEXP = "(?<!\\d)(?:(?:1[3456789]\\d{9})|(?:861[356789]\\d{9}))(?!\\d)";

    static {
        regularMap.put(USER_NAME_STR, USERNAME_REGEXP);
        regularMap.put(USER_IDCARD_STR, IDCARD_REGEXP);
        regularMap.put(USER_PHONE_STR, PHONE_REGEXP);
    }

}
