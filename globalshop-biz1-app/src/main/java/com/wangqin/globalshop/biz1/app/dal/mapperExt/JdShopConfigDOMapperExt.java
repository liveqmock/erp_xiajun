package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopConfigDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.JdShopConfigDOMapper;

import java.util.List;

/**
 * Create by 777 on 2018/6/11
 */
public interface JdShopConfigDOMapperExt extends JdShopConfigDOMapper {

	public JdShopConfigDO searchShopConfig(JdShopConfigDO JdShopConfigDO);

	public List<JdShopConfigDO> searchShopConfigList(JdShopConfigDO JdShopConfigDO);

	public Long searchShopConfigCount(JdShopConfigDO JdShopConfigDO);
}
