package com.wangqin.globalshop.web;


import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * @author biscuit
 * @data 2018/06/21
 */
@Configuration
public class SystemConfig {

    @Bean("sys")
    public PropertiesFactoryBean propertiesFactoryBean(){
        PropertiesFactoryBean config = new PropertiesFactoryBean();
        config.setLocations(new ClassPathResource("/systemConfig.properties"));
        return config;

    }
}
