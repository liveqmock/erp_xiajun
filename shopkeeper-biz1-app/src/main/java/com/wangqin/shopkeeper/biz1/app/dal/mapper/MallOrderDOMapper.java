package com.wangqin.shopkeeper.biz1.app.dal.mapper;

import com.wangqin.shopkeeper.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.shopkeeper.biz1.app.dal.dataObject.MallOrderDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallOrderDOMapper {
    int countByExample(MallOrderDOExample example);

    int deleteByExample(MallOrderDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MallOrderDO record);

    int insertSelective(MallOrderDO record);

    List<MallOrderDO> selectByExample(MallOrderDOExample example);

    MallOrderDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MallOrderDO record, @Param("example") MallOrderDOExample example);

    int updateByExample(@Param("record") MallOrderDO record, @Param("example") MallOrderDOExample example);

    int updateByPrimaryKeySelective(MallOrderDO record);

    int updateByPrimaryKey(MallOrderDO record);
}