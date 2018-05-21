package com.wangqin.shopkeeper.biz1.app.dal.mapper;

import com.wangqin.shopkeeper.biz1.app.dal.dataObject.ChannelListingItemSkuDO;
import com.wangqin.shopkeeper.biz1.app.dal.dataObject.ChannelListingItemSkuDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChannelListingItemSkuDOMapper {
    int countByExample(ChannelListingItemSkuDOExample example);

    int deleteByExample(ChannelListingItemSkuDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ChannelListingItemSkuDO record);

    int insertSelective(ChannelListingItemSkuDO record);

    List<ChannelListingItemSkuDO> selectByExample(ChannelListingItemSkuDOExample example);

    ChannelListingItemSkuDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ChannelListingItemSkuDO record, @Param("example") ChannelListingItemSkuDOExample example);

    int updateByExample(@Param("record") ChannelListingItemSkuDO record, @Param("example") ChannelListingItemSkuDOExample example);

    int updateByPrimaryKeySelective(ChannelListingItemSkuDO record);

    int updateByPrimaryKey(ChannelListingItemSkuDO record);
}