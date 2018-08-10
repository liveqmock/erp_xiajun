package com.wangqin.globalshop.channel.service;

import com.wangqin.globalshop.channel.dal.youzan.YouzanOauthResponse;
import com.youzan.open.sdk.client.auth.Token;
import com.youzan.open.sdk.client.core.DefaultYZClient;
import com.youzan.open.sdk.client.core.YZClient;
import com.youzan.open.sdk.gen.v3_0_0.api.YouzanShopGet;
import com.youzan.open.sdk.gen.v3_0_0.model.YouzanShopGetParams;
import com.youzan.open.sdk.gen.v3_0_0.model.YouzanShopGetResult;
import org.springframework.stereotype.Service;

/**
 * Create by 777 on 2018/8/9
 */

@Service
public class YouzanService {

	public volatile  YZClient client = null;

	public YouzanShopGetResult getShopInfo(YouzanOauthResponse oauthResponse){
		client = new DefaultYZClient(new Token(oauthResponse.getAccess_token()));
		YouzanShopGetParams youzanShopGetParams = new YouzanShopGetParams();
		YouzanShopGet youzanShopGet = new YouzanShopGet();
		youzanShopGet.setAPIParams(youzanShopGetParams);
		YouzanShopGetResult result = client.invoke(youzanShopGet);
		return result;
	}








}
