package com.wangqin.globalshop.channel.task.youzantask;

import com.wangqin.globalshop.biz1.app.dal.dataObject.JdItemDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopConfigDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;
import com.wangqin.globalshop.biz1.app.enums.AccountConfigKey;
import com.wangqin.globalshop.biz1.app.enums.ChannelType;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.service.jingdong.JdItemService;
import com.wangqin.globalshop.channel.service.jingdong.JdShopConfigService;
import com.wangqin.globalshop.channel.service.jingdong.JdShopOauthService;
import com.wangqin.globalshop.channel.service.jingdong.SendStatus;
import com.wangqin.globalshop.channelapi.service.ChannelCommonService;
import com.wangqin.globalshop.common.utils.DateUtil;
import com.wangqin.globalshop.common.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Date;
import java.util.List;

/**
 * Create by 777 on 2018/8/12
 */


@Component
public class AutoYouzanItemsTask {

	private static Logger logger = LoggerFactory.getLogger("AutoYouzanItemsTask");
	@Autowired
	private ChannelCommonService channelCommonService;

	@Autowired
	private JdShopOauthService shopOauthService;

	@Autowired
	private JdShopConfigService jdShopConfigService;

	@Autowired
	private JdItemService jdItemService;


	@Autowired
	private TransactionTemplate transactionTemplate;

	private final static int internalTime = 30 * 1000;//延后30秒

	private final static Long startEndMaxInternalDay = 5*24*60*60*1000L;//查询间隔最大5天

	// 抓商品：在售商品，按照更新时间，暂时没接通
	// 每隔半小时执行一次:该方法存在jar包冲突，暂时不解决
	//@Scheduled(cron = "0/30 * * * * ?")
	public void getItemsByTime() {

		logger.info("youzan get all items start");
		Long startTime = System.currentTimeMillis();

		JdShopOauthDO shopOauthSo = new JdShopOauthDO();
		shopOauthSo.setChannelNo(ChannelType.YouZan.getValue()+"");
		shopOauthSo.setOpen(true);
		List<JdShopOauthDO> shopOauthDOList = shopOauthService.searchShopOauthList(shopOauthSo);

		Long shopCount= 0L;
		JdShopConfigDO configso = new JdShopConfigDO();
		configso.setConfigKey(AccountConfigKey.LAST_ITEM_GET_TIME.getDescription());

		for (JdShopOauthDO shopOauth : shopOauthDOList) {

			configso.setShopCode(shopOauth.getShopCode());
			JdShopConfigDO jdShopConfigDO = jdShopConfigService.searchShopConfig(configso);

			if(jdShopConfigDO == null || jdShopConfigDO.getConfigKey() == null){
				jdShopConfigService.initShopConfig(ChannelType.YouZan, shopOauth);
				continue;
			}

			String beginValue = jdShopConfigDO.getConfigValue();

			Date beginTime = DateUtil.parseDate(beginValue);

			Date endTime =  new Date(System.currentTimeMillis() - internalTime);

			if(beginTime.after(endTime)){
				continue;
			}

			if(endTime.getTime() - beginTime.getTime() > startEndMaxInternalDay){
				endTime = new Date(beginTime.getTime()+startEndMaxInternalDay);
			}

			logger.info("youzan item begin time: " + DateUtil.convertDate2Str(beginTime,DateUtil.formateStr19));
			logger.info("youzan item end time: " + DateUtil.convertDate2Str(endTime,DateUtil.formateStr19));


			Boolean success = true;

			try {
				channelCommonService.getItemsByTime(shopOauth.getShopCode(),beginTime,endTime);
				shopCount++;
			} catch (ErpCommonException e) {
				success = false;
				logger.error("youzan get all items error:",e);
			} catch (Exception e) {
				success = false;
				logger.error("youzan get all items error, shopCode: " + shopOauth.getShopCode(), e);
			}
			if(success){
				String endValue = DateUtil.convertDate2Str(endTime,DateUtil.formateStr19);
				jdShopConfigDO.setConfigValue(endValue);
				jdShopConfigService.updateByPrimaryKey(jdShopConfigDO);
			}
		}
		Long endTime = System.currentTimeMillis();
		logger.info("youzan get all items end, use time:" + (endTime - startTime) +" ms. shopCount: " + shopCount);
	}


	//抓商品：在售商品，抓一次，抓全部
	@Scheduled(cron = "5 5/4 * * * ?")
	public void getAllItems() {

		logger.info("youzan get all items start");
		Long startTime = System.currentTimeMillis();

		JdShopOauthDO shopOauthSo = new JdShopOauthDO();
		shopOauthSo.setChannelNo(ChannelType.YouZan.getValue()+"");
		shopOauthSo.setOpen(true);
		List<JdShopOauthDO> shopOauthDOList = shopOauthService.searchShopOauthList(shopOauthSo);

		Long shopCount= 0L;
		JdShopConfigDO configso = new JdShopConfigDO();
		configso.setConfigKey(AccountConfigKey.NEED_GET_ALL_ITEMS.getDescription());

		for (JdShopOauthDO shopOauth : shopOauthDOList) {

			configso.setShopCode(shopOauth.getShopCode());
			JdShopConfigDO jdShopConfigDO = jdShopConfigService.searchShopConfig(configso);

			if(jdShopConfigDO == null || jdShopConfigDO.getConfigKey() == null){
				jdShopConfigService.initShopConfig(ChannelType.YouZan, shopOauth);
				continue;
			}

			String needStrValue = jdShopConfigDO.getConfigValue();

			if("false".equalsIgnoreCase(needStrValue)){
				continue;
			}

			Boolean success = true;

			try {
				channelCommonService.getAllItems(shopOauth.getShopCode());
				shopCount++;
			} catch (ErpCommonException e) {
				success = false;
				logger.error("youzan get all items error:",e);
			} catch (Exception e) {
				success = false;
				logger.error("youzan get all items error, shopCode: " + shopOauth.getShopCode(), e);
			}
			if(success){
				String endValue = "false";
				jdShopConfigDO.setConfigValue(endValue);
				jdShopConfigService.updateByPrimaryKey(jdShopConfigDO);
			}
		}
		Long endTime = System.currentTimeMillis();
		logger.info("youzan get all items end, use time:" + (endTime - startTime) +" ms. shopCount: " + shopCount);
	}


