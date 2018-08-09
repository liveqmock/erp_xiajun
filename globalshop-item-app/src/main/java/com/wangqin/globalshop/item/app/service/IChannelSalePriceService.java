package com.wangqin.globalshop.item.app.service;

import java.util.List;

import com.wangqin.globalshop.biz1.app.enums.ChannelType;
import org.apache.ibatis.annotations.Param;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelSalePriceDO;

public interface IChannelSalePriceService {

	List<ChannelSalePriceDO> queryPriceListBySkuCode(String skuCode);
	
	void deletePriceListBySkuCode(String skuCode);
	
	void insertChannelSalePriceSelective(ChannelSalePriceDO channelSalePriceDO);
	
	void deletePriceListByItemCode(String itemCode);
	
	void updatePriceBySkuCodeAndChannelNo(String skuCode,Double salePrice,String channelNo);

	List<ChannelSalePriceDO> queryPriceListBySkuCodeAndChannel(String skuCode, int value);
}
