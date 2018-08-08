package com.wangqin.globalshop.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Patrick on 2018/5/18.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/css/**").addResourceLocations("/static_resources/css/");
//        registry.addResourceHandler("/images/**").addResourceLocations("/static_resources/images/");
//        registry.addResourceHandler("/*.html").addResourceLocations("/static_resources/html/");
    }
}
