package com.zhliang.springboot.request.response.wrapper.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

import java.io.IOException;
import java.io.InputStream;

/**
 * @类描述： 这里实现了HttpInputMessage 封装一个自己的HttpInputMessage
 * @创建人：zhiang
 * @创建时间：2020/5/28 19:54
 * @version：V1.0
 */
public class XHttpInputMessage implements HttpInputMessage {
    private HttpHeaders headers;
    private InputStream body;

    public XHttpInputMessage(HttpInputMessage httpInputMessage, String encode) throws IOException {
        this.headers = httpInputMessage.getHeaders();
        this.body = encode(httpInputMessage.getBody(), encode);
    }

    private InputStream encode(InputStream body, String encode) {
        //省略对流进行编码的操作
        return body;
    }

    @Override
    public InputStream getBody() {
        return body;
    }

    @Override
    public HttpHeaders getHeaders() {
        return null;
    }


}
