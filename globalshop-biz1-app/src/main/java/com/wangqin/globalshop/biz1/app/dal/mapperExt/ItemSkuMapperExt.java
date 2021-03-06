package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import java.util.List;

import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemSkuPriceVO;
import com.wangqin.globalshop.biz1.app.bean.dto.QueryItemSkuPriceListDTO;
import com.wangqin.globalshop.biz1.app.bean.dto.SkuChannelPriceDTO;
import org.apache.ibatis.annotations.Param;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ItemSkuDOMapper;
import com.wangqin.globalshop.biz1.app.bean.dto.ISkuDTO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemSkuAddVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemSkuQueryVO;



/**
 * SKU 数据控制层，item_sku表的所有mapper层请放在这里
 * @author xiajun
 *
 */
public interface ItemSkuMapperExt extends ItemSkuDOMapper{

	//渠道商品：根据sku_code更新sku
	void updateSkuBySkuCode(ItemSkuDO sku);
	
	//商品编辑：查找哪些sku被前端删除了
	List<String> queryToDeleteSkuCodeList(@Param("codeList")List<String> codeList,@Param("itemCode")String itemCode);
	
	//根据sku_code查询一条item_sku表里面的记录，不关联其他的表，不做分页，只查一条记录
	ItemSkuDO queryItemSkuDOBySkuCode(String skuCode);
	
	//查询和本sku同属一个商品的所有sku的sale_price
	List<Double> querySalePriceListBySkuCode(String skuCode);
	
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
	
	List<ItemSkuDO> querySkuListByItemCode(String itemCode);
	
	//根据itemCode查询sku列表,包含虚拟库存、颜色、尺寸这3个不在item_sku表里面的字段
    //商品编辑的时候使用
    //TODO颜色尺寸的查询 
	List<ItemSkuQueryVO> querySkuListByItemCodeContainsVirtualInvScale(String itemCode);
	
	List<ItemSkuDO> queryItemSkuListSelective(ItemSkuQueryVO itemSkuQueryVO);
	
	void deleteItemSkuBySkuCode(String skuCode);
	
	void insertItemSkuSelective(ItemSkuDO itemSkuDO);
	
	String querySkuCodeById(Long id);

    List<ItemSkuDO> queryByItemCodeAndCompanyNo(@Param("itemCode") String itemCode, @Param("companyNo") String CompanyNo);

    ItemSkuDO queryBySkuCodeOrUpcAndCompanyNo(@Param("code")String code, @Param("companyNo") String companyNo);
    
    Double querySalePriceByItemCode(String itemCode);

	void inserBatch(List<ItemSkuDO> skuList);
	
	//根据id查出该sku对应的商品在sku表里面映射了几个sku，如果只有一个，禁止删除这个sku
	Integer querySkuNumberBySkuId(Long skuId);
	
	//查询指定的upc对应的item_sku的sku_code,按公司划分,防止重复的upc
	List<String> querySkuCodeListByUpc(@Param("companyNo")String companyNo,@Param("upc")String upc);
	
	//询要更新的upc是否在别的商品名下已经存在,存在则表明出现了upc重复的问题
	Integer queryRecordCountByUpcCompanyNotInSameItem(@Param("companyNo")String companyNo,@Param("upc")String upc,@Param("itemCode")String itemCode);

    Integer queryCountByUpcAndCompanyNo(@Param("companyNo")String companyNo,@Param("upc") String upc);

    List<SkuChannelPriceDTO> queryItemSkuPrices(ItemSkuQueryVO itemSkuQueryVO);

    List<ItemSkuDO> queryItemSkuListOnly(ItemSkuQueryVO itemSkuQueryVO);

    List<ItemSkuPriceVO> queryAllSalableItemSkuList(QueryItemSkuPriceListDTO queryItemSkuPriceListDTO);

	List<ItemSkuPriceVO> querySalableItemSkuList(ItemSkuQueryVO itemSkuQueryVO);

	Integer querySalableItemSkuCount(ItemSkuQueryVO itemSkuQueryVO);


}
