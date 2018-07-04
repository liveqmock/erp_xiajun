package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AppletConfigDO;

public interface AppletConfigDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppletConfigDO record);

    int insertSelective(AppletConfigDO record);

    AppletConfigDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppletConfigDO record);

    int updateByPrimaryKey(AppletConfigDO record);
}