package com.wangqin.globalshop.common.utils;

import org.apache.commons.lang.math.RandomUtils;

import java.util.Date;

/**
 * Create by 777 on 2018/6/20
 */
public class CodeGenUtil {

	public static String genUserNo(){

		String userNo = "U"+DateUtil.formatDate(new Date(),"yyMMddHHmmss")+String.format("%1$06d", RandomUtils.nextInt(1000000));

		return userNo;
	}


	public static String getChannelCode() {
		return "CH"+System.currentTimeMillis()+ RandomUtils.nextInt(10000);
	}

	public static String getScaleCode() {
		return "s"+System.currentTimeMillis()+ RandomUtils.nextInt(10000);
    }

	public static String getItemCode(String categoryCode ) {
		return "I" + categoryCode + "Q" + RandomUtils.nextInt(10000);
	}

	public static String getBuyerTaskNo() {
		return "TASK" + System.currentTimeMillis();
	}

	public static String getBuyerTaskDetailNo() {
		return "TASKD" + System.currentTimeMillis();
	}

    public static String getShopCode() {
		return "SHOP" + RandomUtils.nextInt(10000);
    }

	public static String getOrderNo() {
		return "OR" + System.currentTimeMillis();
	}

	public static String getSubOrderNo() {
		return "SUB" + System.currentTimeMillis();
	}

	public static String getSkuCode(String categoryCode,String ItemCode,int i ) {
		return "S" + categoryCode + "Q"+ItemCode+String.format("%0" + 4 + "d", ++i);
	}

    public static String getShippingNO(Long aLong) {
		return  "PKG" + DateUtil.formatDate(new Date(), DateUtil.DATE_PARTEN_YYMMDDHHMMSS)+aLong;
    }

	public static String getInvOnWarehouseNo() {
		return "IN" + System.currentTimeMillis();
	}

	public static String getInventoryOutNo() {
		return "INO" + System.currentTimeMillis();
	}
}
