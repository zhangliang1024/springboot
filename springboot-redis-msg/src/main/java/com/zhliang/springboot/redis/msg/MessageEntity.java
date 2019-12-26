package com.zhliang.springboot.redis.msg;

import lombok.Data;

import java.io.Serializable;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.redis.msg
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/6 18:01
 * @version：V1.0
 */
@Data
public class MessageEntity implements Serializable {

    private static final long serialVersionUID = 8632296967087444509L;

    private String id;

    private String content;

    public MessageEntity() {
        super();
    }

    public MessageEntity(String id, String content) {
        super();
        this.id = id;
        this.content = content;
    }
}
