package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthRoleDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthRoleDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthRoleDOMapper {
    int countByExample(AuthRoleDOExample example);

    int deleteByExample(AuthRoleDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AuthRoleDO record);

    int insertSelective(AuthRoleDO record);

    List<AuthRoleDO> selectByExample(AuthRoleDOExample example);

    AuthRoleDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AuthRoleDO record, @Param("example") AuthRoleDOExample example);

    int updateByExample(@Param("record") AuthRoleDO record, @Param("example") AuthRoleDOExample example);

    int updateByPrimaryKeySelective(AuthRoleDO record);

    int updateByPrimaryKey(AuthRoleDO record);
}