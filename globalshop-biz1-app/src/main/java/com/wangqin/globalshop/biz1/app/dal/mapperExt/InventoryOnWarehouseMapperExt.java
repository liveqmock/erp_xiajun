package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOnWareHouseDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.InventoryOnWareHouseDOMapper;
import com.wangqin.globalshop.biz1.app.vo.InventoryQueryVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * InventoryArea 数据控制层
 * 
 * @author liuhui
 *
 */
public interface InventoryOnWarehouseMapperExt extends InventoryOnWareHouseDOMapper  {

	/**
	 * 根据唯一约束查询InventoryArea
	 * 
	 * @param itemId
	 *            商品ID ，后续分库分表可使用
	 * @param skuId
	 * @param warehouseId
	 * @param positionNo
	 * @return
	 */
	InventoryOnWareHouseDO getInventoryArea(@Param("itemId") Long itemId, @Param("skuId") Long skuId,
			@Param("warehouseId") Long warehouseId, @Param("positionNo") String positionNo);

	/**
	 * 商品型号总库存
	 * 
	 * @param itemId
	 *            商品ID ，后续分库分表可使用
	 * @param skuId
	 *            唯一商品型号 类似skucode
	 * @return
	 */
	InventoryOnWareHouseDO sumInventoryBySkuId(@Param("itemId") Long itemId, @Param("skuId") Long skuId);

	/**
	 * 计算分仓库总库存量
	 * 
	 * @param itemId
	 *            商品ID ，后续分库分表可使用
	 * @param skuId
	 *            唯一商品型号 类似skucode
	 * @return
	 */
	List<InventoryOnWareHouseDO> sumInventoryBySkuIdGroupbyWarehouse(@Param("itemId") Long itemId, @Param("skuId") Long skuId,@Param("warehouseId") Long warehouseId);

	/**
	 * 某个仓库某个商品库存详情
	 * 
	 * @param warehouseId
	 * @param itemId
	 * @param skuId
	 * @return
	 */
	List<InventoryOnWareHouseDO> queryInventoryAreaByWarehouse(@Param("warehouseId") Long warehouseId,
			@Param("itemId") Long itemId, @Param("skuId") Long skuId);

	Integer queryInventoryAreaCount(InventoryQueryVO inventoryQueryVO);

	List<InventoryOnWareHouseDO> queryInventoryAreas(InventoryQueryVO inventoryQueryVO);
	
	/**
	 * 
	 * @param id 主键
	 * @param toTrans 在途入仓的数量
	 * @param lockedTransInv 入仓时的在途锁定数量，用来防止并发数据过期
	 * @return
	 */
	int updateTransToInventoryArea(@Param("id")Long id , @Param("toTrans")int toTrans, @Param("lockedTransToInv")int lockedTransToInv,  @Param("lockedTransInv")int lockedTransInv);

	/**
	 * 减库存盘点
	 * 
	 * @param id
	 * @param inventory
	 * @param toCheck
	 * @return
	 */
	int updateInventoryAreaCheck(@Param("id")Long id , @Param("inventory")int inventory, @Param("toCheck")int toCheck);
	
	/**
	 * 减在途盘点
	 * 
	 * @param id
	 * @param transInv
	 * @param toCheck
	 * @return
	 */
	int updateInventoryAreaTransCheck(@Param("id")Long id , @Param("transInv")int transInv, @Param("toCheck")int toCheck);
	
	/**
	 * 增加实际库存
	 * 
	 * @param id
	 * @param inventory
	 * @param toAdd
	 * @return
	 */
	int updateInventoryAreaAddInventory(@Param("id")Long id , @Param("inventory")int inventory, @Param("toAdd")int toAdd);
	
	void updateUpcForInventoryArea(MallOrderDO erpOrder);
	
	List<InventoryOnWareHouseDO> queryInventoryAreaForExcel(InventoryQueryVO inventoryQueryVO);

    InventoryOnWareHouseDO selectById(Long inventoryAreaId);

}
