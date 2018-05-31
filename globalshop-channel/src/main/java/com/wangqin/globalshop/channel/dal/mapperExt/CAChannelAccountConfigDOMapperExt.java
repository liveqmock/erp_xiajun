package com.wangqin.globalshop.channel.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountConfigDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ChannelAccountConfigDOMapper;
import com.wangqin.globalshop.channel.dal.dataSo.ChannelAccountConfigSo;

/**
 * Create by 777 on 2018/5/24
 */
public interface CAChannelAccountConfigDOMapperExt extends ChannelAccountConfigDOMapper {

	public ChannelAccountConfigDO queryPo(ChannelAccountConfigSo channelAccountConfigSo);

}
