package com.wangqin.globalshop.channel.service.channel;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelListingItemDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelListingItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.globalshop.channelapi.dal.ItemVo;

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
	public AbstractChannelService.AdapterData adapterAuth();
	
	/**
	 * 创建商品
	 * @param item
	 * @return
	 */
	public AbstractChannelService.AdapterData adapterCreateItem(ItemVo item);
	
	/**
	 * 更新商品
	 * @param item
	 * @param outerItem
	 * @return
	 */
	public AbstractChannelService.AdapterData adapterUpdateItem(ItemVo item, ChannelListingItemDO outerItem);
	
	/**
	 * 上架商品
	 * @param item
	 * @param outerItem
	 * @return
	 */
	public AbstractChannelService.AdapterData adapterListingItem(ItemVo item, ChannelListingItemDO outerItem);
	
	/**
	 * 下架商品
	 * @param item
	 * @param outerItem
	 * @return
	 */
	public AbstractChannelService.AdapterData adapterDelistingItem(ItemVo item, ChannelListingItemDO outerItem);
	
	/**
	 * 更新sku库存
	 * @param sku
	 * @param inventory
	 * @return
	 */
	public AbstractChannelService.AdapterData adapterUpdateSkuInventory(ChannelListingItemSkuDO sku,
			InventoryDO inventory);
}
