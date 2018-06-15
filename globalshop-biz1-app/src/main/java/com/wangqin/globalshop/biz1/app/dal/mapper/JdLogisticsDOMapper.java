package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.JdLogisticsDO;

public interface JdLogisticsDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JdLogisticsDO record);

    int insertSelective(JdLogisticsDO record);

    JdLogisticsDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JdLogisticsDO record);

    int updateByPrimaryKey(JdLogisticsDO record);
}