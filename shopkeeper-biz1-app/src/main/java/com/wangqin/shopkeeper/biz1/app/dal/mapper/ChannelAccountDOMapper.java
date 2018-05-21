package com.wangqin.shopkeeper.biz1.app.dal.mapper;

import com.wangqin.shopkeeper.biz1.app.dal.dataObject.ChannelAccountDO;
import com.wangqin.shopkeeper.biz1.app.dal.dataObject.ChannelAccountDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChannelAccountDOMapper {
    int countByExample(ChannelAccountDOExample example);

    int deleteByExample(ChannelAccountDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ChannelAccountDO record);

    int insertSelective(ChannelAccountDO record);

    List<ChannelAccountDO> selectByExample(ChannelAccountDOExample example);

    ChannelAccountDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ChannelAccountDO record, @Param("example") ChannelAccountDOExample example);

    int updateByExample(@Param("record") ChannelAccountDO record, @Param("example") ChannelAccountDOExample example);

    int updateByPrimaryKeySelective(ChannelAccountDO record);

    int updateByPrimaryKey(ChannelAccountDO record);
}