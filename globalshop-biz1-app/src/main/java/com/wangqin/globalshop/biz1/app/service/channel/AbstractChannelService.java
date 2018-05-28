package com.wangqin.globalshop.biz1.app.service.channel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelListingItemDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelListingItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.service.channelAccount.IChannelAccountService;
import com.wangqin.globalshop.biz1.app.service.channelItem.IChannelListingItemService;
import com.wangqin.globalshop.biz1.app.service.channelItem.IChannelListingItemSkuService;
import com.wangqin.globalshop.biz1.app.service.item.IItemService;
import com.wangqin.globalshop.biz1.app.service.item.IItemSkuService;
import com.wangqin.globalshop.biz1.app.service.order.IShippingOrderService;
import com.wangqin.globalshop.common.scan.SpringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.wangqin.enums.ChannelType;
import com.wangqin.enums.ItemStatus;
import com.wangqin.enums.PlatformType;
import com.wangqin.exception.ErpCommonException;
import com.wangqin.mapper.OuterOrderDetailMapper;
import com.wangqin.mapper.OuterOrderMapper;
import com.wangqin.model.ChannelAccount;
import com.wangqin.model.Inventory;
import com.wangqin.model.Item;
import com.wangqin.model.item.OuterItem;
import com.wangqin.model.item.OuterItemSku;
import com.wangqin.service.IChannelAccountService;
import com.wangqin.service.IErpOrderService;
import com.wangqin.service.IInventoryService;
import com.wangqin.service.IItemService;
import com.wangqin.service.IItemSkuService;
import com.wangqin.service.IOuterOrderService;
import com.wangqin.service.ISequenceUtilService;
import com.wangqin.service.IShippingOrderService;
import com.wangqin.service.IUploadFileService;
import com.wangqin.service.item.IOuterItemService;
import com.wangqin.util.ShiroUtil;
import com.wangqinauth.commons.scan.SpringUtils;
import com.wangqinauth.commons.shiro.ShiroUser;

public abstract class AbstractChannelService implements IChannelService, IChannelAdapter {
	// logger
	protected Logger logger = LogManager.getLogger(getClass());
	
	// 平台账号信息
	protected ChannelAccountDO channelAccount;
	
	protected IChannelAccountService channelAccountService = SpringUtils.getBean(IChannelAccountService.class);

	protected IItemService itemService = SpringUtils.getBean(IItemService.class);
	protected IItemSkuService itemSkuService = SpringUtils.getBean(IItemSkuService.class);

	protected IChannelListingItemService outerItemService = SpringUtils.getBean(IChannelListingItemService.class);
	protected IChannelListingItemSkuService outerItemSkuService = SpringUtils.getBean(IChannelListingItemSkuService.class);


	protected OuterOrderDetailMapper outerOrderDetailMapper = SpringUtils.getBean(OuterOrderDetailMapper.class);

	protected IErpOrderService erpOrderService = SpringUtils.getBean(IErpOrderService.class);
	protected IUploadFileService uploadFileService = SpringUtils.getBean(IUploadFileService.class);
	protected  IInventoryService inventoryService = SpringUtils.getBean(IInventoryService.class);


	protected OuterOrderMapper outerOrderMapper = SpringUtils.getBean(OuterOrderMapper.class);
	protected ISequenceUtilService sequenceUtilService = SpringUtils.getBean(ISequenceUtilService.class);
	protected IOuterOrderService outerOrderService = SpringUtils.getBean(IOuterOrderService.class);
	
	protected IShippingOrderService shippingOrderService = SpringUtils.getBean(IShippingOrderService.class);
	
	
	public AbstractChannelService(ChannelAccountDO channelAccount) {
		this.channelAccount = channelAccount;
	}
	
	
	static public class AdapterData {
		public ItemDO item;
		public ChannelListingItemDO outerItem;
		public List<ChannelListingItemSkuDO> outerItemSkus = new ArrayList<ChannelListingItemSkuDO>();
	}
	
	protected void auth() {
		adapterAuth();
		// 保存到channel_account中
		channelAccount.setGmtModify(new Date());
		channelAccountService.updateByPrimaryKey(channelAccount);
	}
	
	
	@Override
	public void createItem(Long itemId) {
		syncItem(itemId);
	}

