package com.wangqin.globalshop.channelapi.dal;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuScaleDO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Create by 777 on 2018/5/28
 */
public class ItemSkuVo extends ItemSkuDO{



	private static final String scale_color = "color";

	private static final String scale_size = "size";

	private static final String scale_color_name = "颜色";

	private static final String scale_size_name = "尺寸";

	//虚拟库存，插入到库存表的虚拟库存字段(inventory.virtual_inv)
	private Long virtualInv;
	
	//颜色尺寸等规格
	private Map<String,ItemSkuScaleDO> scaleMap = new HashMap<>();


	//库存等
	private InventoryDO inventoryDO;

	//queryItemSkusForItemThirdSale
	private Integer itemSkuQuantity;//这个是海狐查询已经售卖的订单里面的商品数量

	
	public Long getVirtualInv() {
		return virtualInv;
	}
	public void setVirtualInv(Long virtualInv) {
		this.virtualInv = virtualInv;
	}
	public static String getScaleColor() {
		return scale_color;
	}
	public static String getScaleSize() {
		return scale_size;
	}
	public static String getScaleColorName() {
		return scale_color_name;
	}
	public static String getScaleSizeName() {
		return scale_size_name;
	}

	private Map<String,Float> channelSalePriceMap = new HashMap<>();//shopcode全局唯一
	public Map<String, Float> getChannelSalePriceMap() {
		return channelSalePriceMap;
	}
	public void setChannelSalePriceMap(Map<String, Float> channelSalePriceMap) {
		this.channelSalePriceMap = channelSalePriceMap;
	}
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
	public Map<String, ItemSkuScaleDO> getScaleMap() {
		return scaleMap;
	}
	public void setScaleMap(Map<String, ItemSkuScaleDO> scaleMap) {
		this.scaleMap = scaleMap;
	}
	/**
	 * 这两个接口需要重新确认
	 * @return
	 */
	public String getColor(){
		if(scaleMap.get(scale_color_name) ==null){
            return null;
		}else {
			return scaleMap.get(scale_color_name).getScaleValue();
		}
	}

	public String getSize(){
		if(scaleMap.get(scale_size_name) == null){
			return null;
		}else {
			return scaleMap.get(scale_size_name).getScaleValue();
		}
	}
	/**
	 * 获取其他规格属性
	 * @return
	 */
	public String getOtherScale(){
		String otherScale = "";
		for(String scaleKey : scaleMap.keySet()){
			if(!scale_color_name.equalsIgnoreCase(scaleKey) && !scale_size_name.equalsIgnoreCase(scaleKey)){
				if("".equalsIgnoreCase(otherScale)){
					otherScale += scaleMap.get(scaleKey).getScaleName()+":"+scaleMap.get(scaleKey).getScaleValue();
				}else {
					otherScale += ","+scaleMap.get(scaleKey).getScaleName()+":"+scaleMap.get(scaleKey).getScaleValue();
				}
			}
		}
		return  otherScale;
	}

	public Long getTotalAvailableInv(){
		Long quantity = 0L;
		if(inventoryDO == null){
			return quantity;
		}else {
			quantity = inventoryDO.getInv()-inventoryDO.getLockedInv()
					+inventoryDO.getTransInv()-inventoryDO.getLockedTransInv()
					+inventoryDO.getVirtualInv()-inventoryDO.getLockedVirtualInv();
		}
		return quantity;
	}

	public Double getChannelSalePrice(String channelNo){
           Double salePrice = getSalePrice() == null ? 0d : getSalePrice();
           if(channelSalePriceMap.get(channelNo) != null && channelSalePriceMap.get(channelNo) > 0.001){
			   salePrice = BigDecimal.valueOf(channelSalePriceMap.get(channelNo)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		   }
		   return salePrice;

	}


}
