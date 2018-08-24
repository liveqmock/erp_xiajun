package com.wangqin.globalshop.web;


import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;

/**
 * @author biscuit
 * @data 2018/06/21
 */
@Configuration
public class SystemConfig {

    @Bean("sys")
    @Profile("test")
    public PropertiesFactoryBean testPtFactoryBean(){
        PropertiesFactoryBean config = new PropertiesFactoryBean();
        config.setLocations(new ClassPathResource("/systemConfig-test.properties"));
        return config;

    }
    @Bean("sys")
    @Profile("dev")
    public PropertiesFactoryBean devPtFactoryBean(){
        PropertiesFactoryBean config = new PropertiesFactoryBean();
        config.setLocations(new ClassPathResource("/systemConfig-dev.properties"));
        return config;

    }
    @Bean("sys")
    @Profile("prd")
    public PropertiesFactoryBean proPtFactoryBean(){
        PropertiesFactoryBean config = new PropertiesFactoryBean();
        config.setLocations(new ClassPathResource("/systemConfig-prd.properties"));
        return config;

    }


}
