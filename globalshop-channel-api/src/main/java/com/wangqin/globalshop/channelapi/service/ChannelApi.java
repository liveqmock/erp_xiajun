package com.wangqin.globalshop.channelapi.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Create by 777 on 2018/8/10
 */
public interface ChannelApi {



	/**
	 * 新接口：商品发布接口
	 * @param shopCode 店铺唯一性编码
	 * @param itemCode 商品唯一性编码
	 */
	public void addItem(String shopCode, String itemCode);




}
