package com.wangqin.globalshop.channel.service.channel;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelShopDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ChannelShopDOMapperExt;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.EasyUtil;
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
			channelShopDO.init4NoLogin();
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

	/**
	 * 店铺全局唯一性：除非在其他商户下，已经停用
	 * @param channelShop
	 */
	@Override
	public void checkUnique(ChannelShopDO channelShop){
		ChannelShopDO so = new ChannelShopDO();
		so.setChannelNo(channelShop.getChannelNo());
		so.setShopCode(channelShop.getShopCode());
		so.setOpen(true);
		so.setIsDel(false);
		List<ChannelShopDO> shopList = channelShopDOMapperExt.searchShopList(so);

		Boolean unique = EasyUtil.isListEmpty(shopList)
				|| ( shopList.size() == 1 && shopList.get(0).getCompanyNo().equalsIgnoreCase(channelShop.getCompanyNo()) );
		if(!unique){
			throw  new ErpCommonException("shopCode error","当前店铺[ "+channelShop.getShopCode()+" ]已存在其他账户，请先停用.");
		}
	}


}
