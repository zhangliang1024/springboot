package com.zhliang.springboot.nacos.feign;

import feign.Client;
import okhttp3.OkHttpClient;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.openfeign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.nacos.feign
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2021/11/17 10:21
 * @version：V1.0
 */
@Configuration
public class FeignSupportConfig {

    @Bean
    public Client feignClient(CachingSpringLoadBalancerFactory cachingFactory,
                              SpringClientFactory clientFactory,
                              okhttp3.OkHttpClient client){

        MyOpenFeignHttpClient delegate = new MyOpenFeignHttpClient(new feign.okhttp.OkHttpClient(client));
        //先执行下面这个Client(MyLoadBalancerFeignClient)的execute
        // ->
        //后再执行上面一个Client(MyOpenFeignHttpClient)的execute
        return new MyLoadBalancerFeignClient(delegate,cachingFactory,clientFactory);

    }

}
