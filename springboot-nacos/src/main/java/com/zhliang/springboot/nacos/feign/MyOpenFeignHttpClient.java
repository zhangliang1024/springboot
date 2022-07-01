package com.zhliang.springboot.nacos.feign;

import com.google.common.base.Stopwatch;
import com.zhliang.springboot.nacos.config.OpenFeignMetricsInstance;
import feign.Client;
import feign.Request;
import feign.Response;
import feign.okhttp.OkHttpClient;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.util.CollectionUtils;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

/**
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2021/11/17 10:23
 */
public class MyOpenFeignHttpClient implements Client {

    private final OkHttpClient client;


    public MyOpenFeignHttpClient(OkHttpClient client) {
        this.client = client;
    }

    @Override
    public Response execute(Request request, Request.Options options) throws IOException {
        URIBuilder uriBuilder;
        String serviceName = CollectionUtils.isEmpty(request.headers().get(WPSConstants.FEIGN_METRIC_HEADER_SERVICENAME)) ?
                "" : request.headers().get(WPSConstants.FEIGN_METRIC_HEADER_SERVICENAME).iterator().next();
        try {
            uriBuilder = new URIBuilder(request.url());
        } catch (URISyntaxException e) {
            throw new IOException("URL '" + request.url() + "' couldn't be parsed into a URI", e);
        }

        String remoteAddr = uriBuilder.getPort() > 0 ? uriBuilder.getHost() + ":" + uriBuilder.getPort() : uriBuilder.getHost();

        int status = 506;  //IOException的特定特殊码
        String path = uriBuilder.getPath();
        Response response;
        OpenFeignMetricsInstance openFeignMetricsInstance = (OpenFeignMetricsInstance) SpringContext.getBean("openFeignMetricsInstance");
        try {
            //记录api的metric
            final Stopwatch stopwatch = Stopwatch.createStarted();
            //真正执行的逻辑
            response = client.execute(request, options);
            stopwatch.stop();
            status = response.status();
            long time = stopwatch.elapsed(TimeUnit.MILLISECONDS);
            //监控逻辑——记录status以及响应时间
            return response;
        } catch (Exception e) {
            //监控逻辑——记录异常
            throw e;
        } finally {
            //监控逻辑——记录总数
        }
    }

}
