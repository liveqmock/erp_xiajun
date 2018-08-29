package com.wangqin.globalshop.channel.task.imTask;

import com.wangqin.globalshop.biz1.app.dal.dataObject.JdOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopConfigDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;
import com.wangqin.globalshop.biz1.app.enums.AccountConfigKey;
import com.wangqin.globalshop.biz1.app.enums.ChannelType;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.service.jingdong.JdOrderService;
import com.wangqin.globalshop.channel.service.jingdong.JdShopConfigService;
import com.wangqin.globalshop.channel.service.jingdong.JdShopOauthService;
import com.wangqin.globalshop.channel.service.jingdong.SendStatus;
import com.wangqin.globalshop.channelapi.service.ChannelCommonService;
import com.wangqin.globalshop.common.utils.DateUtil;
import com.wangqin.globalshop.common.utils.EasyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;

/**
 * Create by 777 on 2018/8/28
 */
public class AutoIMOrderTask {

	private static Logger logger = LoggerFactory.getLogger("Autointra mirrorOrderTask");

	@Autowired
	private ChannelCommonService channelCommonService;

	@Autowired
	private JdShopOauthService shopOauthService;

	@Autowired
	private JdShopConfigService jdShopConfigService;


	@Autowired
	private JdOrderService jdOrderService;


	private final static int internalTime = 30 * 1000;//延后30秒

	private final static Long startEndMaxInternalDay = 5*24*60*60*1000L;//查询间隔最大5天

	// 每隔半小时执行一次
	//@Scheduled(cron = "0/30 * * * * ?")
//	@Scheduled(cron = "40 0/4 * * * ?")
//	public void run() {
//
//		logger.info("intra mirror get orders Start");
//		Long startTime = System.currentTimeMillis();
//
//		JdShopOauthDO shopOauthSo = new JdShopOauthDO();
//		shopOauthSo.setChannelNo(ChannelType.intra mirror.getValue()+"");
//		shopOauthSo.setOpen(true);
//		List<JdShopOauthDO> shopOauthDOList = shopOauthService.searchShopOauthList(shopOauthSo);
//
//		Long shopCount= 0L;
//		JdShopConfigDO configso = new JdShopConfigDO();
//		configso.setConfigKey(AccountConfigKey.LAST_TRADES_GET_TIME.getDescription());
//
//		for (JdShopOauthDO shopOauth : shopOauthDOList) {
//
//			configso.setShopCode(shopOauth.getShopCode());
//			JdShopConfigDO jdShopConfigDO = jdShopConfigService.searchShopConfig(configso);
//
//			if(jdShopConfigDO == null || jdShopConfigDO.getConfigKey() == null){
//				jdShopConfigService.initShopConfig(ChannelType.intra mirror, shopOauth);
//				break;
//			}
//
//			String beginValue = jdShopConfigDO.getConfigValue();
//
//			Date beginTime = DateUtil.parseDate(beginValue);
//
//			Date endTime =  new Date(System.currentTimeMillis() - internalTime);
//
//			if(beginTime.after(endTime)){
//				break;
//			}
//
//			if(endTime.getTime() - beginTime.getTime() > startEndMaxInternalDay){
//				endTime = new Date(beginTime.getTime()+startEndMaxInternalDay);
//			}
//
//			logger.info("intra mirror get orders begin time: " + DateUtil.convertDate2Str(beginTime,DateUtil.formateStr19));
//			logger.info("intra mirror get orders end time: " + DateUtil.convertDate2Str(endTime,DateUtil.formateStr19));
//
//
//			Boolean success = true;
//
//			try {
//				channelCommonService.getOrders(shopOauth.getShopCode(),beginTime,endTime);
//				shopCount++;
//			} catch (ErpCommonException e) {
//				success = false;
//				logger.error("intra mirror get orders error:",e);
//			} catch (Exception e) {
//				success = false;
//				logger.error("intra mirror get orders error, shopCode: " + shopOauth.getShopCode(), e);
//			}
//			if(success){
//				String endValue = DateUtil.convertDate2Str(endTime,DateUtil.formateStr19);
//				jdShopConfigDO.setConfigValue(endValue);
//				jdShopConfigService.updateByPrimaryKey(jdShopConfigDO);
//			}
//		}
//		Long endTime = System.currentTimeMillis();
//		logger.info("intra mirror get orders end, use time:" + (endTime - startTime) +" ms. shopCount: " + shopCount);
//	}


	/**
	 * 根据get状态抓取订单详情
	 */
	@Scheduled(cron = "50 5/4 * * * ?")
	public void getOrder() {

		logger.info("intra mirror send request orders start");
		Long startTime = System.currentTimeMillis();

		JdShopOauthDO shopOauthSo = new JdShopOauthDO();
		shopOauthSo.setChannelNo(ChannelType.IntraMirror.getValue()+"");
		shopOauthSo.setOpen(true);
		List<JdShopOauthDO> shopOauthDOList = shopOauthService.searchShopOauthList(shopOauthSo);

		Long shopCount = 0L;

		for (JdShopOauthDO shopOauth : shopOauthDOList) {

			JdOrderDO jdOrderSo = new JdOrderDO();
			jdOrderSo.setSendStatus(SendStatus.GET);
			jdOrderSo.setShopCode(shopOauth.getShopCode());
			jdOrderSo.setChannelNo(shopOauth.getChannelNo());

			List<JdOrderDO> getJdOrderIList = jdOrderService.searchJdOrderList(jdOrderSo);
			Boolean success = true;

			for(JdOrderDO getJdOrder : getJdOrderIList){

				String errorMsg = "";
				try {
					channelCommonService.getOrder(shopOauth.getShopCode(),getJdOrder);
					shopCount++;
				} catch (ErpCommonException e) {
					success = false;
					errorMsg += e.getErrorMsg();
					logger.error("intra mirror send get Orders error:",e);
				} catch (Exception e) {
					success = false;
					errorMsg += e.getMessage();
					logger.error("intra mirror send get Orders error, shopCode: " + shopOauth.getShopCode(), e);
				}

				if(success){
					getJdOrder.setSendStatus(SendStatus.REQUEST);
					jdOrderService.updateByPrimaryKey(getJdOrder);
				}else {
					getJdOrder.setSendStatus(SendStatus.FAILURE);
					getJdOrder.setErrorMassge(EasyUtil.truncateLEFitSize(errorMsg,1000));
					jdOrderService.updateByPrimaryKey(getJdOrder);
				}
			}
		}
		Long endTime = System.currentTimeMillis();
		logger.info("intra mirror send request Orders end, use time:" + (endTime - startTime) +" ms. shopCount: " + shopCount);
	}


