package com.wangqin.globalshop.channel.service.jingdong;

import com.alibaba.fastjson.JSONObject;
import com.wangqin.globalshop.biz1.app.constants.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.*;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channelapi.dal.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.List;

/**
 * 实现JdShopService默认接口功能，继承的接口不重写，则不支持此功能
 *
 * Create by 777 on 2018/6/12
 */
public class JdAbstractShopService implements JdShopService {

	// logger
	protected Logger logger = LogManager.getLogger(getClass());

	// 平台账号信息
	protected JdShopOauthDO shopOauth;

	public JdAbstractShopService(JdShopOauthDO shopOauth) {
		this.shopOauth = shopOauth;
	}

	public Object createItem(ItemVo itemVo){
		throw new ErpCommonException("渠道：【"+ChannelType.getChannelType(Integer.valueOf(shopOauth.getChannelNo())).getName()+"】不支持商品上新功能");
	}


	public void updateItem(ItemVo itemVo, ChannelListingItemVo channelListingItemVo){
		throw new ErpCommonException("渠道：【"+ChannelType.getChannelType(Integer.valueOf(shopOauth.getChannelNo())).getName()+"】不支持商品更新功能");
	}


	public void listingItem(ChannelListingItemVo channelListingItemVo){
		throw new ErpCommonException("渠道：【"+ChannelType.getChannelType(Integer.valueOf(shopOauth.getChannelNo())).getName()+"】不支持商品上架功能");
	}


	public void delistingItem(ChannelListingItemVo channelListingItemVo){
		throw new ErpCommonException("渠道：【"+ChannelType.getChannelType(Integer.valueOf(shopOauth.getChannelNo())).getName()+"】不支持商品下架功能");
	}


	public void modifySalePrice(ChannelSalePriceVo channelSalePriceVo){
		throw new ErpCommonException("渠道：【"+ChannelType.getChannelType(Integer.valueOf(shopOauth.getChannelNo())).getName()+"】不支持商品修改价格功能");
	}


	public List<JdItemDO> getItems(Date startTime, Date endTime){
		throw new ErpCommonException("渠道：【"+ChannelType.getChannelType(Integer.valueOf(shopOauth.getChannelNo())).getName()+"】不支持同步商品功能");
	}


	public JdItemDO getItemByItemCode(String itemCode){
		throw new ErpCommonException("渠道：【"+ChannelType.getChannelType(Integer.valueOf(shopOauth.getChannelNo())).getName()+"】不支持单个商品抓取功能");
	}


	public JSONObject convertItemGlobal2Jd(ItemVo itemVo){
		throw new ErpCommonException("渠道：【"+ChannelType.getChannelType(Integer.valueOf(shopOauth.getChannelNo())).getName()+"】不支持商品转换成JD模型功能");
	}


	public GlobalShopItemVo convertItemJd2Global(JSONObject jsonData){
		throw new ErpCommonException("渠道：【"+ChannelType.getChannelType(Integer.valueOf(shopOauth.getChannelNo())).getName()+"】不支持商品转换成内部模型功能");
	}


	public List<JdOrderDO> getOrders(String startTime, String endTime){
		throw new ErpCommonException("渠道：【"+ChannelType.getChannelType(Integer.valueOf(shopOauth.getChannelNo())).getName()+"】不支持批量抓取订单功能");
	}

	/**
	 * 按照订单抓取订单详情
	 * @param tid
	 * @return
	 */
	public JdOrderDO getOrderByTid(String tid){
		throw new ErpCommonException("渠道：【"+ChannelType.getChannelType(Integer.valueOf(shopOauth.getChannelNo())).getName()+"】不支持按单号抓取订单功能");
	}

	/**
	 * 数据模型转换
	 * @param orderJson
	 * @return
	 */
	public JDMallOrderVo convertJdOrder2Globalshop(JSONObject orderJson){
		throw new ErpCommonException("渠道：【"+ChannelType.getChannelType(Integer.valueOf(shopOauth.getChannelNo())).getName()+"】不支持订单转换内部结构功能");
	}

	/**
	 * 发货
	 */
	public void logisticComfire(JdLogisticsDO jdLogisticsDO){
		throw new ErpCommonException("渠道：【"+ChannelType.getChannelType(Integer.valueOf(shopOauth.getChannelNo())).getName()+"】不支持确认发货功能");
	}


}
