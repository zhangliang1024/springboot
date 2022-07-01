package com.zhliang.springboot.cache.cache;

/**
 * @创建人：zhiang
 * @创建时间：2020/5/11 10:59
 * @version：V1.0
 */
public class Constant {

    /**
     * 过期时间
     */
    public static final Long EXPIRE_TIME = 60L;
    /**
     * 设置最大存储
     */
    public static final int MAX_SIZE = 250;

    /**
     * 本地缓存KEY PREFIX
     */
    public static final String LOCAL_PREFIX = "local_";
    /**
     * REDIS缓存KEY PREFIX
     */
    public static final String REDIS_PREFIX = "redis_";
    /**
     * 品类KEY
     */
    public static final String CATEGORY_KEY = "category";
    /**
     * 种类KEY
     */
    public static final String CATEGOR_COLIN_KEY = "category_coil";
    /**
     * 品类CODE KEY
     */
    public static final String CATEGORY_CODE_KEY = "category_code:";

    /**
     * 本地缓存KEY 采购经理指数
     */
    public static final String COMPOSITE_INDEX = "composite_index_";

}
