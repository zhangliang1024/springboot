package com.zhliang.springboot.resttemplate.ssl.domain.vo;

import lombok.Data;

/**
 * @Author: colin
 * @Date: 2019/10/14 12:03
 * @Description:
 * @Version: V1.0
 */
@Data
public class ReqIntention {

    /**
     * 咨询公司
     */
    private String company;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 传真
     */
    private String fax;

    /**
     * 用户备注
     */
    private String more;
}
