package com.wangqin.globalshop.channel.task.jdtask;

import com.wangqin.globalshop.biz1.app.constants.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdItemOperateDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.service.jingdong.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * Create by 777 on 2018/6/13
 */
//@EnableAsync
//@Component
public class JdOperateItemTask {

	protected Logger logger = LogManager.getLogger(getClass());


	@Autowired
	private JdShopOauthService jdShopOauthService;


	@Autowired
	private JdItemOperateService jdItemOperateService;

	// 每隔半小时执行一次
	@Async
	@Scheduled(cron = "0 0/15 * * * ?")
	public void run4AddRequest() {
		logger.info("定时任务：自动发布商品至京东run4AddRequest===>Start");
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
			itemSo.setOperateType(OperateType.OPERATE_ADD);

			List<JdItemOperateDO> jdItemOperateDOS = jdItemOperateService.searchJdItemOperateList(itemSo);

			for(JdItemOperateDO jdItemOperateDO : jdItemOperateDOS){

				try {

					jdItemOperateService.queryItemThenSync2Jd4Add(jdItemOperateDO,shopOauth);

					jdItemCount++;
				} catch (ErpCommonException e) {

					logger.error("run4AddRequest error:",e);
				} catch (Exception e) {

					logger.error("run4AddRequest error, shopCode: " + shopOauth.getShopCode(), e);
				}
			}

			shopCount++;
		}
		Long endTime = System.currentTimeMillis();
		logger.info("定时任务：自动发布商品至京东run4AddRequest===>End, use time:" + (endTime - startTime) +" ms. shopCount: " + shopCount+" jdItemCount: "+jdItemCount);
	}

	// 每隔半小时执行一次
	@Async
	@Scheduled(cron = "0 0/30 * * * ?")
	public void run4AddFailure() {
		logger.info("定时任务：自动发布商品至京东run4AddFailure===>Start");
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
			itemSo.setOperateType(OperateType.OPERATE_ADD);

			List<JdItemOperateDO> jdItemOperateDOS = jdItemOperateService.searchJdItemOperateList(itemSo);

			for(JdItemOperateDO jdItemOperateDO : jdItemOperateDOS){

				try {
					jdItemOperateService.queryItemThenSync2Jd4Add(jdItemOperateDO,shopOauth);

					jdItemCount++;
				} catch (ErpCommonException e) {

					logger.error("run4AddFailure error:",e);
				} catch (Exception e) {

					logger.error("run4AddFailure error, shopCode: " + shopOauth.getShopCode(), e);
				}
			}

			shopCount++;
		}
		Long endTime = System.currentTimeMillis();
		logger.info("定时任务：自动发布商品至京东run4AddFailure===>End, use time:" + (endTime - startTime) +" ms. shopCount: " + shopCount+" jdItemCount: "+jdItemCount);
	}


	@Async
	@Scheduled(cron = "0 0/15 * * * ?")
	public void run4UpdateRequest() {
		logger.info("定时任务：自动更新商品至京东run4UpdateRequest===>Start");
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
			itemSo.setOperateType(OperateType.OPERATE_UPDATE);

			List<JdItemOperateDO> jdItemOperateDOS = jdItemOperateService.searchJdItemOperateList(itemSo);

			for(JdItemOperateDO jdItemOperateDO : jdItemOperateDOS){

				try {

					jdItemOperateService.queryItemThenSync2Jd4Update(jdItemOperateDO,shopOauth);

					jdItemCount++;
				} catch (ErpCommonException e) {

					logger.error("run4UpdateRequest error:",e);
				} catch (Exception e) {

					logger.error("run4UpdateRequest error, shopCode: " + shopOauth.getShopCode(), e);
				}
			}

			shopCount++;
		}
		Long endTime = System.currentTimeMillis();
		logger.info("定时任务：自动更新商品至京东run4UpdateRequest===>End, use time:" + (endTime - startTime) +" ms. shopCount: " + shopCount+" jdItemCount: "+jdItemCount);
	}

	@Async
	@Scheduled(cron = "0 0/30 * * * ?")
	public void run4UpdateFailure() {
		logger.info("定时任务：自动更新商品至京东run4UpdateFailure===>Start");
		Long startTime = System.currentTimeMillis();

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
			itemSo.setOperateType(OperateType.OPERATE_UPDATE);

			List<JdItemOperateDO> jdItemOperateDOS = jdItemOperateService.searchJdItemOperateList(itemSo);

			for(JdItemOperateDO jdItemOperateDO : jdItemOperateDOS){

				try {

					jdItemOperateService.queryItemThenSync2Jd4Update(jdItemOperateDO,shopOauth);

					jdItemCount++;
				} catch (ErpCommonException e) {

					logger.error("run4UpdateFailure error:",e);
				} catch (Exception e) {

					logger.error("run4UpdateFailure error, shopCode: " + shopOauth.getShopCode(), e);
				}
			}

			shopCount++;
		}
		Long endTime = System.currentTimeMillis();
		logger.info("定时任务：自动更新商品至京东run4UpdateFailure===>End, use time:" + (endTime - startTime) +" ms. shopCount: " + shopCount+" jdItemCount: "+jdItemCount);
	}

	@Async
	@Scheduled(cron = "0 0/30 * * * ?")
	public void run4SendItem4Request() {
		logger.info("定时任务：自动下发上新后的商品至globalshop-run4SendItem4Request===>Start");
		Long startTime = System.currentTimeMillis();

		JdShopOauthDO so = new JdShopOauthDO();
		so.setChannelNo(ChannelType.JingDong.getValue()+"");
		so.setIsDel(false);
		List<JdShopOauthDO> jdShopOauthDOS = jdShopOauthService.searchShopOauthList(so);

		Long shopCount= 0L;

		Long jdItemCount = 0L;

		for (JdShopOauthDO shopOauth : jdShopOauthDOS) {

			JdItemOperateDO itemSo = new JdItemOperateDO();
			itemSo.setShopCode(shopOauth.getShopCode());
			itemSo.setSendStatus(SendStatus.REQUEST);
			itemSo.setOperateType(OperateType.OPERATE_ADD);

			List<JdItemOperateDO> jdItemOperateDOS = jdItemOperateService.searchJdItemOperateList(itemSo);

			for(JdItemOperateDO jdItemOperateDO : jdItemOperateDOS){

				try {

					jdItemOperateService.sendItem2Globalshop(jdItemOperateDO,shopOauth);

					jdItemCount++;
				} catch (ErpCommonException e) {

					logger.error("run4SendItem4Request error:",e);
				} catch (Exception e) {

					logger.error("run4SendItem4Request error, shopCode: " + shopOauth.getShopCode(), e);
				}
			}

			shopCount++;
		}
		Long endTime = System.currentTimeMillis();
		logger.info("定时任务：自动下发上新后的商品至globalshop-run4SendItem4Request===>End, use time:" + (endTime - startTime) +" ms. shopCount: " + shopCount+" jdItemCount: "+jdItemCount);
	}


	@Async
	@Scheduled(cron = "0 0/30 * * * ?")
	public void run4SendItem4Failure() {
		logger.info("定时任务：自动下发上新后的商品至globalshop-run4SendItem4Failure===>Start");
		Long startTime = System.currentTimeMillis();

		JdShopOauthDO so = new JdShopOauthDO();
		so.setChannelNo(ChannelType.JingDong.getValue()+"");
		so.setIsDel(false);
		List<JdShopOauthDO> jdShopOauthDOS = jdShopOauthService.searchShopOauthList(so);

		Long shopCount= 0L;

		Long jdItemCount = 0L;

		for (JdShopOauthDO shopOauth : jdShopOauthDOS) {

			JdItemOperateDO itemSo = new JdItemOperateDO();
			itemSo.setShopCode(shopOauth.getShopCode());
			itemSo.setSendStatus(SendStatus.FAILURE);
			itemSo.setOperateType(OperateType.OPERATE_ADD);

			List<JdItemOperateDO> jdItemOperateDOS = jdItemOperateService.searchJdItemOperateList(itemSo);

			for(JdItemOperateDO jdItemOperateDO : jdItemOperateDOS){

				try {

					jdItemOperateService.sendItem2Globalshop(jdItemOperateDO,shopOauth);

					jdItemCount++;
				} catch (ErpCommonException e) {

					logger.error("run4SendItem4Failure error:",e);
				} catch (Exception e) {

					logger.error("run4SendItem4Failure error, shopCode: " + shopOauth.getShopCode(), e);
				}
			}

			shopCount++;
		}
		Long endTime = System.currentTimeMillis();
		logger.info("定时任务：自动下发上新后的商品至globalshop-run4SendItem4Failure===>End, use time:" + (endTime - startTime) +" ms. shopCount: " + shopCount+" jdItemCount: "+jdItemCount);
	}


}
