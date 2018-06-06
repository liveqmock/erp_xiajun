package com.wangqin.globalshop.item.app.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountDO;
import com.wangqin.globalshop.biz1.app.vo.ChannelAccountVO;

import java.util.List;

public interface IChannelAccountServ {

	List<ChannelAccountDO> queryChannelAccountList(ChannelAccountVO channelAccountVO);
}
