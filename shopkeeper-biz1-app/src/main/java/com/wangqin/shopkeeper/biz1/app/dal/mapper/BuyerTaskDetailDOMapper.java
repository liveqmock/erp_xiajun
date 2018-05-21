package com.wangqin.shopkeeper.biz1.app.dal.mapper;

import com.wangqin.shopkeeper.biz1.app.dal.dataObject.BuyerTaskDetailDO;
import com.wangqin.shopkeeper.biz1.app.dal.dataObject.BuyerTaskDetailDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BuyerTaskDetailDOMapper {
    int countByExample(BuyerTaskDetailDOExample example);

    int deleteByExample(BuyerTaskDetailDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BuyerTaskDetailDO record);

    int insertSelective(BuyerTaskDetailDO record);

    List<BuyerTaskDetailDO> selectByExample(BuyerTaskDetailDOExample example);

    BuyerTaskDetailDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BuyerTaskDetailDO record, @Param("example") BuyerTaskDetailDOExample example);

    int updateByExample(@Param("record") BuyerTaskDetailDO record, @Param("example") BuyerTaskDetailDOExample example);

    int updateByPrimaryKeySelective(BuyerTaskDetailDO record);

    int updateByPrimaryKey(BuyerTaskDetailDO record);
}