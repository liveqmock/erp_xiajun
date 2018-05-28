package com.wangqin.globalshop.item.app.service;

import java.util.List;

import com.baomidou.framework.service.ISuperService;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;


public interface IMallOrderService {

	/**
	 * 外部订单到入内部订单
	 * 
	 * @param order
	 */
	//void importOuterOrder(OuterOrder order);

	//JsonPageResult<List<ErpOrder>> queryErpOrders(ErpOrderQueryVO erpOrderQueryVO);

	//void reviewOuterOrder(Long orderId);
	
	/**
	 * 状态：审核通过
	 * 备货状态：未备货 or 部分备货
	 *  
	 * @return
	 */
	//List<ErpOrder> queryUnStockUpErpOrders(Long skuId);
	
	/**
	 * 关闭子订单
	 * @return
	 */
	//void  closeErpOrder(ErpOrder erpOrder) throws InventoryException;

    //List<ErpOrder> queryTransStockUpErpOrders(Long skuId);

    //BizResult lockErpOrder(ErpOrder erpOrder) throws InventoryException;
	
	/**
	 * 拆分订单
	 * @param erpOrder
	 * @param splitCount
	 * @throws InventoryException
	 */
    //void splitErpOrder(ErpOrder erpOrder, int splitCount) throws InventoryException;

	//void lockErpOrderBySkuId(Long skuId) throws InventoryException;

	//List<ErpOrder> queryErpOrderForExcel(ErpOrderQueryVO erpOrderQueryVO);

	void updateUpcForOrder(MallOrderDO erpOrder);

	void updateWeightForOrder(MallOrderDO erpOrder);
	
	//List<ErpOrder> queryHaihuShippingNO(String targetNo,String skuCode);

	//ErpOrder queryHaihuErpOrders(OuterOrderDetail outerOrderDetail);

    //void splithaihuErpOrder(String outerOrderDetailList, String tragetNO) throws InventoryException;
	
    //List<ErpOrder> todaySendOrders(List<Integer> statusList);
}
