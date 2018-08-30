package com.wangqin.globalshop.item.api.channel;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ItemChannelAccountService {

	//商品发布：查询该公司可用的渠道
	@RequestMapping(value = "/channel/queryChannelNoByCompanyNo", method = RequestMethod.POST)
	List<String> queryChannelNoByCompanyNo(@RequestParam("companyNo") String companyNo);
		
	//List<ChannelAccountDO> queryChannelAccountList(ChannelAccountVO channelAccountVO);
	
//	List<ChannelAccountDO> queryChannelAccountListByCompanyNo(String companyNo);
//
//	String queryChannelNoByChannelNameAndCompanyNo(String channelName, String companyNo);
//
//	String queryChannelNameByChannelNoAndCompanyNo(String channelNo, String companyNo);
}
