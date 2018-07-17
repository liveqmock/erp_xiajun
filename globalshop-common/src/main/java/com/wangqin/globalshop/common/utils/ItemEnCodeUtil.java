package com.wangqin.globalshop.common.utils;

/**
 * 商品模块的系统编码
 * @author xiajun
 */
public class ItemEnCodeUtil {

	//商品的编码,@author:xiajun
	public static String generateItemCode(String categoryCode ) {
		return "I" + categoryCode + "T" + RandomUtils.getTimeRandomMillSeconds();
	}
		
	//sku的编码,@author:xiajun
	public static String generateSkuCode(String ItemCode,int i) {
		return "S" + ItemCode.substring(1) + String.format("%0" + 2 + "d", i);
	}
		
}
