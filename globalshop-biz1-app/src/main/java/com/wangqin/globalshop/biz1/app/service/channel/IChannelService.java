package com.wangqin.globalshop.biz1.app.service.channel;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;

public interface IChannelService {
	
	// 同步商品
	public void createItem(Long itemId);
	
	public void syncItem(Long itemId);
	public void syncItem(HttpServletRequest request, HttpServletResponse respose) throws Exception;
	
	// 上架
	public void syncListingItem(Long itemId);
	public void syncListingItem(ItemDO item);
	
	// 下架
	public void syncDelistingItem(Long itemId);
	public void syncDelistingItem(ItemDO item);
	// 下架售罄商品
	public void syncDelistingSaleOutItems();
	
	// 同步库存
	public void syncSkuInventory(Long itemId, Long skuId);
	
	// 确认发货
	//public void syncLogisticsOnlineConfirm(List<MallOrderDO> order, ShippingOrder shippingOrder);
	
	// 同步订单
	public Object syncOrder(Object data);
	
	public void syncOrder() throws ParseException;
	public void syncOrder(HttpServletRequest request, HttpServletResponse respose) throws Exception;
		
}
