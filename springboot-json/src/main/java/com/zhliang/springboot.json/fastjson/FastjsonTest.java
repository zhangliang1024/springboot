package com.zhliang.springboot.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: colin
 * @Date: 2019/8/26 18:42
 * @Description:
 * Json详解以及fastjson使用教程
 *      https://blog.csdn.net/srj1095530512/article/details/82529759
 * @Version: V1.0
 */
@Slf4j
public class FastjsonTest {

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        Person p1 = new Person(1L, "first");
        Person p2 = new Person(2L, "second");
        list.add(p1);
        list.add(p2);
        //实体对象转json
        log.info("Bean -> Json");
        String s1 = JSON.toJSONString(p1);
        String l1 = JSON.toJSONString(list);
        log.info("\n Bean -> Json {}, \n List -> Json {}",s1,l1);

        //json转实体对象
        Person person = JSON.parseObject(s1, Person.class);
        log.info("Json -> Bean: {}",person);

        //实体对象转JSONObject
        JSONObject jsonObject = (JSONObject) JSON.toJSON(p1);
        log.info("Bean -> JSONObject: Person的名称：{}",jsonObject.getString("name"));

        //JSONObject转实体对象
        Person ps = JSON.toJavaObject(jsonObject, Person.class);
        log.info("JSONObject -> Bean : {}",ps);

        //JavaBean -> JSONArray
        JSONArray jsonArray = (JSONArray) JSON.toJSON(list);
        for (int i = 0; i < jsonArray.size(); i++) {
            log.info("JSONObject :{}",jsonArray.getJSONObject(i));
        }

        //JSONObject -> JSON
        String jo = JSON.toJSONString(jsonObject);
        log.info("JSONObject -> JSON :{}",jo);

        //JSON -> JSONObject
        JSONObject object = JSON.parseObject(jo);
        log.info("JSON -> JSONObject {}",object.getString("name"));

        //JSON - > JSONArray
        JSONArray array = JSON.parseArray(JSON.toJSONString(list));


    }
}
