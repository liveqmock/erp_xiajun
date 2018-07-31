package com.wangqin.globalshop.item.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ChannelAccountDOMapperExt;
import com.wangqin.globalshop.item.app.service.IItemChannelAccountService;

@Service
public class ItemChannelAccountServiceImpl implements IItemChannelAccountService{

	@Autowired
	private ChannelAccountDOMapperExt channelAccountDOMapperExt;
	
//	@Override
//	public List<ChannelAccountDO> queryChannelAccountList(ChannelAccountVO channelAccountVO) {
//		return channelAccountDOMapperExt.queryChannelAccountList(channelAccountVO);
//	}
	
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
