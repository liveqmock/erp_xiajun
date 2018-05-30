package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountDO;
import com.wangqin.globalshop.biz1.app.dal.dataSo.ChannelAccountSo;
import com.wangqin.globalshop.biz1.app.dal.mapper.ChannelAccountDOMapper;

import java.util.List;

public interface ChannelAccountDOMapperExt extends ChannelAccountDOMapper {

	public Integer queryPoCount(ChannelAccountSo channelAccountSo);

	public ChannelAccountDO queryPo(ChannelAccountSo channelAccountSo);

	public List<ChannelAccountDO> queryPoList(ChannelAccountSo channelAccountSo);

    ChannelAccountDO queryByChannelNo(String channelNo);

    ChannelAccountDO queryByTypeAndCompanyNo(ChannelAccountDO tmEntity);
}
