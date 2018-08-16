package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShengpaySharingItemDO;

public interface ShengpaySharingItemDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShengpaySharingItemDO record);

    int insertSelective(ShengpaySharingItemDO record);

    ShengpaySharingItemDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShengpaySharingItemDO record);

    int updateByPrimaryKey(ShengpaySharingItemDO record);
}