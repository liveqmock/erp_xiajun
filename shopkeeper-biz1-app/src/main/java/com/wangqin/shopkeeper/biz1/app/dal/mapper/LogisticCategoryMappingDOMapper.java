package com.wangqin.shopkeeper.biz1.app.dal.mapper;

import com.wangqin.shopkeeper.biz1.app.dal.dataObject.LogisticCategoryMappingDO;
import com.wangqin.shopkeeper.biz1.app.dal.dataObject.LogisticCategoryMappingDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogisticCategoryMappingDOMapper {
    int countByExample(LogisticCategoryMappingDOExample example);

    int deleteByExample(LogisticCategoryMappingDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LogisticCategoryMappingDO record);

    int insertSelective(LogisticCategoryMappingDO record);

    List<LogisticCategoryMappingDO> selectByExample(LogisticCategoryMappingDOExample example);

    LogisticCategoryMappingDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LogisticCategoryMappingDO record, @Param("example") LogisticCategoryMappingDOExample example);

    int updateByExample(@Param("record") LogisticCategoryMappingDO record, @Param("example") LogisticCategoryMappingDOExample example);

    int updateByPrimaryKeySelective(LogisticCategoryMappingDO record);

    int updateByPrimaryKey(LogisticCategoryMappingDO record);
}