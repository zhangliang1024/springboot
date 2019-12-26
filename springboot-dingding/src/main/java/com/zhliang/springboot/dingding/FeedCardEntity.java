package com.zhliang.springboot.dingding;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.dingding
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/19 19:07
 * @version：V1.0
 */
@Data
public class FeedCardEntity {

    private String msgType;

    // links
    //private List<FeedCardLinkEntity> links;
    //
    //public String getMsgType() {
    //    return "feedCard";
    //}
    //
    //public String getJSONObjectString() {
    //
    //    // text类型
    //    JSONObject feedCardContent = new JSONObject();
    //    List<FeedCardLinkEntity> links = new ArrayList<FeedCardLinkEntity>();
    //    for (int i = 0; i < this.getLinks().size(); i++) {
    //        links.add(this.getLinks().get(i));
    //    }
    //    if (this.getLinks().size() > 0) {
    //        feedCardContent.put("links", links);
    //    }
    //
    //    JSONObject json = new JSONObject();
    //    json.put("msgtype", this.getMsgType());
    //    json.put("feedCard", feedCardContent);
    //
    //    return json.toJSONString();
    //}
}
