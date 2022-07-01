package com.zhliang.springboot.utils.jwt;

/**
 * @Author: zhliang
 * @Date: 2021/10/11 14:52
 * @Description:JWT常量值
 * @Version: V1.0
 */
public interface SecretConstant {

    // 签名秘钥
    public static final String BASE64SECRET = "ZW]4l5JH[m6Lm)LaQEjpb!4E0lRaG(";

    // 超时毫秒数（默认30分钟）
    public static final int EXPIRESSECOND = 1800000;

    // 用于JWT加密的密匙
    public static final String DATAKEY = "u^3y6SPER41jm*fn";

}
