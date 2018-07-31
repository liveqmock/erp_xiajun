package com.wangqin.globalshop.channel.service.channel;

import com.taobao.api.Constants;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.domain.Order;
import com.taobao.api.domain.Trade;
import com.taobao.api.domain.TradeReceiver;
import com.taobao.api.request.TradeListGetRequest;
import com.taobao.api.request.TradeReceiverGetRequest;
import com.taobao.api.response.TradeListGetResponse;
import com.taobao.api.response.TradeReceiverGetResponse;
import com.wangqin.globalshop.biz1.app.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.enums.OrderStatus;
import com.wangqin.globalshop.biz1.app.enums.PlatformType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.*;
import com.wangqin.globalshop.channelapi.dal.ItemVo;
import com.wangqin.globalshop.common.utils.CodeGenUtil;
import com.wangqin.globalshop.common.utils.DateUtil;
import com.wangqin.globalshop.inventory.app.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.*;

@Channel(type = ChannelType.TaoBao)
public class TaobaoChannelServiceImpl extends AbstractChannelService implements IChannelService{
	
	private DefaultTaobaoClient tbClient = null;
	
	private static final int mode = 1; // 服务商

	private Map<String, Long> expressMap = null;

	static Map<String, String> localExpressMap = new HashMap<String, String>();

	// 暂时硬编码
	static {
		localExpressMap.put("海淘一号仓", "167");
		// localExpressMap.put("顺帮快递", "167");
		localExpressMap.put("运通快递", "102");
		localExpressMap.put("顺丰", "42");
		localExpressMap.put("韵达", "4");
		localExpressMap.put("转运中国", "224");
		localExpressMap.put("邮客", "208");
		// localExpressMap.put("海狐", "167");
		localExpressMap.put("联邦转运", "27");
		localExpressMap.put("4PX", "51");
		// localExpressMap.put("海狐联邦转运", "167");
		// localExpressMap.put("GLS", "167");
	}
	@Autowired
	private InventoryService inventoryService;

	public TaobaoChannelServiceImpl(ChannelAccountDO channelAccount) {
		super(channelAccount);
		auth();
		tbClient = new DefaultTaobaoClient();		
	}
	
	
	private TradeReceiver getTradeReceiver(String tradeId) {
		TradeReceiverGetRequest receiverGetRequest = new TradeReceiverGetRequest();
		receiverGetRequest.setTid(Long.valueOf(tradeId));
		receiverGetRequest.setShopType(Constants.SHOP_TYPE_TAOBAO);		
		TradeReceiverGetResponse receiverGetResponse = null;
		try {
			receiverGetResponse = tbClient.execute(receiverGetRequest, channelAccount.getAppValue2());
		} catch (Exception e) {
			String error = receiverGetResponse == null ? "" : receiverGetResponse.getError();
			logger.error("get trade receiver error:"+error,e);	
		}
		return receiverGetResponse.getTradeReceiver();
	}
	
	@Override
	public void syncOrder() {
		
		//值为当前时间的1个小时前，因为定时任务设置为半个小时,这样每个订单会有2次抓取机会
		Date endCreated = new Date();
		Date startCreated = DateUtil.getDateByCalculate(endCreated, Calendar.HOUR_OF_DAY, -1);
		TradeListGetRequest tradeListGetRequest = new TradeListGetRequest();     
        tradeListGetRequest.setStatus("waitSend");        
        tradeListGetRequest.setStartCreated(startCreated);
        tradeListGetRequest.setEndCreated(endCreated);  
        
        boolean hasNext = true;
        int pageNo = 1;//从第1页开始，默认40个订单一页
        
        while(hasNext) {
        	tradeListGetRequest.setPageNo(pageNo);
        	TradeListGetResponse response = null;
            try {             	
     			response = tbClient.execute(tradeListGetRequest, channelAccount.getAppValue2());
     		} catch (Exception e) {
     			logger.error("get order list error"+response.getError(),e);			
     		}            
            List<Trade> trades = response.getTrades(); 
            
            hasNext = false;//暂时无法解决分页，也无法判断订单有没有完结的状态
            pageNo++;
            
			for (int i = trades.size() - 1; i >= 0; i--) {
				this.syncOrder(trades.get(i));
			}                       
        }              
        
	}
	
