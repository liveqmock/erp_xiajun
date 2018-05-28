package com.wangqin.globalshop.biz1.app.dal.dataObjectVo;

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


}
