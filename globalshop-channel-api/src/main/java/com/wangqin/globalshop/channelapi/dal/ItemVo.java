package com.wangqin.globalshop.channelapi.dal;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by 777 on 2018/5/28
 * 扩展DO
 */
public class ItemVo extends ItemDO {

	private List<ItemSkuVo> itemSkus = new ArrayList<ItemSkuVo>();

	private List<ItemSkuDO> itemSkuDOS;
	public List<ItemSkuDO> getItemSkuDOS() {
		return itemSkuDOS;
	}
	public void setItemSkuDOS(List<ItemSkuDO> itemSkuDOS) {
		this.itemSkuDOS = itemSkuDOS;
	}
	public List<ItemSkuVo> getItemSkus() {
		return itemSkus;
	}
	public void setItemSkus(List<ItemSkuVo> itemSkus) {
		this.itemSkus = itemSkus;
	}
}
