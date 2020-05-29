package com.zhliang.springboot.actuator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 配置Actuator组件，实现系统监控:
 *      https://yq.aliyun.com/articles/715353?spm=a2c4e.11153940.0.0.68304fde93anqa
 * Actuator简介:
 *  在生产环境中，需要实时或定期监控服务的可用性。Spring Boot的actuator（健康监控）功能提供了很多监控所需的接口，可以对应用系统进行配置查看、相关功能统计等
 *
 *
 *  Actuator 提供Rest接口，展示监控信息。
 *  接口分为三大类：
 *      应用配置类：获取应用程序中加载的应用配置、环境变量、自动化配置报告等与SpringBoot应用相关的配置类信息。
 *      度量指标类：获取应用程序运行过程中用于监控的度量指标，比如：内存信息、线程池信息、HTTP请求统计等。
 *      操作控制类：提供了对应用的关闭等操作类功能。
 */
@SpringBootApplication
public class SpringbootActuatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootActuatorApplication.class, args);
    }

}
