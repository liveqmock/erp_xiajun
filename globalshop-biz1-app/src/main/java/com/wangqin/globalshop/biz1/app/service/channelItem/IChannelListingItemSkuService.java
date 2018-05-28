package com.wangqin.globalshop.biz1.app.service.channelItem;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelListingItemSkuDO;

/**
 *
 * ChannelListingItemSku 表数据服务层接口
 *
 */
public interface IChannelListingItemSkuService {

	public int deleteByPrimaryKey(Long id);

	public int insert(ChannelListingItemSkuDO record);

	public int insertSelective(ChannelListingItemSkuDO record);

	public ChannelListingItemSkuDO selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(ChannelListingItemSkuDO record);

	public int updateByPrimaryKey(ChannelListingItemSkuDO record);

	public ChannelListingItemSkuDO queryPo(ChannelListingItemSkuDO so);


}
