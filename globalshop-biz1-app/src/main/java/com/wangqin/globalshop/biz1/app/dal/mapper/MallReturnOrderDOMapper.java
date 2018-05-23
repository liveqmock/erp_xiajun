package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallReturnOrderDO;

public interface MallReturnOrderDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MallReturnOrderDO record);

    int insertSelective(MallReturnOrderDO record);

    MallReturnOrderDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallReturnOrderDO record);

    int updateByPrimaryKey(MallReturnOrderDO record);
}