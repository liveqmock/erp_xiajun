package com.wangqin.globalshop.mall.dal.mapperExt;

import org.apache.ibatis.annotations.Param;

import src.main.java.com.wangqin.globalshop.biz1.app.dal.dataObject.MallCustomerDO;
import src.main.java.com.wangqin.globalshop.biz1.app.dal.mapper.MallCustomerDOMapper;

public interface MallCustomerDOMapperExt extends MallCustomerDOMapper {

    MallCustomerDO selectByOpenId(@Param("openId") String openId);
}
