package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.JdShopOauthDOMapper;

import java.util.List;

/**
 * Create by 777 on 2018/6/11
 */
public interface JdShopOauthDOMapperExt extends JdShopOauthDOMapper {

	public JdShopOauthDO searchShopOauth(JdShopOauthDO jdShopOauthDO);

	public List<JdShopOauthDO> searchShopOauthList(JdShopOauthDO jdShopOauthDO);

	public Long searchShopOauthCount(JdShopOauthDO jdShopOauthDO);




}
