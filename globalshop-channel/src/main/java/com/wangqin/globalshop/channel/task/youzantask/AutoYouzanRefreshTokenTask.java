package com.wangqin.globalshop.channel.task.youzantask;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelShopDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;
import com.wangqin.globalshop.biz1.app.enums.ChannelType;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.dal.youzan.YouzanOauthResponse;
import com.wangqin.globalshop.channel.service.YouzanService;
import com.wangqin.globalshop.channel.service.channel.ChannelShopService;
import com.wangqin.globalshop.channel.service.jingdong.JdShopOauthService;
import com.wangqin.globalshop.common.base.BaseDto;
import com.wangqin.globalshop.common.utils.EasyUtil;
import com.wangqin.globalshop.common.utils.HttpPostUtil;
import com.youzan.open.sdk.gen.v3_0_0.model.YouzanShopGetResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by 777 on 2018/8/14
 */
@Component
public class AutoYouzanRefreshTokenTask {

	private static Logger logger = LoggerFactory.getLogger("AutoYouzanRefreshTokenTask");

	public static final String youzan_client_id = "29f22f7d615e50079f";

	public static final String youzan_client_secret = "efb0091fe76678322000af822c705c0a";

	public static final String youzan_token_url = "https://open.youzan.com/oauth/token";

	public static final String youzan_server_url = "";//现在用sdk，不用URL

	public static final Long advenceTime = 12*60*60*1000L;//提前12小时刷新token



	@Autowired
	private YouzanService youzanService;

	@Autowired
	private JdShopOauthService jdShopOauthService;

	@Autowired
	private ChannelShopService channelShopService;


	@Autowired
	private TransactionTemplate transactionTemplate;

	// 每天0点，12点各跑一次
	//@Scheduled(cron = "0/30 * * * * ?")
	@Scheduled(cron = "0 0 0,12 * * ?")
	public void run() {

		logger.info("youzan refresh token task ===>Start");

		Long shopCount = 0L;
		Date now = new Date();
        Date expiresTime = new Date(now.getTime()+advenceTime);

		List<JdShopOauthDO> expireShopOauthList = jdShopOauthService.searchExpireShopOauthList(expiresTime,ChannelType.YouZan.getValue());

		for(JdShopOauthDO expireShopOauth : expireShopOauthList){
			try {
				doRefreshToken(expireShopOauth);
				shopCount++;
			} catch (Exception e) {
				logger.error("",e);
			}
		}

		logger.info("youzan refresh token task===>End. shopCount: "+shopCount);

	}


	private void doRefreshToken(JdShopOauthDO expireShopOauth){

		Map<String, String> param = new HashMap<String, String>();
		param.put("grant_type", "refresh_token");//用于刷新令牌 access_token 的 refresh_token（过期时间：28 天）
		param.put("refresh_token", expireShopOauth.getRefreshToken());
		param.put("client_id", youzan_client_id);
		param.put("client_secret", youzan_client_secret);

		YouzanOauthResponse oauthResponse = null;
		try {
			logger.info("refresh token param: "+BaseDto.toString(param));
			String str = HttpPostUtil.doHttpPost(youzan_token_url, param);
			logger.info(str);
			oauthResponse = BaseDto.fromJson(str, YouzanOauthResponse.class);
			if (oauthResponse == null || EasyUtil.isStringEmpty(oauthResponse.getRefresh_token()) || EasyUtil.isStringEmpty(oauthResponse.getAccess_token())) {
				logger.error("refresh token error。" + oauthResponse.getError() + ":" + oauthResponse.getError_description());
			}
		} catch (ErpCommonException e) {
			logger.error(e.getErrorCode() + e.getMessage());
		} catch (Exception e) {
			logger.error("refresh token error", e);
		}

		//获取店铺信息
		YouzanShopGetResult shopGetResult = youzanService.getShopInfo(oauthResponse);

		if(shopGetResult == null){
			throw new ErpCommonException("YouzanShopGetResult error",BaseDto.toString(oauthResponse));
		}

		JdShopOauthDO shopOauth = new JdShopOauthDO();

		shopOauth.setChannelNo(String.valueOf(ChannelType.YouZan.getValue()));
		shopOauth.setCompanyNo(expireShopOauth.getCompanyNo());
		shopOauth.setShopCode(shopGetResult.getId()+"");
		shopOauth.setAccessToken(oauthResponse.getAccess_token());

		shopOauth.setGmtModify(new Date());//授权时间
		shopOauth.setExpiresTime(new Date(System.currentTimeMillis() + oauthResponse.getExpires_in() * 1000));//测试token时效1天，生产1年
		shopOauth.setRefreshToken(oauthResponse.getRefresh_token());
		shopOauth.setOpen(true);
		shopOauth.setServerUrl(youzan_server_url);
		shopOauth.setAppKey(youzan_client_id);
		shopOauth.setAppsecretKey(youzan_client_secret);
		shopOauth.setTokenUrl(youzan_token_url);
		shopOauth.setShopType(oauthResponse.getScope());

		//组装channelShop
		ChannelShopDO channelShop = new ChannelShopDO();
		channelShop.setChannelNo(String.valueOf(ChannelType.YouZan.getValue()));
		channelShop.setCompanyNo(expireShopOauth.getCompanyNo());
		channelShop.setShopCode(shopOauth.getShopCode());
		channelShop.setShopName(shopGetResult.getName());
		channelShop.setExpiresTime(shopOauth.getExpiresTime());
		channelShop.setProxyUrl(shopGetResult.getLogo());
		channelShop.setOpen(true);
		try {
			transactionTemplate.execute(new TransactionCallback<Boolean>() {
				@Override
				public Boolean doInTransaction(TransactionStatus transactionStatus) {
					doOauth(shopOauth,channelShop);
					return Boolean.TRUE;
				}
			});
		} catch (ErpCommonException e){
			logger.error("",e);
		} catch (Exception e) {
			logger.error("",e);
		}


	}


	private void doOauth(JdShopOauthDO shopOauth, ChannelShopDO channelShop){
		//创建或更新jd_shop_oauth
		jdShopOauthService.createOrUpdateShopOauth(ChannelType.YouZan,shopOauth);
		//创建或更新jd_shop_config
		//jdShopConfigService.initShopConfig(ChannelType.YouZan,shopOauth);
		//下发创建或更新  channel_shop
		channelShopService.createOrUpdate(channelShop);
	}
}
