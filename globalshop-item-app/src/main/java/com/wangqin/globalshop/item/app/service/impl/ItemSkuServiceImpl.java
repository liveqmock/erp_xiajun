package com.wangqin.globalshop.item.app.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemSkuMapperExt;
import com.wangqin.globalshop.biz1.app.vo.ItemSkuQueryVO;
import com.wangqin.globalshop.biz1.app.vo.JsonPageResult;
import com.wangqin.globalshop.item.app.service.IInventoryService;
import com.wangqin.globalshop.item.app.service.IItemSkuService;


@Service
public class ItemSkuServiceImpl   implements IItemSkuService {
	
	@Autowired
	private IInventoryService inventoryService;
	
	@Autowired
	private ItemSkuMapperExt itemSkuMapperExt;

	@Override
	public JsonPageResult<List<ItemSkuDO>> queryItemSkus(ItemSkuQueryVO itemSkuQueryVO) {
		JsonPageResult<List<ItemSkuDO>> itemResult = new JsonPageResult<>();
		//1、查询总的记录数量
		Integer totalCount =  itemSkuMapperExt.queryItemSkusCount(itemSkuQueryVO);
		
		//2、查询分页记录
		if(totalCount!=null&&totalCount!=0L){
			itemResult.buildPage(totalCount, itemSkuQueryVO);
			List<ItemSkuDO> itemSkus = itemSkuMapperExt.queryItemSkus(itemSkuQueryVO);
			itemResult.setData(itemSkus);
		}
		return itemResult;
	}

	@Override
	public List<InventoryDO> initInventory(List<ItemSkuDO> itemSkuList) {
		List<InventoryDO> inventoryList = null;
		if (CollectionUtils.isNotEmpty(itemSkuList)) {
			inventoryList = Lists.transform(itemSkuList, new Function<ItemSkuDO, InventoryDO>() {
				@Override
				public InventoryDO apply(ItemSkuDO itemSku) {
					// 初始化库存信息
					InventoryDO inventory = new InventoryDO();
					inventory.setGmtCreate(new Date());
					inventory.setGmtModify(new Date());
					inventory.setItemCode(itemSku.getItemCode());
					inventory.setItemName(itemSku.getItemName());
				
									inventory.setSkuCode(itemSku.getSkuCode());
					inventory.setUpc(itemSku.getUpc());
					return inventory;
				}
			});
		}
		return inventoryList;
	}

	@Override
	public void addItemSku(ItemSkuDO itemSku) {
		itemSkuMapperExt.insertSelective(itemSku);
		InventoryDO inventory = inventoryService.queryInventoryBySkuCode(itemSku.getSkuCode());
		if(inventory==null){
			List<ItemSkuDO> newInvList = Lists.newArrayList();
			newInvList.add(itemSku);
			List<InventoryDO>  inventoryList = initInventory(newInvList);
			inventoryService.insertBatch(inventoryList);
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
			List<InventoryDO>  inventoryList = initInventory(newInvList);
			inventoryService.insertBatch(inventoryList);
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
			if(o !=null && o>0) return false;
			
			Integer i = itemSkuMapperExt.queryItemSkusCountInInventoryArea(skuId);
			if(i !=null && i>0) return false;
			
			Integer t =itemSkuMapperExt.queryItemSkusCountInTask(skuId);
			if(t !=null && t>0) return false;
			
			Integer p = itemSkuMapperExt.queryItemSkusCountInPurchase(skuId);
            return p == null || p <= 0;
        }
        return false;
	}
	
	@Override
	public int  deleteByPrimaryKey(Long id) {
		return itemSkuMapperExt.deleteByPrimaryKey(id);
	}
	
	@Override
	public List<ItemSkuDO> queryItemSkusForExcel() {
		return itemSkuMapperExt.queryItemSkusForExcel();
	}
	
	@Override
	public List<ItemSkuDO> queryItemSkusByUpc(String upc) {
		return itemSkuMapperExt.queryItemSkusByUpc(upc);
	}
	
	}

