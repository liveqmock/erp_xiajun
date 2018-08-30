package com.wangqin.globalshop.item.api.sku;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemSkuAddVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemSkuQueryVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonPageResult;
import com.wangqin.globalshop.biz1.app.bean.dataVo.SkuChannelPriceEditVO;
import com.wangqin.globalshop.biz1.app.bean.dto.ISkuDTO;
import com.wangqin.globalshop.biz1.app.bean.dto.SkuChannelPriceDTO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;


public interface ItemSkuService {

	//商品编辑：查找哪些sku被前端删除了
	@RequestMapping(value = "/sku/queryToDeleteSkuCodeList", method = RequestMethod.POST)
	List<String> queryToDeleteSkuCodeList(@RequestBody List<String> codeList, @RequestParam("itemCode") String itemCode);
	
	//查询和本sku同属一个商品的所有sku的sale_price
	@RequestMapping(value = "/sku/querySalePriceListBySkuCode", method = RequestMethod.POST)
	List<Double> querySalePriceListBySkuCode(@RequestParam("skuCode") String skuCode);
		
	/**
	 * 按照条件分页查询商品(分页)
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/sku/queryItemSkus", method = RequestMethod.POST)
	JsonPageResult<List<ISkuDTO>> queryItemSkus(@RequestBody ItemSkuQueryVO itemSkuQueryVO);

	/**
	 * 按照条件分页查询商品多渠道价格(分页)
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/sku/queryItemSkuPrices", method = RequestMethod.POST)
	JsonPageResult<List<SkuChannelPriceDTO>> queryItemSkuPrices(@RequestBody ItemSkuQueryVO itemSkuQueryVO);

	/**
	 * 批量保存SKU+多渠道价格
	 */
	@RequestMapping(value = "/sku/saveItemSkuMultiPriceList", method = RequestMethod.POST)
	public void saveItemSkuMultiPriceList(@RequestBody List<SkuChannelPriceEditVO> skuChannelPriceEditVOList);

	/**
	 * &#x4fdd;&#x5b58;&#x4e00;&#x4e2a;SKU&#x7684;&#x591a;&#x6e20;&#x9053;&#x4ef7;&#x683c;
	 */
	@RequestMapping(value = "/sku/saveOneItemSkuMultiPrice", method = RequestMethod.POST)
	public void saveOneItemSkuMultiPrice(@RequestBody SkuChannelPriceEditVO skuChannelPriceEditVO);

	/**
	 * 设置所有SKU的渠道价格
	 * @param discount 折扣，比如85折，传85，但折扣不能超过100
	 */
	@RequestMapping(value = "/sku/saveAllItemSkuInOneChannelPrice", method = RequestMethod.POST)
	public void saveAllItemSkuInOneChannelPrice(@RequestParam("discount") String discount, @RequestParam("channelNo") String channelNo);

	/**
	 * 根据SKU初始化库存信息
	 * @param itemSkuList
	 * @return
	 */
	@RequestMapping(value = "/sku/initInventory", method = RequestMethod.POST)
	List<InventoryDO> initInventory(@RequestBody List<ItemSkuAddVO> itemSkuList);
	

	

	
	/**
	 * itemid 查询skulist
	 * @param itemId
	 * @return
	 */
	@RequestMapping(value = "/sku/queryItemSkusByItemId", method = RequestMethod.POST)
	List<ItemSkuDO> queryItemSkusByItemId(@RequestParam("itemId") Long itemId);

	@RequestMapping(value = "/sku/selectByPrimaryKey", method = RequestMethod.POST)
	ItemSkuDO selectByPrimaryKey(@RequestParam("id") Long id);
	
	/**
	 * 判断sku是否可以删除
	 * @param itemId
	 * @return
	 */
	@RequestMapping(value = "/sku/isCanDeleteSku", method = RequestMethod.POST)
	boolean isCanDeleteSku(@RequestParam("skuId") Long skuId);

	@RequestMapping(value = "/sku/deleteById", method = RequestMethod.POST)
	void deleteById(@RequestParam("id") Long id);

	@RequestMapping(value = "/sku/queryItemSkusForExcel", method = RequestMethod.POST)
	 List<ItemSkuDO> queryItemSkusForExcel();

	@RequestMapping(value = "/sku/queryItemSkusByUpc", method = RequestMethod.POST)
	 List<ItemSkuDO> queryItemSkusByUpc(@RequestParam("upc") String upc);

	@RequestMapping(value = "/sku/insertBatch", method = RequestMethod.POST)
	 void insertBatch(@RequestBody List<ItemSkuAddVO> skuList);

