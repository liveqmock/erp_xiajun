package com.wangqin.shopkeeper.biz1.app.dal.mapper;

import com.wangqin.shopkeeper.biz1.app.dal.dataObject.LogisticCompanyDO;
import com.wangqin.shopkeeper.biz1.app.dal.dataObject.LogisticCompanyDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogisticCompanyDOMapper {
    int countByExample(LogisticCompanyDOExample example);

    int deleteByExample(LogisticCompanyDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LogisticCompanyDO record);

    int insertSelective(LogisticCompanyDO record);

    List<LogisticCompanyDO> selectByExample(LogisticCompanyDOExample example);

    LogisticCompanyDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LogisticCompanyDO record, @Param("example") LogisticCompanyDOExample example);

    int updateByExample(@Param("record") LogisticCompanyDO record, @Param("example") LogisticCompanyDOExample example);

    int updateByPrimaryKeySelective(LogisticCompanyDO record);

    int updateByPrimaryKey(LogisticCompanyDO record);
}