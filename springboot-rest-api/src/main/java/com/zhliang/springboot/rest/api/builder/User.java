package com.zhliang.springboot.rest.api.builder;

import lombok.*;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.rest.api.builder
 * @类描述：
 * @创建人：colin
 * @创建时间：2020/1/6 16:30
 * @version：V1.0
 *
 * @Builder注解修改原对象的属性值：修改实体，要求实体上添加@Builder(toBuilder=true)
 */
@Builder
@ToString
public class User {

    private final Integer code = 200;
    private String username;
    private String password;

}
