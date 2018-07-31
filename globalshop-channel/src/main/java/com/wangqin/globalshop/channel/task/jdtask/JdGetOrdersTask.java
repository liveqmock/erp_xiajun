package com.wangqin.globalshop.channel.task.jdtask;

import com.wangqin.globalshop.biz1.app.enums.AccountConfigKey;
import com.wangqin.globalshop.biz1.app.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopConfigDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.service.jingdong.JdOrderService;
import com.wangqin.globalshop.channel.service.jingdong.JdShopConfigService;
import com.wangqin.globalshop.channel.service.jingdong.JdShopFactory;
import com.wangqin.globalshop.channel.service.jingdong.JdShopOauthService;
import com.wangqin.globalshop.common.utils.DateUtil;
import com.wangqin.globalshop.common.utils.EasyUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;

/**
 * Create by 777 on 2018/6/12
 */

//@Component
public class JdGetOrdersTask {

	protected Logger logger = LogManager.getLogger(getClass());


	@Autowired
	private JdShopOauthService jdShopOauthService;

	@Autowired
	private JdShopConfigService jdShopConfigService;


	@Autowired
	private JdOrderService jdOrderService;

	private final static int internalTime = 30 * 1000;//延后30秒


	// 每隔半小时执行一次
	@Scheduled(cron = "0 0/1 * * * ?")
	public void run() {
		logger.info("定时任务：自动去京东下载订单===>Start");
		Long startTime = System.currentTimeMillis();
		// 首先轮训出company
		JdShopOauthDO so = new JdShopOauthDO();
		so.setChannelNo(ChannelType.JingDong.getValue()+"");
		so.setIsDel(false);
		List<JdShopOauthDO> jdShopOauthDOS = jdShopOauthService.searchShopOauthList(so);

		Long shopCount= 0L;
		JdShopConfigDO configso = new JdShopConfigDO();
		configso.setConfigKey(AccountConfigKey.LAST_TRADES_GET_TIME.getDescription());
		for (JdShopOauthDO shopOauth : jdShopOauthDOS) {
			configso.setShopCode(shopOauth.getShopCode());

			JdShopConfigDO jdShopConfigDO = jdShopConfigService.searchShopConfig(configso);

			if(jdShopConfigDO == null || jdShopConfigDO.getConfigKey() == null){
				jdShopConfigService.initShopConfig(ChannelType.JingDong, shopOauth);
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
				List<JdOrderDO> jdOrderDOS = JdShopFactory
						.getChannel(shopOauth).getOrders(DateUtil.convertDate2Str(beginTime,DateUtil.formateStr19), DateUtil.convertDate2Str(endTime,DateUtil.formateStr19));

				if(!EasyUtil.isListEmpty(jdOrderDOS)){
					jdOrderService.saveOrders4Task(jdOrderDOS);
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
