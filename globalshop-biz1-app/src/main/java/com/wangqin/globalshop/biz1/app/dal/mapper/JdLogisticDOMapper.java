package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.JdLogisticDO;

public interface JdLogisticDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JdLogisticDO record);

    int insertSelective(JdLogisticDO record);

    JdLogisticDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JdLogisticDO record);

    int updateByPrimaryKey(JdLogisticDO record);
}