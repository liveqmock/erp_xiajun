package com.wangqin.globalshop.channel.service.channelAccountConfig;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountConfigDO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ChannelAccountConfigSo;

public interface IChannelAccountConfigService  {

	public ChannelAccountConfigDO queryPo(ChannelAccountConfigSo channelAccountConfigSo);

	public void createOrupdateConfig(String companyNo, String shopCode);

}
