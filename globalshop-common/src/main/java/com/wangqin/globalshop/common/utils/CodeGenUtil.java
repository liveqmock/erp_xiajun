package com.wangqin.globalshop.common.utils;

import org.apache.commons.lang.math.RandomUtils;

import java.util.Date;

/**
 * Create by 777 on 2018/6/20
 */
public class CodeGenUtil {

	public static String genUserNo(){

		String userNo = "US"+DateUtil.formatDate(new Date(),"yyMMddHHmmss")+String.format("%1$06d", RandomUtils.nextInt(1000000));

		return userNo;
	}


	public static String getChannelCode() {
		return "CH"+System.currentTimeMillis()+ RandomUtils.nextInt(10000);
	}

	public static String getScaleCode() {
		return "SC"+System.currentTimeMillis()+ RandomUtils.nextInt(10000);
    }

	//商品的编码,@author:xiajun
	public static String generateItemCode(String categoryCode ) {
		return "I" + categoryCode + "T" + com.wangqin.globalshop.common.utils.RandomUtils.getTimeRandom()+RandomUtils.nextInt(10000);
	}

	public static String getBuyerTaskNo(Long buyerId,Long nextTaskNum) {

		String taskOrderNo = "T" + TimeUtil.getDate(TimeUtil.DEFAULT_DATE_NO_SEPRATORS_FORMAT, new Date())
				+ "U" +  com.wangqin.globalshop.common.utils.RandomUtils.getTimeRandom()+RandomUtils.nextInt(10000) + nextTaskNum;
		return taskOrderNo;
	}

	public static String getBuyerTaskDetailNo() {
		return "TASKD" + System.currentTimeMillis();
	}

    public static String getShopCode() {
		return "SHOP"+System.currentTimeMillis();
    }

	public static String getOrderNo() {
		return "OR" + System.currentTimeMillis();
	}

	public static String getMallReturnOrderNo() {
		return "MRO" + System.currentTimeMillis();
	}

	public static String getSubOrderNo() {
		return "SUB" + System.currentTimeMillis();
	}

	//sku的编码,@author:xiajun
	public static String generateSkuCode(String ItemCode,int i) {
		return "SKU" + ItemCode.substring(1) + String.format("%0" + 2 + "d", i);
	}

    public static String getShippingNO(Long aLong) {
		return  "PKG" + DateUtil.formatDate(new Date(), DateUtil.DATE_PARTEN_YYMMDDHHMMSS)+aLong;
    }

	public static String getInvOnWarehouseNo() {
		return "INW" + System.currentTimeMillis();
	}

	public static String getInventoryOutNo() {
		return "INO" + System.currentTimeMillis();
	}

	public static String getSettlementNo(){
		return "SN"+ DateUtil.formatDate(new Date(), DateUtil.DATE_PARTEN_YYMMDDHHMMSS);
	}
}
