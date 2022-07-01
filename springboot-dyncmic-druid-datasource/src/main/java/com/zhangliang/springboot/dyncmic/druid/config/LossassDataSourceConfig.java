package com.zhangliang.springboot.dyncmic.druid.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 主数据源配置
 */
@Configuration
@MapperScan(basePackages = LossassDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "lossassSqlSessionFactory")
public class LossassDataSourceConfig {

    /**
     * 配置多数据源 关键就在这里 这里配置了不同的mapper对应不同的数据源
     */
    static final String PACKAGE = "com.zhangliang.springboot.dyncmic.druid.dao.lossass";
    static final String MAPPER_LOCATION = "classpath*:mapper/lossass/*.xml";

    /**
     * 连接数据库信息 这个其实更好的是用配置中心完成
     */
    @Value("${lossass.datasource.url}")
    private String url;  
      
    @Value("${lossass.datasource.username}")
    private String username;  
      
    @Value("${lossass.datasource.password}")
    private String password;  
      
    @Value("${lossass.datasource.driverClassName}")
    private String driverClassName;


    /**
     * 下面的配置信息可以读取配置文件，其实可以直接写死 如果是多数据源的话 还是考虑读取配置文件
     */
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
    
    
    @Bean(name = "lossassDataSource")
    @Primary //标志这个 Bean 如果在多个同类 Bean 候选时，该 Bean 优先被考虑。
    public DataSource lossassDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);  
        dataSource.setUsername(username);  
        dataSource.setPassword(password);  
        dataSource.setDriverClassName(driverClassName);  
          
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
        dataSource.setPoolPreparedStatements(poolPreparedStatements);  
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);

        /**
         * 这个是用来配置 druid 监控sql语句的 非常有用 如果你有两个数据源 这个配置哪个数据源就坚实哪个数据源的sql 同时配置那就都监控
         */
        try {
            dataSource.setFilters(filters);
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        dataSource.setConnectionProperties(connectionProperties);  
        return dataSource;
    }

    @Bean(name = "lossassTransactionManager")
    @Primary
    public DataSourceTransactionManager lossassTransactionManager() {
        return new DataSourceTransactionManager(lossassDataSource());
    }

    @Bean(name = "lossassSqlSessionFactory")
    @Primary
    public SqlSessionFactory lossassSqlSessionFactory(@Qualifier("lossassDataSource") DataSource lossassDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(lossassDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(LossassDataSourceConfig.MAPPER_LOCATION));

//        //分页插件
//        Interceptor interceptor = new PageInterceptor();
//        Properties properties = new Properties();
//        //数据库
//        properties.setProperty("helperDialect", "mysql");
//        //是否将参数offset作为PageNum使用
//        properties.setProperty("offsetAsPageNum", "true");
//        //是否进行count查询
//        properties.setProperty("rowBoundsWithCount", "true");
//        //是否分页合理化
//        properties.setProperty("reasonable", "false");
//        interceptor.setProperties(properties);
//        sessionFactory.setPlugins(new Interceptor[] {interceptor});
        
        return sessionFactory.getObject();
    }
}