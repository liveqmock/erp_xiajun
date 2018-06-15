package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;

public interface JdShopOauthDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JdShopOauthDO record);

    int insertSelective(JdShopOauthDO record);

    JdShopOauthDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JdShopOauthDO record);

    int updateByPrimaryKey(JdShopOauthDO record);
}