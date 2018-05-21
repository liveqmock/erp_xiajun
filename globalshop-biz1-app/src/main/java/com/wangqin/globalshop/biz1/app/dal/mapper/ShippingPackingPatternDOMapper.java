package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingPackingPatternDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingPackingPatternDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShippingPackingPatternDOMapper {
    int countByExample(ShippingPackingPatternDOExample example);

    int deleteByExample(ShippingPackingPatternDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShippingPackingPatternDO record);

    int insertSelective(ShippingPackingPatternDO record);

    List<ShippingPackingPatternDO> selectByExample(ShippingPackingPatternDOExample example);

    ShippingPackingPatternDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShippingPackingPatternDO record, @Param("example") ShippingPackingPatternDOExample example);

    int updateByExample(@Param("record") ShippingPackingPatternDO record, @Param("example") ShippingPackingPatternDOExample example);

    int updateByPrimaryKeySelective(ShippingPackingPatternDO record);

    int updateByPrimaryKey(ShippingPackingPatternDO record);
}