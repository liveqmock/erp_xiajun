package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.CountryDO;

public interface CountryDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CountryDO record);

    int insertSelective(CountryDO record);

    CountryDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CountryDO record);

    int updateByPrimaryKey(CountryDO record);
}