package com.wangqin.globalshop.channel.dal.dataObjectVo;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuScaleDO;

/**
 * Create by 777 on 2018/5/28
 */
public class ItemSkuVo extends ItemSkuDO{



	//颜色尺寸等规格
	private ItemSkuScaleDO itemSkuScaleDO;


	//库存等
	private InventoryDO inventoryDO;

	//queryItemSkusForItemThirdSale
	private Integer itemSkuQuantity;


	public Integer getItemSkuQuantity() {
		return itemSkuQuantity;
	}
	public void setItemSkuQuantity(Integer itemSkuQuantity) {
		this.itemSkuQuantity = itemSkuQuantity;
	}
	public InventoryDO getInventoryDO() {
		return inventoryDO;
	}
	public void setInventoryDO(InventoryDO inventoryDO) {
		this.inventoryDO = inventoryDO;
	}
	public ItemSkuScaleDO getItemSkuScaleDO() {
		return itemSkuScaleDO;
	}
	public void setItemSkuScaleDO(ItemSkuScaleDO itemSkuScaleDO) {
		this.itemSkuScaleDO = itemSkuScaleDO;
	}



	/**
	 * 这两个接口需要重新确认
	 * @return
	 */
	public String getColor(){
		if(itemSkuScaleDO == null){
			return null;
		}
		return itemSkuScaleDO.getScaleCode();
	}

	public String getScale(){
		if(itemSkuScaleDO == null){
			return null;
		}
		return itemSkuScaleDO.getScaleName();
	}

	public Long getTotalAvailableInv(){
		return  inventoryDO.getInv()-inventoryDO.getLockedInv()+inventoryDO.getTransInv()-inventoryDO.getLockedTransInv();
	}


}
