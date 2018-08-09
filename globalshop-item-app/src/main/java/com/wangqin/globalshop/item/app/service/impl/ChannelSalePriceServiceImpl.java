package com.wangqin.globalshop.item.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelSalePriceDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ChannelSalePriceDOMapperExt;
import com.wangqin.globalshop.item.app.service.IChannelSalePriceService;
@Service
public class ChannelSalePriceServiceImpl implements IChannelSalePriceService{

	@Autowired
	private ChannelSalePriceDOMapperExt channelSalePriceDOMapperExt;
	@Override
	public List<ChannelSalePriceDO> queryPriceListBySkuCode(String skuCode) {
		return channelSalePriceDOMapperExt.queryPriceListBySkuCode(skuCode);
	}
	
	@Override
	public void deletePriceListBySkuCode(String skuCode) {
		channelSalePriceDOMapperExt.deletePriceListBySkuCode(skuCode);
	}
	
	@Override
	public void insertChannelSalePriceSelective(ChannelSalePriceDO channelSalePriceDO) {
		channelSalePriceDOMapperExt.insertChannelSalePriceSelective(channelSalePriceDO);
	}
	
	@Override
	public void deletePriceListByItemCode(String itemCode) {
		channelSalePriceDOMapperExt.deletePriceListByItemCode(itemCode);
	}
	
	@Override
	public void updatePriceBySkuCodeAndChannelNo(String skuCode,Double salePrice,String channelNo) {
		channelSalePriceDOMapperExt.updatePriceBySkuCodeAndChannelNo(skuCode, salePrice,channelNo);
	}

	@Override
	public List<ChannelSalePriceDO> queryPriceListBySkuCodeAndChannel(String skuCode, int value){
		return channelSalePriceDOMapperExt.queryPriceListBySkuCodeAndChannel(skuCode, value);
	}
}
