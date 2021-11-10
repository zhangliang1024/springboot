package com.zhliang.dynamic.thread.pool.alarm;

/**
 * @Author: zhliang
 * @Date: 2021/11/8 20:00
 * @Description:告警类型
 */
public enum AlarmTypeEnum {

    /**
     * 钉钉
     */
    DING_TALK("DingTalk"),

    /**
     * 企业微信
     */
    WEIXIN_TALK("WeiXin"),
    /**
     * 外部系统
     */
    EXTERNAL_SYSTEM("ExternalSystem");

    AlarmTypeEnum(String type) {
        this.type = type;
    };

    private String type;

    public String getType() {
        return type;
    }
}
