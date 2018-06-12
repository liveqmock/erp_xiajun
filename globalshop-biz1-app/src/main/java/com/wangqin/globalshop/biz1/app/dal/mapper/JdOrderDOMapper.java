package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.JdOrderDO;

public interface JdOrderDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JdOrderDO record);

    int insertSelective(JdOrderDO record);

    JdOrderDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JdOrderDO record);

    int updateByPrimaryKeyWithBLOBs(JdOrderDO record);

    int updateByPrimaryKey(JdOrderDO record);
}