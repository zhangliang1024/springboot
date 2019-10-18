package com.zhliang.springboot.dynamic.datasource.config;

/**
 * @Author: colin
 * @Date: 2019/9/25 19:27
 * @Description:
 * @Version: V1.0
 */
public class DynamicDataSourceContextHolder {


    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "dynamic_db0";
        }
    };

    public static void setDataSourceKey(String key){
        contextHolder.set(key);
    }

    public static String getDataSourceKey(){
        return contextHolder.get();
    }

    public static void clearDataSourceKey(){
        contextHolder.remove();
    }
}
