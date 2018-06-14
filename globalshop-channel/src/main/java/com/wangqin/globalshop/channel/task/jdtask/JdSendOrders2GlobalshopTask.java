package com.wangqin.globalshop.channel.task.jdtask;

import com.wangqin.globalshop.biz1.app.constants.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.service.jingdong.JdOrderService;
import com.wangqin.globalshop.channel.service.jingdong.JdShopOauthService;
import com.wangqin.globalshop.channel.service.jingdong.SendStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * Create by 777 on 2018/6/13
 */
//@Component
public class JdSendOrders2GlobalshopTask {


	protected Logger logger = LogManager.getLogger(getClass());


	@Autowired
	private JdShopOauthService jdShopOauthService;

	@Autowired
	private JdOrderService jdOrderService;

	// 每隔半小时执行一次
	@Scheduled(cron = "0 0/15 * * * ?")
	public void run4Request() {
		logger.info("定时任务：自动将京东下载的商品下发至globalshop===>Start");
		Long startTime = System.currentTimeMillis();
		// 首先轮训出company
		JdShopOauthDO so = new JdShopOauthDO();
		so.setChannelNo(ChannelType.JingDong.getValue()+"");
		so.setIsDel(false);
		List<JdShopOauthDO> jdShopOauthDOS = jdShopOauthService.searchShopOauthList(so);

		Long shopCount= 0L;

		Long jdOrderCount = 0L;

		for (JdShopOauthDO shopOauth : jdShopOauthDOS) {

			JdOrderDO OrderSo = new JdOrderDO();
			OrderSo.setShopCode(shopOauth.getShopCode());
			OrderSo.setSendStatus(SendStatus.REQUEST);

			List<JdOrderDO> jdOrderDOS = jdOrderService.searchJdOrderList(OrderSo);

			for(JdOrderDO jdOrderDO : jdOrderDOS){

				try {
					jdOrderService.sendJdOrder2globalshop4Task(jdOrderDO, shopOauth);
					jdOrderCount++;
				} catch (ErpCommonException e) {

					logger.error("JdSendOrders2GlobalshopTask error:",e);
				} catch (Exception e) {

					logger.error("JdSendOrders2GlobalshopTask error, shopCode: " + shopOauth.getShopCode(), e);
				}
			}

			shopCount++;
		}
		Long endTime = System.currentTimeMillis();
		logger.info("定时任务：自动将京东下载的商品下发至globalshop===>End, use time:" + (endTime - startTime) +" ms. shopCount: " + shopCount+" jdOrderCount: "+jdOrderCount);
	}

	// 每隔半小时执行一次
	@Async
	@Scheduled(cron = "0 0/30 * * * ?")
	public void run4Failure() {
		logger.info("定时任务：自动将京东下载的商品下发至globalshop至globalshop===>Start");
		Long startTime = System.currentTimeMillis();
		// 首先轮训出company
		JdShopOauthDO so = new JdShopOauthDO();
		so.setChannelNo(ChannelType.JingDong.getValue()+"");
		so.setIsDel(false);
		List<JdShopOauthDO> jdShopOauthDOS = jdShopOauthService.searchShopOauthList(so);

		Long shopCount= 0L;

		Long jdOrderCount = 0L;

		for (JdShopOauthDO shopOauth : jdShopOauthDOS) {

			JdOrderDO OrderSo = new JdOrderDO();
			OrderSo.setShopCode(shopOauth.getShopCode());
			OrderSo.setSendStatus(SendStatus.FAILURE);

			List<JdOrderDO> jdOrderDOS = jdOrderService.searchJdOrderList(OrderSo);

			for(JdOrderDO jdOrderDO : jdOrderDOS){

				try {
					jdOrderService.sendJdOrder2globalshop4Task(jdOrderDO, shopOauth);
					jdOrderCount++;
				} catch (ErpCommonException e) {

					logger.error("JdSendOrders2GlobalshopTask error:",e);
				} catch (Exception e) {

					logger.error("JdSendOrders2GlobalshopTask error, shopCode: " + shopOauth.getShopCode(), e);
				}
			}

			shopCount++;
		}
		Long endTime = System.currentTimeMillis();
		logger.info("定时任务：自动将京东下载的商品下发至globalshop===>End, use time:" + (endTime - startTime) +" ms. shopCount: " + shopCount+" jdOrderCount: "+jdOrderCount);
	}

}
