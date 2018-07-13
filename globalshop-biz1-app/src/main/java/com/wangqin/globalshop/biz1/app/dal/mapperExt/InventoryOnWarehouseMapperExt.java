package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOnWareHouseDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.InventoryOnWarehouseVO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.InventoryQueryVO;
import com.wangqin.globalshop.biz1.app.dal.mapper.InventoryOnWareHouseDOMapper;


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
	InventoryOnWareHouseDO getInventoryArea(@Param("itemId") String itemId, @Param("skuId") String skuId,
			@Param("warehouseNo") String warehouseNo, @Param("positionNo") String positionNo);

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
	List<InventoryOnWareHouseDO> sumInventoryBySkuIdGroupbyWarehouse(@Param("itemCode") String itemCode, @Param("skuCode") String skuCode,@Param("warehouseNo") String warehouseNo);

	/**
	 * 某个仓库某个商品库存详情
	 * 
	 * @param warehouseId
	 * @param itemId
	 * @param skuId
	 * @return
	 */
	List<InventoryOnWareHouseDO> queryInventoryAreaByWarehouse(@Param("warehouseId") String warehouseId,
			@Param("itemId") String itemId, @Param("skuId") String skuId);

	Integer queryInventoryAreaCount(InventoryQueryVO inventoryQueryVO);


	/**
	 * 
	 * @param id 主键
	 * @param toTrans 在途入仓的数量
	 * @param lockedTransInv 入仓时的在途锁定数量，用来防止并发数据过期
	 * @return
	 */
	int updateTransToInventoryArea(@Param("id")Long id , @Param("toTrans")int toTrans, @Param("lockedTransToInv")Long lockedTransToInv,  @Param("lockedTransInv")Long lockedTransInv);

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
	int updateInventoryAreaTransCheck(@Param("id")Long id , @Param("transInv")Long transInv, @Param("toCheck")int toCheck);
	
	/**
	 * 增加实际库存
	 * 
	 * @param id
	 * @param inventory
	 * @param toAdd
	 * @return
	 */
	int updateInventoryAreaAddInventory(@Param("id")Long id , @Param("inventory")Long inventory, @Param("toAdd")int toAdd);
	
	void updateUpcForInventoryArea(MallOrderDO erpOrder);
	
	List<InventoryOnWareHouseDO> queryInventoryAreaForExcel(InventoryQueryVO inventoryQueryVO);

    InventoryOnWareHouseDO selectById(Long inventoryAreaId);
    /**new-------------------------------------------------------------------*/
	List<InventoryOnWarehouseVO> queryInventoryAreas(InventoryQueryVO inventoryQueryVO);
	InventoryOnWareHouseDO selectByCompanyNoAndSkuCodeAndWarehouseNo(@Param("companyNo")String companyNo, @Param("skuCode")String skuCode, @Param("warehouseNo")String warehouseNo);

	List<InventoryOnWareHouseDO> getINvOnWarehouseListOfShip(@Param("skuCode")String skuCode, @Param("companyNo")String companyNo);

//    List<InventoryOnWareHouseDO> selectBySkuCode(@Param("skuCode")String skuCode);
	InventoryOnWareHouseDO getByInventoryOnWarehouseNo(String inventoryOnWarehouseNo);

	List<InventoryOnWareHouseDO> selectByCompanyNoAndSkuCode(@Param("companyNo") String companyNo,@Param("skuCode") String skuCode);
	
	InventoryOnWareHouseDO selectByWarehouseNo(String warehouseNo);
	
	InventoryOnWareHouseDO selectByInventoryOnWarehouseNo(String inventoryOnWarehouseNo);
}
