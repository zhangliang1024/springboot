package com.zhliang.springboot.elasticsearch.entity;

import java.io.Serializable;

/**
 * @创建人：zhiang
 * @创建时间：2020/12/23 10:00
 * @version：V1.0
 */
public abstract class IdEntity<ID extends Serializable> implements Serializable {

    public abstract ID getId();

    public abstract void setId(ID id);
}
