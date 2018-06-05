package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.SiteMsgDO;

public interface SiteMsgDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SiteMsgDO record);

    int insertSelective(SiteMsgDO record);

    SiteMsgDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SiteMsgDO record);

    int updateByPrimaryKey(SiteMsgDO record);
}