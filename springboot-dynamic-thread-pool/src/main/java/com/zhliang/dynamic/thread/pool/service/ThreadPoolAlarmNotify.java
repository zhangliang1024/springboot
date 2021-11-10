package com.zhliang.dynamic.thread.pool.service;

/**
 * @Author: zhliang
 * @Date: 2021/11/8 18:41
 * @类描述: 线程池告警通知 ，使用者可实现该接口进行告警方式的扩展
 */
public interface ThreadPoolAlarmNotify {

    /**
     * 告警通知
     *
     * @param alarmMessage
     */
    void alarmNotify(AlarmMessage alarmMessage);

}
