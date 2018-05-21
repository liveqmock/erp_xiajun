package com.wangqin.shopkeeper.biz1.app.dal.mapper;

import com.wangqin.shopkeeper.biz1.app.dal.dataObject.AuthUserDO;
import com.wangqin.shopkeeper.biz1.app.dal.dataObject.AuthUserDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthUserDOMapper {
    int countByExample(AuthUserDOExample example);

    int deleteByExample(AuthUserDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AuthUserDO record);

    int insertSelective(AuthUserDO record);

    List<AuthUserDO> selectByExample(AuthUserDOExample example);

    AuthUserDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AuthUserDO record, @Param("example") AuthUserDOExample example);

    int updateByExample(@Param("record") AuthUserDO record, @Param("example") AuthUserDOExample example);

    int updateByPrimaryKeySelective(AuthUserDO record);

    int updateByPrimaryKey(AuthUserDO record);
}