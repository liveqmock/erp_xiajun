package com.wangqin.globalshop.channel.service.inventory;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.channel.Exception.InventoryException;

/**
 *
 * Inventory 表数据服务层接口
 *
 */
public interface IInventoryService  {

	public int deleteByPrimaryKey(Long id);

	public int insert(InventoryDO record);

	public int insertSelective(InventoryDO record);

	public InventoryDO selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(InventoryDO record);

	public int updateByPrimaryKey(InventoryDO record);

	public InventoryDO queryInventoryByCode(String itemCode, String skuCode);


	/**
	 * 未备货的子订单，选定唯一仓库
	 * @param erpOrders
	 * @return
	 * @throws InventoryException
	 */
	List<InventoryServiceImpl.WarehouseCollector> selectWarehousesByErpOrders(List<MallSubOrderDO> erpOrders)throws InventoryException ;


	/**
	 * 单商品库存,根据提前选择的仓库
	 * zhulu
	 * @throws InventoryException
	 *
	 */
	void lockedInventroy(InventoryServiceImpl.WarehouseCollector warehouseCollector) throws InventoryException;





}
