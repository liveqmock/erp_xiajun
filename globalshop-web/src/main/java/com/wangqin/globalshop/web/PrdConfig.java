package com.wangqin.globalshop.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * @author biscuit
 * @data 2018/07/09
 */
@Configuration
@Profile("prd")
@PropertySource("classpath:props/${BIZ_SYSTEM}-config-prd.properties")
public class PrdConfig {
}
