package com.wangqin.globalshop.channel.dal.mapperExt;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ItemSkuDOMapper;
import com.wangqin.globalshop.biz1.app.vo.ItemSkuQueryVO;

/**
 * SKU 数据控制层
 * @author zhulu
 *
 */
public interface ItemSkuMapperExt extends ItemSkuDOMapper{

	Integer queryItemSkusCount(ItemSkuQueryVO itemSkuQueryVO);
	
	List<ItemSkuDO> queryItemSkus(ItemSkuQueryVO itemSkuQueryVO);
	
	List<ItemSkuDO> queryItemSkusById(@Param(value = "itemId") Long itemId, @Param(value = "skuId") Long skuId);

	Integer queryItemSkusCountInOrder(@Param(value = "skuId") Long skuId);

	Integer queryItemSkusCountInTask(@Param(value = "skuId") Long skuId);

	Integer queryItemSkusCountInPurchase(@Param(value = "skuId") Long skuId);

	Integer queryItemSkusCountInInventoryArea(@Param(value = "skuId") Long skuId);

	Integer queryMaxSkuCodeIndex(@Param(value = "itemId") Long itemId);
	
	List<ItemSkuDO> queryItemSkusForExcel();
	
	List<ItemSkuDO> queryItemSkusForItemThirdSale(Long itemId);
	
	/**
	 * 根据upc获取商品信息(item_sku+inventory+virtualInv)
	 * @param sku
	 * @return
	 */
	List<ItemSkuDO> queryItemSkusByUpc(String upc);
}
