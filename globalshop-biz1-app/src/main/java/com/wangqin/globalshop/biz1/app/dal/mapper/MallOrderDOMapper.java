package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;

public interface MallOrderDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MallOrderDO record);

    int insertSelective(MallOrderDO record);

    MallOrderDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallOrderDO record);



}