package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallCustomerDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallCustomerDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallCustomerDOMapper {
    int countByExample(MallCustomerDOExample example);

    int deleteByExample(MallCustomerDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MallCustomerDO record);

    int insertSelective(MallCustomerDO record);

    List<MallCustomerDO> selectByExample(MallCustomerDOExample example);

    MallCustomerDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MallCustomerDO record, @Param("example") MallCustomerDOExample example);

    int updateByExample(@Param("record") MallCustomerDO record, @Param("example") MallCustomerDOExample example);

    int updateByPrimaryKeySelective(MallCustomerDO record);

    int updateByPrimaryKey(MallCustomerDO record);
}