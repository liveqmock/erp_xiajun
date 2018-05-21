package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChannelDOMapper {
    int countByExample(ChannelDOExample example);

    int deleteByExample(ChannelDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ChannelDO record);

    int insertSelective(ChannelDO record);

    List<ChannelDO> selectByExample(ChannelDOExample example);

    ChannelDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ChannelDO record, @Param("example") ChannelDOExample example);

    int updateByExample(@Param("record") ChannelDO record, @Param("example") ChannelDOExample example);

    int updateByPrimaryKeySelective(ChannelDO record);

    int updateByPrimaryKey(ChannelDO record);
}