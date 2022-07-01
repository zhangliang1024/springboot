package com.zhlinag.springboot.rocketmq.sample;

/**
 * .::::.
 * .::::::::.
 * :::::::::::    佛主保佑、永无Bug
 * ..:::::::::::'
 * '::::::::::::'
 * .::::::::::
 * '::::::::::::::..
 * ..::::::::::::.
 * ``::::::::::::::::
 * ::::``:::::::::'        .:::.
 * ::::'   ':::::'       .::::::::.
 * .::::'      ::::     .:::::::'::::.
 * .:::'       :::::  .:::::::::' ':::::.
 * .::'        :::::.:::::::::'      ':::::.
 * .::'         ::::::::::::::'         ``::::.
 * ...:::           ::::::::::::'              ``::.
 * ```` ':.          ':::::::::'                  ::::..
 * '.:::::'                    ':'````..
 * ClassName: MqConstants
 * Function:  MQ 常量配置
 * Date: 2022年04月24 16:06:27
 *
 * @author 张良 E-mail:zhangliang01@jingyougroup.com
 * @version V1.0.0
 */
public class MqConstants {

    /**
     * RocketMQ Topic 前缀
     */
    public static final String TOPIC_PREFFIX = "Topic-";
    /**
     * RocketMQ Group 前缀
     */
    public static final String GROUP_PREFFIX = "Group-";

    /**
     * Topic 常量配置
     *
     * @Author: sun
     * @Date: 2021/4/25 3:36 下午
     */
    public static class Topic {
        /**
         * Topic 发送 String 字符串
         **/
        public static final String TOPICSTRING = "TopicString";
        /**
         * Topic 发送 Users  Object
         **/
        public static final String TOPICUSERS = "TopicUsers";

        /**
         * Topic 发送 Users  顺序单队列
         **/
        public static final String TOPICUSERSORDERLY = "TopicUsersOrderLy";
        /**
         * Topic 发送 Users  无序多线程接收
         **/
        public static final String TOPICUSERSCONCURRENTLY = "TopicUsersConcurrently";

        /**
         * Topic 发送 含 tag 标签的消息
         **/
        public static final String TOPICTAG = "TopicTag";

        /**
         * 广播消息
         **/
        public static final String TOPICBROADCASTING = "TopicBroadcasting";

        /**
         * 其他消息语法接收
         **/
        public static final String TOPICOTHER = "TopicOther";

        /**
         * 模拟创建订单事务消息
         **/
        public static final String topic_transaction_reward_coupon = "topic_transaction_createorder";
    }
}
