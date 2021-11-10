package com.zhliang.dynamic.thread.pool.service;

import lombok.Data;

/**
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2021/11/9 09:44
 */
@Data
public class Message {

    private String msgtype;
    private MessageInfo text;

}
