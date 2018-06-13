package com.wangqin.globalshop.channel.task.jdtask;

import com.wangqin.globalshop.biz1.app.constants.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdItemOperateDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.service.jingdong.JdItemOperateService;
import com.wangqin.globalshop.channel.service.jingdong.JdShopOauthService;
import com.wangqin.globalshop.channel.service.jingdong.SyncStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Create by 777 on 2018/6/13
 */
@Component
public class JdOperateItemTask {

	protected Logger logger = LogManager.getLogger(getClass());


	@Autowired
	private JdShopOauthService jdShopOauthService;


	@Autowired
	private JdItemOperateService jdItemOperateService;

	// 每隔半小时执行一次
	@Scheduled(cron = "0 0/15 * * * ?")
	public void run4AddRequest() {
		logger.info("定时任务：自动获取商品，并且上传至globalshop===>Start");
		Long startTime = System.currentTimeMillis();
		// 首先轮训出company
		JdShopOauthDO so = new JdShopOauthDO();
		so.setChannelNo(ChannelType.JingDong.getValue()+"");
		so.setIsDel(false);
		List<JdShopOauthDO> jdShopOauthDOS = jdShopOauthService.searchShopOauthList(so);

		Long shopCount= 0L;

		Long jdItemCount = 0L;

		for (JdShopOauthDO shopOauth : jdShopOauthDOS) {

			JdItemOperateDO itemSo = new JdItemOperateDO();
			itemSo.setShopCode(shopOauth.getShopCode());
			itemSo.setSyncStatus(SyncStatus.REQUEST);

			List<JdItemOperateDO> jdItemOperateDOS = jdItemOperateService.searchJdItemOperateList(itemSo);

			for(JdItemOperateDO jdItemOperateDO : jdItemOperateDOS){

				try {

					jdItemOperateService.queryItemThenSync2Jd4Task(jdItemOperateDO,shopOauth);

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
		logger.info("定时任务：自动获取商品，并且上传至京东globalshop===>End, use time:" + (endTime - startTime) +" ms. shopCount: " + shopCount+" jdItemCount: "+jdItemCount);
	}

	// 每隔半小时执行一次
	@Async
	@Scheduled(cron = "0 0/30 * * * ?")
	public void run4AddFailure() {
		logger.info("定时任务：自动获取商品，并且上传至京至globalshop至globalshop===>Start");
		Long startTime = System.currentTimeMillis();
		// 首先轮训出company
		JdShopOauthDO so = new JdShopOauthDO();
		so.setChannelNo(ChannelType.JingDong.getValue()+"");
		so.setIsDel(false);
		List<JdShopOauthDO> jdShopOauthDOS = jdShopOauthService.searchShopOauthList(so);

		Long shopCount= 0L;

		Long jdItemCount = 0L;

		for (JdShopOauthDO shopOauth : jdShopOauthDOS) {

			JdItemOperateDO itemSo = new JdItemOperateDO();
			itemSo.setShopCode(shopOauth.getShopCode());
			itemSo.setSyncStatus(SyncStatus.FAILURE);

			List<JdItemOperateDO> jdItemOperateDOS = jdItemOperateService.searchJdItemOperateList(itemSo);

			for(JdItemOperateDO jdItemOperateDO : jdItemOperateDOS){

				try {
					jdItemOperateService.queryItemThenSync2Jd4Task(jdItemOperateDO,shopOauth);

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
		logger.info("定时任务：自动获取商品，并且上传至京东globalshop===>End, use time:" + (endTime - startTime) +" ms. shopCount: " + shopCount+" jdItemCount: "+jdItemCount);
	}
}
