package com.wangqin.globalshop.channel.task.jdtask;

import com.wangqin.globalshop.biz1.app.constants.enums.AccountConfigKey;
import com.wangqin.globalshop.biz1.app.constants.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopConfigDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.service.jingdong.JdCategoryService;
import com.wangqin.globalshop.channel.service.jingdong.JdShopConfigService;
import com.wangqin.globalshop.channel.service.jingdong.JdShopOauthService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * Create by 777 on 2018/6/16
 */
//@Component

public class JdCategoryTask {

	protected Logger logger = LogManager.getLogger(getClass());


	@Autowired
	private JdShopOauthService jdShopOauthService;

	@Autowired
	private JdShopConfigService jdShopConfigService;


	@Autowired
	private JdCategoryService jdCategoryService;



	// 每隔半小时执行一次
	@Scheduled(cron = "0 0/30 * * * ?")
	public void run() {
		logger.info("定时任务：自动去京东下载商品===>Start");
		Long startTime = System.currentTimeMillis();
		// 首先轮训出company
		JdShopOauthDO so = new JdShopOauthDO();
		so.setChannelNo(ChannelType.JingDong.getValue()+"");
		so.setIsDel(false);
		List<JdShopOauthDO> jdShopOauthDOS = jdShopOauthService.searchShopOauthList(so);

		Long shopCount= 0L;
		JdShopConfigDO configso = new JdShopConfigDO();
		configso.setConfigKey(AccountConfigKey.NEED_GET_CATEGORY.getDescription());
		for (JdShopOauthDO shopOauth : jdShopOauthDOS) {
			configso.setShopCode(shopOauth.getShopCode());

			JdShopConfigDO jdShopConfigDO = jdShopConfigService.searchShopConfig(configso);

			if(jdShopConfigDO == null || jdShopConfigDO.getConfigKey() == null){
				jdShopConfigService.initShopConfig(ChannelType.JingDong, shopOauth);
				break;
			}

			Boolean need = "true".equalsIgnoreCase(jdShopConfigDO.getConfigValue());

			if(!need){
				break;
			}

			Boolean success = true;

			try {

				//JdShopFactory.getChannel(shopOauth).getCategory();
				jdCategoryService.getCategory(shopOauth);
				shopCount++;
			} catch (ErpCommonException e) {
				success = false;
				logger.error("JdGetItemsTask error:",e);
			} catch (Exception e) {
				success = false;
				logger.error("get jingdong Items error, shopCode: " + shopOauth.getShopCode(), e);
			}

			if(success){
				jdShopConfigDO.setConfigValue("false");
				jdShopConfigService.updateByPrimaryKey(jdShopConfigDO);
			}
		}
		Long endTime = System.currentTimeMillis();
		logger.info("定时任务：自动去京东下载订===>End, use time:" + (endTime - startTime) +" ms. shopCount: " + shopCount);
	}

}