	void syncOrder(Trade trade) {
		if(trade == null) {
			return;
		}
		List<String> outOrderIdList = new ArrayList<>();

		// 如果订单已存在，略过
		MallOrderDO p = new MallOrderDO();
		p.setChannelOrderNo(trade.getId());
		if (outerOrderMapper.queryPoCount(p) > 0) {
			logger.error("订单已经存在 tid:" + trade.getId());
		}				
		TradeReceiver receiver = this.getTradeReceiver(trade.getId());
						
		// 订单头就这么点信息，明细不足够
		//trade.getActualFee();
		//trade.getCreateTime();
		//trade.getId();
		//trade.getNick();
		//trade.getStatus();	
		
		// 如果有赞订单还不存在，继续
		MallOrderDO outerOrder = new MallOrderDO();
		
		Date createTime=null;
		try {
			createTime = DateUtil.convertStr2Date(trade.getCreateTime(),DateUtil.DATE_PARTEN_YYYYMMDDHHMMSS);
		} catch (ParseException e) {			
			e.printStackTrace();
		}

		outerOrder.setCompanyNo(channelAccount.getCompanyNo());
		outerOrder.setChannelNo(channelAccount.getChannelNo());
		outerOrder.setChannelName(channelAccount.getChannelName());
		outerOrder.setChannelType(channelAccount.getType().toString());
		outerOrder.setShopCode(channelAccount.getShopCode());


		outerOrder.setOrderNo(CodeGenUtil.getOrderNo()); // 系统自动生成
		outerOrder.setOrderTime(createTime); // 付款时间
		outerOrder.setStatus(OrderStatus.INIT.getCode()); // 状态：新建

		outerOrder.setChannelOrderNo(trade.getId()); // 有赞平台交易编号
		outerOrder.setChannelNo(PlatformType.TAOBAO.getDescription()); // 销售来源(平台)

		outerOrder.setCreator("淘宝task自动抓取订单"); // 创建者
		outerOrder.setGmtCreate(createTime); // 创建时间
		outerOrder.setGmtModify(new Date()); // 修改时间
		outerOrderMapper.insertSelective(outerOrder); // 添加主订单
		outOrderIdList.add(outerOrder.getOrderNo()); // 收集主订单ID
					
		List<Order> tradeDetals = trade.getOrders();
		
		List<MallSubOrderDO> outerOrderDetails = new ArrayList<MallSubOrderDO>();
		for (Order order : tradeDetals) {
									
//			order.getPrice();//单价
//			order.getQty();//数量
//			order.getSkuNo();//商品编码
//			order.getSkuText();//商品规格
//			order.getTitle();//商品名称
									
			MallSubOrderDO outerOrderDetail = new MallSubOrderDO();
			outerOrderDetail.setCompanyNo(channelAccount.getCompanyNo());
			outerOrderDetail.setChannelOrderNo(outerOrder.getChannelOrderNo()); // 主订单ID
			outerOrderDetail.setSkuCode(order.getSkuNo()); // sku编码
			outerOrderDetail.setSalePrice(Double.parseDouble(order.getPrice())); // 商品单价
			outerOrderDetail.setQuantity(Integer.parseInt(order.getQty())); // 购买数量
			outerOrderDetail.setGmtCreate(createTime); // 创建时间
			outerOrderDetail.setGmtModify(new Date()); // 修改时间



			outerOrderDetail.setReceiver(receiver.getReceiverName()); // 收货人
			outerOrderDetail.setReceiverState(receiver.getReceiverState()); // 省
			outerOrderDetail.setReceiverCity(receiver.getReceiverCity()); // 市
			outerOrderDetail.setReceiverDistrict(receiver.getReceiverCounty()); // 区
			outerOrderDetail.setReceiverAddress(receiver.getReceiverAddress()); // 详细地址
			outerOrderDetail.setTelephone(receiver.getReceiverMobile()); // 联系电话
			outerOrderDetail.setPostcode(receiver.getReceiverZip()); // 邮编


			outerOrderDetail.setShopCode(channelAccount.getShopCode());
			outerOrderDetail.setOrderNo(outerOrder.getOrderNo());
			outerOrderDetail.setSubOrderNo(CodeGenUtil.getSubOrderNo());

			outerOrderDetails.add(outerOrderDetail);

//			// 如果有虚拟库存就扣减虚拟库存
//			ItemSkuDO tjItemSku = new ItemSkuDO();
//			tjItemSku.setSkuCode(order.getSkuNo());
//			ItemSkuDO itemSku = itemSkuService.queryPo(tjItemSku);
//			if (itemSku != null) {
//				InventoryDO inventory = inventoryService.selectByItemCodeAndSkuCode(itemSku.getItemCode(), itemSku.getSkuCode());
//				if (inventory.getVirtualInv() > 0) {
//					Long totalAvailableInv= inventory.getInv()-inventory.getLockedInv()+inventory.getTransInv()-inventory.getLockedTransInv();
//					Long virtualInv = inventory.getVirtualInv() - outerOrderDetail.getQuantity();
//					virtualInv = virtualInv > 0 ? virtualInv : 0;
//					// 如果虚拟库存小于等于可售库存，虚拟库存清零
//					virtualInv = virtualInv > totalAvailableInv ? virtualInv : 0;
//
//					// 如果虚拟占用库存大于零，有赞下单不应该减少虚拟预扣
//					/*
//					 * if(inventory.getLockedVirtualInv() > 0) { int lockedVirtualInv =
//					 * inventory.getLockedVirtualInv() - outerOrderDetail.getQuantity();
//					 * lockedVirtualInv = lockedVirtualInv>0 ? lockedVirtualInv : 0;
//					 * inventory.setLockedVirtualInv(lockedVirtualInv); }
//					 */
//					inventory.setVirtualInv(virtualInv);
//					inventory.setGmtModify(new Date());
					inventoryService.order(outerOrderDetail);
//				}
//			}
		}
		mallSubOrderService.insertBatch(outerOrderDetails); // 添加子订单

		if (outOrderIdList.size() > 0) {
			// 把商品详情更新到主订单明细里面
			outerOrderDetailMapper.updateOuterOrderDetailByItemSku(outOrderIdList);
//			// 生成子订单并配货
//			outerOrderService.reviewByIdList(outOrderIdList);
		}
	}
	
	
	
