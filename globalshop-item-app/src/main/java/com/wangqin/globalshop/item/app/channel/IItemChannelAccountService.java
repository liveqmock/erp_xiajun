package com.wangqin.globalshop.item.app.channel;

import com.wangqin.globalshop.biz1.app.constants.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountDO;
import com.wangqin.globalshop.biz1.app.dal.dataSo.ChannelAccountSo;

import java.util.List;

public interface IItemChannelAccountService {


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

	public ChannelAccountDO selectOne(ChannelAccountDO tmEntity);

	public List<ChannelAccountDO> searchCAListByUserNo(String userNo);

	public List<ChannelAccountDO> searchCAListByComNoChType(String companyNo,ChannelType channelType);


}
