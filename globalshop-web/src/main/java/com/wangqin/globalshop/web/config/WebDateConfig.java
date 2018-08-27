package com.wangqin.globalshop.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.text.DateFormat;

/**
 * Created by BG235729 on 2018/8/25.
 */

@Configuration
public class WebDateConfig {
        @Autowired
        private Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder;
        @Bean
        public MappingJackson2HttpMessageConverter MappingJsonpHttpMessageConverter() {
            ObjectMapper mapper = jackson2ObjectMapperBuilder.build();
            // ObjectMapper为了保障线程安全性，里面的配置类都是一个不可变的对象
            // 所以这里的setDateFormat的内部原理其实是创建了一个新的配置类
            DateFormat dateFormat = mapper.getDateFormat();
            mapper.setDateFormat(new WebDateFormat(dateFormat));
            MappingJackson2HttpMessageConverter mappingJsonpHttpMessageConverter = new MappingJackson2HttpMessageConverter(
                    mapper);
            return mappingJsonpHttpMessageConverter;
        }
}
