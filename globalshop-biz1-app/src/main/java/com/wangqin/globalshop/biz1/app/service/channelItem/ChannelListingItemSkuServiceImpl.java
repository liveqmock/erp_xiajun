package com.wangqin.globalshop.biz1.app.service.channelItem;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelListingItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ChannelListingItemSkuDOMapper;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ChannelListingItemSkuDOMapperExt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 * ChannelListingItemSku 表数据服务层接口实现类
 *
 */
@Service("channelListingItemSkuService")
public class ChannelListingItemSkuServiceImpl  implements IChannelListingItemSkuService {


	@Resource ChannelListingItemSkuDOMapperExt channelListingItemSkuDOMapperExt;


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
