package com.wangqin.globalshop.biz1.app.service.channelAccount;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountDO;
import com.wangqin.globalshop.biz1.app.dal.dataSo.ChannelAccountSo;

import java.util.List;

public interface IChannelAccountService {

	public Integer queryPoCount(ChannelAccountSo channelAccountSo);

	public ChannelAccountDO queryPo(ChannelAccountSo channelAccountSo);

	public List<ChannelAccountDO> queryPoList(ChannelAccountSo channelAccountSo);

	public void createOrupdateAccount4Taobao(String shopCode, String cookie);

	ChannelAccountDO queryByChannelNo(String channelNo);
}
