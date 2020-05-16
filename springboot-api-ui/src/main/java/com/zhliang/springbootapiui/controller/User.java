package com.zhliang.springbootapiui.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springbootapiui.controller
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/5 16:08
 * @version：V1.0
 */
@ApiModel
public class User {

    @ApiModelProperty(value = "用户id")
    private Integer id;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "用户地址")
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
