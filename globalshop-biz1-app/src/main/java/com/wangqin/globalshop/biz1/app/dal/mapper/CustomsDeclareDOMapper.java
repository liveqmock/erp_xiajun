package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.CustomsDeclareDO;

public interface CustomsDeclareDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomsDeclareDO record);

    int insertSelective(CustomsDeclareDO record);

    CustomsDeclareDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomsDeclareDO record);

    int updateByPrimaryKey(CustomsDeclareDO record);
}