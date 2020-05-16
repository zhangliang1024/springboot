package com.zhliang.springbootapiui.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springbootapiui.config
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/5 15:59
 * @version：V1.0
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    @Bean(value = "createRestApi")
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                //标题
                .title("日志处理")
                //版本信息
                .version("9.0")
                //描述消息
                .description("日志处理")
                .contact(new Contact("xxxx", "http://www.xxxx.com/", "http://www.xxx.com/"))
                .license("重庆xxx技术有限公司")
                .licenseUrl("http://www.xxxx.com/")
                .build())
                //最终调用接口后会和paths拼接在一起
                .pathMapping("/")
                .select()
                //包路径
                .apis(RequestHandlerSelectors.basePackage("com.zhliang.springbootapiui.controller"))
                //过滤的接口
                .paths(PathSelectors.any())
                .build();
    }

}
