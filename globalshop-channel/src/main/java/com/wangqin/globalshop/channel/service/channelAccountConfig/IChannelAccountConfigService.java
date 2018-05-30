package com.wangqin.globalshop.channel.service.channelAccountConfig;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountConfigDO;
import com.wangqin.globalshop.channel.dal.dataSo.ChannelAccountConfigSo;

public interface IChannelAccountConfigService  {

	public ChannelAccountConfigDO queryPo(ChannelAccountConfigSo channelAccountConfigSo);

	public void createOrupdateConfig(String companyNo, String shopCode);

}