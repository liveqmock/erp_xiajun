package com.wangqin.shopkeeper.biz1.app.dal.mapper;

import com.wangqin.shopkeeper.biz1.app.dal.dataObject.AuthUserRoleDO;
import com.wangqin.shopkeeper.biz1.app.dal.dataObject.AuthUserRoleDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthUserRoleDOMapper {
    int countByExample(AuthUserRoleDOExample example);

    int deleteByExample(AuthUserRoleDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AuthUserRoleDO record);

    int insertSelective(AuthUserRoleDO record);

    List<AuthUserRoleDO> selectByExample(AuthUserRoleDOExample example);

    AuthUserRoleDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AuthUserRoleDO record, @Param("example") AuthUserRoleDOExample example);

    int updateByExample(@Param("record") AuthUserRoleDO record, @Param("example") AuthUserRoleDOExample example);

    int updateByPrimaryKeySelective(AuthUserRoleDO record);

    int updateByPrimaryKey(AuthUserRoleDO record);
}