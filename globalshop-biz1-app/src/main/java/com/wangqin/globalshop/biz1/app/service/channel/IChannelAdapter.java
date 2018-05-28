package com.wangqin.globalshop.biz1.app.service.channel;

import com.wangqin.model.Inventory;
import com.wangqin.model.Item;
import com.wangqin.model.item.OuterItem;
import com.wangqin.model.item.OuterItemSku;
import com.wangqin.service.channels.AbstractChannelService.AdapterData;

/**
 * 渠道适配接口
 * 用于适配各个渠道SKD
 * @author zhubowen
 *
 */
public interface IChannelAdapter {
	
	/**
	 * 授权
	 * @return
	 */
	public AdapterData adapterAuth();
	
	/**
	 * 创建商品
	 * @param item
	 * @return
	 */
	public AdapterData adapterCreateItem(Item item);
	
	/**
	 * 更新商品
	 * @param item
	 * @param outerItem
	 * @return
	 */
	public AdapterData adapterUpdateItem(Item item, OuterItem outerItem);
	
	/**
	 * 上架商品
	 * @param item
	 * @param outerItem
	 * @return
	 */
	public AdapterData adapterListingItem(Item item, OuterItem outerItem);
	
	/**
	 * 下架商品
	 * @param item
	 * @param outerItem
	 * @return
	 */
	public AdapterData adapterDelistingItem(Item item, OuterItem outerItem);
	
	/**
	 * 更新sku库存
	 * @param sku
	 * @param inventory
	 * @return
	 */
	public AdapterData adapterUpdateSkuInventory(OuterItemSku sku, Inventory inventory);
}
