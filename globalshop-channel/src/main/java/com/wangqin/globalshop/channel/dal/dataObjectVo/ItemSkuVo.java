package com.wangqin.globalshop.channel.dal.dataObjectVo;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by 777 on 2018/5/28
 */
public class ItemSkuVo extends ItemSkuDO{



	private static final String scale_color = "color";

	private static final String scale_size = "size";

	//颜色尺寸等规格
	private Map<String,String> scaleMap = new HashMap<>();


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
	public Map<String, String> getScaleMap() {
		return scaleMap;
	}
	public void setScaleMap(Map<String, String> scaleMap) {
		this.scaleMap = scaleMap;
	}
	/**
	 * 这两个接口需要重新确认
	 * @return
	 */
	public String getColor(){
		return scaleMap.get(scale_color);
	}

	public String getSize(){
		return scaleMap.get(scale_size);
	}

	public Long getTotalAvailableInv(){
		return  inventoryDO.getInv()-inventoryDO.getLockedInv()+inventoryDO.getTransInv()-inventoryDO.getLockedTransInv();
	}


}
