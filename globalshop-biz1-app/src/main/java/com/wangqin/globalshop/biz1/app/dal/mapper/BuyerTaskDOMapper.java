package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerTaskDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerTaskDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BuyerTaskDOMapper {
    int countByExample(BuyerTaskDOExample example);

    int deleteByExample(BuyerTaskDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BuyerTaskDO record);

    int insertSelective(BuyerTaskDO record);

    List<BuyerTaskDO> selectByExample(BuyerTaskDOExample example);

    BuyerTaskDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BuyerTaskDO record, @Param("example") BuyerTaskDOExample example);

    int updateByExample(@Param("record") BuyerTaskDO record, @Param("example") BuyerTaskDOExample example);

    int updateByPrimaryKeySelective(BuyerTaskDO record);

    int updateByPrimaryKey(BuyerTaskDO record);
}