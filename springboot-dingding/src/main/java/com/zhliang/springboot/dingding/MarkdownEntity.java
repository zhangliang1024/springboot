package com.zhliang.springboot.dingding;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.dingding
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/19 19:04
 * @version：V1.0
 */
@Data
public class MarkdownEntity {

    private String msgType;

    // 显示标题
    private String title;

    // 显示内容
    private String content;

    // 是否at所有人
    private Boolean isAtAll;

    // 被@人的手机号(在content里添加@人的手机号)
    private List<String> atMobiles;

    public String getJSONObjectString() {
        // markdown类型
        JSONObject markdownContent = new JSONObject();
        markdownContent.put("title", this.getTitle());
        markdownContent.put("text", this.getContent());

        // at some body
        JSONObject atMobile = new JSONObject();
        if(this.getAtMobiles().size() > 0){
            List<String> mobiles = new ArrayList<String>();
            for (int i=0;i<this.getAtMobiles().size();i++){
                mobiles.add(this.getAtMobiles().get(i));
            }
            if(mobiles.size()>0){
                atMobile.put("atMobiles", mobiles);
            }
            atMobile.put("isAtAll", this.getIsAtAll());
        }

        JSONObject json = new JSONObject();
        json.put("msgtype", this.getMsgType());
        json.put("markdown", markdownContent);
        json.put("at", atMobile);
        return json.toJSONString();
    }
}
