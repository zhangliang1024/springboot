package com.zhliang.springboot.dingding.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.sun.deploy.net.HttpResponse;
import com.zhliang.springboot.dingding.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.http.HttpStatus;
import sun.net.www.http.HttpClient;

import java.util.Arrays;
import java.util.List;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.dingding.utils
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/19 18:56
 * @version：V1.0
 */
@Slf4j
public class DingdingUtils {

    private static OkHttpClient client = new OkHttpClient();
    /**
     * 发送钉钉消息
     * @param jsonString 消息内容
     * @param webhook 钉钉自定义机器人webhook
     * @return
     */
    public static boolean sendToDingding(String jsonString, String webhook) {
        try{
            String type = "application/json; charset=utf-8";
            RequestBody body = RequestBody.create(MediaType.parse(type), jsonString);
            Request.Builder builder = new Request.Builder().url(webhook);
            builder.addHeader("Content-Type", type).post(body);

            Request request = builder.build();
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            System.out.println(String.format("send ding message:%s", string));
            JSONObject res = JSONObject.parseObject(string);
            return res.getIntValue("errcode") == 0;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


}
