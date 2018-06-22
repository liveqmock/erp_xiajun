package com.wangqin.globalshop.common.utils;

import org.apache.commons.lang.math.RandomUtils;

import java.util.Date;

/**
 * Create by 777 on 2018/6/20
 */
public class CodeGenUtil {

	public static String genUserNo(){

		String userNo = "9999"+DateUtil.formatDate(new Date(),"yyMMddHHmmss")+String.format("%1$06d", RandomUtils.nextInt(1000000));

		return userNo;
	}


}
