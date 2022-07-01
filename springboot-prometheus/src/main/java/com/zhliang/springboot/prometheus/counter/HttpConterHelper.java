package com.zhliang.springboot.prometheus.counter;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

/**
 * @类描述：
 * @创建时间：2021/8/13 15:38
 */
@Component
public class HttpConterHelper {

    public static final String CUSTOM_TOTAL = "custom_api_http_requests_total";

    private final Counter counter;

    public HttpConterHelper(MeterRegistry registry){
        this.counter = registry.counter(CUSTOM_TOTAL);
    }

    public void count(){
        this.counter.increment();
    }
}
