package com.zhliang.springboot.dingding;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.dingding
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/19 19:05
 * @version：V1.0
 */
@Data
public class LinkEntity {

    private String msgType;

    // 显示标题
    private String title;

    // 显示内容
    private String content;

    // icon url
    private String picUrl;

    // 内容对链接
    private String messageUrl;

    public String getMsgType() {
        return "link";
    }

    public String getJSONObjectString() {
        // text类型
        JSONObject linkContent = new JSONObject();
        linkContent.put("title", this.getTitle());
        linkContent.put("text", this.getContent());
        linkContent.put("picUrl", this.getPicUrl());
        linkContent.put("messageUrl", this.getMessageUrl());

        JSONObject json = new JSONObject();
        json.put("msgtype", this.getMsgType());
        json.put("link", linkContent);
        return json.toJSONString();
    }

}