	@RequestMapping(value = "/sku/queryItemSkuBySkuCode", method = RequestMethod.POST)
	 ISkuDTO queryItemSkuBySkuCode(@RequestParam("skuCode") String skuCode);

	@RequestMapping(value = "/sku/updateById", method = RequestMethod.POST)
	 void updateById(@RequestBody ItemSkuQueryVO itemSkuUpdateVO);
	 
	 //查询可售的sku
	 @RequestMapping(value = "/sku/querySaleableSkus", method = RequestMethod.POST)
	 List<ItemSkuDO> querySaleableSkus();

	@RequestMapping(value = "/sku/querySkuListByItemCode", method = RequestMethod.POST)
	 List<ItemSkuDO> querySkuListByItemCode(@RequestParam("itemCode") String itemCode);
	 
	 //根据itemCode查询sku列表,包含虚拟库存、颜色、尺寸这3个不在item_sku表里面的字段
	 //商品编辑的时候使用
	 //TODO颜色尺寸的查询
	 @RequestMapping(value = "/sku/querySkuListByItemCodeContainsVirtualInvScale", method = RequestMethod.POST)
     List<ItemSkuQueryVO> querySkuListByItemCodeContainsVirtualInvScale(@RequestParam("itemCode") String itemCode);

	@RequestMapping(value = "/sku/queryItemSkuListSelective", method = RequestMethod.POST)
	 List<ItemSkuDO> queryItemSkuListSelective(@RequestBody ItemSkuQueryVO itemSkuQueryVO);

	@RequestMapping(value = "/sku/deleteItemSkuBySkuCode", method = RequestMethod.POST)
	 void deleteItemSkuBySkuCode(@RequestParam("skuCode") String skuCode);

	@RequestMapping(value = "/sku/insertItemSkuSelective", method = RequestMethod.POST)
	 void insertItemSkuSelective(@RequestBody ItemSkuDO itemSkuDO);

	@RequestMapping(value = "/sku/querySkuCodeById", method = RequestMethod.POST)
	 String querySkuCodeById(@RequestParam("id") Long id);

	@RequestMapping(value = "/sku/queryByItemCodeAndCompanyNo", method = RequestMethod.POST)
     List<ItemSkuDO> queryByItemCodeAndCompanyNo(@RequestParam("itemCode") String itemCode, @RequestParam("loginUserCompanyNo") String loginUserCompanyNo);

	@RequestMapping(value = "/sku/queryBySkuCodeOrUpcAndCompanyNo", method = RequestMethod.POST)
    ItemSkuDO queryBySkuCodeOrUpcAndCompanyNo(@RequestParam("code") String code, @RequestParam("companyNo") String companyNo);

	@RequestMapping(value = "/sku/querySalePriceByItemCode", method = RequestMethod.POST)
    Double querySalePriceByItemCode(@RequestParam("itemCode") String itemCode);
    
    //根据id查出该sku对应的商品在sku表里面映射了几个sku，如果只有一个，禁止删除这个sku
	@RequestMapping(value = "/sku/querySkuNumberBySkuId", method = RequestMethod.POST)
  	Integer querySkuNumberBySkuId(@RequestParam("skuId") Long skuId);
  	
    //查询指定的upc对应的item_sku的sku_code,按公司划分,防止重复的upc
	@RequestMapping(value = "/sku/querySkuCodeListByUpc", method = RequestMethod.POST)
  	List<String> querySkuCodeListByUpc(@RequestParam("companyNo") String companyNo, @RequestParam("upc") String upc);
  	
    //询要更新的upc是否在别的商品名下已经存在,存在则表明出现了upc重复的问题
	@RequestMapping(value = "/sku/queryRecordCountByUpcCompanyNotInSameItem", method = RequestMethod.POST)
  	Integer queryRecordCountByUpcCompanyNotInSameItem(@RequestParam("companyNo") String companyNo, @RequestParam("upc") String upc, @RequestParam("itemCode") String itemCode);

	@RequestMapping(value = "/sku/queryCountByUpcAndCompanyNo", method = RequestMethod.POST)
    Integer queryCountByUpcAndCompanyNo(@RequestParam("companyNo") String companyNo, @RequestParam("upc") String upc);

	@RequestMapping(value = "/sku/querySkuSalePrice", method = RequestMethod.POST)
	JsonPageResult<List<SkuChannelPriceDTO>> querySkuSalePrice(@RequestParam("channelNo") String channelNo, @RequestBody ItemSkuQueryVO itemSkuQueryVO);
}
