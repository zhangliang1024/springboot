package com.zhliang.ons.spring.boot.starter.constants;

import javax.annotation.PostConstruct;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.ons.spring.boot.starter.constants
 * @类描述：MQ 常量
 * @创建人：colin
 * @创建时间：2020/1/2 10:24
 * @version：V1.0
 */
public class MQConstants {

    /**
     * 默认TOPIC
     */
    public static String DEFAULT_TOPIC;

    private String profile;

    @PostConstruct
    public void init() {
        switch (profile) {
            case "dev":
                DEFAULT_TOPIC = TOPIC.DEVELOP_DEFAULT_TOPIC.getName();
                break;
            case "uat":
                DEFAULT_TOPIC = TOPIC.DEFAULT_TOPIC.getName();
                break;
            case "online":
                DEFAULT_TOPIC = TOPIC.ONLINE_DEFAULT_TOPIC.getName();
                break;
            case "pre-online":
                DEFAULT_TOPIC = TOPIC.PRE_ONLINE_DEFAULT_TOPIC.getName();
                break;
        }

        if (null == DEFAULT_TOPIC || "".equals(DEFAULT_TOPIC)) {
            throw new RuntimeException("DEFAULT_TOPIC 不能为空！");
        }
    }

    /**
     * MQ topic
     */
    public enum TOPIC {
        /* uat - 环境 */
        DEFAULT_TOPIC("DEFAULT_TOPIC"),
        /* dev - 环境 */
        DEVELOP_DEFAULT_TOPIC("DEVELOP_DEFAULT_TOPIC"),
        /* online - 环境 */
        ONLINE_DEFAULT_TOPIC("ONLINE_DEFAULT_TOPIC"),
        /* pre-online - 环境 */
        PRE_ONLINE_DEFAULT_TOPIC("PRE_ONLINE_DEFAULT_TOPIC");

        private String name;

        TOPIC(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * MQ tag
     */
    public enum TAG {
        TAG_DEFAULT,
        /*发送 短信*/
        TAG_SEND_SMS,
        /*发送 邮件*/
        TAG_SEND_MAIL,
        /*发送 钉钉消息*/
        TAG_SEND_DINGDING,
        /*发送 支付成功通知*/
        TAG_SEND_APPT_PAY_SUCCESS,
        /*套支付 支付成功通知*/
        TAG_SEND_PACKAGE_PAY_SUCCESS,
        /*自由训练卡 支付成功通知*/
        TAG_SEND_TRAINCARD_PAY_SUCCESS

    }
}
