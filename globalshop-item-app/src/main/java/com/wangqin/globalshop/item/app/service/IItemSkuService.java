package com.wangqin.globalshop.item.app.service;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dto.ISkuDTO;
import com.wangqin.globalshop.biz1.app.vo.ItemSkuAddVO;
import com.wangqin.globalshop.biz1.app.vo.ItemSkuQueryVO;
import com.wangqin.globalshop.biz1.app.vo.JsonPageResult;



public interface IItemSkuService  {

	/**
	 * 按照条件分页查询商品(分页)
	 * @param itemQueryVO
	 * @return
	 */
	JsonPageResult<List<ISkuDTO>> queryItemSkus(ItemSkuQueryVO itemSkuQueryVO);
	
	
	
	/**
	 * 根据SKU初始化库存信息
	 * @param itemSkuList
	 * @return
	 */
	List<InventoryDO> initInventory(List<ItemSkuAddVO> itemSkuList);
	
	/**
	 * 新增SKU
	 * @param itemSku
	 */
	void addItemSku(ItemSkuDO itemSku);
	
	/**
	 * update SKU
	 * @param itemSku
	 */
	void updateItemSku(ItemSkuDO itemSku);
	
	/**
	 * 新增SKU
	 * @param itemSku
	 */
//	ItemSku queryById(Long skuId);
	
	/**
	 * itemid 查询skulist
	 * @param itemId
	 * @return
	 */
	List<ItemSkuDO> queryItemSkusByItemId(Long itemId );
	
	
	ItemSkuDO selectByPrimaryKey(Long id);
	
	/**
	 * 判断sku是否可以删除
	 * @param itemId
	 * @return
	 */
	boolean isCanDeleteSku(Long skuId);

	
	void deleteById(Long id);
	 
	 List<ItemSkuDO> queryItemSkusForExcel();
	 
	 List<ItemSkuDO> queryItemSkusByUpc(String upc);
	
	 void insertBatch(List<ItemSkuAddVO> skuList);
	 
	 ISkuDTO queryItemSkuBySkuCode(String skuCode);
	 
	 void updateById(ItemSkuQueryVO itemSkuUpdateVO);
	 
	 //查询可售的sku
	 List<ItemSkuDO> querySaleableSkus();
	 
	 Integer queryItemCountByUpc(String upc);
	 
	 List<ItemSkuDO> querySkuListByItemCode(String itemCode);
	 
	 List<ItemSkuDO> queryItemSkuListSelective(ItemSkuQueryVO itemSkuQueryVO);
	 
	 void deleteItemSkuBySkuCode(String skuCode);
	 
	 void insertItemSkuSelective(ItemSkuDO itemSkuDO);
}
