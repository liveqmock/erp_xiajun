package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShengpaySharingDO;

public interface ShengpaySharingDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShengpaySharingDO record);

    int insertSelective(ShengpaySharingDO record);

    ShengpaySharingDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShengpaySharingDO record);

    int updateByPrimaryKey(ShengpaySharingDO record);
}