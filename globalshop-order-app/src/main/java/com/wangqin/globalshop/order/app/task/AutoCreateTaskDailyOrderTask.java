package com.wangqin.globalshop.order.app.task;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingOrderDO;
import com.wangqin.globalshop.order.app.service.shipping.kuaidi100.IKuaidi100Service;
import com.wangqin.globalshop.order.app.service.shipping.IShippingOrderService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 根据当前订单以及库存情况计算当前采购所需
 * @author liuyang
 *
 */
@Component
public class AutoCreateTaskDailyOrderTask {
	protected Logger logger = LogManager.getLogger(getClass());
//	@Autowired
//	private ITaskDailyOrderService taskDailyOrderService;
//	@Autowired
//	private ITaskDailyOrderHistoryService taskDailyOrderHistoryService;
//	@Autowired
//	private ITaskDailyService taskDailyService;
//	@Autowired
//	private ItemMapper itemMapper;
//	@Autowired
//	private IOuterItemService outerItemService;
//	@Autowired
//	private IHaihuService haiHuService;
	@Autowired
	private IShippingOrderService shippingOrderService;
//	@Autowired
//	private ITaskNoStockReportService taskNoStockReportService;
//	@Autowired
//	private ISiFangService siFangService;
////	@Autowired
//	private IFadRoadService fadRoadService;
//
//	@Autowired
//	private IOuterOrderService outerOrderService;
//	@Autowired
//	IErpOrderService erpOrderService;
	@Autowired
	private IKuaidi100Service kuaidi100Service;
	
//	//每天凌晨00:01:00 执行一次
//	@Scheduled(cron = "0 1 0 ? * *")
//	public void run() {
//		logger.info("定时任务：根据当前订单以及库存情况计算当前采购所需===>Start");
//		List<TaskDailyOrderHistory> taskDailyOrderListInsert =taskDailyOrderService.createTaskDailyOrder();
//		taskDailyOrderHistoryService.insertBatch(taskDailyOrderListInsert);
//		logger.info("定时任务：根据当前订单以及库存情况计算当前采购所需===>End");
//
//		//自动关闭那些过期的采购计划
//		logger.info("定时任务：自动关闭那些过期的采购计划===>Start");
//		taskDailyService.autoCloseTaskDaily();
//		logger.info("定时任务：自动关闭那些过期的采购计划===>End");
//
//		//自动同步商品到有赞
//		logger.info("定时任务：自动同步商品到有赞===>Start");
//		//sysAllItemSale();
//		logger.info("定时任务：自动同步商品到有赞===>End");
//
//		//每天凌晨跟新每日未备货的商品数量和未备货的商品sku 数量
//		logger.info("定时任务：定时任务插入统计每日未入库商品和sku统计====>Start");
//		taskNoStockReportService.insertTaskNoStockReport();
//		logger.info("定时任务：定时任务插入统计每日未入库商品和sku统计====>End");
//	}
//
//	/**
//	 * 未完成采购单的商品
//	 */
//	//每天凌晨00:30:00 执行一次
//	@Scheduled(cron = "0 45 23 ? * *")
//	public void uncompleteTaskDailyOrder() {
//		taskDailyOrderHistoryService.addNocomplestTaskDailyOrder();
//	}
	/**
	 * 订阅快递100
	 */
//	@Scheduled(cron = "0 12 0/1 * * ?")
//	@Scheduled(cron = "0 0/5 * * * ?")
	public void subscribeKuaidi100() {
        System.out.println("定时任务：订阅快递100===>Start");
        logger.info("定时任务：订阅快递100===>Start");
		// 查出需要订阅的国内物流
//		EntityWrapper<ShippingOrder> entityWrapper = new EntityWrapper<>();
//		entityWrapper.where("subscribe_kuaidi100={0} and logistic_no is not null", Kuaidi100Status.Need_Subscribe.getValue());
		List<ShippingOrderDO> shippingOrderList = shippingOrderService.selectByLogisticNoIsNotNull();
		
		for (ShippingOrderDO order : shippingOrderList) {
			try {
				kuaidi100Service.subscribe(order);
			} catch (Exception e) {
				logger.info("订阅快递100 异常！ shippingOrder：" + order.getShippingNo());
			}
		}
		
		logger.info("定时任务：订阅快递100===>End");
	}
	
