package com.wangqin.globalshop.channel.service.channel;

import com.wangqin.globalshop.biz1.app.dal.dataObject.*;
import com.wangqin.globalshop.biz1.app.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ChannelAccountSo;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.service.YouzanService;
import com.wangqin.globalshop.channel.service.channelAccount.IChannelAccountService;
import com.wangqin.globalshop.channel.service.channelItem.IChannelListingItemService;
import com.wangqin.globalshop.channel.service.item.IItemService;
import com.wangqin.globalshop.channel.service.jingdong.JdShopOauthService;
import com.wangqin.globalshop.channelapi.dal.GlobalShopItemVo;
import com.wangqin.globalshop.channelapi.dal.ItemVo;
import com.wangqin.globalshop.channelapi.service.ChannelCommonService;
import com.wangqin.globalshop.common.utils.DateUtil;
import com.wangqin.globalshop.common.utils.EasyUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.*;

/**
 * Create by 777 on 2018/6/7
 */
@Service
public class ChannelCommonServiceImpl implements ChannelCommonService {

	protected Logger logger = LogManager.getLogger("ChannelCommonServiceImpl");

	@Autowired
	private JdShopOauthService shopOauthService;

	@Autowired
	private  IItemService itemService;

	@Autowired
	private IChannelListingItemService channelListingItemService;


	@Autowired
	private YouzanService youzanService;

	@Autowired
	private TransactionTemplate transactionTemplate;

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
	 * 一次只发一个主订单，可以多个明细，
	 *
	 * @param mallSubOrderList
	 * @param shippingOrder
	 */
	@Override
    public void syncLogistics2Channel(List<MallSubOrderDO> mallSubOrderList, ShippingOrderDO shippingOrder){
		if(EasyUtil.isListEmpty(mallSubOrderList)){
             throw new ErpCommonException("mallSubOrderList为空");
		}
		String shopCode = mallSubOrderList.get(0).getShopCode();
		JdShopOauthDO shopOauthSo = new JdShopOauthDO();
		shopOauthSo.setIsDel(false);
		shopOauthSo.setShopCode(shopCode);
		JdShopOauthDO shopOauth = shopOauthService.searchShopOauth(shopOauthSo);
		if (shopOauth == null) {
			throw new ErpCommonException("channel_account,shopCode: " + shopCode + " 不存在");
		}
		try {
			ChannelFactory.getChannel(shopOauth).syncLogisticsOnlineConfirm(mallSubOrderList, shippingOrder);
		} catch (Exception e) {
			logger.error("", e);
			throw new ErpCommonException("", e.getMessage());
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
            throw new ErpCommonException("",e.getMessage());
		}
	}


	@Override
	public void getItems(String shopCode, Date startTime, Date endTime){
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

			if(Integer.valueOf(ChannelType.YouZan.getValue()).equals(Integer.valueOf(shopOauth.getChannelNo()))){
				youzanService.getItems(shopOauth,startTime, endTime);
			}else{
				throw new ErpCommonException("","暂不支持该方法");
			}

			//ChannelFactory.getChannel(shopOauth).getItems(startTime, endTime);
		}catch (Exception e){
			logger.error("",e);
			throw new ErpCommonException("",e.getMessage());
		}
	}

	@Override
	public void getAllItems(String shopCode){
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

			if(Integer.valueOf(ChannelType.YouZan.getValue()).equals(Integer.valueOf(shopOauth.getChannelNo()))){
				youzanService.getAllItems(shopOauth);
			}else{
				throw new ErpCommonException("","暂不支持该方法");
			}

			//ChannelFactory.getChannel(shopOauth).getItems(startTime, endTime);
		}catch (Exception e){
			logger.error("",e);
			throw new ErpCommonException("",e.getMessage());
		}
	}

	@Override
	public void sendItem(String shopCode, JdItemDO jdItem){
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
			GlobalShopItemVo globalShopItemVo = null;
			if(Integer.valueOf(ChannelType.YouZan.getValue()).equals(Integer.valueOf(shopOauth.getChannelNo()))){
				 globalShopItemVo = youzanService.convertYZItem(shopOauth,jdItem);
			}else{
				throw new ErpCommonException("","暂不支持该方法");
			}
			GlobalShopItemVo finalGlobalShopItemVo = globalShopItemVo;
			transactionTemplate.execute(new TransactionCallback<Boolean>() {
				@Override
				public Boolean doInTransaction(TransactionStatus transactionStatus) {
					doSendItem(finalGlobalShopItemVo);
					return Boolean.TRUE;
				}
			});



			//ChannelFactory.getChannel(shopOauth).getItems(startTime, endTime);
		}catch (ErpCommonException e){
			logger.error("",e);
			throw e;
		}catch (Exception e){
			logger.error("",e);
			throw new ErpCommonException("",e.getMessage());
		}
	}


	/**
	 * 下发商品的逻辑
	 * @param globalShopItemVo
	 */
	private void doSendItem(GlobalShopItemVo globalShopItemVo){
		if(globalShopItemVo == null){
			return;
		}
		channelListingItemService.dealChannelListtingItem(globalShopItemVo.getChannelListingItemVo());
		itemService.addChannelItem(globalShopItemVo.getItemVo());
	}
}