	@Override
	public AdapterData adapterAuth() {

//		String urlresponse = DimensionCodeUtil.sendGet("https://open.youzan.com/oauth/token",
//				"client_id=" + this.channelAccount.getAppKey() + "&client_secret=" + this.channelAccount.getAppSecret()
//						+ "&grant_type=silent&kdt_id=" + this.channelAccount.getAppValue1());
//
//		JSONObject jsonObject = JSONObject.fromObject(urlresponse);
//		String youZanToken = jsonObject.getString("access_token");
//
//		this.channelAccount.setAccessToken(youZanToken);

		return null;
	}

	

	/**
	 * 商品创建到平台接口
	 */
	@Override
	public AdapterData adapterCreateItem(ItemVo item) {
		// 构造上传的商品对象
//		YouzanItemCreateParams youzanItemCreateParams = createYouzanAddItem(item);
//		YouzanItemCreateResult youzanItemCreateResult = youzanItemCreate(youzanItemCreateParams);
//
//		item.setImageIds(youzanItemCreateParams.getImageIds());		
//		return addOuterItem(item, youzanItemCreateResult);
		
		AdapterData adapterData = new AdapterData();
		return adapterData;
	}

	/**
	 * 商品更新到平台接口
	 */
	@Override
	public AdapterData adapterUpdateItem(ItemVo item, ChannelListingItemDO outerItem) {
//		YouzanItemUpdateParams youzanItemUpdateParams = createYouzanUpdateItem(item);
//		youzanItemUpdateParams.setItemId(outerItem.getOuterId());
//		youzanItemUpdateParams.setRemoveImageIds(outerItem.getImageIds());
//		YouzanItemUpdateResult youzanItemUpdateResult = youzanItemUpdate(youzanItemUpdateParams);
//
//		item.setImageIds(youzanItemUpdateParams.getImageIds());
//		updateOuterItem(item, youzanItemUpdateResult);
		return null;
	}

