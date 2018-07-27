package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelSalePriceDO;



public interface ChannelSalePriceDOMapperExt {

	List<ChannelSalePriceDO> queryPriceListBySkuCode(String skuCode);
	
	void deletePriceListBySkuCode(String skuCode);
	
	void insertChannelSalePriceSelective(ChannelSalePriceDO channelSalePriceDO);
	
	void deletePriceListByItemCode(String itemCode);
	
	void updatePriceBySkuCodeAndChannelNo(@Param("skuCode")String skuCode,
			@Param("salePrice")Double salePrice,@Param("channelNo")String channelNo);
	
}