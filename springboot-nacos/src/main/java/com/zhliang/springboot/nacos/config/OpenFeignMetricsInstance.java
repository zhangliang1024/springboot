package com.zhliang.springboot.nacos.config;

import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.springframework.stereotype.Component;

/**
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2021/11/15 20:09
 */
@Component
public class OpenFeignMetricsInstance extends AbstractHttpMetricsInstance {

    private static final String RSP_SS = "metric.openfeign.response";

    private static final String REQ_DS = "metric.openfeign.request.duration";

    private static final String RSP_EX = "metric.openfeign.client.exception";

    public OpenFeignMetricsInstance(PrometheusMeterRegistry registry) {
        super(RSP_SS, REQ_DS, RSP_EX, registry, 1000);
    }
}