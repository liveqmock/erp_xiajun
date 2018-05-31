package com.wangqin.globalshop.channel.service.channelAccount;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountDO;
import com.wangqin.globalshop.channel.dal.dataSo.ChannelAccountSo;

public interface IChannelAccountService {


	public int deleteByPrimaryKey(Long id);

	public int insert(ChannelAccountDO record);

	public int insertSelective(ChannelAccountDO record);

	public ChannelAccountDO selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(ChannelAccountDO record);

	public int updateByPrimaryKey(ChannelAccountDO record);


	public Integer queryPoCount(ChannelAccountSo channelAccountSo);

	public ChannelAccountDO queryPo(ChannelAccountSo channelAccountSo);

	public List<ChannelAccountDO> queryPoList(ChannelAccountSo channelAccountSo);

	public void createOrupdateAccount4Taobao(String shopCode, String cookie);

}
