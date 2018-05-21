package com.wangqin.shopkeeper.biz1.app.dal.mapper;

import com.wangqin.shopkeeper.biz1.app.dal.dataObject.ReactVersionDO;
import com.wangqin.shopkeeper.biz1.app.dal.dataObject.ReactVersionDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReactVersionDOMapper {
    int countByExample(ReactVersionDOExample example);

    int deleteByExample(ReactVersionDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ReactVersionDO record);

    int insertSelective(ReactVersionDO record);

    List<ReactVersionDO> selectByExample(ReactVersionDOExample example);

    ReactVersionDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ReactVersionDO record, @Param("example") ReactVersionDOExample example);

    int updateByExample(@Param("record") ReactVersionDO record, @Param("example") ReactVersionDOExample example);

    int updateByPrimaryKeySelective(ReactVersionDO record);

    int updateByPrimaryKey(ReactVersionDO record);
}