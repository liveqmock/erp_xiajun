package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ReactVersionDO;

public interface ReactVersionDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ReactVersionDO record);

    int insertSelective(ReactVersionDO record);

    ReactVersionDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReactVersionDO record);

    int updateByPrimaryKey(ReactVersionDO record);
}