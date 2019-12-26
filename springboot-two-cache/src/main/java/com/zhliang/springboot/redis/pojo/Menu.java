package com.zhliang.springboot.redis.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.redis.pojo
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/4 09:32
 * @version：V1.0
 */
@Data
public class Menu implements Serializable {

    Long id;
    String code;
    String name;
    Long parentId;

}
