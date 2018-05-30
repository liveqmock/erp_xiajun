package com.wangqin.globalshop.channel.dal.mapperExt;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.InventoryDOMapper;

/**
 * Inventory 数据控制层
 * 
 * @author liuhui
 *
 */
public interface InventoryMapperExt extends InventoryDOMapper{

	/**
	 * 根据skuId
	 * 
	 * @param itemId
	 * @param skuId
	 * @return
	 */
	InventoryDO getInventoryBySkuId(@Param("itemId") Long itemId, @Param("skuId") Long skuId);

	/**
	 * 根据skuId 加悲观锁
	 *
	 * @param itemId
	 * @param skuId
	 * @return
	 */
	//Inventory getInventoryBySkuIdLock(@Param("itemId") Long itemId,@Param("skuId")  Long skuId);

	int updateLockInventory(@Param("id") Long id, @Param("booked") int booked, @Param("transBooked") int transBooked);

	void updateInventoryByInventoryArea();

	/**
	 *
	 * @param id 主键
	 * @param toTrans 在途入仓的数量
	 * @param lockedTransInv 入仓时的在途锁定数量，用来防止并发数据过期
	 * @return
	 */
	int updateTransToInventory(@Param("itemId") Long itemId, @Param("skuId") Long skuId, @Param("toTrans") int toTrans,
			@Param("lockedTransToInv") int lockedTransToInv, @Param("lockedTransInv") int lockedTransInv);

	/**
	 * 删除sku时，根据删除库存记录
	 */
	void deleteInventoryBySkuId(@Param("skuId") Long skuId);

	/**
	 * 减库存盘点
	 *
	 * @param id
	 * @param inventory
	 * @param toCheck
	 * @return
	 */
	int updateInventoryCheck(@Param("id") Long id, @Param("inventory") int inventory, @Param("toCheck") int toCheck);

	/**
	 * 减在途库存盘点
	 *
	 * @param id
	 * @param transInv
	 * @param toCheck
	 * @return
	 */
	int updateInventoryTransCheck(@Param("id") Long id, @Param("transInv") int transInv, @Param("toCheck") int toCheck);
	
	void updateUpcForInventory(MallOrderDO erpOrder);
	
	void updateVirtualInvByItemId(Long itemId);
	
	void insertBatch(List<InventoryDO> inv);
	
	InventoryDO queryInventoryBySkuCode(String skuCode);
}