	/**
	 * 查询快递100物流
	 */
//	@Scheduled(cron = "0 20 9,13,18 * * ?")
//	@Scheduled(cron = "0 0/1 * * * ?")
	public void queryKuaidi100Track() {
        System.out.println("定时任务：查询快递100物流轨迹===>Start");
		logger.info("定时任务：查询快递100物流轨迹===>Start");
		List<ShippingOrderDO> shippingOrderList = shippingOrderService.selectInOneMonth();
		
		for (ShippingOrderDO order : shippingOrderList) {
			try {
				kuaidi100Service.fetchTrackByShippingOrder(order);
			} catch (Exception e) {
				logger.info("查询快递100物流轨迹 异常！ shippingOrder：" + order.getShippingNo());
			}
		}
		
		logger.info("定时任务：查询快递100物流轨迹===>End");
	}
	
//	/**
//	 * 上架可售的商品，下架不可售的商品
//	 */
//	private void sysAllItemSale() {
//		//根据销售时间设置商品是否可售
//		itemMapper.updateItemSale();
//		itemMapper.updateItemNotSale();
//
//		//可售，新建状态
//		Item tjItem = new Item();
//		tjItem.setIsSale(1);
//		tjItem.setStatus(ItemStatus.INIT.getCode());
//		EntityWrapper<Item> entityWrapperInit = new EntityWrapper<Item>();
//		entityWrapperInit.setEntity(tjItem);
//		List<Item> itemListInit =  itemMapper.selectList(entityWrapperInit);
//		if(itemListInit.size()>0) {
//			itemListInit.forEach(item -> {
//				try {
//					outerItemService.synItemYouzan(item.getId());
//				} catch(exception e) {
//					logger.error("商品定时同步有赞ItemId:" + item.getId(), e);
//				}
//			});
//		}
//
//		//可售
//		tjItem = new Item();
//		tjItem.setIsSale(1);
//		//tjItem.setStatus(ItemStatus.DELISTING.getCode());
//		EntityWrapper<Item> entityWrapperDelisting = new EntityWrapper<Item>();
//		entityWrapperDelisting.setEntity(tjItem);
//		List<Item> itemListDelisting =  itemMapper.selectList(entityWrapperDelisting);
//		if(itemListDelisting.size()>0) {
//			itemListDelisting.forEach(item -> {
//				try {
//					outerItemService.listingYouzan(item);
//				} catch(exception e) {
//					logger.error("商品定时上架有赞ItemId:" + item.getId(), e);
//				}
//			});
//		}
//
//		//不可售，上架状态
//		tjItem = new Item();
//		tjItem.setIsSale(0);
//		tjItem.setStatus(ItemStatus.LISTING.getCode());
//		EntityWrapper<Item> entityWrapperListing = new EntityWrapper<Item>();
//		entityWrapperListing.setEntity(tjItem);
//		List<Item> itemListListing =  itemMapper.selectList(entityWrapperListing);
//		if(itemListListing.size()>0) {
//			itemListListing.forEach(item -> {
//				try {
//					outerItemService.delistingYouzan(item);
//				} catch(exception e) {
//					logger.error("商品定时下架有赞ItemId:" + item.getId(), e);
//				}
//			});
//		}
//	}
//	//每天凌晨00:18:15 执行一次暂时单号每天限制抓取2000条
//    //@Scheduled(cron = "0 15 18 * * ?")
//	public void runUpdateShippingTrack() {
//		logger.info("定时任务：更新写入国内物流运输轨迹===>Start");
//		List<CommonShippingTrack> shippingTrackList = shippingTrackMapper.queryStatus();
//		Map<String, String> categorymap = MapUtil.getCategoryKuaiDi();
//		String inlandExpressId = "";
//		for (int i = 0; i < shippingTrackList.size(); i++) {
//		if (StringUtil.isNotBlank(shippingTrackList.get(i).getInlandExpressId())
//					&& StringUtil.isNotBlank(shippingTrackList.get(i).getInlandExpressNo())
//					&& !shippingTrackList.get(i).getInlandExpressId().contains("haihu")) {
//				inlandExpressId = shippingTrackList.get(i).getInlandExpressId();
//				if (categorymap.get(inlandExpressId) != null) {
//					inlandExpressId = categorymap.get(inlandExpressId);
//				}
//
//				try {
//					String info = "";
//					if ("tiantian".equals(inlandExpressId) || "huitongkuaidi".equals(inlandExpressId)) {
//						info = ExpressUtil.callbackLogisticsTrajectory(inlandExpressId,
//								shippingTrackList.get(i).getInlandExpressNo());
//					} else {
//						info = ExpressUtil.getOrderTracesByJson(inlandExpressId,
//								shippingTrackList.get(i).getInlandExpressNo());
//					}
//					if (info.contains("签收")) {
//						shippingOrderMapper.updateStatusByShippingNo(shippingTrackList.get(i).getShippingNo());
//					}
//					shippingTrackMapper.updateInfo(info, shippingTrackList.get(i).getShippingNo());
//					Thread.sleep(10000);
//				} catch (exception e) {
//					logger.error("抓取物流轨迹定时任务(非海狐)", e);
//				}
//
//			}
//		}
//
//	}
	
//	// 预报转运仓
////	@Scheduled(cron = "0/30 * * * * ?")
//	@Scheduled(cron = "0 10 1/2 * * ?")
//	public void runPredictShippingOrder() {
//		logger.info("定时任务：预报转运===>Start");
//		System.out.println("定时任务：预报转运===>Start");
//		EntityWrapper<ShippingOrder> entityWrapper = new EntityWrapper<>();
//		entityWrapper.where("transfer_status < {0}", TransferStatus.PREDICTED.getValue());
//		List<ShippingOrder> list = shippingOrderService.selectList(entityWrapper);
//
//		if (CollectionUtils.isEmpty(list)) {
//			return;
//		}
//
//		for (ShippingOrder order : list) {
//			try {
//				if (order.getLogisticCompany().contains("四方") || order.getLogisticCompany().contains("4PX")) {
//					siFangService.createOrder(order.getId());
//				}
//			} catch (exception e) {
//				logger.error("四方转运预报异常 id：" + order.getId(), e);
//			}
//		}
//		System.out.println("定时任务：预报转运===>End");
//		logger.info("定时任务：预报转运===>End");
//	}
//
//    //抓取4px物流轨迹
////    @Scheduled(cron = "0 0/1 * * * ?")
//	@Scheduled(cron = "0 30 10,14 * * ?")
//   	public void runFourPxShippingTrack() {
//   		logger.info("定时任务：抓取4px物流轨迹===>Start");
//
//
//		List<ShippingOrder> Flist = shippingOrderService.queryAllFourPx();//所有走4PX的
//		if(CollectionUtils.isNotEmpty(Flist)){
//			for (ShippingOrder shippingOrder : Flist) {
//				try {
//					siFangService.shippingTrack(shippingOrder.getShippingNo());
////					siFangService.shippingTrack("1Z1Y84F50355689584");
//					Thread.sleep(1000);
//				} catch (exception e) {
//					logger.error("抓取物流轨迹定时任务(4PX)", e);
//				}
//			}
//		}
//   		logger.info("定时任务：抓取4px物流轨迹===>End");
//   	}
//
//	// 同步发货状态给渠道
//	@Scheduled(cron = "0 25 0/1 * * ?")
//	public void runSyncSendPackage() {
//		logger.info("定时任务：通知渠道，已经发货===>Start");
//
//		// 查询“未同步发货信息”的订单
//		List<ShippingOrder> shippingOrderList = shippingOrderService.queryNeedSyncSendPackage();
//		if (CollectionUtils.isNotEmpty(shippingOrderList)) {
//			for (ShippingOrder shippingOrder : shippingOrderList) {
//				try {
//					List<Long> erpOrderIdList = HaiJsonUtils.toBean(shippingOrder.getErpOrderId(), new TypeReference<List<Long>>() {
//					});
//
//					// 查出 erpOrder
//					List<ErpOrder> erpOrderList = erpOrderService.selectBatchIds(erpOrderIdList);
//
//					if (CollectionUtils.isEmpty(erpOrderList)) {
//						continue;
//					}
//
//					// 查出 outer_order
//					OuterOrder outerOrder = outerOrderService.selectById(erpOrderList.get(0).getOuterOrderId());
//
//					// 渠道
//					Integer platformType = outerOrder.getPlatformType();
//					ChannelType channelType = ChannelType.getChannelType(platformType);
//					if (channelType == null) {
//						shippingOrder.setSyncSendStatus(1);
//						shippingOrderService.update(shippingOrder);
//						continue;
//					}
//					// 同步给渠道
//					ChannelFactory.getChannel(outerOrder.getCompanyId(), channelType).syncLogisticsOnlineConfirm(erpOrderList, shippingOrder);
//
//					Thread.sleep(1000);
//				} catch (exception e) {
//					logger.error("通知渠道，已经发货 异常", e);
//				}
//			}
//		}
//
//   		logger.info("定时任务：通知渠道，已经发货===>End");
//	}
//
//
//
//    //抓取联邦物流轨迹
//    //@Scheduled(cron = "0 30 19 * * ?")
//   	public void runFadRoadPxShippingTrack() {
//   		logger.info("定时任务：更新写入国内物流运输轨迹===>Start");
//		List<ShippingOrder> Fadlist = shippingOrderService.queryAllFad();//所有联邦转运
//		if(CollectionUtils.isNotEmpty(Fadlist)){
//			for (ShippingOrder shippingOrder : Fadlist) {
//				try {
//					fadRoadService.shippingTrack(shippingOrder.getShippingNo());
//					Thread.sleep(1000);
//				} catch (exception e) {
//					logger.error("抓取物流轨迹定时任务(联邦转运)", e);
//
//				}
//			}
//		}
//		logger.info("定时任务：更新写入国内物流运输轨迹===>End");
//   	}

	public IKuaidi100Service getKuaidi100Service() {
		return kuaidi100Service;
	}

	public void setKuaidi100Service(IKuaidi100Service kuaidi100Service) {
		this.kuaidi100Service = kuaidi100Service;
	}
}
