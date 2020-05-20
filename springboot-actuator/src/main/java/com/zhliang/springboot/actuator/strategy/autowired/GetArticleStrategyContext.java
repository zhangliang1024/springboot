package com.zhliang.springboot.actuator.strategy.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @创建人：zhiang
 * @创建时间：2020/5/20 11:02
 * @version：V1.0
 */
@Service
public class GetArticleStrategyContext {

    /**
     * 通过扫描：
     *  项目启动后，可以实现自动注入。
     */
    @Autowired
    private final Map<String, GetArticleStrategy> strategyMap = new ConcurrentHashMap<>();

    /**
     * 工厂模式将实现了GetArticleStrategy的Bean注入Context中
     * @param strategyMap
     */

    public GetArticleStrategyContext(Map<String, GetArticleStrategy> strategyMap) {
        this.strategyMap.clear();
        strategyMap.forEach((k,v)->strategyMap.put(k,v));
    }
    public JSONObject getArticle(String type){
        return strategyMap.get(type).getArticle(type);
    }
}