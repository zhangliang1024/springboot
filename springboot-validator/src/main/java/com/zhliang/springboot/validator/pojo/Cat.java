package com.zhliang.springboot.validator.pojo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.validator.pojo
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/8/4 09:17
 * @version：V1.0
 */
@Data
public class Cat {

    private Integer id;
    @NotBlank(message = "小猫名称不能为空")
    private String name;
}
