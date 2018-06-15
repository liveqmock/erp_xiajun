package com.wangqin.globalshop.channel.service.channel;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelDO;

import java.util.List;

/**
 * 注意这个是1.0版本的销售渠道界面，
 *
 * Create by 777 on 2018/6/8
 */
public interface IChannelOfflineService {

	public int deleteByPrimaryKey(Long id);

	public int insert(ChannelDO record);

	public int insertSelective(ChannelDO record);

	public ChannelDO selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(ChannelDO record);

	public int updateByPrimaryKey(ChannelDO record);

	public List<ChannelDO> 	queryChannelList();




}
