package com.zhliang.springboot.resttemplate.ssl.config;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * @Author: colin
 * @Date: 2019/10/14 12:02
 * @Description: 域名验证类
 * @Version: V1.0
 */
public class DefaultHostnameVerifier implements HostnameVerifier {

    @Override
    public boolean verify(String hostname, SSLSession session) {
        return true;
    }

}
