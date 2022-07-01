package com.zhliang.springboot.elasticsearch.entity;

import com.zhliang.springboot.elasticsearch.config.Global;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/12/23 09:58
 * @version：V1.0
 */
public abstract class BaseEntity<ID extends Serializable> extends IdEntity<ID>{

    public static String DB_TYPE ="db.dtype";

    //@JSONField
    private String dtype;

    public String getDtype() {
        return Global.getConfig(DB_TYPE);
    }

    /** 请求参数 */
    private Map<String, Object> params;

    public Map<String, Object> getParams()
    {
        if (params == null)
        {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params)
    {
        this.params = params;
    }
}