	/**
	 * 下发抓到的REQUEST商品至globalshop
	 */
	@Scheduled(cron = "6 6/4 * * * ?")
	public void sendRequestItem2erp() {

		logger.info("youzan send request items start");
		Long startTime = System.currentTimeMillis();

		JdShopOauthDO shopOauthSo = new JdShopOauthDO();
		shopOauthSo.setChannelNo(ChannelType.YouZan.getValue()+"");
		shopOauthSo.setOpen(true);
		List<JdShopOauthDO> shopOauthDOList = shopOauthService.searchShopOauthList(shopOauthSo);

		Long shopCount = 0L;

		for (JdShopOauthDO shopOauth : shopOauthDOList) {

			JdItemDO jdItemSo = new JdItemDO();
			jdItemSo.setSendStatus(SendStatus.REQUEST);
			jdItemSo.setShopCode(shopOauth.getShopCode());
			jdItemSo.setChannelNo(shopOauth.getChannelNo());

			List<JdItemDO> requestJdItemList = jdItemService.searchJdItemList(jdItemSo);
			Boolean success = true;

			for(JdItemDO requestJdItem : requestJdItemList){

				String errorMsg = "";
				try {
					channelCommonService.sendItem(shopOauth.getShopCode(),requestJdItem);
					shopCount++;
				} catch (ErpCommonException e) {
					success = false;
					errorMsg += e.getErrorMsg();
					if(errorMsg.indexOf("重复的itemCode") >= 0){
                        //重复的商品，不打印日志
					}else {
						logger.error("youzan send request items error:",e);
					}
				} catch (Exception e) {
					success = false;
					errorMsg += e.getMessage();
					logger.error("youzan send request items error, shopCode: " + shopOauth.getShopCode(), e);
				}

				if(success){
					requestJdItem.setSendStatus(SendStatus.SUCCESS);
					jdItemService.updateByPrimaryKey(requestJdItem);
				}else {
					requestJdItem.setSendStatus(SendStatus.FAILURE);
					requestJdItem.setErrorMassge(errorMsg);
					jdItemService.updateByPrimaryKey(requestJdItem);
				}
			}
		}
		Long endTime = System.currentTimeMillis();
		logger.info("youzan send request items end, use time:" + (endTime - startTime) +" ms. shopCount: " + shopCount);
	}



	/**
	 * 下发抓到的FAILURE商品至globalshop
	 */
	@Scheduled(cron = "7 7/10 * * * ?")
	public void sendFailureItem2erp() {

		logger.info("youzan send failure items start");
		Long startTime = System.currentTimeMillis();

		JdShopOauthDO shopOauthSo = new JdShopOauthDO();
		shopOauthSo.setChannelNo(ChannelType.YouZan.getValue()+"");
		shopOauthSo.setOpen(true);
		List<JdShopOauthDO> shopOauthDOList = shopOauthService.searchShopOauthList(shopOauthSo);

		Long shopCount= 0L;

		for (JdShopOauthDO shopOauth : shopOauthDOList) {

			JdItemDO jdItemSo = new JdItemDO();
			jdItemSo.setSendStatus(SendStatus.FAILURE);
			jdItemSo.setShopCode(shopOauth.getShopCode());
			jdItemSo.setChannelNo(shopOauth.getChannelNo());

			List<JdItemDO> failureJdItemList = jdItemService.searchJdItemList(jdItemSo);
			Boolean success = true;

			for(JdItemDO failureJdItem : failureJdItemList){
				String errorMsg = "";
				try {
					channelCommonService.sendItem(shopOauth.getShopCode(),failureJdItem);
					shopCount++;
				} catch (ErpCommonException e) {
					success = false;
					errorMsg += e.getErrorMsg();
					logger.error("youzan send failure items error:",e);
				} catch (Exception e) {
					success = false;
					errorMsg += e.getMessage();
					logger.error("youzan send failure items error, shopCode: " + shopOauth.getShopCode(), e);
				}
				if(success){
					failureJdItem.setSendStatus(SendStatus.SUCCESS);
					jdItemService.updateByPrimaryKey(failureJdItem);
				}else {
					failureJdItem.setSendStatus(SendStatus.STOP);
					failureJdItem.setErrorMassge(errorMsg);
					jdItemService.updateByPrimaryKey(failureJdItem);
				}
			}
		}
		Long endTime = System.currentTimeMillis();
		logger.info("youzan send failure items end, use time:" + (endTime - startTime) +" ms. shopCount: " + shopCount);
	}

}
