package com.zhliang.ons.spring.boot.starter.client;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.ons.api.*;
import com.aliyun.openservices.ons.api.exception.ONSClientException;
import com.zhliang.ons.spring.boot.starter.property.AliyunOnsProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Properties;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.ons.spring.boot.starter.client
 * @类描述：阿里云 mq
 * @创建人：colin
 * @创建时间：2020/1/2 10:33
 * @version：V1.0
 */
public class MQClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(MQClient.class);

    @Autowired
    private AliyunOnsProperty aliyunOnsProperty;

    private static AliyunOnsProperty aliyunOnsPropertyBean = new AliyunOnsProperty();

    /**
     * ons mq 实例
     */
    private static Producer producer;
    /**
     * 项目使用的 topic, 数据自${aliyun.ons.topic} 读取
     */
    public static String TOPIC;

    /**
     * 初始化mq
     */
    @PostConstruct
    public synchronized void init() {
        BeanUtils.copyProperties(aliyunOnsProperty, aliyunOnsPropertyBean);
        long now = System.currentTimeMillis();
        LOGGER.info("获取ons配置信息...");
        checkConfig(aliyunOnsPropertyBean);
        LOGGER.info("MQClient.Producer 初始化...");
        LOGGER.info("MQClient.Producer producerId [{}]", aliyunOnsPropertyBean.getProducerId());
        LOGGER.info("MQClient.Producer ONSAddr [{}]", aliyunOnsPropertyBean.getOnsAddr());
        Properties properties = new Properties();
        // 您在 MQ 控制台创建的 Producer ID
        properties.put(PropertyKeyConst.ProducerId, aliyunOnsPropertyBean.getProducerId());
        // 鉴权用 AccessKey，在阿里云服务器管理控制台创建
        properties.put(PropertyKeyConst.AccessKey, aliyunOnsPropertyBean.getAccessKey());
        // 鉴权用 SecretKey，在阿里云服务器管理控制台创建
        properties.put(PropertyKeyConst.SecretKey, aliyunOnsPropertyBean.getSecretKey());
        // 设置 TCP 接入域名（此处以公共云的公网接入为例）
        properties.put(PropertyKeyConst.ONSAddr, aliyunOnsPropertyBean.getOnsAddr());
        producer = ONSFactory.createProducer(properties);
        // 在发送消息前，必须调用 start 方法来启动 Producer，只需调用一次即可
        producer.start();
        LOGGER.info("MQClient.Producer 启动完成, 总耗时 {} 毫秒!", (System.currentTimeMillis() - now));
        // 设置topic
        TOPIC = aliyunOnsPropertyBean.getTopic();
        LOGGER.info("设置默认TOPIC: {}", aliyunOnsPropertyBean.getTopic());
        LOGGER.info("MQClient.Producer create success...");
    }

    /**
     * 重连
     */
    public static synchronized void reInit() {
        LOGGER.info("自动重连 ons ...");
        long now = System.currentTimeMillis();
        LOGGER.info("获取ons配置信息...");
        //TODO 验证重连时配置信息是否存在
        checkConfig(aliyunOnsPropertyBean);
        LOGGER.info("MQClient.Producer 初始化...");
        LOGGER.info("MQClient.Producer producerId [{}]", aliyunOnsPropertyBean.getProducerId());
        LOGGER.info("MQClient.Producer ONSAddr [{}]", aliyunOnsPropertyBean.getOnsAddr());
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.ProducerId, aliyunOnsPropertyBean.getProducerId());
        properties.put(PropertyKeyConst.AccessKey, aliyunOnsPropertyBean.getAccessKey());
        properties.put(PropertyKeyConst.SecretKey, aliyunOnsPropertyBean.getSecretKey());
        properties.put(PropertyKeyConst.ONSAddr, aliyunOnsPropertyBean.getOnsAddr());
        producer = ONSFactory.createProducer(properties);
        producer.start();
        LOGGER.info("MQClient.Producer 启动完成, 总耗时 {} 毫秒!", (System.currentTimeMillis() - now));
        LOGGER.info("自动重连 ons 完成...");
        // 设置topic
        TOPIC = aliyunOnsPropertyBean.getTopic();
        LOGGER.info("设置默认TOPIC: {}", aliyunOnsPropertyBean.getTopic());
        LOGGER.info("MQClient.Producer create success...");
    }

    /**
     * 销毁 MQ方法（需在应用停止时调用）
     */
    @PreDestroy
    public synchronized void destroy() {
        long now = System.currentTimeMillis();
        LOGGER.info("销毁 MQClient.Producer 开始...");
        producer.shutdown();
        LOGGER.info("销毁 MQClient.Producer 完成...消耗：{}毫秒!", System.currentTimeMillis() - now);
    }

    /**
     * 同步发送消息，只要不抛异常就表示成功
     *
     * @param tag   Message Tag
     * @param body  Message Body, 任意二进制形式的数据, 需要 Producer 与 Consumer 协商好一致的序列化和反序列化方式
     * @param key   代表消息的业务关键属性，全局唯一，以方便您在无法正常收到消息情况下，可通过 MQ 控制台查询消息并补发; 注意：不设置也不会影响消息正常收发
     */
    public static SendResult send(String tag, byte[] body, String key) {
        if (null == TOPIC || "".equals(TOPIC)) {
            throw new RuntimeException(" TOPIC 未配置！");
        }
        Message msg = new Message(TOPIC, tag, body);

        if (null != key && !"".equals(key)) {
            msg.setKey(key);
        }
        SendResult sendResult = null;
        try {
            // 发送消息，只要不抛异常就是成功
            sendResult = producer.send(msg);
        } catch (ONSClientException e) {
            LOGGER.error(e.getMessage());
            MQClient.reInit();
            try {
                sendResult = producer.send(msg);
            } catch (Exception exception) {
                LOGGER.error("MQ 重连后，发送异常: {}！", exception.getMessage());
                e.printStackTrace();
            }
        }

        // 发送成功 打印 Message ID，以便用于消息发送状态查询
        LOGGER.info("Send Message success. Message ID is: {}; ", sendResult.getMessageId());
        return sendResult;
    }
    /**
     * 同步发送消息，只要不抛异常就表示成功
     *
     * @param topic 消息所属的 Topic 名称
     * @param tag   Message Tag
     * @param body  Message Body, 任意二进制形式的数据, 需要 Producer 与 Consumer 协商好一致的序列化和反序列化方式
     * @param key   代表消息的业务关键属性，全局唯一，以方便您在无法正常收到消息情况下，可通过 MQ 控制台查询消息并补发; 注意：不设置也不会影响消息正常收发
     */
    public static SendResult send(String topic, String tag, byte[] body, String key) {
        Message msg = new Message(topic, tag, body);

        if (null != key && !"".equals(key)) {
            msg.setKey(key);
        }
        SendResult sendResult = null;
        try {
            // 发送消息，只要不抛异常就是成功
            sendResult = producer.send(msg);
        } catch (ONSClientException e) {
            LOGGER.error(e.getMessage());
            MQClient.reInit();
            try {
                sendResult = producer.send(msg);
            } catch (Exception exception) {
                LOGGER.error("MQ 重连后，发送异常: {}！", exception.getMessage());
                e.printStackTrace();
            }
        }

        // 发送成功 打印 Message ID，以便用于消息发送状态查询
        LOGGER.info("Send Message success. Message ID is: {}; ", sendResult.getMessageId());
        return sendResult;
    }

    /**
     * 发送消息，异步Callback形式
     *
     * @param topic 消息所属的 Topic 名称
     * @param tag   Message Tag
     * @param body  Message Body, 任意二进制形式的数据, 需要 Producer 与 Consumer 协商好一致的序列化和反序列化方式
     * @param key   代表消息的业务关键属性，全局唯一，以方便您在无法正常收到消息情况下，可通过 MQ 控制台查询消息并补发; 注意：不设置也不会影响消息正常收发
     */
    public static SendResult[] sendAsync(String topic, String tag, byte[] body, String key) {
        Message msg = new Message(topic, tag, body);

        if (null != key && !"".equals(key)) {
            msg.setKey(key);
        }
        final SendResult[] result = {null};
        try {
            // 发送消息，异步Callback形式
            producer.sendAsync(msg, new SendCallback() {
                @Override
                public void onSuccess(final SendResult sendResult) {
                    assert sendResult != null;
                    result[0] = sendResult;
                    LOGGER.info(JSONObject.toJSONString(sendResult));
                }

                @Override
                public void onException(final OnExceptionContext context) {
                    //出现异常意味着发送失败，为了避免消息丢失，建议缓存该消息然后进行重试。
                    LOGGER.error(context.getException().getMessage());
                }
            });
        } catch (ONSClientException e) {
            MQClient.reInit();
            producer.sendAsync(msg, new SendCallback() {
                @Override
                public void onSuccess(final SendResult sendResult) {
                    assert sendResult != null;
                    result[0] = sendResult;
                    LOGGER.info(JSONObject.toJSONString(sendResult));
                }

                @Override
                public void onException(final OnExceptionContext context) {
                    //出现异常意味着发送失败，为了避免消息丢失，建议缓存该消息然后进行重试。
                    LOGGER.error(context.getException().getMessage());
                }
            });
        }

        // 打印 Message ID，以便用于消息发送状态查询
        LOGGER.info("Send Message success. Message ID is: {}; ", result[0].getMessageId());
        return result;

    }


    /**
     * 校验配置属性是否配置
     */
    private static void checkConfig(AliyunOnsProperty onsProperty) {
        Assert.notNull(onsProperty, "aliyun.ons 未配置！");
        if (null == onsProperty.getTopic() || "".equals(onsProperty.getTopic())) {
            throw new RuntimeException("aliyun.ons.topic 未配置！");
        }
        if (null == onsProperty.getProducerId() || "".equals(onsProperty.getProducerId())) {
            throw new RuntimeException("aliyun.ons.producerId 未配置！");
        }
        if (null == onsProperty.getAccessKey() || "".equals(onsProperty.getAccessKey())) {
            throw new RuntimeException("aliyun.ons.accessKey 未配置！");
        }
        if (null == onsProperty.getSecretKey() || "".equals(onsProperty.getSecretKey())) {
            throw new RuntimeException("aliyun.ons.secretKey 未配置！");
        }
        if (null == onsProperty.getOnsAddr() || "".equals(onsProperty.getOnsAddr())) {
            throw new RuntimeException("aliyun.ons.onsAddr 未配置！");
        }
    }

}
