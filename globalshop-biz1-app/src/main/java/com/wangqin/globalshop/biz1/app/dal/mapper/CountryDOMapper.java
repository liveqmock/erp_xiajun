package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.CountryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.CountryDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CountryDOMapper {
    int countByExample(CountryDOExample example);

    int deleteByExample(CountryDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CountryDO record);

    int insertSelective(CountryDO record);

    List<CountryDO> selectByExample(CountryDOExample example);

    CountryDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CountryDO record, @Param("example") CountryDOExample example);

    int updateByExample(@Param("record") CountryDO record, @Param("example") CountryDOExample example);

    int updateByPrimaryKeySelective(CountryDO record);

    int updateByPrimaryKey(CountryDO record);
}