package com.wangqin.globalshop.channel.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelListingItemDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelListingItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.enums.ItemStatus;
import com.wangqin.globalshop.biz1.app.enums.PlatformType;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.dal.youzan.ItemImages;
import com.wangqin.globalshop.channel.dal.youzan.PicModel;
import com.wangqin.globalshop.channel.dal.youzan.YouzanOauthResponse;
import com.wangqin.globalshop.channelapi.dal.ItemSkuVo;
import com.wangqin.globalshop.channelapi.dal.ItemVo;
import com.wangqin.globalshop.common.utils.HaiJsonUtils;
import com.youzan.open.sdk.client.auth.Token;
import com.youzan.open.sdk.client.core.DefaultYZClient;
import com.youzan.open.sdk.client.core.YZClient;
import com.youzan.open.sdk.gen.v3_0_0.api.YouzanItemCreate;
import com.youzan.open.sdk.gen.v3_0_0.api.YouzanMaterialsStoragePlatformImgUpload;
import com.youzan.open.sdk.gen.v3_0_0.api.YouzanShopGet;
import com.youzan.open.sdk.gen.v3_0_0.model.*;
import com.youzan.open.sdk.model.ByteWrapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.entity.ContentType;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * Create by 777 on 2018/8/9
 */

@Service
public class YouzanService {

	public volatile  YZClient client = new DefaultYZClient();

	public YouzanShopGetResult getShopInfo(YouzanOauthResponse oauthResponse){
		client = new DefaultYZClient(new Token(oauthResponse.getAccess_token()));
		YouzanShopGetParams youzanShopGetParams = new YouzanShopGetParams();
		YouzanShopGet youzanShopGet = new YouzanShopGet();
		youzanShopGet.setAPIParams(youzanShopGetParams);
		YouzanShopGetResult result = client.invoke(youzanShopGet);
		return result;
	}


}
