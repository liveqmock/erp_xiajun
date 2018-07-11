package com.wangqin.globalshop.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

/**
 * @author biscuit
 * @data 2018/07/09
 */
@Configuration
@Profile("prd")
@ImportResource("classpath:props/${BIZ_SYSTEM}-config-prd.properties")
public class PrdConfig {
}
