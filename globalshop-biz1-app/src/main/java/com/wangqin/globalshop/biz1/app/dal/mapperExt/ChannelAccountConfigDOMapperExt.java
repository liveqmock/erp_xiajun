package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountConfigDO;
import com.wangqin.globalshop.biz1.app.dal.dataSo.ChannelAccountConfigSo;
import com.wangqin.globalshop.biz1.app.dal.mapper.ChannelAccountConfigDOMapper;

/**
 * Create by 777 on 2018/5/24
 */
public interface ChannelAccountConfigDOMapperExt extends ChannelAccountConfigDOMapper {

	public ChannelAccountConfigDO queryPo(ChannelAccountConfigSo channelAccountConfigSo);

}
