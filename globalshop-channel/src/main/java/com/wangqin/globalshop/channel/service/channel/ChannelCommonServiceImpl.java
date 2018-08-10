package com.wangqin.globalshop.channel.service.channel;

import com.wangqin.globalshop.biz1.app.dal.dataObject.*;
import com.wangqin.globalshop.biz1.app.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ChannelAccountSo;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.service.channelAccount.IChannelAccountService;
import com.wangqin.globalshop.channel.service.item.IItemService;
import com.wangqin.globalshop.channel.service.jingdong.JdShopOauthService;
import com.wangqin.globalshop.channelapi.dal.ItemVo;
import com.wangqin.globalshop.channelapi.service.ChannelCommonService;
import com.wangqin.globalshop.common.utils.DateUtil;
import com.wangqin.globalshop.common.utils.EasyUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Create by 777 on 2018/6/7
 */
@Service
public class ChannelCommonServiceImpl implements ChannelCommonService {

	protected Logger logger = LogManager.getLogger(getClass());

	@Autowired
	private IChannelAccountService channelAccountService;

	@Autowired
	private JdShopOauthService shopOauthService;

	@Autowired
	private  IItemService itemService;

	/**
	 * 上新接口
	 * @param itemId
	 */
	@Override
    public void createItem(String shopCode, Long itemId){
		if(EasyUtil.isStringEmpty(shopCode) || itemId == null || itemId < 0){
			throw  new ErpCommonException("shopCode或itemId为空，"+shopCode+";"+itemId);
		}

		JdShopOauthDO shopOauthSo = new JdShopOauthDO();
		shopOauthSo.setShopCode(shopCode);
		//shopOauthSo.setChannelNo(ChannelType.YouZan.getValue()+"");
		List<JdShopOauthDO> shopOauthList = shopOauthService.searchShopOauthList(shopOauthSo);

		if(EasyUtil.isListEmpty(shopOauthList)){
			throw  new ErpCommonException("channel_shop,shopCode: "+shopCode+" 无有赞店铺存在");
		}
		for (JdShopOauthDO shopOauth : shopOauthList) {
			try {
				ChannelFactory.getChannel(shopOauth).createItem(itemId);
			}catch (Exception e){
				logger.error("",e);
			}
		}
	}





	/**
	 * 发货：同步运单号至各个平台
	 *
	 * 一次只发一个订单，但可能是两个不同平台的子订单
	 *
	 * @param mallSubOrderList
	 * @param shippingOrder
	 */
	@Override
    public void syncLogistics2Channel(List<MallSubOrderDO> mallSubOrderList, ShippingOrderDO shippingOrder){
		if(EasyUtil.isListEmpty(mallSubOrderList)){
             throw new ErpCommonException("mallSubOrderList为空");
		}
		//按照订单分配
		Map<String,List<MallSubOrderDO>> shopCodeSubOrderListMap = new HashMap<>();
		for (MallSubOrderDO mallSubOrderDO : mallSubOrderList) {
			if(EasyUtil.isStringEmpty(mallSubOrderDO.getShopCode())){
				throw new ErpCommonException("子订单号，sub_Order_no: "+mallSubOrderDO.getSubOrderNo()+" shopCode不存在");
			}
			if(shopCodeSubOrderListMap.get(mallSubOrderDO.getShopCode()) == null){
				List<MallSubOrderDO> mallSubOrderDOS = new ArrayList<>();
				mallSubOrderDOS.add(mallSubOrderDO);
				shopCodeSubOrderListMap.put(mallSubOrderDO.getShopCode(),mallSubOrderDOS);
			}else {
				shopCodeSubOrderListMap.get(mallSubOrderDO.getShopCode()).add(mallSubOrderDO);
			}
		}


		List<String> mallSubOrderErrorList = new ArrayList<>();

		JdShopOauthDO shopOauthSo = new JdShopOauthDO();
		shopOauthSo.setIsDel(false);
		for(String shopCode : shopCodeSubOrderListMap.keySet()){
			shopOauthSo.setShopCode(shopCode);
			JdShopOauthDO shopOauth = shopOauthService.searchShopOauth(shopOauthSo);
			if(shopOauth == null){
				//addErrorSubOrderNo(shopCodeSubOrderListMap.get(shopCode),mallSubOrderErrorList);
				throw  new ErpCommonException("channel_account,shopCode: "+shopCode+" 不存在");
			}
			try {
				ChannelFactory.getChannel(shopOauth).syncLogisticsOnlineConfirm(shopCodeSubOrderListMap.get(shopCode),shippingOrder);
			}catch (Exception e){
				logger.error("",e);
				//addErrorSubOrderNo(shopCodeSubOrderListMap.get(shopCode),mallSubOrderErrorList);
			}
		}

	}


