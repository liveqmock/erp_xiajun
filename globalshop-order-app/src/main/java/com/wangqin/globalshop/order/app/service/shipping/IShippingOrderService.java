package com.wangqin.globalshop.order.app.service.shipping;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingOrderDO;
import com.wangqin.globalshop.biz1.app.dto.MultiDeliveryFormDTO;
import com.wangqin.globalshop.biz1.app.vo.JsonPageResult;
import com.wangqin.globalshop.biz1.app.vo.ShippingOrderVO;

import java.util.List;
import java.util.Set;

public interface IShippingOrderService {
	

//	Set<Long> multiDelivery(ShippingOrderDO shippingOrder) ;

	JsonPageResult<List<ShippingOrderDO>> queryShippingOrders(ShippingOrderVO shippingOrderVO);

    MultiDeliveryFormDTO queryByOrderId(String erpOrderId);

    Set<Long> multiDelivery(ShippingOrderDO shippingOrderDO);

    List<MallSubOrderDO> queryShippingOrderDetail(String mallOrders);

    void updateOuterstatus(Set<Long> mainIds);


//	MultiDeliveryFormDTO queryByErpOrderId(String erpOrderId);
//
//	void update(ShippingOrder shippingOrder);
//
//	List<LogisticCompany> queryLogisticCompany();
//
//	List<ErpOrder> queryShippingOrderDetail(String erpOrderId);
//
//	List<ErpOrder> queryByShippingOrderTime(ShippingOrderQueryVO shippingOrderQueryVO);
//
//	Map<String, Set<Long>> batchDelivery(ShippingOrder shippingOrder) throws InventoryException;
//
//	void updateOuterstatus(Set<Long> mainIds);
//
//	void updateStatusByShippingNo(String shippingNo);
//
//	List<ShippingOrder> queryAllInHaihu();
//
//	List<ShippingOrder> queryAllFourPx();
//
//	List<ShippingOrder> queryNeedSyncSendPackage();
//
//	List<ShippingOrder> queryAllFad();
//
//	List<ShippingOrder> queryByShippingOrderPackageTime(ShippingOrderQueryVO shippingOrderQueryVO);
}
