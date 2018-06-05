package com.wangqin.globalshop.inventory.app.vo;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;

/**
 * Create by 777 on 2018/5/30
 */
public class InventoryVo extends InventoryDO {

	public Long getAvailableInv(){
		return getInv()-getLockedInv();
	}
	public Long	getAvailableTransInv(){
		return getTransInv()-getLockedTransInv();
	}

	public Long getTotalAvailableInv(){
		return  getInv()-getLockedInv()+getTransInv()-getLockedTransInv();
	}


}