	/**
	 * 下发抓到的REQUEST订单至globalshop
	 */
	@Scheduled(cron = "50 5/4 * * * ?")
	public void sendRequestOrder2erp() {

		logger.info("intra mirror send request orders start");
		Long startTime = System.currentTimeMillis();

		JdShopOauthDO shopOauthSo = new JdShopOauthDO();
		shopOauthSo.setChannelNo(ChannelType.IntraMirror.getValue()+"");
		shopOauthSo.setOpen(true);
		List<JdShopOauthDO> shopOauthDOList = shopOauthService.searchShopOauthList(shopOauthSo);

		Long shopCount = 0L;

		for (JdShopOauthDO shopOauth : shopOauthDOList) {

			JdOrderDO jdOrderSo = new JdOrderDO();
			jdOrderSo.setSendStatus(SendStatus.REQUEST);
			jdOrderSo.setShopCode(shopOauth.getShopCode());
			jdOrderSo.setChannelNo(shopOauth.getChannelNo());

			List<JdOrderDO> requestJdOrderIList = jdOrderService.searchJdOrderList(jdOrderSo);
			Boolean success = true;

			for(JdOrderDO requestJdOrder : requestJdOrderIList){

				String errorMsg = "";
				try {
					channelCommonService.sendOrder(shopOauth.getShopCode(),requestJdOrder);
					shopCount++;
				} catch (ErpCommonException e) {
					success = false;
					errorMsg += e.getErrorMsg();
					logger.error("intra mirror send request Orders error:",e);
				} catch (Exception e) {
					success = false;
					errorMsg += e.getMessage();
					logger.error("intra mirror send request Orders error, shopCode: " + shopOauth.getShopCode(), e);
				}

				if(success){
					requestJdOrder.setSendStatus(SendStatus.SUCCESS);
					jdOrderService.updateByPrimaryKey(requestJdOrder);
				}else {
					requestJdOrder.setSendStatus(SendStatus.FAILURE);
					requestJdOrder.setErrorMassge(EasyUtil.truncateLEFitSize(errorMsg,1000));
					jdOrderService.updateByPrimaryKey(requestJdOrder);
				}
			}
		}
		Long endTime = System.currentTimeMillis();
		logger.info("intra mirror send request Orders end, use time:" + (endTime - startTime) +" ms. shopCount: " + shopCount);
	}



	/**
	 * 下发抓到的FAILURE商品至globalshop
	 */
	@Scheduled(cron = "45 6/10 * * * ?")
	public void sendFailureOrder2erp() {

		logger.info("intra mirror send failure Orders start");
		Long startTime = System.currentTimeMillis();

		JdShopOauthDO shopOauthSo = new JdShopOauthDO();
		shopOauthSo.setChannelNo(ChannelType.IntraMirror.getValue()+"");
		shopOauthSo.setOpen(true);
		List<JdShopOauthDO> shopOauthDOList = shopOauthService.searchShopOauthList(shopOauthSo);

		Long shopCount= 0L;

		for (JdShopOauthDO shopOauth : shopOauthDOList) {

			JdOrderDO jdOrderSo = new JdOrderDO();
			jdOrderSo.setSendStatus(SendStatus.FAILURE);
			jdOrderSo.setShopCode(shopOauth.getShopCode());
			jdOrderSo.setChannelNo(shopOauth.getChannelNo());

			List<JdOrderDO> failureJdOrderList = jdOrderService.searchJdOrderList(jdOrderSo);
			Boolean success = true;

			for(JdOrderDO failureJdOrder : failureJdOrderList){
				String errorMsg = "";
				try {
					channelCommonService.sendOrder(shopOauth.getShopCode(),failureJdOrder);
					shopCount++;
				} catch (ErpCommonException e) {
					success = false;
					errorMsg += e.getErrorMsg();
					logger.error("intra mirror send failure Orders error:",e);
				} catch (Exception e) {
					success = false;
					errorMsg += e.getMessage();
					logger.error("intra mirror send failure Orders error, shopCode: " + shopOauth.getShopCode(), e);
				}
				if(success){
					failureJdOrder.setSendStatus(SendStatus.SUCCESS);
					jdOrderService.updateByPrimaryKey(failureJdOrder);
				}else {
					failureJdOrder.setSendStatus(SendStatus.STOP);
					failureJdOrder.setErrorMassge(EasyUtil.truncateLEFitSize(errorMsg,1000));
					jdOrderService.updateByPrimaryKey(failureJdOrder);
				}
			}
		}
		Long endTime = System.currentTimeMillis();
		logger.info("intra mirror send failure orders end, use time:" + (endTime - startTime) +" ms. shopCount: " + shopCount);
	}
}
