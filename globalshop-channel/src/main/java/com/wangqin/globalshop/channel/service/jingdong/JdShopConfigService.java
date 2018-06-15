package com.wangqin.globalshop.channel.service.jingdong;

import com.wangqin.globalshop.biz1.app.constants.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopConfigDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;

import java.util.List;

/**
 * Create by 777 on 2018/6/11
 */
public interface JdShopConfigService {

	public void initShopConfig(ChannelType channelType, JdShopOauthDO jdShopOauthDO);

	public JdShopConfigDO searchShopConfig(JdShopConfigDO jdShopConfigDO);

	public List<JdShopConfigDO> searchShopConfigList(JdShopConfigDO jdShopConfigDO);

	public Long searchShopConfigCount(JdShopConfigDO jdShopConfigDO);


	public int deleteByPrimaryKey(Long id);

	public int insert(JdShopConfigDO record);

	public int insertSelective(JdShopConfigDO record);

	public JdShopConfigDO selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(JdShopConfigDO record);

	public int updateByPrimaryKey(JdShopConfigDO record);

}
