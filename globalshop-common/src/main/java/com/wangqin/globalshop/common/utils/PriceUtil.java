package com.wangqin.globalshop.common.utils;

import java.math.BigDecimal;
import java.util.List;

public class PriceUtil {

	/**
     * 重新计算商品的价格区间
     * @param newSkuPrice 当前sku的价格List
     * @return 商品的价格区间
     */
    public static String calNewPriceRange(List<Double> newSkuPrice) {
    	BigDecimal minPrice = new BigDecimal(newSkuPrice.get(0).toString());
		BigDecimal maxPrice = new BigDecimal(newSkuPrice.get(0).toString());		
		for (Double newPirce:newSkuPrice) {		
			BigDecimal curSkuPrice = new BigDecimal(newPirce.toString());
			maxPrice = maxPrice.compareTo(curSkuPrice) < 0 ? curSkuPrice : maxPrice;
			minPrice = minPrice.compareTo(curSkuPrice) > 0 ? curSkuPrice : minPrice;
		}   		 
		if (0 == maxPrice.compareTo(minPrice)) {
			return formatPrice(maxPrice.toPlainString());
		} else {
			return formatPrice(minPrice.toPlainString())+"-"+formatPrice(maxPrice.toPlainString());
		}
    }
    
    /**
     * 价格字符串格式化处理，包装小数点后面有两位
     * @param price
     * @return
     */
    public static String formatPrice(String price) {
    	String priceArray[] = price.split("\\.");
    	if(1 == priceArray.length) {
    		return price+".00";
    	} else {
    		if(1 == priceArray[1].length()) {
    			return price+"0";
    		}
    	}
    	return price;
    }
}
