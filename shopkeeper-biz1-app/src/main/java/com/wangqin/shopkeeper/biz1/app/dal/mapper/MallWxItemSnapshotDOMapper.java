package com.wangqin.shopkeeper.biz1.app.dal.mapper;

import com.wangqin.shopkeeper.biz1.app.dal.dataObject.MallWxItemSnapshotDO;
import com.wangqin.shopkeeper.biz1.app.dal.dataObject.MallWxItemSnapshotDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallWxItemSnapshotDOMapper {
    int countByExample(MallWxItemSnapshotDOExample example);

    int deleteByExample(MallWxItemSnapshotDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MallWxItemSnapshotDO record);

    int insertSelective(MallWxItemSnapshotDO record);

    List<MallWxItemSnapshotDO> selectByExample(MallWxItemSnapshotDOExample example);

    MallWxItemSnapshotDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MallWxItemSnapshotDO record, @Param("example") MallWxItemSnapshotDOExample example);

    int updateByExample(@Param("record") MallWxItemSnapshotDO record, @Param("example") MallWxItemSnapshotDOExample example);

    int updateByPrimaryKeySelective(MallWxItemSnapshotDO record);

    int updateByPrimaryKey(MallWxItemSnapshotDO record);
}