package com.zhliang.springboot.request.response.wrapper.filter;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jodd.io.StreamUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.redisson
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/27 17:41
 * @version：V1.0
 */
public class BodyRequestWarpper extends HttpServletRequestWrapper {

    private byte[] body;

    public BodyRequestWarpper(HttpServletRequest request) throws IOException {
        super(request);
        StreamUtil.readBytes(request.getReader(), "utf-8");
        //由于request并没有提供现成的获取json字符串的方法，所以我们需要将body中的流转为字符串
        String json = new String(StreamUtil.readBytes(request.getReader(), "utf-8"));
        body = getData(json).getBytes();
    }

    private String getData(String json){
        if(!json.contains("\"data\"")) {
            return StringUtils.EMPTY;
        }
        ObjectMapper mapper = new ObjectMapper();
        String data = StringUtils.EMPTY;
        try{
            JsonNode jsonNode = mapper.readTree(json);
            data = jsonNode.get("data").toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(body);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return inputStream.read();
            }
        };
    }
}
