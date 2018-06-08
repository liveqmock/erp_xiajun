package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ChannelDOMapper;

import java.util.List;

/**
 * Create by 777 on 2018/6/8
 */
public interface ChannelDOMapperExt extends ChannelDOMapper {

	public List<ChannelDO> queryChannelList();

}
