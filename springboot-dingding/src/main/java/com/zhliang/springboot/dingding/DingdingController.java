package com.zhliang.springboot.dingding;

import com.zhliang.springboot.dingding.utils.DingdingUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.dingding
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/19 19:03
 * @version：V1.0
 */
@RestController
@RequestMapping(value = "/dingding")
public class DingdingController {

    // 发送钉钉消息
    // example
    //{
    //    "content": "content @16619930394",
    //    "atMobiles": ["16619930394"],
    //    "isAtAll": false
    //}
    @PostMapping(value = "/sendText")
    public static boolean sendTextMessage(@RequestBody TextEntity text) {
        return DingdingUtils.sendToDingding(text.getJSONObjectString(), "https://oapi.dingtalk.com/robot/send?access_token=0a661f21d97f0bcc188c5f77339eed26369a77e3fd7ad782c823ee6adb06c7cd");
    }

    public static void main(String[] args) {
        TextEntity entity = new TextEntity();
        entity.setAtMobiles(Arrays.asList("13552651943,h1a-n2sdi6hl5"));
        entity.setIsAtAll(true);
        entity.setContent("红队 欢迎胡大哥");
        //sendTextMessage(entity);
        String msg = "";

        MarkdownEntity mark = new MarkdownEntity();
        mark.setIsAtAll(false);
        mark.setTitle("杭州天气");
        mark.setAtMobiles(Arrays.asList("13552651943,h1a-n2sdi6hl5"));
        mark.setMsgType("markdown");
        mark.setContent("#### 红队杭州天气 @13552651943 > 9度，西北风1级，空气良89，相对温度73% > ![screenshot](https://gw" +
                ".alipayobjects.com/zos/skylark-tools/public/files/84111bbeba74743d2771ed4f062d1f25.png) > ###### " +
                "10点20分发布 [天气](http://www.thinkpage.cn/) ");

        DingdingUtils.sendToDingding(mark.getJSONObjectString(), "https://oapi.dingtalk" +
                ".com/robot/send?access_token=0a661f21d97f0bcc188c5f77339eed26369a77e3fd7ad782c823ee6adb06c7cd");
    }
    @PostMapping(value = "/sendLink")
    // example
    //{
    //  "title": "时代的火车向前开",
    //  "content": "这个即将发布的新版本，创始人陈航（花名“无招”）称它为“红树林”。而在此之前，每当面临重大升级，产品经理们都会取一个应景的代号，这一次，为什么是“红树林”？",
    //  "picUrl": "https://avatar.csdn.net/5/F/0/3_zww1984774346.jpg",
    //  "messageUrl": "https://mp.weixin.qq.com/s?__biz=MzA4NjMwMTA2Ng==&mid=2650316842&idx=1&sn=60da3ea2b29f1dcc43a7c8e4a7c97a16&scene=2&srcid=09189AnRJEdIiWVaKltFzNTw&from=timeline&isappinstalled=0&key=&ascene=2&uin=&devicetype=android-23&version=26031933&nettype=WIFI"
    //}
    public boolean sendLinkMessage(@RequestBody LinkEntity linkEntity) {
        return DingdingUtils.sendToDingding(linkEntity.getJSONObjectString(), "https://oapi.dingtalk.com/robot/send?access_token=xxx");
    }


    @PostMapping(value = "/sendMarkdown")
    //{
//   "title":"杭州天气",
//   "content": "#### 杭州天气 @16619930394\n > 9度，西北风1级，空气良89，相对温度73%\n\n > ![screenshot](https://gw.alipayobjects.com/zos/skylark-tools/public/files/84111bbeba74743d2771ed4f062d1f25.png)\n > ###### 10点20分发布 [天气](http://www.thinkpage.cn/) \n",
//   "atMobiles": ["16619930394"],
//   "isAtAll": false
//}
    public boolean sendMarkdownMessage(@RequestBody MarkdownEntity markdownEntity) {
        return DingdingUtils.sendToDingding(markdownEntity.getJSONObjectString(), "https://oapi.dingtalk.com/robot/send?access_token=xxx");
    }

    //@PostMapping(value = "/sendActionCard")
    // example
//{
//   "title": "乔布斯 20 年前想打造一间苹果咖啡厅，而它正是 Apple Store 的前身",
//   "content": "![screenshot](https://gw.alipayobjects.com/zos/skylark-tools/public/files/84111bbeba74743d2771ed4f062d1f25.png)\n ### 乔布斯 20 年前想打造的苹果咖啡厅\n Apple Store 的设计正从原来满满的科技感走向生活化，而其生活化的走向其实可以追溯到 20 年前苹果一个建立咖啡馆的计划",
//   "hideAvatar": "0",
//   "btnOrientation": "0",
//   "singleTitle" : "阅读全文",
//   "singleURL" : "https://www.dingtalk.com/"
//}
//    public boolean sendActionCardMessage(@RequestBody ActionCardEntity actionCardEntity) {
//        return DingdingUtils.sendToDingding(actionCardEntity.getJSONObjectString(), "https://oapi.dingtalk.com/robot/send?access_token=xxx");
//    }

    //@PostMapping(value = "/sendFeedCard")
    //  example
//{
//        "links": [
//        {
//        "title": "时代的火车向前开",
//        "messageURL": "https://mp.weixin.qq.com/s?__biz=MzA4NjMwMTA2Ng==&mid=2650316842&idx=1&sn=60da3ea2b29f1dcc43a7c8e4a7c97a16&scene=2&srcid=09189AnRJEdIiWVaKltFzNTw&from=timeline&isappinstalled=0&key=&ascene=2&uin=&devicetype=android-23&version=26031933&nettype=WIFI",
//        "picURL": "https://img-blog.csdnimg.cn/20181129152228878.png?imageView2/5/w/120/h/120"
//        },
//        {
//        "title": "时代的火车向前开2",
//        "messageURL": "https://mp.weixin.qq.com/s?__biz=MzA4NjMwMTA2Ng==&mid=2650316842&idx=1&sn=60da3ea2b29f1dcc43a7c8e4a7c97a16&scene=2&srcid=09189AnRJEdIiWVaKltFzNTw&from=timeline&isappinstalled=0&key=&ascene=2&uin=&devicetype=android-23&version=26031933&nettype=WIFI",
//        "picURL": "https://img-blog.csdnimg.cn/20181129152228878.png?imageView2/5/w/120/h/120"
//        }
//        ]
// }
//    public boolean sendFeedCardMessage(@RequestBody FeedCardEntity feedCardEntity) {
//        return DingdingUtils.sendToDingding(feedCardEntity.getJSONObjectString(), "https://oapi.dingtalk.com/robot/send?access_token=xxx");
//    }

}
