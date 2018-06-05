package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.SiteMsgReadDO;

public interface SiteMsgReadDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SiteMsgReadDO record);

    int insertSelective(SiteMsgReadDO record);

    SiteMsgReadDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SiteMsgReadDO record);

    int updateByPrimaryKey(SiteMsgReadDO record);
}