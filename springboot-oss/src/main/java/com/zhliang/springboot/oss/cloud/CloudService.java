package com.zhliang.springboot.oss.cloud;

import lombok.Data;
import lombok.Getter;

/**
 * @Author: colin
 * @Date: 2019/8/20 20:11
 * @Description:
 * @Version: V1.0
 */
@Getter
public enum CloudService {
    /**
     * 七牛云
     */
    QINIU(1),
    /**
     * 阿里云
     */
    ALIYUN(2),
    /**
     * 腾讯云
     */
    QCLOUD(3);

    private int value;

    CloudService(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
