package com.wangqin.globalshop.channel.task.jdtask;

import com.wangqin.globalshop.biz1.app.constants.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdItemDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.service.jingdong.JdItemService;
import com.wangqin.globalshop.channel.service.jingdong.JdShopOauthService;
import com.wangqin.globalshop.channel.service.jingdong.SendStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * Create by 777 on 2018/6/13
 */
//@Component
@EnableAsync
public class JdSendItems2GlobalshopTask {

	protected Logger logger = LogManager.getLogger(getClass());


	@Autowired
	private JdShopOauthService jdShopOauthService;

	@Autowired
	private JdItemService jdItemService;

	// 每隔半小时执行一次
	@Scheduled(cron = "0 0/30 * * * ?")
	public void run4Request() {
		logger.info("定时任务：自动将京东下载的商品下发至globalshop===>Start");
		Long startTime = System.currentTimeMillis();
		// 首先轮训出company
		JdShopOauthDO so = new JdShopOauthDO();
		so.setChannelNo(ChannelType.JingDong.getValue()+"");
		so.setIsDel(false);
		List<JdShopOauthDO> jdShopOauthDOS = jdShopOauthService.searchShopOauthList(so);

		Long shopCount= 0L;

		Long jdItemCount = 0L;

		for (JdShopOauthDO shopOauth : jdShopOauthDOS) {

			JdItemDO itemSo = new JdItemDO();
			itemSo.setShopCode(shopOauth.getShopCode());
			itemSo.setSendStatus(SendStatus.REQUEST);

			List<JdItemDO> jdItemDOS = jdItemService.searchJdItemList(itemSo);

			for(JdItemDO jdItemDO : jdItemDOS){

				try {
					jdItemService.sendJdItem2globalshop4Task(jdItemDO,shopOauth);
					jdItemCount++;
				} catch (ErpCommonException e) {

					logger.error("JdSendItems2GlobalshopTask error:",e);
				} catch (Exception e) {

					logger.error("JdSendItems2GlobalshopTask error, shopCode: " + shopOauth.getShopCode(), e);
				}
			}

			shopCount++;
		}
		Long endTime = System.currentTimeMillis();
		logger.info("定时任务：自动将京东下载的商品下发至globalshop===>End, use time:" + (endTime - startTime) +" ms. shopCount: " + shopCount+" jdItemCount: "+jdItemCount);
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

		Long jdItemCount = 0L;

		for (JdShopOauthDO shopOauth : jdShopOauthDOS) {

			JdItemDO itemSo = new JdItemDO();
			itemSo.setShopCode(shopOauth.getShopCode());
			itemSo.setSendStatus(SendStatus.FAILURE);

			List<JdItemDO> jdItemDOS = jdItemService.searchJdItemList(itemSo);

			for(JdItemDO jdItemDO : jdItemDOS){

				try {
					jdItemService.sendJdItem2globalshop4Task(jdItemDO, shopOauth);
					jdItemCount++;
				} catch (ErpCommonException e) {

					logger.error("JdSendItems2GlobalshopTask error:",e);
				} catch (Exception e) {

					logger.error("JdSendItems2GlobalshopTask error, shopCode: " + shopOauth.getShopCode(), e);
				}
			}

			shopCount++;
		}
		Long endTime = System.currentTimeMillis();
		logger.info("定时任务：自动将京东下载的商品下发至globalshop===>End, use time:" + (endTime - startTime) +" ms. shopCount: " + shopCount+" jdItemCount: "+jdItemCount);
	}



}
