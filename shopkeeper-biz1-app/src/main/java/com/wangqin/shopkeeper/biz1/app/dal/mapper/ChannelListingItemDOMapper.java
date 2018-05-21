package com.wangqin.shopkeeper.biz1.app.dal.mapper;

import com.wangqin.shopkeeper.biz1.app.dal.dataObject.ChannelListingItemDO;
import com.wangqin.shopkeeper.biz1.app.dal.dataObject.ChannelListingItemDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChannelListingItemDOMapper {
    int countByExample(ChannelListingItemDOExample example);

    int deleteByExample(ChannelListingItemDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ChannelListingItemDO record);

    int insertSelective(ChannelListingItemDO record);

    List<ChannelListingItemDO> selectByExample(ChannelListingItemDOExample example);

    ChannelListingItemDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ChannelListingItemDO record, @Param("example") ChannelListingItemDOExample example);

    int updateByExample(@Param("record") ChannelListingItemDO record, @Param("example") ChannelListingItemDOExample example);

    int updateByPrimaryKeySelective(ChannelListingItemDO record);

    int updateByPrimaryKey(ChannelListingItemDO record);
}