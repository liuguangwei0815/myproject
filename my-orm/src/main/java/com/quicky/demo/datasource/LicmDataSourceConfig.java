package com.quicky.demo.datasource;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@MapperScan(
        basePackages = {"com.quicky.demo.licm.mapper"},
        sqlSessionFactoryRef = "licmSqlSession"
)
public class LicmDataSourceConfig {
 
    @Value("${spring.datasource.licm.url}")
    private String url;
 
    @Value("${spring.datasource.licm.username}")
    private String user;
 
    @Value("${spring.datasource.licm.password}")
    private String password;
 
    @Value("${spring.datasource.licm.driver-class-name}")
    private String driverClass;
 
    @Bean(name = "licmDataSource")
    public DataSource setDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        try {
            dataSource.setFilters("stat");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }
 
    @Bean(name = "licmTransation")
    public DataSourceTransactionManager setTransationManager(){
        return new DataSourceTransactionManager(setDataSource());
    }
 
    @Bean(name = "licmSqlSession")
    public SqlSessionFactory setSqlSession() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(setDataSource());
        bean.setMapperLocations( new PathMatchingResourcePatternResolver().getResources("classpath:licm/mapper/*.xml"));
        return bean.getObject();
    }
 
}