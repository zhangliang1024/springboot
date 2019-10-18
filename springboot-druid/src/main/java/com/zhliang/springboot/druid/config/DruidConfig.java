package com.zhliang.springboot.druid.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Author: colin
 * @Date: 2019/8/12 16:47
 * @Description:
 * @Version: V1.0
 */
//@Configuration
public class DruidConfig {

    /**
     * 注册 数据源
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){
        return new DruidDataSource();
    }

    /**
     * 注册 servletRegistrationBean
     */
    @Bean
    public ServletRegistrationBean druidServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        //初始化参数配置

        //设置白名单,多个IP 逗号隔开
        bean.addInitParameter("allow","127.0.0.1");
        //设置黑名单(同时存在时，deny优先于allow)
        //如果满足deny的话提示:Sorry, you are not permitted to view this page.
        bean.addInitParameter("deny","127.0.0.2");
        //是否能够重置数据
        bean.addInitParameter("resetEnable","false");
        //登陆查看的用户名密码
        bean.addInitParameter("loginUsername","admin");
        bean.addInitParameter("loginPassword","admin");
        return bean;
    }

    /**
     * 注册filterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean statFilter(){
        FilterRegistrationBean filter = new FilterRegistrationBean<>(new WebStatFilter());
        //添加过滤规则
        filter.addUrlPatterns("/*");
        //添加不需要忽略的格式信息
        filter.addInitParameter("exclustions","*.js,*.gif,/druid/*");
        return filter;
    }

}
