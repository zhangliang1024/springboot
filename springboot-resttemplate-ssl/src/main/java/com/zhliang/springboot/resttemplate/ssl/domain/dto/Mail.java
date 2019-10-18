package com.zhliang.springboot.resttemplate.ssl.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @Author: colin
 * @Date: 2019/10/14 12:04
 * @Description:
 * @Version: V1.0
 */
@Data
public class Mail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 接收方邮件
     */
    private String receiverEmail;

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 模板名
     */
    private String templateName;

    /**
     * 模板中自定义的参数
     */
    private Map<String, Object> params;


}
