package com.wangqin.globalshop.channel.service.channelItem;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelListingItemDO;

/**
 *
 * ChannelListingItem 表数据服务层接口
 *
 */
public interface IChannelListingItemService  {

	public int deleteByPrimaryKey(Long id);

	public int insert(ChannelListingItemDO record);

	public int insertSelective(ChannelListingItemDO record);

	public ChannelListingItemDO selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(ChannelListingItemDO record);

	public int updateByPrimaryKey(ChannelListingItemDO record);

	public ChannelListingItemDO queryPo(ChannelListingItemDO so);


}
