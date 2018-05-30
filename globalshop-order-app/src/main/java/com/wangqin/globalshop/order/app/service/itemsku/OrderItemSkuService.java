package com.wangqin.globalshop.order.app.service.itemsku;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;

public interface OrderItemSkuService {
    ItemSkuDO selectBySkuCode(String skuCode);

//	/**
//	 * 按照条件分页查询商品
//	 * @param itemQueryVO
//	 * @return
//	 */
//	JsonPageResult<List<ItemSku>> queryItemSkus(ItemSkuQueryVO itemSkuQueryVO);
//
//	/**
//	 * 根据SKU初始化库存信息
//	 * @param itemSkuList
//	 * @return
//	 */
//	List<Inventory> initInventory(List<ItemSku> itemSkuList);
//
//	/**
//	 * 新增SKU
//	 * @param itemSku
//	 */
//	void addItemSku(ItemSku itemSku);
//
//	/**
//	 * update SKU
//	 * @param itemSku
//	 */
//	void updateItemSku(ItemSku itemSku);
//
//	/**
//	 * 新增SKU
//	 * @param itemSku
//	 */
////	ItemSku queryById(Long skuId);
//
//	/**
//	 * itemid 查询skulist
//	 * @param itemId
//	 * @return
//	 */
//	List<ItemSku> queryItemSkusByItemId(Long itemId);
//
//	/**
//	 * itemid 查询skulist
//	 * @param itemId
//	 * @return
//	 */
//	ItemSku queryItemSkusBySkuId(Long skuId);
//
//	/**
//	 * 判断sku是否可以删除
//	 * @param itemId
//	 * @return
//	 */
//	boolean isCanDeleteSku(Long skuId);
//
//	/**
//	 * 删除sku
//	 * @param skuId
//	 */
//	void deleteSkuById(Long skuId);
//
//	Integer queryMaxSkuCodeIndex(Long itemId);
//
//	List<ItemSku> queryItemSkusForExcel();
//
//	/**
//	 * 海狐可售商品查询
//	 * @return
//	 */
//	List<ItemSku> queryItemSkusForItemThirdSale(Long itemId);
//
//	/**
//	 * 根据upc获取商品信息(item_sku+inventory+virtualInv)
//	 * @param sku
//	 * @return
//	 */
//	List<ItemSku> queryItemSkusByUpc(String upc);
}
