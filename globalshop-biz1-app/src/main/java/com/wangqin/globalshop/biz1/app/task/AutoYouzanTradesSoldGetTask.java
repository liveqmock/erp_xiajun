package com.wangqin.globalshop.biz1.app.task;

import com.wangqin.globalshop.biz1.app.service.channel.ChannelFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wangqin.enums.ChannelType;
import com.wangqin.service.channels.ChannelFactory;

/**
 * 自动去有赞下载订单
 * 
 * @author LiuYang
 *
 */
@Component
public class AutoYouzanTradesSoldGetTask {
	protected Logger logger = LogManager.getLogger(getClass());
	

	
	//每隔半小时执行一次
	@Scheduled(cron = "0 0/30 * * * ?")
	public void run(){
		logger.info("定时任务：自动去有赞下载订单===>Start");

		//首先轮训出company





		//第二步，轮训出该账户下的有赞账户token等



		//第三步，抓单




		try {
			ChannelFactory.getChannel(1L, ChannelType.YouZan).syncOrder();
		} catch (Exception e) {

			e.printStackTrace();
		}
		logger.info("定时任务：自动去有赞下载订单===>End");
		
//		logger.info("定时任务：自动下架已售罄的商品===>Start");
//		youzanService.youzanSaleOutItemToDelisting();
//		logger.info("定时任务：自动下架已售罄的商品===>End");
	}
}
