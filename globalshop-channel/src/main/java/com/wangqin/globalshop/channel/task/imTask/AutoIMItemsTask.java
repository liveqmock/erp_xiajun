package com.wangqin.globalshop.channel.task.imTask;

import com.alibaba.fastjson.JSON;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdItemDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopConfigDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemDOMapperExt;
import com.wangqin.globalshop.biz1.app.enums.AccountConfigKey;
import com.wangqin.globalshop.biz1.app.enums.ChannelType;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.service.intramirror.IMProduct;
import com.wangqin.globalshop.channel.service.jingdong.JdItemService;
import com.wangqin.globalshop.channel.service.jingdong.JdShopConfigService;
import com.wangqin.globalshop.channel.service.jingdong.JdShopOauthService;
import com.wangqin.globalshop.channel.service.jingdong.SendStatus;
import com.wangqin.globalshop.channelapi.service.ChannelCommonService;
import com.wangqin.globalshop.common.utils.DateUtil;
import com.wangqin.globalshop.common.utils.EasyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Create by 777 on 2018/8/12
 */


@Component
public class AutoIMItemsTask {

	private static Logger logger = LoggerFactory.getLogger("AutoIMItemsTask");
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




	//抓商品：在售商品，抓一次，抓全部，intraMirrors只有在售有库存的商品才会抓回来
	@Scheduled(cron = "0 0 0/1 * * ?")
	//@Scheduled(cron = "0/30 * * * * ?")
	public void getAllItems() {

		logger.info("intraMirror get all items start");
		Long startTime = System.currentTimeMillis();

		JdShopOauthDO shopOauthSo = new JdShopOauthDO();
		shopOauthSo.setChannelNo(ChannelType.IntraMirror.getValue()+"");
		shopOauthSo.setOpen(true);
		List<JdShopOauthDO> shopOauthDOList = shopOauthService.searchShopOauthList(shopOauthSo);

		Long shopCount= 0L;
		JdShopConfigDO configso = new JdShopConfigDO();
		configso.setConfigKey(AccountConfigKey.NEED_GET_ALL_ITEMS.getDescription());

		for (JdShopOauthDO shopOauth : shopOauthDOList) {

			configso.setShopCode(shopOauth.getShopCode());
			JdShopConfigDO jdShopConfigDO = jdShopConfigService.searchShopConfig(configso);

			if(jdShopConfigDO == null || jdShopConfigDO.getConfigKey() == null){
				jdShopConfigService.initShopConfig(ChannelType.IntraMirror, shopOauth);
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
				logger.error("intramirror get all items error:",e);
			} catch (Exception e) {
				success = false;
				logger.error("intramirror get all items error, shopCode: " + shopOauth.getShopCode(), e);
			}
			if(success){
				String endValue = "false";
				jdShopConfigDO.setConfigValue(endValue);
				jdShopConfigService.updateByPrimaryKey(jdShopConfigDO);
			}
		}
		Long endTime = System.currentTimeMillis();
		logger.info("intramirror get all items end, use time:" + (endTime - startTime) +" ms. shopCount: " + shopCount);
	}


