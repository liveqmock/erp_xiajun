package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.WxUserDO;

public interface WxUserDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxUserDO record);

    int insertSelective(WxUserDO record);

    WxUserDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxUserDO record);

    int updateByPrimaryKey(WxUserDO record);
}