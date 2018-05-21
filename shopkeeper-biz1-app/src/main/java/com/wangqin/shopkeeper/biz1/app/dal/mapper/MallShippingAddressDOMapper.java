package com.wangqin.shopkeeper.biz1.app.dal.mapper;

import com.wangqin.shopkeeper.biz1.app.dal.dataObject.MallShippingAddressDO;
import com.wangqin.shopkeeper.biz1.app.dal.dataObject.MallShippingAddressDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallShippingAddressDOMapper {
    int countByExample(MallShippingAddressDOExample example);

    int deleteByExample(MallShippingAddressDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MallShippingAddressDO record);

    int insertSelective(MallShippingAddressDO record);

    List<MallShippingAddressDO> selectByExample(MallShippingAddressDOExample example);

    MallShippingAddressDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MallShippingAddressDO record, @Param("example") MallShippingAddressDOExample example);

    int updateByExample(@Param("record") MallShippingAddressDO record, @Param("example") MallShippingAddressDOExample example);

    int updateByPrimaryKeySelective(MallShippingAddressDO record);

    int updateByPrimaryKey(MallShippingAddressDO record);
}