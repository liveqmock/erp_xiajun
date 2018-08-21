package com.wangqin.globalshop.channel.task;

import com.fasterxml.jackson.core.type.TypeReference;
import com.wangqin.globalshop.biz1.app.dal.dataObject.*;
import com.wangqin.globalshop.biz1.app.dal.mapper.JdLogisticsDOMapper;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallSubOrderMapperExt;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ShippingOrderVO;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.service.channel.ChannelShopService;
import com.wangqin.globalshop.channel.service.jingdong.JdLogisticsService;
import com.wangqin.globalshop.channel.service.jingdong.JdShopOauthService;
import com.wangqin.globalshop.channel.service.jingdong.SendStatus;
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
public class AutoFeedBackOrderTask {
	protected Logger logger = LogManager.getLogger("AutoFeedBackOrderTask");

	@Autowired
	private ChannelIShippingOrderService shippingOrderService;

	@Autowired
	private MallSubOrderMapperExt mallSubOrderDOMapperExt;

	@Autowired
	private ChannelCommonService channelCommonService;

	@Autowired
	private ChannelShopService channelShopService;

	@Autowired
	private JdLogisticsService logisticsService;

	//@Scheduled(cron = "0 2/10 * * * ? ")
	@Scheduled(cron = "10 1/10 * * * ?")
	public void runSyncSendPackage() {
		logger.info("shipping order task start");
		
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
						channelShopSo.setOpen(true);
						ChannelShopDO channelShopDO = channelShopService.searchShop(channelShopSo);
						if(channelShopDO == null){
							continue;
						}
						// 同步给渠道
						//channelCommonService.syncLogistics2Channel(channelOrderListMap.get(channelOrderNo), shippingOrder);
						//todo,未处理修改运单号，二次发货，多次发货问题
						logisticsService.dealLogistics(channelShopDO, channelOrderListMap.get(channelOrderNo), shippingOrder);
					}

				} catch (ErpCommonException e){
					success = false;
					shippingOrder.setMemo(EasyUtil.truncateLEFitSize(shippingOrder.getMemo()+"-"+e.getErrorMsg(),1000));
					logger.error("shipping order task error", e);
				}catch (Exception e) {
					success = false;
					shippingOrder.setMemo(EasyUtil.truncateLEFitSize(shippingOrder.getMemo()+"-"+e.getMessage(),1000));
					logger.error("shipping order task error", e);
				}
				if(success){
					shippingOrder.setSyncSendStatus(1);
					shippingOrderService.updateByPrimaryKey(shippingOrder);
				}else {
					shippingOrderService.updateByPrimaryKey(shippingOrder);
				}
			}
		}
   		logger.info("shipping order task end");
	}


	@Scheduled(cron = "20 2/5 * * * ?")
	public void feedBackOrder() {
		logger.info("feed back order task start");
		JdLogisticsDO logisticsSo = new JdLogisticsDO();
		logisticsSo.setSendStatus(SendStatus.REQUEST);
		logisticsSo.setIsDel(false);
		List<JdLogisticsDO> logisticsDOList = logisticsService.queryPoList(logisticsSo);
		for (JdLogisticsDO requestLogistic : logisticsDOList) {
			Boolean success = true;
			String errorMsg = "";
			try {
				if (EasyUtil.isStringEmpty(requestLogistic.getChannelOrderNo()) || EasyUtil
						.isStringEmpty(requestLogistic.getShopCode())) {
					errorMsg += "channelOrderNo or shopcode emptity";
					success = false;
				}
				if (success) {
					channelCommonService.feedbackOrder(requestLogistic);
				}
			} catch (ErpCommonException e) {
				success = false;
				errorMsg += e.getErrorCode() + ":" + e.getErrorMsg();
				requestLogistic.setErrormsg(EasyUtil.truncateLEFitSize(errorMsg, 1000));
				logger.error("feed back order task error", e);
			} catch (Exception e) {
				success = false;
				errorMsg += e.getMessage();
				requestLogistic.setErrormsg(EasyUtil.truncateLEFitSize(errorMsg, 1000));
				logger.error("feed back order task error", e);
			}
			if (success) {
				requestLogistic.setSendStatus(SendStatus.SUCCESS);
				logisticsService.updateByPrimaryKey(requestLogistic);
			} else {
				requestLogistic.setSendStatus(SendStatus.FAILURE);
				logisticsService.updateByPrimaryKey(requestLogistic);
			}
		}
		logger.info("feed back order task end");
	}


	@Scheduled(cron = "30 3/10 * * * ?")
	public void feedBackOrderFailure() {
		logger.info("feed back order failure task start");
		JdLogisticsDO logisticsSo = new JdLogisticsDO();
		logisticsSo.setSendStatus(SendStatus.FAILURE);
		logisticsSo.setIsDel(false);
		List<JdLogisticsDO> logisticsDOList = logisticsService.queryPoList(logisticsSo);
		if (CollectionUtils.isNotEmpty(logisticsDOList)) {
			for (JdLogisticsDO requestLogistic : logisticsDOList) {
				Boolean success = true;
				String errorMsg = "";
				try {
					if(EasyUtil.isStringEmpty(requestLogistic.getChannelOrderNo()) || EasyUtil.isStringEmpty(requestLogistic.getShopCode())){
						errorMsg += "channelOrderNo or shopcode emptity";
						success = false;
					}
					if(success){
						channelCommonService.feedbackOrder(requestLogistic);
					}
				} catch (ErpCommonException e){
					success = false;
					errorMsg += e.getErrorCode()+":"+e.getErrorMsg();
					requestLogistic.setErrormsg(EasyUtil.truncateLEFitSize(errorMsg,1000));
					logger.error("feed back order failure task error", e);
				}catch (Exception e) {
					success = false;
					errorMsg += e.getMessage();
					requestLogistic.setErrormsg(EasyUtil.truncateLEFitSize(errorMsg,1000));
					logger.error("feed back order failure task error", e);
				}
				if(success){
					requestLogistic.setSendStatus(SendStatus.SUCCESS);
					logisticsService.updateByPrimaryKey(requestLogistic);
				}else {
					requestLogistic.setSendStatus(SendStatus.STOP);
					logisticsService.updateByPrimaryKey(requestLogistic);
				}
			}
		}
		logger.info("feed back order task end");
	}
	
	
	

}
