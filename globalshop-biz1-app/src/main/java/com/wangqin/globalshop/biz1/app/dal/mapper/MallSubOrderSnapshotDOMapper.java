package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderSnapshotDO;

public interface MallSubOrderSnapshotDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MallSubOrderSnapshotDO record);

    int insertSelective(MallSubOrderSnapshotDO record);

    MallSubOrderSnapshotDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallSubOrderSnapshotDO record);

    int updateByPrimaryKey(MallSubOrderSnapshotDO record);
}