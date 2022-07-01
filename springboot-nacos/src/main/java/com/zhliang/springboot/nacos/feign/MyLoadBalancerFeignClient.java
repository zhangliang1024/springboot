package com.zhliang.springboot.nacos.feign;

import feign.Client;
import feign.Request;
import feign.Response;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.openfeign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @类描述：需要装饰LoadBalancerFeignClient类，将serverName放置到header中。
 * @创建人：zhiang
 * @创建时间：2021/11/17 10:29
 */
public class MyLoadBalancerFeignClient extends LoadBalancerFeignClient {

    public MyLoadBalancerFeignClient(Client delegate, CachingSpringLoadBalancerFactory lbClientFactory, SpringClientFactory clientFactory) {
        super(delegate, lbClientFactory, clientFactory);
    }

    /**
     * 装饰器模式
     * @param request 不可改变的request对象，故填充header只能重写
     * @param options 配置信息
     * @return
     * @throws IOException
     */
    @Override
    public Response execute(Request request, Request.Options options) throws IOException {
        URI asUri = URI.create(request.url());
        String clientName = asUri.getHost();
        Collection<String> headerValues = new ArrayList<>();
        headerValues.add(clientName);
        Map<String,Collection<String>> safeCopy = new ConcurrentHashMap<>(request.headers());
        safeCopy.put(WPSConstants.FEIGN_METRIC_HEADER_SERVICENAME,headerValues);
        Request newReqeust = Request.create(request.httpMethod(), request.url(), safeCopy, request.requestBody());
        return super.execute(newReqeust, options);
    }
}
