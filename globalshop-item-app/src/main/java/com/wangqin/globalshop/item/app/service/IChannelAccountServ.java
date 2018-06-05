package com.wangqin.globalshop.item.app.service;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountDO;
import com.wangqin.globalshop.biz1.app.vo.ChannelAccountVO;

public interface IChannelAccountServ {

	List<ChannelAccountDO> queryChannelAccountList(ChannelAccountVO channelAccountVO);
}
