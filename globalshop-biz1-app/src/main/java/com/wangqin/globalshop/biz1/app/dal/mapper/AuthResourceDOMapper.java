package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthResourceDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthResourceDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthResourceDOMapper {
    int countByExample(AuthResourceDOExample example);

    int deleteByExample(AuthResourceDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AuthResourceDO record);

    int insertSelective(AuthResourceDO record);

    List<AuthResourceDO> selectByExample(AuthResourceDOExample example);

    AuthResourceDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AuthResourceDO record, @Param("example") AuthResourceDOExample example);

    int updateByExample(@Param("record") AuthResourceDO record, @Param("example") AuthResourceDOExample example);

    int updateByPrimaryKeySelective(AuthResourceDO record);

    int updateByPrimaryKey(AuthResourceDO record);
}