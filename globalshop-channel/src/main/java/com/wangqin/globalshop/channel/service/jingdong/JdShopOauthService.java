package com.wangqin.globalshop.channel.service.jingdong;

import com.wangqin.globalshop.biz1.app.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;

import java.util.List;

/**
 * Create by 777 on 2018/6/11
 */
public interface JdShopOauthService {

	public int deleteByPrimaryKey(Long id);

	public int insert(JdShopOauthDO record);

	public int insertSelective(JdShopOauthDO record);

	public JdShopOauthDO selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(JdShopOauthDO record);

	public int updateByPrimaryKey(JdShopOauthDO record);

	public JdShopOauthDO searchShopOauth(JdShopOauthDO jdShopOauthDO);

	public List<JdShopOauthDO> searchShopOauthList(JdShopOauthDO jdShopOauthDO);

	public Long searchShopOauthCount(JdShopOauthDO jdShopOauthDO);

	public void createOrUpdateShopOauth(ChannelType channelType, JdShopOauthDO jdShopOauthDO);

	public JdShopOauthDO searchShopOauthByCCS(String channelNo, String companyNo, String shopCode);
}
