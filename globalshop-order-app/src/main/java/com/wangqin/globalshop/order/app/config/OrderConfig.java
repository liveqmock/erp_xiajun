package com.wangqin.globalshop.order.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author xiajun
 *
 */

@Configuration
public class OrderConfig {

    @Value("${OPERATOR}")
    private String operator;
    
    @Value("${CONSTANT}")
    private String constant;

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getConstant() {
		return constant;
	}

	public void setConstant(String constant) {
		this.constant = constant;
	}
    
    

}
