package com.wangqin.globalshop.channel.service.channelItem;


import com.mchange.v2.holders.ChangeNotifyingSynchronizedIntHolder;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelListingItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ChannelListingItemDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ChannelListingItemSkuDOMapperExt;
import com.wangqin.globalshop.biz1.app.enums.ItemStatus;
import com.wangqin.globalshop.channelapi.dal.ChannelListingItemSkuVo;
import com.wangqin.globalshop.channelapi.dal.ChannelListingItemVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelListingItemDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ChannelListingItemDOMapper;

import java.util.Date;

/**
 *
 * ChannelListingItem 表数据服务层接口实现类
 *
 */
@Service("channelListingItemService")
public class ChannelListingItemServiceImpl implements IChannelListingItemService {

	@Autowired
	ChannelListingItemDOMapperExt channelListingItemDOMapperExt;

	@Autowired
	private ChannelListingItemSkuDOMapperExt skuDOMapperExt;

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

	@Override
	public void dealChannelListtingItem(ChannelListingItemVo channelListingItemVo){
		ChannelListingItemDO channelListingItemSo = new ChannelListingItemDO();
		channelListingItemSo.setCompanyNo(channelListingItemVo.getCompanyNo());
		channelListingItemSo.setShopCode(channelListingItemVo.getShopCode());
		channelListingItemSo.setChannelItemCode(channelListingItemVo.getChannelItemCode());

		ChannelListingItemDO oldTbItem = this.channelListingItemDOMapperExt.queryPo(channelListingItemSo);

		if(oldTbItem == null){
			oldTbItem = new ChannelListingItemDO();
			BeanUtils.copyProperties(channelListingItemVo,oldTbItem);
			oldTbItem.setStatus(ItemStatus.LISTING.getCode());
			//补充必填信息
			oldTbItem.setIsDel(false);
			oldTbItem.setGmtCreate(new Date());
			oldTbItem.setGmtModify(new Date());
			oldTbItem.setCreator("-1");
			oldTbItem.setModifier("-1");

			channelListingItemDOMapperExt.insert(oldTbItem);
		}else{
			oldTbItem.setIsDel(false);
			oldTbItem.setGmtModify(new Date());
			oldTbItem.setModifier("-1");
			channelListingItemDOMapperExt.updateByPrimaryKeySelective(oldTbItem);
		}

		for(ChannelListingItemSkuVo channelListingItemSkuVo : channelListingItemVo.getChannelListingItemSkuVos()){
			ChannelListingItemSkuDO channelListingItemSkuSo = new ChannelListingItemSkuDO();
			channelListingItemSkuSo.setCompanyNo(channelListingItemVo.getCompanyNo());
			channelListingItemSkuSo.setChannelItemSkuCode(channelListingItemSkuVo.getChannelItemSkuCode());
			channelListingItemSkuSo.setChannelItemCode(channelListingItemVo.getChannelItemCode());

			ChannelListingItemSkuDO oldSku = skuDOMapperExt.queryPo(channelListingItemSkuSo);

			if(oldSku == null){
				BeanUtils.copyProperties(channelListingItemSkuVo,oldSku);
				//补充必填信息
				oldSku.setIsDel(false);
				oldSku.setGmtCreate(new Date());
				oldSku.setGmtModify(new Date());
				oldSku.setCreator("-1");
				oldSku.setModifier("-1");
				skuDOMapperExt.insert(oldSku);
			}else {
				oldSku.setIsDel(false);
				oldSku.setGmtModify(new Date());
				oldSku.setModifier("-1");
				skuDOMapperExt.updateByPrimaryKeySelective(oldSku);
			}
		}
	}


}
