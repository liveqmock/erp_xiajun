package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.IdCardDO;

public interface IdCardDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IdCardDO record);

    int insertSelective(IdCardDO record);

    IdCardDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IdCardDO record);

    int updateByPrimaryKey(IdCardDO record);
}