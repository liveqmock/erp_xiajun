package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelShopDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ChannelShopDOMapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * Create by 777 on 2018/6/11
 */
public interface ChannelShopDOMapperExt extends ChannelShopDOMapper {

	Integer queryHaihuRecordNumByCompanyNo(@Param("companyNo")String companyNo, @Param("haihuNo")String haihuNo);
	
	//商品发布：查询该公司可用的渠道 
	List<String> queryChannelNoByCompanyNo(String companyNo);
	
	public Long searchShopCount(ChannelShopDO channelShopDO);

	public ChannelShopDO searchShop(ChannelShopDO channelShopDO);

	public List<ChannelShopDO> searchShopList(ChannelShopDO channelShopDO);

	public Long gainShopCodeSequence();
}
