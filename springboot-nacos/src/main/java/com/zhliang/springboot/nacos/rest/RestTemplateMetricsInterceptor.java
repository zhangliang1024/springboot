package com.zhliang.springboot.nacos.rest;

import com.google.common.base.Stopwatch;
import com.zhliang.springboot.nacos.config.OpenFeignMetricsInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.TimeUnit;

/**
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2021/11/15 20:10
 */
@Service
public class RestTemplateMetricsInterceptor implements ClientHttpRequestInterceptor {

    @Autowired
    private OpenFeignMetricsInstance openFeignMetricsInstance;

    //自定义的配置文件
    private XxConfigProperties xxConfigProperties;

    public RestTemplateMetricsInterceptor(XxConfigProperties xxConfigProperties) {
        this.xxConfigProperties = xxConfigProperties;
    }


    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        HttpMethod method = request.getMethod();
        //修改请求的header对象
        request.getHeaders().set("sign", "sign");
        //执行请求

        ClientHttpResponse response;
        URI uri = request.getURI();
        String path = uri.getPath();
        String serviceName = uri.getHost();
        int status = 599;
        try {
            final Stopwatch stopwatch = Stopwatch.createStarted();
            response = execution.execute(request, body);
            stopwatch.stop();
            //覆盖错误码
            status = response.getRawStatusCode();
            openFeignMetricsInstance.requestDurationSeconds(serviceName, serviceName, path, stopwatch.elapsed(TimeUnit.MILLISECONDS));
        } catch (Exception e) {
            openFeignMetricsInstance.requestExceptionCount(serviceName, serviceName, path, e);
            throw e;
        } finally {
            openFeignMetricsInstance.responseStatusCodeCount(serviceName, serviceName, path, status);
        }
        return response;
    }
}
