package com.zhliang.springboot.nacos.config;

import com.google.common.base.Strings;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.core.instrument.distribution.DistributionStatisticConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.apache.commons.lang3.StringUtils;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2021/11/15 20:08
 */
public class AbstractHttpMetricsInstance extends AbstractMetricsInstance  {

    private static final String TAG_RA = "remote";

    private static final String TAG_SN = "svc_name";

    private static final String TAG_UP = "uri";

    private static final String TAG_SC = "status_code";

    private static final String TAG_EX = "exception";

    private static final String NONE_VAL = "-";

    private final String RSP_SS;

    private final String REQ_DS;

    private final String RSP_EX;

    private final int maxAllowUriTags;

    public AbstractHttpMetricsInstance(String RSP_SS, String REQ_DS, String RSP_EX,
                                       PrometheusMeterRegistry registry,
                                       int maxAllowUriTags) {
        super(registry);
        this.RSP_SS = RSP_SS;
        this.REQ_DS = REQ_DS;
        this.RSP_EX = RSP_EX;
        this.maxAllowUriTags = maxAllowUriTags;
        initMaxAllowUriTags();
        initPercentilesHistogram();
    }

    private void initPercentilesHistogram() {
        if (registry == null) {
            return;
        }
        registry.config().meterFilter(new MeterFilter() {
            @Override
            public DistributionStatisticConfig configure(final Meter.Id id, final DistributionStatisticConfig config) {
                if (Meter.Type.TIMER.equals(id.getType()) && id.getName().startsWith(REQ_DS)) {
                    return DistributionStatisticConfig.builder().percentilesHistogram(true)
                            .sla(Duration.ofMillis(25).toNanos(), Duration.ofMillis(50).toNanos(),
                                    Duration.ofMillis(75).toNanos(), Duration.ofMillis(100).toNanos(),
                                    Duration.ofMillis(200).toNanos(), Duration.ofMillis(500).toNanos(),
                                    Duration.ofMillis(750).toNanos(), Duration.ofSeconds(1).toNanos(),
                                    Duration.ofSeconds(2).toNanos())
                            .minimumExpectedValue(Duration.ofSeconds(5).toNanos())
                            .maximumExpectedValue(Duration.ofSeconds(5).toNanos())
                            .build().merge(config);
                }
                return config;
            }
        });
    }

    private void initMaxAllowUriTags() {
        maximumAllowableTags(RSP_SS, TAG_UP, maxAllowUriTags);
        maximumAllowableTags(REQ_DS, TAG_UP, maxAllowUriTags);
        maximumAllowableTags(RSP_EX, TAG_UP, maxAllowUriTags);
    }

    /**
     * The process for metrics of metric_openfeign_request_duration_seconds_bucket
     * The process for metrics of metric_openfeign_request_duration_seconds_count
     * The process for metrics of metric_openfeign_request_duration_seconds_max
     * The process for metrics of metric_openfeign_request_duration_seconds_sum
     *
     * @param remoteAddr  远程服务ip地址
     * @param serviceName api的eureka name
     * @param path        uri
     * @param durations   请求耗时
     */
    public void requestDurationSeconds(final String remoteAddr, final String serviceName, final String path,
                                       final Long durations) {
        if (this.registry == null
                || Strings.isNullOrEmpty(remoteAddr)
                || Strings.isNullOrEmpty(path)) {
            return;
        }
        Tags tags = Tags.of(TAG_RA, remoteAddr)
                .and(TAG_SN, StringUtils.isNotBlank(serviceName) ? serviceName : NONE_VAL)
                .and(TAG_UP, getMatchPatternByUri(path));
        Timer.builder(REQ_DS)
                .tags(tags)
                .register(registry)
                .record(durations, TimeUnit.MILLISECONDS);
    }

    /**
     * The process for metrics of metric_openfeign_response_total
     *
     * @param remoteAddr  远程服务ip地址
     * @param serviceName api的eureka name
     * @param path        uri
     * @param statusCode  返回状态码
     */
    public void responseStatusCodeCount(final String remoteAddr, final String serviceName, final String path,
                                        final int statusCode) {
        if (this.registry == null
                || Strings.isNullOrEmpty(remoteAddr)
                || Strings.isNullOrEmpty(path)) {
            return;
        }
        Tags tags = Tags.of(TAG_RA, remoteAddr)
                .and(TAG_SN, StringUtils.isNotBlank(serviceName) ? serviceName : NONE_VAL)
                .and(TAG_UP, getMatchPatternByUri(path))
                .and(TAG_SC, String.valueOf(statusCode));
        registry.counter(RSP_SS, tags).increment();
    }

    /**
     * The process for metrics of metric_openfeign_client_exception_total
     *
     * @param remoteAddr  远程服务ip地址
     * @param serviceName api的eureka name
     * @param path        uri
     * @param exception   异常
     */
    public void requestExceptionCount(final String remoteAddr, final String serviceName, final String path,
                                      final Throwable exception) {
        if (this.registry == null
                || Strings.isNullOrEmpty(path)
                || exception == null) {
            return;
        }
        Tags tags = Tags.of(TAG_RA, remoteAddr)
                .and(TAG_SN, StringUtils.isNotBlank(serviceName) ? serviceName : NONE_VAL)
                .and(TAG_UP, getMatchPatternByUri(path))
                .and(exceptionTag(exception));

        registry.counter(RSP_EX, tags).increment();
    }

    private Tags exceptionTag(final Throwable exception) {
        String simpleName = exception.getClass().getSimpleName();
        return Tags.of(TAG_EX, simpleName.isEmpty() ? exception.getClass().getName() : simpleName);
    }

}
