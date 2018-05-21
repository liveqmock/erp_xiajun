package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallWxItemSnapshotDO;

public interface MallWxItemSnapshotDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MallWxItemSnapshotDO record);

    int insertSelective(MallWxItemSnapshotDO record);

    MallWxItemSnapshotDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallWxItemSnapshotDO record);

    int updateByPrimaryKey(MallWxItemSnapshotDO record);
}