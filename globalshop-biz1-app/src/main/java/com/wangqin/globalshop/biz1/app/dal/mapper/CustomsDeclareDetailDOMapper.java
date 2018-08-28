package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.CustomsDeclareDetailDO;

public interface CustomsDeclareDetailDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomsDeclareDetailDO record);

    int insertSelective(CustomsDeclareDetailDO record);

    CustomsDeclareDetailDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomsDeclareDetailDO record);

    int updateByPrimaryKey(CustomsDeclareDetailDO record);
}