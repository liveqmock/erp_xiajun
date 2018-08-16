package com.wangqin.globalshop.channel.task;

import com.fasterxml.jackson.core.type.TypeReference;
import com.wangqin.globalshop.biz1.app.dal.dataObject.*;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallSubOrderMapperExt;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ShippingOrderVO;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.service.channel.ChannelShopService;
import com.wangqin.globalshop.channel.service.order.ChannelIShippingOrderService;
import com.wangqin.globalshop.channelapi.service.ChannelCommonService;
import com.wangqin.globalshop.common.utils.EasyUtil;
import com.wangqin.globalshop.common.utils.HaiJsonUtils;
import org.apache.bcel.generic.DALOAD;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 异步通知有赞，海狐发货,每小时发货一次
 * @author liuyang
 *
 */
@Component
public class AutoRepairFeedBackOrderTask {
	protected Logger logger = LogManager.getLogger("AutoRepairFeedBackOrderTask");

	@Autowired
	private ChannelIShippingOrderService shippingOrderService;

	@Autowired
	private MallSubOrderMapperExt mallSubOrderDOMapperExt;

	@Autowired
	private ChannelCommonService channelCommonService;


	@Autowired
	private ChannelShopService channelShopService;


	
	//每小时一次
	@Scheduled(cron = "0 2/10 * * * ? ")
	//@Scheduled(cron = "0/30 * * * * ?")
	public void runSyncSendPackage() {
		logger.info("定时任务：通知渠道，已经发货===>Start");
		
		// 查询“未同步发货信息”的订单
		ShippingOrderVO shippingOrderVO = new ShippingOrderVO();
		shippingOrderVO.setSyncSendStatus(0);
		List<ShippingOrderDO> shippingOrderList = shippingOrderService.queryNotSyncShippingOrders(shippingOrderVO);
		if (CollectionUtils.isNotEmpty(shippingOrderList)) {
			for (ShippingOrderDO shippingOrder : shippingOrderList) {

				Boolean success = true;

				try {

					if(EasyUtil.isStringEmpty(shippingOrder.getMallOrders())){
						continue;
					}

					List<String> malSubOrderNoList = new ArrayList<>();

					String[] malSubOrderNoArray  = shippingOrder.getMallOrders().split(",");
                    for(int i=0; i < malSubOrderNoArray.length; i++ ){
						malSubOrderNoList.add(malSubOrderNoArray[i]);
					}

					// 查出 erpOrder
					List<MallSubOrderDO> erpOrderList = mallSubOrderDOMapperExt.queryByOrderNoList(malSubOrderNoList);
					
					if (CollectionUtils.isEmpty(erpOrderList)) {
						continue;
					}

					//按照订单分配
					Map<String,List<MallSubOrderDO>> channelOrderListMap = new HashMap<>();
					for (MallSubOrderDO mallSubOrderDO : erpOrderList) {
						if(EasyUtil.isStringEmpty(mallSubOrderDO.getChannelOrderNo())){
							logger.info("子订单号，sub_Order_no:" +mallSubOrderDO.getSubOrderNo()+"  不存在，非渠道订单,忽略");
							continue;
						}
						if(channelOrderListMap.get(mallSubOrderDO.getChannelOrderNo()) == null){
							List<MallSubOrderDO> mallSubOrderDOS = new ArrayList<>();
							mallSubOrderDOS.add(mallSubOrderDO);
							channelOrderListMap.put(mallSubOrderDO.getChannelOrderNo(),mallSubOrderDOS);
						}else {
							channelOrderListMap.get(mallSubOrderDO.getChannelOrderNo()).add(mallSubOrderDO);
						}
					}

                    for(String channelOrderNo : channelOrderListMap.keySet()){

						//如果没有这个渠道的shopCode,拦截掉
						MallSubOrderDO exampleSubOrder = channelOrderListMap.get(channelOrderNo).get(0);
						ChannelShopDO channelShopSo = new ChannelShopDO();
						channelShopSo.setShopCode(exampleSubOrder.getShopCode());
						channelShopSo.setIsDel(false);
						ChannelShopDO channelShopDO = channelShopService.searchShop(channelShopSo);
						if(channelShopDO == null){
							continue;
						}
						// 同步给渠道
						channelCommonService.syncLogistics2Channel(channelOrderListMap.get(channelOrderNo), shippingOrder);

						//todo,如果同一个运单号，一个是海狐，一个是有赞，一个成功，一个失败，shipping_order不知道是设置成功还是失败
					}

				} catch (Exception e) {
					success = false;
					shippingOrder.setMemo(EasyUtil.truncateLEFitSize(shippingOrder.getMemo()+"-"+e.getMessage(),1000));
					logger.error("通知渠道，已经发货 异常", e);
				}
				if(success){
					shippingOrder.setSyncSendStatus(1);
					shippingOrderService.updateByPrimaryKey(shippingOrder);
				}else {
					shippingOrderService.updateByPrimaryKey(shippingOrder);
				}
			}
		}
		
   		logger.info("定时任务：通知渠道，已经发货===>End");
	}
	
	
	

}
