package com.wangqin.globalshop.channel.service.jingdong;

import com.wangqin.globalshop.biz1.app.constants.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;

/**
 * Create by 777 on 2018/6/11
 */
public interface JdShopConfigService {

	public void initShopConfig(ChannelType channelType, JdShopOauthDO jdShopOauthDO);

}
