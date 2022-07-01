package com.zhangliang.springboot.dyncmic.druid.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 次数据源 另一个数据源配置
 */
@Configuration
@MapperScan(basePackages = SurveyDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "surveySqlSessionFactory")
public class SurveyDataSourceConfig {

    /**
     * 配置多数据源 关键就在这里 这里配置了不同的mapper对应不同的数据源
     */
    static final String PACKAGE = "com.zhangliang.springboot.dyncmic.druid.dao.survey";
    static final String MAPPER_LOCATION = "classpath*:mapper/survey/*.xml";

    @Value("${survey.datasource.url}")
    private String url;

    @Value("${survey.datasource.username}")
    private String username;

    @Value("${survey.datasource.password}")
    private String password;

    @Value("${survey.datasource.driverClassName}")
    private String driverClass;


    @Value("${spring.datasource.initialSize}")
    private int initialSize;

    @Value("${spring.datasource.minIdle}")
    private int minIdle;

    @Value("${spring.datasource.maxActive}")
    private int maxActive;

    @Value("${spring.datasource.maxWait}")
    private int maxWait;

    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;

    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;

    @Value("${spring.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;

    @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Value("${spring.datasource.filters}")
    private String filters;

    @Value("{spring.datasource.connectionProperties}")
    private String connectionProperties;

    @Bean(name = "surveyDataSource")
    public DataSource surveyDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClass);

        //具体配置
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        try {
            dataSource.setFilters("stat,wall,slf4j");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            dataSource.setFilters(filters);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dataSource.setConnectionProperties(connectionProperties);
        return dataSource;
    }

    @Bean(name = "surveyTransactionManager")
    public DataSourceTransactionManager surveyTransactionManager() {
        return new DataSourceTransactionManager(surveyDataSource());
    }

    @Bean(name = "surveySqlSessionFactory")
    public SqlSessionFactory surveySqlSessionFactory(@Qualifier("surveyDataSource") DataSource surveyDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(surveyDataSource);

//     //分页插件
//     Interceptor interceptor = new PageInterceptor();
//     Properties properties = new Properties();
//     //数据库
//     properties.setProperty("helperDialect", "mysql");
//     //是否将参数offset作为PageNum使用
//     properties.setProperty("offsetAsPageNum", "true");
//     //是否进行count查询
//     properties.setProperty("rowBoundsWithCount", "true");
//     //是否分页合理化
//     properties.setProperty("reasonable", "false");
//     interceptor.setProperties(properties);
//     sessionFactory.setPlugins(new Interceptor[] {interceptor});


        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(SurveyDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}