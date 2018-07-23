package com.wangqin.globalshop.order.app.service.shipping.kuaidi100;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingOrderDO;
import com.wangqin.globalshop.order.app.kuaidi_bean.NoticeResponse;

public interface IKuaidi100Service {
	/**
	 * 订阅快递100
	 * @param shippingNo erp打包号
	 */
	public void subscribe(String shippingNo);
	
	/**
	 * 订阅快递100
	 * @param shippingOrder 包裹订单
	 */
	public void subscribe(ShippingOrderDO shippingOrder);

	/**
	 * 查询物流
	 * @param logisticsNo 物流号
	 */
	public void fetchTrack(String logisticsNo);
	
	/**
	 * 查询物流
	 * @param shippingNo erp打包号
	 */
	public void fetchTrackByShippingNo(String shippingNo);
	
	/**
	 * 查询物流
	 * @param shippingOrder
	 */
	public void fetchTrackByShippingOrder(ShippingOrderDO shippingOrder);
	
	/**
	 * 接收快递100回调
	 * @param json 内容
	 */
	public NoticeResponse handleCallback(String json);
}
