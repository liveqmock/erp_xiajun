package com.wangqin.shopkeeper.biz1.app.dal.mapper;

import com.wangqin.shopkeeper.biz1.app.dal.dataObject.SequenceDO;
import com.wangqin.shopkeeper.biz1.app.dal.dataObject.SequenceDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SequenceDOMapper {
    int countByExample(SequenceDOExample example);

    int deleteByExample(SequenceDOExample example);

    int deleteByPrimaryKey(String name);

    int insert(SequenceDO record);

    int insertSelective(SequenceDO record);

    List<SequenceDO> selectByExample(SequenceDOExample example);

    SequenceDO selectByPrimaryKey(String name);

    int updateByExampleSelective(@Param("record") SequenceDO record, @Param("example") SequenceDOExample example);

    int updateByExample(@Param("record") SequenceDO record, @Param("example") SequenceDOExample example);

    int updateByPrimaryKeySelective(SequenceDO record);

    int updateByPrimaryKey(SequenceDO record);
}