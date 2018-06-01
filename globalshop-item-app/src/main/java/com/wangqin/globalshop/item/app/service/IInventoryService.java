package com.wangqin.globalshop.item.app.service;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.globalshop.biz1.app.vo.InventoryAddVO;


public interface IInventoryService {

	/**
	 * 单商品库存预定
	 * @throws InventoryException 
	 * 
	 */
	//int lockedInventroy(MallOrderDO erpOrder,WarehouseCollector warehouseCollector) throws InventoryException;
	
	
	/**
	 * 单商品库存,根据提前选择的仓库
	 * zhulu
	 * @throws InventoryException 
	 * 
	 */
	//void lockedInventroy(WarehouseCollector warehouseCollector) throws InventoryException;

	/**
	 * 根据订单锁定各个库存 记录锁定明细
	 * 
	 * @param order
	 * @throws InventoryException 
	 */
	/*int lockedInventoryOrder(Long outerOrderId) throws InventoryException;*/
	
	/**
	 * 根据skuId 查询商品库存
	 * 
	 * @param itemId
	 * @param skuId
	 * @return
	 */
	InventoryDO queryInventoryBySkuId(Long itemId,Long skuId);
	InventoryDO queryInventoryBySkuId(String itemId,String skuId) ;

	InventoryAddVO queryInvBySkuCode(String skuCode);

	void lockVirtualInv(InventoryAddVO inventory);

	//item_module
	void deleteInvBySkuCode(String skuCode);

	//int updateLockInventory(Long id , int booked, int transBooked);

	/**
	 * 库存锁定
	 * @param erpOrder
	 * @return
	 * @throws InventoryException
	 */
	//int lockedInventroyErpOrder(MallOrderDO erpOrder) throws InventoryException;
	
	/**
	 * 单商品发货
	 * @param orderDetail
	 */
	//void sendInventroyOrder(MallOrderDO order) throws InventoryException;
	/**
	 * 库存释放
	 * @param order
	 * @return
	 * @throws InventoryException
	 */
	//int releaseInventory(ErpOrder order) throws InventoryException;

	//void deleteInventoryBySkuId(Long skuId);

	/**
	 * 库存盘出
	 * @param inventoryAreaId
	 * @param quantity
	 * @throws InventoryException
	 */
	//void inventoryCheckOut(Long inventoryAreaId, int quantity) throws InventoryException;

	/**
	 * 在途入仓库，入到相同的货架
	 * 
	 * @param inventoryAreaId
	 * @param toTrans
	 * @throws InventoryException
	 */
	//void transToSameInventory(InventoryArea inventoryArea, int toTrans) throws InventoryException;
	
	/**
	 * 在途入仓
	 * 
	 * @param inventoryAreaId
	 * @param toTrans
	 * @param positionNo
	 */
	//void transToInventory(Long inventoryAreaId, int toTrans, String positionNo) throws InventoryException;

	/**
	 * 一个货架入仓到另外一个货架
	 * 
	 * @param inventoryAreaId
	 * @param toTrans
	 * @param positionNo
	 * @throws InventoryException
	 */
	//void transToOtherInventory(InventoryArea inventoryArea, int toTrans, String positionNo) throws InventoryException;
	
	/**
	 * 未备货的子订单，选定唯一仓库
	 * @param erpOrders
	 * @return
	 * @throws InventoryException
	 */
	 //List<WarehouseCollector> selectWarehousesByErpOrders(List<ErpOrder> erpOrders) throws InventoryException;
	 
	 
	 /**
	  * 已备货的子订单，初始化仓库信息
	  * @param erpOrder
	  * @return
	  * @throws InventoryException
	  */
	// WarehouseCollector selectWarehousesByErpOrder(ErpOrder erpOrder) throws InventoryException;
	 
	 
	void updateVirtualInvByItemCode(String itemCode);
	
	//void insertBatch(List<InventoryDO> invList);
	
	void insertBatchInventory(List<InventoryAddVO> inventoryList);

	InventoryDO queryInventoryBySkuCode(String skuCode);

}
