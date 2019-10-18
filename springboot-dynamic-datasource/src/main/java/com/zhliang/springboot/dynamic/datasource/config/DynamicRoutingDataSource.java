package com.zhliang.springboot.dynamic.datasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: colin
 * @Date: 2019/9/25 19:24
 * @Description:  动态数据源
 * @Version: V1.0
 */
@Slf4j
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    /**
     * 多数据源容器
     */
    private static Map<Object, Object> targetDataSources = new HashMap<>();

    /**
     * 设置当前数据源
     */
    @Override
    protected Object determineCurrentLookupKey() {
        log.info("Current DataSource is [{}]", DynamicDataSourceContextHolder.getDataSourceKey());
        return DynamicDataSourceContextHolder.getDataSourceKey();
    }

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        DynamicRoutingDataSource.targetDataSources = targetDataSources;
    }

    /**
     * 是否存在当前key的 DataSource
     *
     * @param key
     * @return 存在返回 true, 不存在返回 false
     */
    public static boolean isExistDataSource(String key) {
        return targetDataSources.containsKey(key);
    }

    /**
     * 动态增加数据源
     * @param map 数据源属性
     */
    public synchronized boolean addDataSource(Map<String, String> map) {
        try {
            Connection connection = null;
            // 排除连接不上的错误
            try {
                Class.forName(map.get(DruidDataSourceFactory.PROP_DRIVERCLASSNAME));
                connection = DriverManager.getConnection(
                        map.get(DruidDataSourceFactory.PROP_URL),
                        map.get(DruidDataSourceFactory.PROP_USERNAME),
                        map.get(DruidDataSourceFactory.PROP_PASSWORD));
                System.out.println(connection.isClosed());
            } catch (Exception e) {
                return false;
            } finally {
                if (connection != null && !connection.isClosed())
                    connection.close();
            }
            String database = map.get("database");//获取要添加的数据库名
            if (StringUtils.isBlank(database)) return false;
            if (DynamicRoutingDataSource.isExistDataSource(database)) return true;
            DruidDataSource druidDataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(map);
            druidDataSource.init();
            Map<Object, Object> targetMap = DynamicRoutingDataSource.targetDataSources;
            targetMap.put(database, druidDataSource);
            // 当前 targetDataSources 与 父类 targetDataSources 为同一对象 所以不需要set
			//this.setTargetDataSources(targetMap);
            this.afterPropertiesSet();
            log.info("dataSource [{}] has been added", database);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }
}
