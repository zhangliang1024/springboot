package com.zhliang.springboot.elasticsearch.utils;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.elasticsearch.utils
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/12/23 09:33
 * @version：V1.0
 */
@Slf4j
public class ElasticUtil {

    private ElasticUtil(){}

    public static Class<?> getClazz(String clazzName){
        try {
            return Class.forName(clazzName);
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * @date 2019/10/26 0:01
     * @param queryBuilder  设置查询对象
     * @param from  设置from选项，确定要开始搜索的结果索引。 默认为0。
     * @param size  设置大小选项，确定要返回的搜索匹配数。 默认为10。
     * @param timeout
     * @return org.elasticsearch.search.builder.SearchSourceBuilder
     * @throws
     * @since
     */
    public static SearchSourceBuilder initSearchSourceBuilder(QueryBuilder queryBuilder, int from, int size, int timeout){

        //使用默认选项创建 SearchSourceBuilder 。
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //设置查询对象。可以使任何类型的 QueryBuilder
        sourceBuilder.query(queryBuilder);
        //设置from选项，确定要开始搜索的结果索引。 默认为0。
        sourceBuilder.from(from);
        //设置大小选项，确定要返回的搜索匹配数。 默认为10。
        sourceBuilder.size(size);
        sourceBuilder.timeout(new TimeValue(timeout, TimeUnit.SECONDS));
        return sourceBuilder;
    }

    /**
     * @date 2019/10/26 0:01
     * @param queryBuilder
     * @return org.elasticsearch.search.builder.SearchSourceBuilder
     * @throws
     * @since
     */
    public static SearchSourceBuilder initSearchSourceBuilder(QueryBuilder queryBuilder){
        return initSearchSourceBuilder(queryBuilder,0,10,60);
    }

    /**
     * @date 2019/10/26 0:01
     * @param queryBuilder
     * @return org.elasticsearch.search.builder.SearchSourceBuilder
     * @throws
     * @since
     */
    public static SearchSourceBuilder initSearchSourceBuilder(QueryBuilder queryBuilder,int from, int size){
        return initSearchSourceBuilder(queryBuilder,from,size,60);
    }
}
