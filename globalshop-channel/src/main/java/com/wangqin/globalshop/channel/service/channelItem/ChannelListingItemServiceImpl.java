package com.wangqin.globalshop.channel.service.channelItem;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelListingItemDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ChannelListingItemDOMapper;
import com.wangqin.globalshop.channel.dal.mapperExt.ChannelListingItemDOMapperExt;

/**
 *
 * ChannelListingItem 表数据服务层接口实现类
 *
 */
@Service("channelListingItemService")
public class ChannelListingItemServiceImpl implements IChannelListingItemService {

	@Resource ChannelListingItemDOMapperExt channelListingItemDOMapperExt;

	public  ChannelListingItemDOMapper getMapper(){
		return  channelListingItemDOMapperExt;
	}


	public int deleteByPrimaryKey(Long id){
		return channelListingItemDOMapperExt.deleteByPrimaryKey(id);
	}

	public int insert(ChannelListingItemDO record){
		return channelListingItemDOMapperExt.insert(record);
	}

	public int insertSelective(ChannelListingItemDO record){
		return channelListingItemDOMapperExt.insertSelective(record);
	}

	public ChannelListingItemDO selectByPrimaryKey(Long id){
		return channelListingItemDOMapperExt.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(ChannelListingItemDO record){
		return channelListingItemDOMapperExt.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(ChannelListingItemDO record){
		return channelListingItemDOMapperExt.updateByPrimaryKey(record);
	}

	@Override
	public ChannelListingItemDO queryPo(ChannelListingItemDO so){
		return this.channelListingItemDOMapperExt.queryPo(so);
	}



}