	/**
	 * 商品上架接口
	 */
	@Override
	public AdapterData adapterListingItem(ItemVo item, ChannelListingItemDO outerItem) {
//		YouzanItemUpdateListingParams youzanItemUpdateListingParams = new YouzanItemUpdateListingParams();
//		youzanItemUpdateListingParams.setItemId(outerItem.getOuterId());
//		youzanItemUpdateListing(youzanItemUpdateListingParams);
		return null;
	}

	
	/**
	 * 商品下架接口
	 */
	@Override
	public AdapterData adapterDelistingItem(ItemVo item, ChannelListingItemDO outerItem) {
//		YouzanItemUpdateDelistingParams youzanItemUpdateDelistingParams = new YouzanItemUpdateDelistingParams();
//		youzanItemUpdateDelistingParams.setItemId(outerItem.getOuterId());
//		youzanItemUpdateDelisting(youzanItemUpdateDelistingParams);
		return null;
	}

	/**
	 * 同步商品库存接口
	 */
	@Override
	public AdapterData adapterUpdateSkuInventory(ChannelListingItemSkuDO sku, InventoryDO inventory) {
//		YouzanItemSkuUpdateParams youzanItemSkuUpdateParams = new YouzanItemSkuUpdateParams();
//		youzanItemSkuUpdateParams.setItemId(sku.getOuterId());
//		youzanItemSkuUpdateParams.setSkuId(sku.getOuterSkuId());
//		youzanItemSkuUpdateParams.setQuantity(inventory.getSysInventory() + "");
//		youzanItemSkuUpdate(youzanItemSkuUpdateParams);
		return null;
	}

	@Override
	public void syncItem(HttpServletRequest request, HttpServletResponse respose) throws Exception {
		throw new Exception("有赞 不支持此方法");
	}

	@Override
	public void syncOrder(HttpServletRequest request, HttpServletResponse respose) throws Exception {
	}
	@Override
	public Object syncOrder(Object data) {
		return null;
	}


    @Override
    public void syncLogisticsOnlineConfirm(List<MallSubOrderDO> erpOrderList, ShippingOrderDO shippingOrder) {
        // TODO Auto-generated method stub
        
    }
	
	/**
	 * 发货接口
	 */
//	@Override
//	public void syncLogisticsOnlineConfirm(List<MallSubOrderDO> orderList, ShippingOrderDO shippingOrder) {
//		logger.error("发货");
//		
//		boolean hasFailed = false;
//		for (MallSubOrderDO order : orderList) {
//			try {
//				// 获取第三方订单
//				String tid = order.getChannelOrderNo();
//				// 获取物流信息
//				String logisticNo = shippingOrder.getShippingNo();
//				String logisticCompany = shippingOrder.getLogisticCompany();
//
//				// 调用"商家已发货"
//				String expressType = null;
//				if (logisticCompany != null) {
//					expressType = localExpressMap.get(logisticCompany);
//				}
//				if (expressType == null) {
//					expressType = "1";
//				}
////				YouzanLogisticsOnlineConfirmParams youzanLogisticsOnlineConfirmParams = new YouzanLogisticsOnlineConfirmParams();
////
////				youzanLogisticsOnlineConfirmParams.setTid(tid);
////				youzanLogisticsOnlineConfirmParams.setOutStype(expressType);
////				youzanLogisticsOnlineConfirmParams.setOutSid(logisticNo);
////				// youzanLogisticsOnlineConfirmParams.setOids("1440273929715322794");
////
////				YouzanLogisticsOnlineConfirm youzanLogisticsOnlineConfirm = new YouzanLogisticsOnlineConfirm();
////				youzanLogisticsOnlineConfirm.setAPIParams(youzanLogisticsOnlineConfirmParams);
////				YouzanLogisticsOnlineConfirmResult result = yzClient.invoke(youzanLogisticsOnlineConfirm);
//				
////				if (!hasFailed && !result.getIsSuccess()) {
////					hasFailed = true;
////					this.logger.error("同步发货给 有赞 返回结果异常：" + result.toString());
////				}
//			} catch (exception e) {
//				hasFailed = true;
//				logger.error("淘宝发货异常: ", e);
//			}
//		}
//
//		if (!hasFailed) {
//			try {
//				shippingOrder.setSyncSendStatus(1);
//				shippingOrderService.updateByPrimaryKey(shippingOrder);
//			} catch (exception e) {
//				this.logger.error("同步发货给 淘宝 返回结果异常");
//			}
//		}
//	}

}