	/**
	 * 下发抓到的REQUEST商品至globalshop
	 */
	//@Scheduled(cron = "0/30 * * * * ?")
	@Scheduled(cron = "0 30 0/1 * * ?")
	public void sendRequestItem2erp() {

		logger.info("intramirror send request items start");
		Long startTime = System.currentTimeMillis();

		JdShopOauthDO shopOauthSo = new JdShopOauthDO();
		shopOauthSo.setChannelNo(ChannelType.IntraMirror.getValue()+"");
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
						logger.error("intramirror send request items error:",e);
					}
				} catch (Exception e) {
					success = false;
					errorMsg += e.getMessage();
					logger.error("intramirror send request items error, shopCode: " + shopOauth.getShopCode(), e);
				}

				if(success){
					requestJdItem.setSendStatus(SendStatus.SUCCESS);
					jdItemService.updateByPrimaryKey(requestJdItem);
				}else {
					requestJdItem.setSendStatus(SendStatus.FAILURE);
					requestJdItem.setErrorMassge(EasyUtil.truncateLEFitSize(errorMsg,1000));
					jdItemService.updateByPrimaryKey(requestJdItem);
				}
			}
		}
		Long endTime = System.currentTimeMillis();
		logger.info("intramirror send request items end, use time:" + (endTime - startTime) +" ms. shopCount: " + shopCount);
	}



	/**
	 * 下发抓到的FAILURE商品至globalshop
	 */
	//@Scheduled(cron = "7 7/10 * * * ?")
	public void sendFailureItem2erp() {

		logger.info("intramirror send failure items start");
		Long startTime = System.currentTimeMillis();

		JdShopOauthDO shopOauthSo = new JdShopOauthDO();
		shopOauthSo.setChannelNo(ChannelType.IntraMirror.getValue()+"");
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
					logger.error("intramirror send failure items error:",e);
				} catch (Exception e) {
					success = false;
					errorMsg += e.getMessage();
					logger.error("intramirror send failure items error, shopCode: " + shopOauth.getShopCode(), e);
				}
				if(success){
					failureJdItem.setSendStatus(SendStatus.SUCCESS);
					jdItemService.updateByPrimaryKey(failureJdItem);
				}else {
					failureJdItem.setSendStatus(SendStatus.STOP);
					failureJdItem.setErrorMassge(EasyUtil.truncateLEFitSize(errorMsg,1000));
					jdItemService.updateByPrimaryKey(failureJdItem);
				}
			}
		}
		Long endTime = System.currentTimeMillis();
		logger.info("intramirror send failure items end, use time:" + (endTime - startTime) +" ms. shopCount: " + shopCount);
	}


	//@Scheduled(cron = "0/30 * * * * ?")
	public void update4Detail() {

		logger.info("intramirror update4Detail items start");
		Long startTime = System.currentTimeMillis();

		JdShopOauthDO shopOauthSo = new JdShopOauthDO();
		shopOauthSo.setChannelNo(ChannelType.IntraMirror.getValue()+"");
		shopOauthSo.setOpen(true);
		List<JdShopOauthDO> shopOauthDOList = shopOauthService.searchShopOauthList(shopOauthSo);

		Long shopCount= 0L;

		for (JdShopOauthDO shopOauth : shopOauthDOList) {

			JdItemDO jdItemSo = new JdItemDO();
			jdItemSo.setSendStatus(SendStatus.UPDATE);
			jdItemSo.setShopCode(shopOauth.getShopCode());
			jdItemSo.setChannelNo(shopOauth.getChannelNo());

			List<JdItemDO> failureJdItemList = jdItemService.searchJdItemList(jdItemSo);
			Boolean success = true;

			for(JdItemDO failureJdItem : failureJdItemList){
				String errorMsg = "";
				try {
					doUpdate4Detail(failureJdItem);
					shopCount++;
				} catch (ErpCommonException e) {
					success = false;
					errorMsg += e.getErrorMsg();
					logger.error("intramirror update4Detail items error:",e);
				} catch (Exception e) {
					success = false;
					errorMsg += e.getMessage();
					logger.error("intramirror update4Detail items error, shopCode: " + shopOauth.getShopCode(), e);
				}
				if(success){
					failureJdItem.setSendStatus(SendStatus.SUCCESS);
					jdItemService.updateByPrimaryKey(failureJdItem);
				}else {
					failureJdItem.setSendStatus(SendStatus.STOP);
					failureJdItem.setErrorMassge(EasyUtil.truncateLEFitSize(errorMsg,1000));
					jdItemService.updateByPrimaryKey(failureJdItem);
				}
			}
		}
		Long endTime = System.currentTimeMillis();
		logger.info("intramirror update4Detail items end, use time:" + (endTime - startTime) +" ms. shopCount: " + shopCount);
	}



	@Autowired
	private ItemDOMapperExt itemDOMapperExt;

	private void doUpdate4Detail(JdItemDO failureJdItem){

		IMProduct product = JSON.parseObject(failureJdItem.getItemJson(), IMProduct.class);

		if(product == null ){
			throw new ErpCommonException(" IMItem empty","tbspuid: "+failureJdItem.getChannelItemCode());
		}

		String product_description = product.getProduct_description();
		List<String> img_description = new ArrayList<>();
		List<String> picList = JSON.parseObject(product.getDescription_img().get(0), ArrayList.class);
		if(!EasyUtil.isListEmpty(picList)){
			for(String pic : picList){
				img_description.add(pic);
			}
		}

		//准备好了，工具组装代码
		String detail = handleDetail(product_description,img_description);
		ItemDO itemDO = new ItemDO();
		itemDO.setItemCode(failureJdItem.getChannelItemCode());
		itemDO.setDetail(detail);
		itemDOMapperExt.updateItemByItemCode(itemDO);
	}


	private  String handleDetail(String text, List<String> imgList) {
		Integer lineWidth = 500;//一行的宽度
		StringBuilder sb = new StringBuilder("");
		if (null != text && !"".equals(text)) {
			Integer lineCount = text.length()/lineWidth;
			for (int i = 0;i < lineCount;i++) {
				sb.append("<p>");
				sb.append(text.substring(i*lineWidth, i*lineWidth+lineWidth));
				sb.append("</p>");
			}
			sb.append("<p>");
			sb.append(text.substring((lineCount)*lineWidth, text.length()));
			sb.append("</p>");
		}
		if (null != imgList && 0 != imgList.size()) {
			for (String img:imgList) {
				sb.append("<img src=\"");
				sb.append(img);
				sb.append("\"");
				sb.append(" style=\"max-width:100%;\"");
				sb.append(">");
			}
		}

		return sb.toString();
	}



}
