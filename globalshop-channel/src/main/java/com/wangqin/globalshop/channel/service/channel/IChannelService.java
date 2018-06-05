package com.wangqin.globalshop.channel.service.channel;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingOrderDO;
import com.wangqin.globalshop.channel.dal.dataObjectVo.ItemVo;

public interface IChannelService {
	
	// 同步商品
	public void createItem(Long itemId);
	
	public void syncItem(Long itemId);
	public void syncItem(HttpServletRequest request, HttpServletResponse respose) throws Exception;
	
	// 上架
	public void syncListingItem(Long itemId);
	public void syncListingItem(ItemVo item);
	
	// 下架
	public void syncDelistingItem(Long itemId);
	public void syncDelistingItem(ItemVo item);
	// 下架售罄商品
	public void syncDelistingSaleOutItems();
	
	// 同步库存
	public void syncSkuInventory(String itemCode, String skuCode);
	
	// 确认发货
	//public void syncLogisticsOnlineConfirm(List<MallOrderDO> order, ShippingOrder shippingOrder);
	
	// 同步订单
	public Object syncOrder(Object data);
	
	public void syncOrder() throws ParseException;
	public void syncOrder(HttpServletRequest request, HttpServletResponse respose) throws Exception;

    void syncLogisticsOnlineConfirm(List<MallSubOrderDO> erpOrderList, ShippingOrderDO shippingOrder);
}
