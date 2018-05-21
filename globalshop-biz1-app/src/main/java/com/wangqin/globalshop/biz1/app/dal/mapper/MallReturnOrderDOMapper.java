package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallReturnOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallReturnOrderDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallReturnOrderDOMapper {
    int countByExample(MallReturnOrderDOExample example);

    int deleteByExample(MallReturnOrderDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MallReturnOrderDO record);

    int insertSelective(MallReturnOrderDO record);

    List<MallReturnOrderDO> selectByExample(MallReturnOrderDOExample example);

    MallReturnOrderDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MallReturnOrderDO record, @Param("example") MallReturnOrderDOExample example);

    int updateByExample(@Param("record") MallReturnOrderDO record, @Param("example") MallReturnOrderDOExample example);

    int updateByPrimaryKeySelective(MallReturnOrderDO record);

    int updateByPrimaryKey(MallReturnOrderDO record);
}