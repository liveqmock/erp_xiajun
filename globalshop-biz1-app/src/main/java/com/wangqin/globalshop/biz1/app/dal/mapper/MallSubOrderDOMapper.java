package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallSubOrderDOMapper {
    int countByExample(MallSubOrderDOExample example);

    int deleteByExample(MallSubOrderDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MallSubOrderDO record);

    int insertSelective(MallSubOrderDO record);

    List<MallSubOrderDO> selectByExample(MallSubOrderDOExample example);

    MallSubOrderDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MallSubOrderDO record, @Param("example") MallSubOrderDOExample example);

    int updateByExample(@Param("record") MallSubOrderDO record, @Param("example") MallSubOrderDOExample example);

    int updateByPrimaryKeySelective(MallSubOrderDO record);

    int updateByPrimaryKey(MallSubOrderDO record);
}