package com.wangqin.globalshop.item.app.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountDO;

import java.util.List;

public interface IItemChannelAccountService {

	//商品发布：查询该公司可用的渠道 
	List<String> queryChannelNoByCompanyNo(String companyNo);
		
	//List<ChannelAccountDO> queryChannelAccountList(ChannelAccountVO channelAccountVO);
	
	List<ChannelAccountDO> queryChannelAccountListByCompanyNo(String companyNo);
	
	String queryChannelNoByChannelNameAndCompanyNo(String channelName,String companyNo);
	
	String queryChannelNameByChannelNoAndCompanyNo(String channelNo,String companyNo);
}
