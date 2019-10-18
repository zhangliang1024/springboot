package com.zhliang.springboot.resttemplate.ssl.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;

/**
 * @Author: colin
 * @Date: 2019/10/14 11:07
 * @Description: RestTemplate 配置类
 * @Version: V1.0
 */
@Slf4j
@Configuration
public class RestConfiguration {

    /**
     * @author David Hong
     * @description 普通RestTemplate
     *
     * @return org.springframework.web.client.RestTemplate
     */
    @Bean("restTemplate20s")
    RestTemplate restTemplate20s() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        // 连接超时时间
        requestFactory.setConnectTimeout(2000);
        // 读取超时时间
        requestFactory.setReadTimeout(20000);
        RestTemplate restTemplate = new RestTemplate(requestFactory);

        // 解决中文乱码问题
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        return restTemplate;
    }

    @Bean("restTemplate")
    RestTemplate restTemplate() throws Exception {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectionRequestTimeout(5000);
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(5000);

        CloseableHttpClient httpClient = initSSLConfig();
        factory.setHttpClient(httpClient);

        RestTemplate restTemplate = new RestTemplate(factory);

        // 解决中文乱码问题
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        return restTemplate;
    }

    /**
     * 客户证书验证密码，请根据outgoing.CertwithKey.pkcs12文件修改
     */
    private static String SELFCERTPWD = "12345";

    /**
     * 信任客户端验证密码，请根据ca.jks文件修改
     */
    private static String TRUSTCAPWD = "54321";

    /**
     * @author David Hong
     * @description 初始化ssl证书
     *
     * @return org.apache.http.impl.client.CloseableHttpClient
     */
    public CloseableHttpClient initSSLConfig() throws Exception {

        // 1 设置客户端证书
        KeyStore selfCert = KeyStore.getInstance("pkcs12");
        selfCert.load(RestConfiguration.class.getClassLoader().getResourceAsStream("cert/outgoing.CertwithKey.pkcs12"),
                SELFCERTPWD.toCharArray());
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("sunx509");
        kmf.init(selfCert, SELFCERTPWD.toCharArray());

        // 2 向信任管理器中导入服务器的ca认证证书
        KeyStore caCert = KeyStore.getInstance("jks");
        caCert.load(RestConfiguration.class.getClassLoader().getResourceAsStream("cert/ca.jks"), TRUSTCAPWD.toCharArray());
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("sunx509");
        tmf.init(caCert);

        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

        // 3 设置不认证域名 (设置DefaultHostnameVerifier.verify直接返回false)
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sc, new DefaultHostnameVerifier());

        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf)
                .build();

        return httpClient;
    }

}
