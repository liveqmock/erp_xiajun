package com.wangqin.shopkeeper.biz1.app.dal.mapper;

import com.wangqin.shopkeeper.biz1.app.dal.dataObject.AuthOrganizationDO;
import com.wangqin.shopkeeper.biz1.app.dal.dataObject.AuthOrganizationDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthOrganizationDOMapper {
    int countByExample(AuthOrganizationDOExample example);

    int deleteByExample(AuthOrganizationDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AuthOrganizationDO record);

    int insertSelective(AuthOrganizationDO record);

    List<AuthOrganizationDO> selectByExample(AuthOrganizationDOExample example);

    AuthOrganizationDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AuthOrganizationDO record, @Param("example") AuthOrganizationDOExample example);

    int updateByExample(@Param("record") AuthOrganizationDO record, @Param("example") AuthOrganizationDOExample example);

    int updateByPrimaryKeySelective(AuthOrganizationDO record);

    int updateByPrimaryKey(AuthOrganizationDO record);
}