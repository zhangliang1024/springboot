package com.zhliang.springboot.dingding;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.dingding
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/19 19:06
 * @version：V1.0
 */
public class ActionCardEntity {

    //private String msgType;
    //
    //// 显示标题
    //private String title;
    //
    //// 显示内容 markdown格式的消息
    //private String content;
    //
    //// 0-正常发消息者头像，1-隐藏发消息者头像
    //private String hideAvatar;
    //
    //// 0-按钮竖直排列，1-按钮横向排列
    //private String btnOrientation;
    //
    //// 单个按钮的方案。(设置此项和singleURL后btns无效)
    //private String singleTitle;
    //
    //// 点击singleTitle按钮触发的URL
    //private String singleURL;
    //
    //private List<ActionCardButtonEntity> btns;
    //
    //public String getMsgType() {
    //    return "actionCard";
    //}
    //
    //public String getJSONObjectString() {
    //    // text类型
    //    JSONObject actionCardContent = new JSONObject();
    //    actionCardContent.put("title", this.getTitle());
    //    actionCardContent.put("text", this.getContent());
    //    actionCardContent.put("hideAvatar", this.getHideAvatar());
    //    actionCardContent.put("btnOrientation", this.getBtnOrientation());
    //    if(!StringUtils.isEmpty(this.getSingleTitle()) && !StringUtils.isEmpty(this.getSingleURL())){
    //        actionCardContent.put("singleTitle", this.getSingleTitle());
    //        actionCardContent.put("singleURL", this.getSingleURL());
    //    }else{
    //        List<ActionCardButtonEntity> btns = new ArrayList<ActionCardButtonEntity>();
    //        for (int i=0;i<this.getBtns().size();i++){
    //            btns.add(this.getBtns().get(i));
    //        }
    //        if(this.getBtns().size()>0){
    //            actionCardContent.put("btns", btns);
    //        }
    //    }
    //
    //
    //    JSONObject json = new JSONObject();
    //    json.put("msgtype", this.getMsgType());
    //    json.put("actionCard", actionCardContent);
    //
    //    return json.toJSONString();
    //}
}
