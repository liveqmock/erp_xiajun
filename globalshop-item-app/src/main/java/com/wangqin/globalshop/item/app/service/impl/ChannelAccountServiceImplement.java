package com.wangqin.globalshop.item.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ChannelAccountDOMapperExt;
import com.wangqin.globalshop.biz1.app.vo.ChannelAccountVO;
import com.wangqin.globalshop.item.app.service.IChannelAccountServ;

@Service
public class ChannelAccountServiceImplement implements IChannelAccountServ{

	@Autowired
	private ChannelAccountDOMapperExt channelAccountDOMapperExt;
	
	@Override
	public List<ChannelAccountDO> queryChannelAccountList(ChannelAccountVO channelAccountVO) {
		return channelAccountDOMapperExt.queryChannelAccountList(channelAccountVO);
	}
}