	@Override
	public void syncItem(Long itemId) {
		Item item = itemService.queryItem(itemId);

		//0正常，1关闭
		if (this.channelAccount.getStatus().equals(1)) {
			return;
		}

		
		// 查看outerItem是否存在
		OuterItem outerItem = new OuterItem();
		outerItem.setItemId(item.getId());
		outerItem.setPlatformType(this.channelAccount.getType());
		OuterItem selOuterItem = this.outerItemService.selectOne(outerItem);
		// 未同步过
		if(selOuterItem == null) {
			AdapterData adapterData = this.adapterCreateItem(item);
			this.outerItemService.insert(adapterData.outerItem);
			
			for (OuterItemSku sku : adapterData.outerItemSkus) {
				OuterItemSku outerItemSku = new OuterItemSku();
				outerItemSku.setSkuId(sku.getSkuId());
				outerItemSku.setPlatformType(this.channelAccount.getType());
				OuterItemSku selOuterItemSku = this.outerItemService.getSkuMapper().selectOne(outerItemSku);
				if (selOuterItemSku == null) {
					this.outerItemService.getSkuMapper().insert(sku);
				} else {
					this.outerItemService.getSkuMapper().updateSelectiveById(sku);
				}
			}
		} else {
			this.adapterUpdateItem(item, selOuterItem);
		}
	}

	// 上架
	public void syncListingItem(Long itemId) {
		Item item = itemService.queryItem(itemId);
		syncListingItem(item);
	}
	public void syncListingItem(Item item) {
		// 查询出第三方平台帐号，上架
		OuterItem outerItemQuery = new OuterItem();
		outerItemQuery.setItemId(item.getId());
		OuterItem outerItemDb = this.outerItemService.selectOne(outerItemQuery);
		if (outerItemDb != null) {
		    adapterListingItem(item, outerItemDb);
		    // 更新外部商品状态
	        outerItemDb.setStatus(ItemStatus.LISTING.getCode());
	        this.outerItemService.updateById(outerItemDb);
		}else{
		    if(item.getSaleOnYouzan()>0) {
                try {
                    ShiroUser user = ShiroUtil.getShiroUser();
                    ChannelFactory.getChannel(user.getCompanyId(), ChannelType.YouZan).createItem(item.getId());
                } catch(Exception e) {
                    logger.error("商品上架时同步到有赞失败：", e);
                }
		    }
		    if(item.getThirdSale()>0) {
		        try {
                    ShiroUser user = ShiroUtil.getShiroUser();
                    ChannelFactory.getChannel(user.getCompanyId(), ChannelType.HaiHu).createItem(item.getId());
                } catch(Exception e) {
                    logger.error("商品上架时同步到海狐失败：", e);
                }
		    }
		}
	}

	// 下架
	public void syncDelistingItem(Long itemId) {
		Item item = itemService.queryItem(itemId);
		syncDelistingItem(item);
	}
	public void syncDelistingItem(Item item) {
		OuterItem outerItemQuery = new OuterItem();
		outerItemQuery.setItemId(item.getId());
		OuterItem outerItemDb = this.outerItemService.selectOne(outerItemQuery);
		if(outerItemDb!=null){
		    adapterDelistingItem(item, outerItemDb);
		    //更新外部商品状态
	        outerItemDb.setStatus(ItemStatus.DELISTING.getCode());
	        this.outerItemService.updateById(outerItemDb);
		}
	}
	
	public void syncDelistingSaleOutItems() {
		
	}
	
	public void syncSkuInventory(Long itemId,Long skuId) {
		//查询是否同步到有赞，并且为已上架状态
		OuterItem outerItemQuery = new OuterItem();
		outerItemQuery.setItemId(itemId);
		OuterItem outerItemDb = this.outerItemService.selectOne(outerItemQuery);
		if(outerItemDb==null){
//			throw new ErpCommonException("更新outerItem 订单信息错误;");
//			this.logger.info("没有找到对应的有赞商品，itemId:"+itemId);
		}
		else{
			//上架中的商品
			if(outerItemDb.getStatus()!=null&&outerItemDb.getStatus()==ItemStatus.LISTING.getCode()){
				OuterItemSku outerItemSkuQuery = new OuterItemSku();
				outerItemSkuQuery.setItemId(itemId);
				outerItemSkuQuery.setSkuId(skuId);
				OuterItemSku outerItemSkuDb = this.outerItemService.getSkuMapper().selectOne(outerItemSkuQuery);
				if(outerItemSkuDb!=null&&outerItemSkuDb.getOuterSkuId()!=null){
					Inventory inventory=inventoryService.queryInventoryBySkuId(itemId, skuId);
					//同步
					if(inventory!=null){
						adapterUpdateSkuInventory(outerItemSkuDb, inventory);
					}else{
//						this.logger.info("没有找到库存信息，skuId:"+skuId);
					}
				}else{
//					this.logger.info("没有找到对应的有赞sku，skuId:"+skuId);
				}
			}else{
//				this.logger.info("商品未上架不能同步库存，skuId:"+skuId);
			}
		}
	}
	
//	public void syncLogisticsOnlineConfirm(ErpOrder order, ShippingOrder shippingOrder) {
//		// 查出物流信息
//	}
	
}
