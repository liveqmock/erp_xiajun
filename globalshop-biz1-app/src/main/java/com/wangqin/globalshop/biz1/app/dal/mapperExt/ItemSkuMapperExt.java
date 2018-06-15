package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ItemSkuDOMapper;
import com.wangqin.globalshop.biz1.app.dto.ISkuDTO;
import com.wangqin.globalshop.biz1.app.vo.ItemSkuAddVO;
import com.wangqin.globalshop.biz1.app.vo.ItemSkuQueryVO;



/**
 * SKU 数据控制层，item_module use
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
	
	List<ItemSkuDO> queryItemSkusForItemThirdSale(@Param(value="itemCode")String itemCode, @Param(value="shopCode")String shopCode);
	
	/**
	 * 根据upc获取商品信息(item_sku+inventory+virtualInv)
	 * @param sku
	 * @return
	 */
	List<ItemSkuDO> queryItemSkusByUpc(String upc);

	void insertBatch(List<ItemSkuAddVO> skuList);

	ISkuDTO queryItemSkuBySkuCode(String skuCode);

	void updateById(ItemSkuQueryVO itemSkuQueryVO);

    ItemSkuDO queryItemBySkuCode(@Param("skuCode") String skuCode);

    List<ItemSkuDO> queryItemSkuList(ItemSkuDO skuSo);

	ItemSkuDO queryItemSku(ItemSkuDO itemSkuDO);

	Integer queryItemSkuCount(ItemSkuDO itemSkuDO);
	
	//删除sku
	void deleteById(Long id);
	
	//查询可售的sku
	List<ItemSkuDO> querySaleableSkus();
	
	Integer queryItemCountByUpc(String upc);
	
	List<ItemSkuDO> querySkuListByItemCode(String itemCode);
	
	List<ItemSkuDO> queryItemSkuListSelective(ItemSkuQueryVO itemSkuQueryVO);
	
	void deleteItemSkuBySkuCode(String skuCode);
	
	void insertItemSkuSelective(ItemSkuDO itemSkuDO);
	
	String querySkuCodeById(Long id);

    List<ItemSkuDO> queryByItemCodeAndCompanyNo(@Param("itemCode") String itemCode, @Param("companyNo") String CompanyNo);
}
