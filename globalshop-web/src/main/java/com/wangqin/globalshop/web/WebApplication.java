package com.wangqin.globalshop.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@PropertySource("classpath:props/${BIZ_SYSTEM}-config-${DEPLOY_ENV}.properties")
@ImportResource("classpath:spring/applicationContext.xml")
@EnableScheduling
@ServletComponentScan
@EnableFeignClients(basePackages = {"com.wangqin.globalshop.item.api"})
//@EnableDiscoveryClient
public class WebApplication {


	@LoadBalanced
	@Bean
	public RestTemplate loadbalancedRestTemplate() {
		return new RestTemplate();
	}



    public static void main(String[] args) {
        //java -jar ./globalshop.war --DEPLOY_ENV=test --BIZ_SYSTEM=globalshop
        System.setProperty("BIZ_SYSTEM", "globalshop");
        System.setProperty("DEPLOY_ENV", "prd");
        SpringApplication.run(WebApplication.class, args);
    }

}
