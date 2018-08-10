package com.wangqin.globalshop.channel.task;

import com.wangqin.globalshop.biz1.app.dal.dataObject.*;
import com.wangqin.globalshop.biz1.app.enums.AccountConfigKey;
import com.wangqin.globalshop.biz1.app.enums.ChannelType;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.service.company.ICompanyService;
import com.wangqin.globalshop.channel.service.jingdong.JdShopConfigService;
import com.wangqin.globalshop.channel.service.jingdong.JdShopOauthService;
import com.wangqin.globalshop.channelapi.service.ChannelCommonService;
import com.wangqin.globalshop.common.utils.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 自动去有赞下载订单
 * 
 * @author 777
 */
@Component
public class AutoYouzanTradesSoldGetTask {

    protected Logger       logger = LogManager.getLogger(getClass());

    @Autowired
    ICompanyService        companyService;

    @Autowired
	private ChannelCommonService channelCommonService;

	@Autowired
	private JdShopOauthService shopOauthService;

	@Autowired
	private JdShopConfigService jdShopConfigService;

	private final static int internalTime = 30 * 1000;//延后30秒

    // 每隔半小时执行一次
    @Scheduled(cron = "0 0/30 * * * ?")
    public void run() {

		logger.info("定时任务：自动去京东下载订单===>Start");
		Long startTime = System.currentTimeMillis();

		JdShopOauthDO shopOauthSo = new JdShopOauthDO();
		shopOauthSo.setChannelNo(ChannelType.YouZan.getValue()+"");
		List<JdShopOauthDO> shopOauthDOList = shopOauthService.searchShopOauthList(shopOauthSo);

		Long shopCount= 0L;
		JdShopConfigDO configso = new JdShopConfigDO();
		configso.setConfigKey(AccountConfigKey.LAST_TRADES_GET_TIME.getDescription());

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

			Boolean success = true;

			try {
				try {
					channelCommonService.getOrders(shopOauth.getShopCode(),beginTime,endTime);
					shopCount++;
				} catch (Exception e) {
					logger.error("get youzan orders error, shopCode: " + shopOauth.getShopCode(), e);
				}

				shopCount++;
			} catch (ErpCommonException e) {
				success = false;
				logger.error("JdGetOrdersTask error:",e);
			} catch (Exception e) {
				success = false;
				logger.error("get jingdong orders error, shopCode: " + shopOauth.getShopCode(), e);
			}

			if(success){
				String endValue = DateUtil.convertDate2Str(endTime,DateUtil.formateStr19);
				jdShopConfigDO.setConfigValue(endValue);
				jdShopConfigService.updateByPrimaryKey(jdShopConfigDO);
			}
		}
		Long endTime = System.currentTimeMillis();
		logger.info("定时任务：自动去京东下载订单===>End, use time:" + (endTime - startTime) +" ms. shopCount: " + shopCount);
    }
}
