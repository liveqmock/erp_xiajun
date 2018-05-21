package com.wangqin.shopkeeper.biz1.app.dal.mapper;

import com.wangqin.shopkeeper.biz1.app.dal.dataObject.CompanyDO;
import com.wangqin.shopkeeper.biz1.app.dal.dataObject.CompanyDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CompanyDOMapper {
    int countByExample(CompanyDOExample example);

    int deleteByExample(CompanyDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CompanyDO record);

    int insertSelective(CompanyDO record);

    List<CompanyDO> selectByExampleWithBLOBs(CompanyDOExample example);

    List<CompanyDO> selectByExample(CompanyDOExample example);

    CompanyDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CompanyDO record, @Param("example") CompanyDOExample example);

    int updateByExampleWithBLOBs(@Param("record") CompanyDO record, @Param("example") CompanyDOExample example);

    int updateByExample(@Param("record") CompanyDO record, @Param("example") CompanyDOExample example);

    int updateByPrimaryKeySelective(CompanyDO record);

    int updateByPrimaryKeyWithBLOBs(CompanyDO record);

    int updateByPrimaryKey(CompanyDO record);
}