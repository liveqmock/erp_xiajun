package com.wangqin.globalshop.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

/**
 * @author biscuit
 * @data 2018/07/09
 */
@Configuration
@Profile("test")
@ImportResource("classpath:props/${BIZ_SYSTEM}-config-test.properties")
public class TestConfig {
}
