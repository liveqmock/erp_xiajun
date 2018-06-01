package com.wangqin.globalshop.channel.service.channelItem;

import javax.annotation.Resource;

import com.wangqin.globalshop.channel.dal.mapperExt.CAChannelListingItemSkuDOMapperExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelListingItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ChannelListingItemSkuDOMapper;

/**
 *
 * ChannelListingItemSku 表数据服务层接口实现类
 *
 */
@Service("channelListingItemSkuService")
public class ChannelListingItemSkuServiceImpl  implements IChannelListingItemSkuService {


	@Autowired CAChannelListingItemSkuDOMapperExt channelListingItemSkuDOMapperExt;


	public ChannelListingItemSkuDOMapper getMapper(){
		return channelListingItemSkuDOMapperExt;
	}

	public int deleteByPrimaryKey(Long id){
		return channelListingItemSkuDOMapperExt.deleteByPrimaryKey(id);
	}

	public int insert(ChannelListingItemSkuDO record){
		return channelListingItemSkuDOMapperExt.insert(record);
	}

	public int insertSelective(ChannelListingItemSkuDO record){
		return channelListingItemSkuDOMapperExt.insertSelective(record);
	}

	public ChannelListingItemSkuDO selectByPrimaryKey(Long id){
		return channelListingItemSkuDOMapperExt.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(ChannelListingItemSkuDO record){
		return channelListingItemSkuDOMapperExt.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(ChannelListingItemSkuDO record){
		return channelListingItemSkuDOMapperExt.updateByPrimaryKey(record);
	}


	@Override
	public ChannelListingItemSkuDO queryPo(ChannelListingItemSkuDO so){
		return this.channelListingItemSkuDOMapperExt.queryPo(so);
	}


}
