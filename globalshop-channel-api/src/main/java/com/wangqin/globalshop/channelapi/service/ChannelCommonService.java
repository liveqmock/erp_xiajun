package com.wangqin.globalshop.channelapi.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingOrderDO;

import java.util.List;




/**
 * Create by 777 on 2018/6/6
 * 所有的信息都应该遵从一个原则，数据全部查出来json打包，然后指定要和哪个店铺交流
 * 参数：处理的数据，处理的店铺
 * 现在因为耦合在一起了，所以传递的还只是一个id
 */
public interface ChannelCommonService {

	// 同步商品
	public void createItem(String companyNo, Long itemId);

	/**
	 * 发货，支持全部渠道
	 * @param erpOrderList
	 * @param shippingOrder
	 */
	void syncLogistics2Channel(List<MallSubOrderDO> erpOrderList, ShippingOrderDO shippingOrder);



	//public void syncItem(Long itemId);

	//public void syncItem(HttpServletRequest request, HttpServletResponse respose) throws exception;

	// 上架
	//public void syncListingItem(Long itemId);

	//public void syncListingItem(ItemVo item);

	// 下架
	//public void syncDelistingItem(Long itemId);

	//public void syncDelistingItem(ItemVo item);
	// 下架售罄商品
	//public void syncDelistingSaleOutItems();

	// 同步库存
	//public void syncSkuInventory(String itemCode, String skuCode);

	// 确认发货
	//public void syncLogisticsOnlineConfirm(List<MallOrderDO> order, ShippingOrder shippingOrder);

	// 同步订单
	//public Object syncOrder(Object data);

	//public void syncOrder() throws ParseException;

	//public void syncOrder(HttpServletRequest request, HttpServletResponse respose) throws exception;
}
