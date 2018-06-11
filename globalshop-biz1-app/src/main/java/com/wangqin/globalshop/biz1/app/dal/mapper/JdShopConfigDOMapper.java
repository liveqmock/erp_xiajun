package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopConfigDO;

public interface JdShopConfigDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JdShopConfigDO record);

    int insertSelective(JdShopConfigDO record);

    JdShopConfigDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JdShopConfigDO record);

    int updateByPrimaryKey(JdShopConfigDO record);
}