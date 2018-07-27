package com.wangqin.globalshop.item.app.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountDO;
import com.wangqin.globalshop.biz1.app.vo.ChannelAccountVO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface IItemChannelAccountService {

	//List<ChannelAccountDO> queryChannelAccountList(ChannelAccountVO channelAccountVO);
	
	List<ChannelAccountDO> queryChannelAccountListByCompanyNo(String companyNo);
	
	String queryChannelNoByChannelNameAndCompanyNo(String channelName,String companyNo);
	
	String queryChannelNameByChannelNoAndCompanyNo(String channelNo,String companyNo);
}
