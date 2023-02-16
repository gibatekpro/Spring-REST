package com.gibatekpro.customer_tracker_rest.config.app_config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

@Configuration
@EnableTransactionManagement
@EnableWebMvc
@ComponentScan("com.gibatekpro.customer_tracker_rest")
@PropertySource("classpath:persistence-mysql.properties")
public class AppConfig {

    @Autowired
    private Environment environment;

    private Logger logger = Logger.getLogger(getClass().getName());

    @Bean
    public ViewResolver viewResolver() {

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }

    @Bean
    public DataSource dataSource() {

        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();

        try {
            comboPooledDataSource.setDriverClass(environment.getProperty("jdbc.driver"));
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }

        logger.info("jdbc Url = " + environment.getProperty("jdbc.url"));
        logger.info("jdbc user = " + environment.getProperty("jdbc.user"));
        logger.info("jdbc password = " + environment.getProperty("jdbc.password"));

        comboPooledDataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
        comboPooledDataSource.setUser(environment.getProperty("jdbc.user"));
        comboPooledDataSource.setPassword(environment.getProperty("jdbc.password"));


        comboPooledDataSource.setInitialPoolSize(propInt("connection.pool.initialPoolSize"));
        comboPooledDataSource.setMinPoolSize(propInt("connection.pool.minPoolSize"));
        comboPooledDataSource.setMaxPoolSize(propInt("connection.pool.maxPoolSize"));
        comboPooledDataSource.setMaxIdleTime(propInt("connection.pool.maxIdleTime"));

        return comboPooledDataSource;

    }

    private int propInt(String propVal){

        String propName = environment.getProperty(propVal);

        assert propName != null;
        return Integer.parseInt(propName);
    }

    private Properties hibernateProperties() {

        Properties properties = new Properties();

        properties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        properties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));

        return properties;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {

        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();

        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan(environment.getProperty("hibernate.packagesToScan"));
        sessionFactoryBean.setHibernateProperties(hibernateProperties());

        return sessionFactoryBean;

    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {

        HibernateTransactionManager transactionManager = new HibernateTransactionManager();

        transactionManager.setSessionFactory(sessionFactory);

        return transactionManager;

    }

}
