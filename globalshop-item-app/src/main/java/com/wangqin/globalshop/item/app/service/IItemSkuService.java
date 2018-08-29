package com.wangqin.globalshop.item.app.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangqin.globalshop.biz1.app.bean.dataVo.SkuChannelPriceEditVO;
import com.wangqin.globalshop.biz1.app.bean.dto.SkuChannelPriceDTO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.bean.dto.ISkuDTO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemSkuAddVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemSkuQueryVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonPageResult;


public interface IItemSkuService  {

	//商品编辑：查找哪些sku被前端删除了
	List<String> queryToDeleteSkuCodeList(List<String> codeList,String itemCode);
	
	//查询和本sku同属一个商品的所有sku的sale_price
	List<Double> querySalePriceListBySkuCode(String skuCode);
		
	/**
	 * 按照条件分页查询商品(分页)
	 * @param
	 * @return
	 */
	JsonPageResult<List<ISkuDTO>> queryItemSkus(ItemSkuQueryVO itemSkuQueryVO);

	/**
	 * 按照条件分页查询商品多渠道价格(分页)
	 * @param
	 * @return
	 */
	JsonPageResult<List<SkuChannelPriceDTO>> queryItemSkuPrices(ItemSkuQueryVO itemSkuQueryVO);

	/**
	 * 批量保存SKU+多渠道价格
	 */
	public void saveItemSkuMultiPriceList(List<SkuChannelPriceEditVO> skuChannelPriceEditVOList);

	/**
	 * 保存一个SKU的多渠道价格
	 */
	public void saveOneItemSkuMultiPrice(SkuChannelPriceEditVO skuChannelPriceEditVO);

	/**
	 * 设置所有SKU的渠道价格
	 * @param discount 折扣，比如85折，传85，但折扣不能超过100
	 */
	public void saveAllItemSkuInOneChannelPrice(String discount, String channelNo);

	/**
	 * 根据SKU初始化库存信息
	 * @param itemSkuList
	 * @return
	 */
	List<InventoryDO> initInventory(List<ItemSkuAddVO> itemSkuList);
	



	

	
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
	 
	 List<ItemSkuDO> querySkuListByItemCode(String itemCode);
	 
	 //根据itemCode查询sku列表,包含虚拟库存、颜色、尺寸这3个不在item_sku表里面的字段
	 //商品编辑的时候使用
	 //TODO颜色尺寸的查询 
     List<ItemSkuQueryVO> querySkuListByItemCodeContainsVirtualInvScale(String itemCode);
	 
	 List<ItemSkuDO> queryItemSkuListSelective(ItemSkuQueryVO itemSkuQueryVO);
	 
	 void deleteItemSkuBySkuCode(String skuCode);
	 
	 void insertItemSkuSelective(ItemSkuDO itemSkuDO);
	 
	 String querySkuCodeById(Long id);

    List<ItemSkuDO> queryByItemCodeAndCompanyNo( String itemCode,  String loginUserCompanyNo);

    ItemSkuDO queryBySkuCodeOrUpcAndCompanyNo(String code, String companyNo);
    
    Double querySalePriceByItemCode(String itemCode);
    
    //根据id查出该sku对应的商品在sku表里面映射了几个sku，如果只有一个，禁止删除这个sku
  	Integer querySkuNumberBySkuId(Long skuId);
  	
    //查询指定的upc对应的item_sku的sku_code,按公司划分,防止重复的upc
  	List<String> querySkuCodeListByUpc(String companyNo,String upc);
  	
    //询要更新的upc是否在别的商品名下已经存在,存在则表明出现了upc重复的问题
  	Integer queryRecordCountByUpcCompanyNotInSameItem(String companyNo,String upc,String itemCode);

    Integer queryCountByUpcAndCompanyNo(String companyNo, String upc);

	JsonPageResult<List<SkuChannelPriceDTO>> querySkuSalePrice(String channelNo, ItemSkuQueryVO itemSkuQueryVO);
}
