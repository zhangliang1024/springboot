package com.zhliang.dynamic.thread.pool.alarm;

import com.zhliang.dynamic.thread.pool.service.AlarmMessage;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2021/11/9 09:43
 */
public class AlarmManager {
    /**
     * 存储上次告警的时间，Key:名称 Value:时间戳
     */
    private static Map<String, AtomicLong> threadPoolExecutorAlarmTimeMap = new ConcurrentHashMap<>();

    /**
     * 发送告警消息
     * @param alarmMessage
     */
    public static void sendAlarmMessage(AlarmMessage alarmMessage) {
        AtomicLong alarmTime = threadPoolExecutorAlarmTimeMap.get(alarmMessage.getAlarmName());
        if (alarmTime != null && (alarmTime.get() + alarmMessage.getAlarmTimeInterval() * 60 * 1000) > System.currentTimeMillis()) {
            return;
        }
        if (alarmMessage.getAlarmType() == AlarmTypeEnum.DING_TALK) {
            DingDingMessageUtil.sendTextMessage(alarmMessage.getAccessToken(), alarmMessage.getSecret(), alarmMessage.getMessage());
        }

        if (alarmMessage.getAlarmType() == AlarmTypeEnum.EXTERNAL_SYSTEM) {
            Map<String, String> data = new HashMap<>(2);
            data.put("alarmName", alarmMessage.getAlarmName());
            data.put("message", alarmMessage.getMessage());
            DingDingMessageUtil.post(alarmMessage.getApiUrl(), JsonUtils.toJson(data));
        }

        threadPoolExecutorAlarmTimeMap.put(alarmMessage.getAlarmName(), new AtomicLong(System.currentTimeMillis()));

    }
}
