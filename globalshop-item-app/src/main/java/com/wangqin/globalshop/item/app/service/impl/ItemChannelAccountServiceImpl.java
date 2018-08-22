package com.wangqin.globalshop.item.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ChannelAccountDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ChannelShopDOMapperExt;
import com.wangqin.globalshop.item.app.service.IItemChannelAccountService;

@Service
public class ItemChannelAccountServiceImpl implements IItemChannelAccountService{

	@Autowired
	private ChannelAccountDOMapperExt channelAccountDOMapperExt;
	@Autowired
	private ChannelShopDOMapperExt shopMapperExt;
	
//	@Override
//	public List<ChannelAccountDO> queryChannelAccountList(ChannelAccountVO channelAccountVO) {
//		return channelAccountDOMapperExt.queryChannelAccountList(channelAccountVO);
//	}
	
	//商品发布：查询该公司可用的渠道 
	@Override
	public List<String> queryChannelNoByCompanyNo(String companyNo) {
		return shopMapperExt.queryChannelNoByCompanyNo(companyNo);
	}
	
	@Override
	public List<ChannelAccountDO> queryChannelAccountListByCompanyNo(String companyNo) {
		return channelAccountDOMapperExt.queryChannelAccountListByCompanyNo(companyNo);
	}
	
	@Override
	public String queryChannelNoByChannelNameAndCompanyNo(String channelName,String companyNo) {
		return channelAccountDOMapperExt.queryChannelNoByChannelNameAndCompanyNo(channelName, companyNo);				
	}
	
	@Override
	public String queryChannelNameByChannelNoAndCompanyNo(String channelNo,String companyNo) {
			return channelAccountDOMapperExt.queryChannelNameByChannelNoAndCompanyNo(channelNo, companyNo);
	}
	 
}
