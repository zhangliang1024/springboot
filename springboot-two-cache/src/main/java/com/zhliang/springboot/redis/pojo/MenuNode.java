package com.zhliang.springboot.redis.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.redis.pojo
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/4 09:33
 * @version：V1.0
 */
@Data
public class MenuNode implements Serializable {

    private Menu menu;
    private List<MenuNode> children = new ArrayList<MenuNode>();
    @JsonIgnore
    private MenuNode parent;

}
