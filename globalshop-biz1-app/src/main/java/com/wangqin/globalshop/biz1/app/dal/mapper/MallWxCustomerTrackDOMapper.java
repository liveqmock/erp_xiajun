package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallWxCustomerTrackDO;

public interface MallWxCustomerTrackDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MallWxCustomerTrackDO record);

    int insertSelective(MallWxCustomerTrackDO record);

    MallWxCustomerTrackDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallWxCustomerTrackDO record);

    int updateByPrimaryKey(MallWxCustomerTrackDO record);
}