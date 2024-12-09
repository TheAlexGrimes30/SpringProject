package com.example.demo.config;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Конфигурационный класс Spring-приложения.
 * Содержит определения бинов и настройки, необходимые для работы приложения.
 */
@Configuration
@ComponentScan(basePackages = "com.example.demo")
@PropertySource("classpath:application.properties")
public class SpringConfig {

    /**
     * Объект Environment используется для доступа к свойствам,
     * определенным в файле application.properties.
     */
    @Autowired
    private Environment env;

    /**
     * Создает и настраивает бин DataSource для подключения к базе данных.
     *
     * @return настроенный объект {@link DataSource}.
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }
}
