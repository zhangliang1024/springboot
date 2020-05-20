package com.zhliang.springboot.actuator.strategy.autowired;

import org.springframework.stereotype.Component;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.actuator.strategy.autowired
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/20 11:02
 * @version：V1.0
 */
@Component("Image")
public class GetArticleWithImage implements GetArticleStrategy {
    @Override
    public JSONObject getArticle(String type) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg","包含图片的文章");
        return jsonObject;
    }
}
