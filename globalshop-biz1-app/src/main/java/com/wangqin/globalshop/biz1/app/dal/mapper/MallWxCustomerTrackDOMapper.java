package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallWxCustomerTrackDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallWxCustomerTrackDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallWxCustomerTrackDOMapper {
    int countByExample(MallWxCustomerTrackDOExample example);

    int deleteByExample(MallWxCustomerTrackDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MallWxCustomerTrackDO record);

    int insertSelective(MallWxCustomerTrackDO record);

    List<MallWxCustomerTrackDO> selectByExample(MallWxCustomerTrackDOExample example);

    MallWxCustomerTrackDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MallWxCustomerTrackDO record, @Param("example") MallWxCustomerTrackDOExample example);

    int updateByExample(@Param("record") MallWxCustomerTrackDO record, @Param("example") MallWxCustomerTrackDOExample example);

    int updateByPrimaryKeySelective(MallWxCustomerTrackDO record);

    int updateByPrimaryKey(MallWxCustomerTrackDO record);
}