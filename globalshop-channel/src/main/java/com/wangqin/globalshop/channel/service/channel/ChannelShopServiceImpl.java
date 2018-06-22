package com.wangqin.globalshop.channel.service.channel;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelShopDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ChannelShopDOMapperExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Create by 777 on 2018/6/11
 */
@Service
public class ChannelShopServiceImpl implements ChannelShopService {

	@Autowired
	private ChannelShopDOMapperExt channelShopDOMapperExt;


	@Override
    public Long searchShopCount(ChannelShopDO channelShopDO){
		return channelShopDOMapperExt.searchShopCount(channelShopDO);
	}

	@Override
    public ChannelShopDO searchShop(ChannelShopDO channelShopDO){
		return channelShopDOMapperExt.searchShop(channelShopDO);
	}

	@Override
    public List<ChannelShopDO> searchShopList(ChannelShopDO channelShopDO){
		return channelShopDOMapperExt.searchShopList(channelShopDO);
	}


	@Override
    public void createOrUpdate(ChannelShopDO channelShopDO){
		ChannelShopDO so = new ChannelShopDO();
		so.setChannelNo(channelShopDO.getChannelNo());
		so.setCompanyNo(channelShopDO.getCompanyNo());
		so.setShopCode(channelShopDO.getShopCode());

		ChannelShopDO result = channelShopDOMapperExt.searchShop(so);
		if(result == null){
			channelShopDO.setGmtCreate(new Date());
			channelShopDO.setIsDel(false);
			channelShopDO.setVersion(0L);
			channelShopDO.setCreator("-1");
			channelShopDOMapperExt.insertSelective(channelShopDO);
		}else {
			result.setShopName(channelShopDO.getShopName());
			result.setExpiresTime(channelShopDO.getExpiresTime());
			result.setGmtModify(new Date());
			result.setModifier("-1");
			result.setOpen(true);
			channelShopDOMapperExt.updateByPrimaryKey(result);
		}
	}


}