	private void addErrorSubOrderNo(List<MallSubOrderDO> mallSubOrderList, List<String> mallSubOrderErrorList){
		for(MallSubOrderDO subOrderDO : mallSubOrderList){
			mallSubOrderErrorList.add(subOrderDO.getSubOrderNo());
		}
	}



	/**
	 * 商品上新接口
	 * @param shopCode 店铺唯一性编码
	 * @param itemCode 商品唯一性编码
	 */
	public void addItem(String shopCode, String itemCode){
		if(EasyUtil.isStringEmpty(shopCode) || EasyUtil.isStringEmpty(itemCode)){
			throw  new ErpCommonException("companyNo或itemId为空，"+shopCode+";"+itemCode);
		}

		JdShopOauthDO shopOauthSo = new JdShopOauthDO();
		shopOauthSo.setShopCode(shopCode);
		shopOauthSo.setIsDel(false);
		JdShopOauthDO shopOauth = shopOauthService.searchShopOauth(shopOauthSo);

		if(shopOauth == null){
			throw new ErpCommonException("shop_error","未找到对应店铺信息shopCode:"+shopCode);
		}
		//0正常，1关闭
		if (!shopOauth.getOpen()) {
			throw new ErpCommonException("shop_error","当前店铺已停用，请重新启用shopCode:"+shopCode);
		}

		ItemDO itemDO = itemService.getByItemCode(itemCode);

		if(itemDO == null){
			throw new ErpCommonException("shop_error","商品未找到 itemCode:"+itemCode);
		}

		try {
			ChannelFactory.getChannel(shopOauth).createItem(itemDO.getId());
		}catch (Exception e){
			logger.error("",e);

		}
	}


	// 上架
	public void syncListingItem(String shopCode, Long itemId){
		JdShopOauthDO shopOauthSo = new JdShopOauthDO();
		shopOauthSo.setShopCode(shopCode);
		shopOauthSo.setIsDel(false);
		JdShopOauthDO shopOauth = shopOauthService.searchShopOauth(shopOauthSo);

		if(shopOauth == null){
			throw new ErpCommonException("shop_error","未找到对应店铺信息shopCode:"+shopCode);
		}
		//0正常，1关闭
		if (!shopOauth.getOpen()) {
			throw new ErpCommonException("shop_error","当前店铺已停用，请重新启用shopCode:"+shopCode);
		}

		try {
			ChannelFactory.getChannel(shopOauth).syncListingItem(itemId);
		}catch (Exception e){
			logger.error("",e);

		}

	}

	// 下架
	public void syncDelistingItem(String shopCode, Long itemId){
		JdShopOauthDO shopOauthSo = new JdShopOauthDO();
		shopOauthSo.setShopCode(shopCode);
		shopOauthSo.setIsDel(false);
		JdShopOauthDO shopOauth = shopOauthService.searchShopOauth(shopOauthSo);

		if(shopOauth == null){
			throw new ErpCommonException("shop_error","未找到对应店铺信息shopCode:"+shopCode);
		}
		//0正常，1关闭
		if (!shopOauth.getOpen()) {
			throw new ErpCommonException("shop_error","当前店铺已停用，请重新启用shopCode:"+shopCode);
		}

		try {
			ChannelFactory.getChannel(shopOauth).syncDelistingItem(itemId);
		}catch (Exception e){
			logger.error("",e);

		}
	}


	@Override
	public void getOrders(String shopCode, Date startTime, Date endTime){
		JdShopOauthDO shopOauthSo = new JdShopOauthDO();
		shopOauthSo.setShopCode(shopCode);
		shopOauthSo.setIsDel(false);
		JdShopOauthDO shopOauth = shopOauthService.searchShopOauth(shopOauthSo);

		if(shopOauth == null){
			throw new ErpCommonException("shop_error","未找到对应店铺信息shopCode:"+shopCode);
		}
		//0正常，1关闭
		if (!shopOauth.getOpen()) {
			throw new ErpCommonException("shop_error","当前店铺已停用，请重新启用shopCode:"+shopCode);
		}

		try {
			ChannelFactory.getChannel(shopOauth).getOrders(startTime, endTime);
		}catch (Exception e){
			logger.error("",e);

		}
	}
}
