package com.wangqin.globalshop.item.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuScaleDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemSkuMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemSkuScaleMapperExt;
import com.wangqin.globalshop.biz1.app.dto.ISkuDTO;
import com.wangqin.globalshop.biz1.app.vo.ItemSkuAddVO;
import com.wangqin.globalshop.biz1.app.vo.ItemSkuQueryVO;
import com.wangqin.globalshop.biz1.app.vo.JsonPageResult;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.EasyUtil;
import com.wangqin.globalshop.item.app.service.IItemSkuService;
import com.wangqin.globalshop.item.app.service.ItemIInventoryService;



@Service
public class ItemSkuServiceImpl   implements IItemSkuService {
	
	@Autowired
	private ItemIInventoryService inventoryService;
	
	@Autowired
	private ItemSkuMapperExt itemSkuMapperExt;
	
	@Autowired
	private ItemSkuScaleMapperExt itemSkuScaleMapperExt;

	//查询和本sku同属一个商品的所有sku的sale_price（自己除外）
	@Override
	public List<Double> querySalePriceListBySkuCode(String skuCode) {
		return itemSkuMapperExt.querySalePriceListBySkuCode(skuCode);
	}
	
	@Override
	public void insertBatch(List<ItemSkuAddVO> skuList) {
		itemSkuMapperExt.insertBatch(skuList);
	}
	/**
	 * 按条件查询sku列表(分页）
	 */
	@Override
	@Transactional(rollbackFor = ErpCommonException.class)
	public JsonPageResult<List<ISkuDTO>> queryItemSkus(ItemSkuQueryVO itemSkuQueryVO) {
		JsonPageResult<List<ISkuDTO>> itemResult = new JsonPageResult<>();
		//1、查询总的记录数量
		Integer totalCount =  itemSkuMapperExt.queryItemSkusCount(itemSkuQueryVO);
		//2、查询分页记录
		if(totalCount!=null&&totalCount!=0L){
			itemResult.buildPage(totalCount, itemSkuQueryVO);
			List<ISkuDTO> itemSkus = itemSkuMapperExt.queryItemSkus(itemSkuQueryVO);
			//查询sku的规格信息
			itemSkus.forEach(itemSku -> {
				List<ItemSkuScaleDO> skuScaleList = itemSkuScaleMapperExt.selectScaleNameValueBySkuCode(itemSku.getSkuCode());
	        	if(!EasyUtil.isListEmpty(skuScaleList)) {
	        		for(ItemSkuScaleDO scale:skuScaleList) {
	        			if("颜色".equals(scale.getScaleName())) {
	        				itemSku.setColor(scale.getScaleValue());
	        			}
	        			if("尺寸".equals(scale.getScaleName())) {
	        				itemSku.setScale(scale.getScaleValue());
	        			}
	        		}
	        	}
			});
			
			itemResult.setData(itemSkus);
		}
		return itemResult;
	}
	
	

	/**
	 * 初始化库存信息，添加商品时用
	 */
	@Override
	public List<InventoryDO> initInventory(List<ItemSkuAddVO> itemSkuList) {
		List<InventoryDO> inventoryList = new ArrayList<InventoryDO>();
		itemSkuList.forEach(itemSku -> {
			// 初始化库存信息
			InventoryDO inventory = new InventoryDO();
			inventory.setItemName(itemSku.getItemName());
		    inventory.setItemCode(itemSku.getItemCode());
			inventory.setSkuCode(itemSku.getSkuCode());
			inventory.setUpc(itemSku.getUpc());
			inventory.setVirtualInv(Long.valueOf(itemSku.getVirtualInv()));
			inventoryList.add(inventory);
		});
		return inventoryList;
	}

	@Override
	public void addItemSku(ItemSkuDO itemSku) {
		itemSkuMapperExt.insertSelective(itemSku);
		InventoryDO inventory = inventoryService.queryInventoryBySkuCode(itemSku.getSkuCode());
		if(inventory==null){
			List<ItemSkuDO> newInvList = Lists.newArrayList();
			newInvList.add(itemSku);
			//List<InventoryDO>  inventoryList = initInventory(newInvList);
			//inventoryService.insertBatch(inventoryList);
		}else{
			//if(inventory.getVirtualInv()!=itemSku.getVirtualInv()){
			//	inventory.setVirtualInv(itemSku.getVirtualInv());
			//	inventory.setGmtModify(new Date());
			//	inventoryService.updateById(inventory);
			}
		}
	


	@Override
	public void updateItemSku(ItemSkuDO itemSku) {
		itemSkuMapperExt.updateByPrimaryKey(itemSku);
		InventoryDO inventory = inventoryService.queryInventoryBySkuCode(itemSku.getSkuCode());
		if(inventory==null){
			List<ItemSkuDO> newInvList = Lists.newArrayList();
			newInvList.add(itemSku);
			//List<InventoryDO>  inventoryList = initInventory(newInvList);
			//inventoryService.insertBatch(inventoryList);
		}else{
			//if(inventory.getVirtualInv()!=itemSku.getVirtualInv()){
			//	inventory.setVirtualInv(itemSku.getVirtualInv());
			//	inventory.setGmtModify(new Date());
			//	inventoryService.updateById(inventory);
			//}
		}
	}

