package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ItemSkuDOMapper;
import com.wangqin.globalshop.biz1.app.dto.ISkuDTO;
import com.wangqin.globalshop.biz1.app.vo.ItemSkuAddVO;
import com.wangqin.globalshop.biz1.app.vo.ItemSkuQueryVO;
import com.wangqin.globalshop.biz1.app.vo.ItemSkuUpdateVO;


/**
 * SKU 数据控制层
 * @author zhulu
 *
 */
public interface ItemSkuMapperExt extends ItemSkuDOMapper{

	Integer queryItemSkusCount(ItemSkuQueryVO itemSkuQueryVO);
	
	List<ISkuDTO> queryItemSkus(ItemSkuQueryVO itemSkuQueryVO);
	
	List<ItemSkuDO> queryItemSkusById(@Param(value="itemId") Long itemId,@Param(value="skuId") Long skuId );
	
	Integer queryItemSkusCountInOrder(@Param(value="skuId") Long skuId);
	
	Integer queryItemSkusCountInTask(@Param(value="skuId") Long skuId);
	
	Integer queryItemSkusCountInPurchase(@Param(value="skuId") Long skuId);
	
	Integer queryItemSkusCountInInventoryArea(@Param(value="skuId") Long skuId);
	
	Integer queryMaxSkuCodeIndex(@Param(value="itemId") Long itemId);
	
	List<ItemSkuDO> queryItemSkusForExcel();
	
	List<ItemSkuDO> queryItemSkusForItemThirdSale(Long itemId);
	
	/**
	 * 根据upc获取商品信息(item_sku+inventory+virtualInv)
	 * @param sku
	 * @return
	 */
	List<ItemSkuDO> queryItemSkusByUpc(String upc);

	void insertBatch(List<ItemSkuAddVO> skuList);

	ISkuDTO queryItemSkuBySkuCode(String skuCode);

	void updateById(ItemSkuUpdateVO itemSkuUpdateVO);

	void deleteBySkuCode(String skuCode);

    ItemSkuDO queryItemBySkuCode(@Param("skuCode") String skuCode);
}
