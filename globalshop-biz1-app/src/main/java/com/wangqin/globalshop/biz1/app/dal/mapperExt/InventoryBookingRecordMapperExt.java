package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryBookingRecordDO;

public interface InventoryBookingRecordMapperExt {

	/**
	 * 订单某个商品已预定量
	 * 
	 * @param itemId 商品ID ，后续分库分表可使用
	 * @param skuId 唯一商品型号  类似skucode
	 * @return
	 */
	Integer sumBookedBySkuId(@Param("orderId")Long orderId,@Param("erpOrderId")Long erpOrderId);
	
	/**
	 * @return
	 */
	List<InventoryBookingRecordDO> queryByErpOrderId(@Param("erpOrderId")Long erpOrderId);
	
	/**
	 * 订单某个商品已预定量分预定类型
	 * 
	 * @param itemId 商品ID ，后续分库分表可使用
	 * @param skuId 唯一商品型号  类似skucode
	 * @return
	 */
	List<InventoryBookingRecordDO> sumBookedByInventoryType(@Param("orderId")Long orderId,@Param("erpOrderId")Long erpOrderId);
	
	/**
	 * 可释放的库存的订单 用于盘点释放库存
	 * 
	 * @param stockStatus
	 * @param status
	 * @return
	 */
	//List<InventoryBookingRecordDO> sumInventoryCheckRecord(@Param("inventoryAreaId")Long inventoryAreaId,@Param("inventoryType")InventoryType inventoryType,@Param("status")List<Integer> status);
	
	void changePositionNo(@Param("inventoryAreaId")Long inventoryAreaId , @Param("positionNo")String positionNo);
}
