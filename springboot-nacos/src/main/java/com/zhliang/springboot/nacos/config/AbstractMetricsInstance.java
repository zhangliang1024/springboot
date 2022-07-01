package com.zhliang.springboot.nacos.config;

import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.actuate.autoconfigure.metrics.OnlyOnceLoggingDenyMeterFilter;

/**
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2021/11/15 20:07
 */
public class AbstractMetricsInstance {

    protected PrometheusMeterRegistry registry;

    public AbstractMetricsInstance(PrometheusMeterRegistry registry) {
        this.registry = registry;
    }

    protected void maximumAllowableTags(String meterName, String tagName, int maxCount) {
        if (registry == null || StringUtils.isEmpty(meterName) || StringUtils.isEmpty(tagName) || maxCount < 0) {
            return;
        }
        MeterFilter denyFilter = new OnlyOnceLoggingDenyMeterFilter(() ->
                String.format("Metrics reached the maximum number of '%s' tags for '%s'.", tagName, meterName));
        registry.config().meterFilter(MeterFilter.maximumAllowableTags(meterName, tagName, maxCount, denyFilter));
    }

    protected String getMatchPatternByUri(String uri) {
        return uri;
    }

}
