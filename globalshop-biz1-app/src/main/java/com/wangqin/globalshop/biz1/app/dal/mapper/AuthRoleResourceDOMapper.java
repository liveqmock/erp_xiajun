package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthRoleResourceDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthRoleResourceDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthRoleResourceDOMapper {
    int countByExample(AuthRoleResourceDOExample example);

    int deleteByExample(AuthRoleResourceDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AuthRoleResourceDO record);

    int insertSelective(AuthRoleResourceDO record);

    List<AuthRoleResourceDO> selectByExample(AuthRoleResourceDOExample example);

    AuthRoleResourceDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AuthRoleResourceDO record, @Param("example") AuthRoleResourceDOExample example);

    int updateByExample(@Param("record") AuthRoleResourceDO record, @Param("example") AuthRoleResourceDOExample example);

    int updateByPrimaryKeySelective(AuthRoleResourceDO record);

    int updateByPrimaryKey(AuthRoleResourceDO record);
}