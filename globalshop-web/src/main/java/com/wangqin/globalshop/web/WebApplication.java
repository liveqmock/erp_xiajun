package com.wangqin.globalshop.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@PropertySource("classpath:props/${BIZ_SYSTEM}-config-${DEPLOY_ENV}.properties")
@ImportResource("classpath:spring/applicationContext.xml")
public class WebApplication {

    public static void main(String[] args) {
        //java -jar ./globalshop.war --DEPLOY_ENV=test --BIZ_SYSTEM=globalshop
        System.setProperty("BIZ_SYSTEM", "globalshop");
        System.setProperty("DEPLOY_ENV", "dev");
        SpringApplication.run(WebApplication.class, args);
    }

}
