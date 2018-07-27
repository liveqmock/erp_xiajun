package com.wangqin.globalshop.channel.task;

import com.fasterxml.jackson.core.type.TypeReference;
import com.wangqin.globalshop.biz1.app.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingOrderDO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ChannelAccountSo;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallSubOrderMapperExt;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ShippingOrderVO;
import com.wangqin.globalshop.channel.service.channel.ChannelFactory;
import com.wangqin.globalshop.channel.service.channelAccount.IChannelAccountService;
import com.wangqin.globalshop.channel.service.order.ChannelIMallOrderService;
import com.wangqin.globalshop.channel.service.order.ChannelIShippingOrderService;
import com.wangqin.globalshop.common.utils.HaiJsonUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 同步发货状态给渠道,查看那些发货失败的订单，进行再次补偿发货到各个平台
 * @author liuyang
 *
 */
@Component
public class AutoRepairFeedBackOrderTask {
	protected Logger logger = LogManager.getLogger(getClass());

	@Autowired
	private ChannelIShippingOrderService shippingOrderService;

	@Autowired
	private ChannelIMallOrderService outerOrderService;


	@Autowired
	private MallSubOrderMapperExt mallSubOrderDOMapperExt;

	@Autowired
	private IChannelAccountService channelAccountService;


	
	// 同步发货状态给渠道
	@Scheduled(cron = "0 25 0/1 * * ?")
	public void runSyncSendPackage() {
		logger.info("定时任务：通知渠道，已经发货===>Start");
		
		// 查询“未同步发货信息”的订单
		ShippingOrderVO shippingOrderVO = new ShippingOrderVO();
		shippingOrderVO.setSyncSendStatus(0);
		List<ShippingOrderDO> shippingOrderList = shippingOrderService.queryShippingOrders(shippingOrderVO);
		if (CollectionUtils.isNotEmpty(shippingOrderList)) {
			for (ShippingOrderDO shippingOrder : shippingOrderList) {
				try {					
					List<Long> malSubOrderIdList = HaiJsonUtils.toBean(shippingOrder.getMallOrders(), new TypeReference<List<Long>>() {
					});

					// 查出 erpOrder
					List<MallSubOrderDO> erpOrderList = mallSubOrderDOMapperExt.queryByOrderId(malSubOrderIdList);
					
					if (CollectionUtils.isEmpty(erpOrderList)) {
						continue;
					}
					
					// 查出 outer_order
					MallOrderDO so = new MallOrderDO();
					so.setOrderNo(erpOrderList.get(0).getOrderNo());
					MallOrderDO outerOrder = outerOrderService.queryPo(so);

					// 渠道
					ChannelAccountSo caSo = new ChannelAccountSo();
					so.setChannelNo(outerOrder.getChannelNo());
					so.setShopCode(outerOrder.getShopCode());
					ChannelAccountDO channelAccountDO = channelAccountService.queryPo(caSo);


					ChannelType channelType = ChannelType.getChannelType(channelAccountDO.getType());
					if (channelType == null) {
						shippingOrder.setSyncSendStatus(1);
						shippingOrderService.updateByPrimaryKey(shippingOrder);
						continue;
					}
					// 同步给渠道
					ChannelFactory.getChannel(channelAccountDO).syncLogisticsOnlineConfirm(erpOrderList, shippingOrder);
					
					Thread.sleep(1000);
				} catch (Exception e) {
					logger.error("通知渠道，已经发货 异常", e);
				}
			}
		}
		
   		logger.info("定时任务：通知渠道，已经发货===>End");
	}
	
	
	

}
