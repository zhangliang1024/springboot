package com.zhliang.springbootmysqlwr.key;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Author: colin
 * @Date: 2019/10/18 10:43
 * @Description:
 * @Version: V1.0
 */
public class MyRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DBContextHolder.get();
    }
}
