package com.wangqin.globalshop.order.app.service.shipping;


import com.wangqin.globalshop.biz1.app.dal.dataObject.LogisticCompanyDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingOrderDO;
import com.wangqin.globalshop.biz1.app.dto.MultiDeliveryFormDTO;
import com.wangqin.globalshop.biz1.app.vo.ShippingOrderVO;
import com.wangqin.globalshop.common.exception.InventoryException;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IShippingOrderService {
	

//	Set<Long> multiDelivery(ShippingOrderDO shippingOrder) ;

	List<ShippingOrderDO> queryShippingOrders(ShippingOrderVO shippingOrderVO);

    MultiDeliveryFormDTO queryByOrderId(String erpOrderId);

    Set<String> multiDelivery(ShippingOrderDO shippingOrderDO) throws Exception;

    List<MallSubOrderDO> queryShippingOrderDetail(String mallOrders);

    void updateOuterstatus(Set<String> mainIds);

    Map<String,Set<String>> batchDelivery(ShippingOrderDO shippingOrder) throws InventoryException;

    void update(ShippingOrderDO shippingOrder);

    List<LogisticCompanyDO> queryLogisticCompany();

    List<ShippingOrderDO> selectBatchIds(List<Long> shippingOrderIdList);

    List<ShippingOrderDO> queryByShippingOrderPackageTime(ShippingOrderVO shippingOrderQueryVO);

    ShippingOrderDO selectByShippingNO(String str);

    int selectCount(String idCard, String logisticCompany);

    ShippingOrderDO selectById(Long shippingOrderId);

    ShippingOrderDO selectOne(ShippingOrderDO order);

    void updateStatusByShippingNo(String logisticNo);
//    -------------------------------------------------

    void ship(ShippingOrderDO erpOrderId);

    List<ShippingOrderDO> selectByLogisticNoIsNotNull();

    List<ShippingOrderDO> selectInOneMonth();


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
	List<ShippingOrderDO> queryAllInHaihu();
//
//	List<ShippingOrder> queryAllFourPx();
//
//	List<ShippingOrder> queryNeedSyncSendPackage();
//
//	List<ShippingOrder> queryAllFad();
//
//	List<ShippingOrder> queryByShippingOrderPackageTime(ShippingOrderQueryVO shippingOrderQueryVO);
}
