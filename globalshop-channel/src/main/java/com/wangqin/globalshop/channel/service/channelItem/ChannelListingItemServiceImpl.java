package com.wangqin.globalshop.channel.service.channelItem;


import com.wangqin.globalshop.biz1.app.dal.mapperExt.ChannelListingItemDOMapperExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelListingItemDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ChannelListingItemDOMapper;

/**
 *
 * ChannelListingItem 表数据服务层接口实现类
 *
 */
@Service("channelListingItemService")
public class ChannelListingItemServiceImpl implements IChannelListingItemService {

	@Autowired
	ChannelListingItemDOMapperExt channelListingItemDOMapperExt;

	public  ChannelListingItemDOMapper getMapper(){
		return  channelListingItemDOMapperExt;
	}


	@Override
    public int deleteByPrimaryKey(Long id){
		return channelListingItemDOMapperExt.deleteByPrimaryKey(id);
	}

	@Override
    public int insert(ChannelListingItemDO record){
		return channelListingItemDOMapperExt.insert(record);
	}

	@Override
    public int insertSelective(ChannelListingItemDO record){
		return channelListingItemDOMapperExt.insertSelective(record);
	}

	@Override
    public ChannelListingItemDO selectByPrimaryKey(Long id){
		return channelListingItemDOMapperExt.selectByPrimaryKey(id);
	}

	@Override
    public int updateByPrimaryKeySelective(ChannelListingItemDO record){
		return channelListingItemDOMapperExt.updateByPrimaryKeySelective(record);
	}

	@Override
    public int updateByPrimaryKey(ChannelListingItemDO record){
		return channelListingItemDOMapperExt.updateByPrimaryKey(record);
	}

	@Override
	public ChannelListingItemDO queryPo(ChannelListingItemDO so){
		return this.channelListingItemDOMapperExt.queryPo(so);
	}



}
