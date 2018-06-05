package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.SysLogDO;

public interface SysLogDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysLogDO record);

    int insertSelective(SysLogDO record);

    SysLogDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysLogDO record);

    int updateByPrimaryKeyWithBLOBs(SysLogDO record);

    int updateByPrimaryKey(SysLogDO record);
}