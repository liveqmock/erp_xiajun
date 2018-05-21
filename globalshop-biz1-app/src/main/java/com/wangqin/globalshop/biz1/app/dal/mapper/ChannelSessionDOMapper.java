package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelSessionDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelSessionDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChannelSessionDOMapper {
    int countByExample(ChannelSessionDOExample example);

    int deleteByExample(ChannelSessionDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ChannelSessionDO record);

    int insertSelective(ChannelSessionDO record);

    List<ChannelSessionDO> selectByExample(ChannelSessionDOExample example);

    ChannelSessionDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ChannelSessionDO record, @Param("example") ChannelSessionDOExample example);

    int updateByExample(@Param("record") ChannelSessionDO record, @Param("example") ChannelSessionDOExample example);

    int updateByPrimaryKeySelective(ChannelSessionDO record);

    int updateByPrimaryKey(ChannelSessionDO record);
}