	@Override
	public List<ItemSkuDO> queryItemSkusByItemId(Long itemId) {
		if(itemId!=null){
			return itemSkuMapperExt.queryItemSkusById(itemId, itemId);
		}
		return null;
	}
	
	
	@Override
	public ItemSkuDO selectByPrimaryKey(Long id) {
		return itemSkuMapperExt.selectByPrimaryKey(id);
	}
	
	
	
	@Override
	public boolean isCanDeleteSku(Long skuId) {
		if(skuId!=null) {
			Integer  o = itemSkuMapperExt.queryItemSkusCountInOrder(skuId);
			if(o !=null && o>0) {
                return false;
            }
			
			Integer i = itemSkuMapperExt.queryItemSkusCountInInventoryArea(skuId);
			if(i !=null && i>0) {
                return false;
            }
			
			Integer t =itemSkuMapperExt.queryItemSkusCountInTask(skuId);
			if(t !=null && t>0) {
                return false;
            }
			
			Integer p = itemSkuMapperExt.queryItemSkusCountInPurchase(skuId);
            return p == null || p <= 0;
        }
        return false;
	}
	
	@Override
	public void deleteById(Long id) {
		 itemSkuMapperExt.deleteById(id);
	}
	
	@Override
	public List<ItemSkuDO> queryItemSkusForExcel() {
		return itemSkuMapperExt.queryItemSkusForExcel();
	}
	
	@Override
	public List<ItemSkuDO> queryItemSkusByUpc(String upc) {
		return itemSkuMapperExt.queryItemSkusByUpc(upc);
	}
	
	@Override
	public ISkuDTO queryItemSkuBySkuCode(String skuCode) {
		 return itemSkuMapperExt.queryItemSkuBySkuCode(skuCode); 
	}
	
	@Override
	public void updateById(ItemSkuQueryVO itemSkuUpdateVO) {
		itemSkuMapperExt.updateById(itemSkuUpdateVO);
	}
	
	 //查询可售的sku 
	@Override
	public List<ItemSkuDO> querySaleableSkus() {
		return itemSkuMapperExt.querySaleableSkus();
	}
	
	@Override
	public List<ItemSkuDO> querySkuListByItemCode(String itemCode) {
		return itemSkuMapperExt.querySkuListByItemCode(itemCode);
	}
	
	//根据itemCode查询sku列表,包含虚拟库存、颜色、尺寸这3个不在item_sku表里面的字段
    //商品编辑的时候使用
    //TODO颜色尺寸的查询 
	@Override
	public List<ItemSkuQueryVO> querySkuListByItemCodeContainsVirtualInvScale(String itemCode) {
		return itemSkuMapperExt.querySkuListByItemCodeContainsVirtualInvScale(itemCode);
	}
	
	@Override
	public List<ItemSkuDO> queryItemSkuListSelective(ItemSkuQueryVO itemSkuQueryVO) {
		return itemSkuMapperExt.queryItemSkuListSelective(itemSkuQueryVO);
	}
	
	@Override
	public void deleteItemSkuBySkuCode(String skuCode) {
		 itemSkuMapperExt.deleteItemSkuBySkuCode(skuCode);
	}
	
	@Override
	public void insertItemSkuSelective(ItemSkuDO itemSkuDO) {
		itemSkuMapperExt.insertItemSkuSelective(itemSkuDO);
	}
	
	@Override
	public String querySkuCodeById(Long id) {
		return itemSkuMapperExt.querySkuCodeById(id);
	}

	@Override
	public List<ItemSkuDO> queryByItemCodeAndCompanyNo(String itemCode, String loginUserCompanyNo) {
		return itemSkuMapperExt.queryByItemCodeAndCompanyNo(itemCode,loginUserCompanyNo);
	}

	@Override
	public ItemSkuDO queryBySkuCodeOrUpcAndCompanyNo(String code, String companyNo) {
		return itemSkuMapperExt.queryBySkuCodeOrUpcAndCompanyNo(code,companyNo);
	}
	
	@Override
	public Double querySalePriceByItemCode(String itemCode) {
		// TODO Auto-generated method stub
		return itemSkuMapperExt.querySalePriceByItemCode(itemCode);
	}
	
	//根据id查出该sku对应的商品在sku表里面映射了几个sku，如果只有一个，禁止删除这个sku
	@Override
	public Integer querySkuNumberBySkuId(Long skuId) {
		return itemSkuMapperExt.querySkuNumberBySkuId(skuId);
	}
	
	//查询指定的upc对应的item_sku的sku_code,按公司划分,防止重复的upc
	@Override
	public List<String> querySkuCodeListByUpc(String companyNo,String upc) {
		return itemSkuMapperExt.querySkuCodeListByUpc(companyNo, upc);
	}
	
	@Override
	public Integer queryRecordCountByUpcCompanyNotInSameItem(String companyNo,String upc,String itemCode) {
		return itemSkuMapperExt.queryRecordCountByUpcCompanyNotInSameItem(companyNo, upc, itemCode);
	}

}

