package com.wangqin.globalshop.channel.task.youzantask;

import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopConfigDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;
import com.wangqin.globalshop.biz1.app.enums.AccountConfigKey;
import com.wangqin.globalshop.biz1.app.enums.ChannelType;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.service.jingdong.JdShopConfigService;
import com.wangqin.globalshop.channel.service.jingdong.JdShopOauthService;
import com.wangqin.globalshop.channelapi.service.ChannelCommonService;
import com.wangqin.globalshop.common.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Create by 777 on 2018/8/12
 */


//@Component
public class AutoYouzanItemsGetTask {

	private static Logger logger = LoggerFactory.getLogger("AutoYouzanItemsGetTask");
	@Autowired
	private ChannelCommonService channelCommonService;

	@Autowired
	private JdShopOauthService shopOauthService;

	@Autowired
	private JdShopConfigService jdShopConfigService;

	private final static int internalTime = 30 * 1000;//延后30秒

	private final static Long startEndMaxInternalDay = 5*24*60*60*1000L;//查询间隔最大5天

	// 每隔半小时执行一次
	@Scheduled(cron = "0/30 * * * * ?")
	public void run() {

		logger.info("定时任务：自动去有赞下载商品===>Start");
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
				break;
			}

			String beginValue = jdShopConfigDO.getConfigValue();

			Date beginTime = DateUtil.parseDate(beginValue);

			Date endTime =  new Date(System.currentTimeMillis() - internalTime);

			if(beginTime.after(endTime)){
				break;
			}

			if(endTime.getTime() - beginTime.getTime() > startEndMaxInternalDay){
				endTime = new Date(beginTime.getTime()+startEndMaxInternalDay);
			}

			logger.info("youzan item begin time: " + DateUtil.convertDate2Str(beginTime,DateUtil.formateStr19));
			logger.info("youzan item end time: " + DateUtil.convertDate2Str(endTime,DateUtil.formateStr19));


			Boolean success = true;

			try {
				channelCommonService.getItems(shopOauth.getShopCode(),beginTime,endTime);
				shopCount++;
			} catch (ErpCommonException e) {
				success = false;
				logger.error("AutoYouzanTradesSoldGetTask error:",e);
			} catch (Exception e) {
				success = false;
				logger.error("get youzan orders error, shopCode: " + shopOauth.getShopCode(), e);
			}
			if(success){
				String endValue = DateUtil.convertDate2Str(endTime,DateUtil.formateStr19);
				jdShopConfigDO.setConfigValue(endValue);
				jdShopConfigService.updateByPrimaryKey(jdShopConfigDO);
			}
		}
		Long endTime = System.currentTimeMillis();
		logger.info("定时任务：自动去有赞下载商品===>End, use time:" + (endTime - startTime) +" ms. shopCount: " + shopCount);
	}
}
