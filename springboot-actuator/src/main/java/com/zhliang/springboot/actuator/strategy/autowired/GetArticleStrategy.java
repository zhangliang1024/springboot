package com.zhliang.springboot.actuator.strategy.autowired;


/**
 * @Author: zhliang
 * @Date: 2020/5/20 10:59
 * @Description:
 * @Version: V1.0
 */
public interface GetArticleStrategy {

    /**
     * 获得文章策略接口
     * @param type
     * 1.Image
     * 2.Video
     * @return
     */
    JSONObject getArticle(String type);
}
