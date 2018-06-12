package com.wangqin.globalshop.channel.service.jingdong;

import com.alibaba.fastjson.JSONObject;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdItemDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdLogisticsDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdOrderDO;
import com.wangqin.globalshop.channelapi.dal.*;

import java.util.Date;
import java.util.List;

/**
 * 面向平台：定义一些基本的方法接口
 *
 * Create by 777 on 2018/6/12
 */
public interface JdShopService {


	//商品：上新add,更新update,上架，下架，修改价格，抓商品（按时间），抓商品（按ID），globalshop转jd,jd转globalshop
	//jd库存修改

	/**
	 * itemVo：上新的商品，及商品明细
	 * @param itemVo
	 */
	public Object createItem(ItemVo itemVo);

	/**
	 * 更改的商品，对应的外部商品
	 * @param itemVo
	 * @param channelListingItemVo
	 */
	public void updateItem(ItemVo itemVo, ChannelListingItemVo channelListingItemVo);

	/**
	 * 要上架的item，或者其中某个sku，没有sku，则全部上架，有则部分上架
	 * @param channelListingItemVo
	 */
	public void listingItem(ChannelListingItemVo channelListingItemVo);

	/**
	 * 要上架的sku加进来，默认全部下架
	 * @param channelListingItemVo
	 */
	public void delistingItem(ChannelListingItemVo channelListingItemVo);

	/**
	 * 一定要带channel的itemcode和itemskucode
	 * @param channelSalePriceVo
	 */
	public void modifySalePrice(ChannelSalePriceVo channelSalePriceVo);

	/**
	 * 根据时间抓商品
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<JdItemDO> getItems(Date startTime, Date endTime);

	/**
	 * 获取单个商品
	 * @param itemCode
	 * @return
	 */
	public JdItemDO getItemByItemCode(String itemCode);

	/**
	 * 将内部item转换成外部Item
	 * @param itemVo
	 */
	public JSONObject convertItemGlobal2Jd(ItemVo itemVo);

	/**
	 * json数据，转换为内部结构
	 * @param jsonData
	 * @return
	 */
	public GlobalShopItemVo convertItemJd2Global(JSONObject jsonData);


	//订单:抓订单（按时间），抓订单（按ID），数据模型转换：jd转globalshop,发货

	/**
	 * 按时间增量抓取订单
	 * @param startTime
	 * @param endTime
	 * @return
	 */
    public List<JdOrderDO> getOrders(String startTime, String endTime);

	/**
	 * 按照订单抓取订单详情
	 * @param tid
	 * @return
	 */
	public JdOrderDO getOrderByTid(String tid);

	/**
	 * 数据模型转换
	 * @param orderJson
	 * @return
	 */
	public JDMallOrderVo convertJdOrder2Globalshop(JSONObject orderJson);

	/**
	 * 发货
	 */
	public void logisticComfire(JdLogisticsDO jdLogisticsDO);


}
