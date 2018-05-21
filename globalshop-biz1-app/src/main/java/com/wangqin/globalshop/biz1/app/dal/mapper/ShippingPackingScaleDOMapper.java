package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingPackingScaleDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingPackingScaleDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShippingPackingScaleDOMapper {
    int countByExample(ShippingPackingScaleDOExample example);

    int deleteByExample(ShippingPackingScaleDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShippingPackingScaleDO record);

    int insertSelective(ShippingPackingScaleDO record);

    List<ShippingPackingScaleDO> selectByExample(ShippingPackingScaleDOExample example);

    ShippingPackingScaleDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShippingPackingScaleDO record, @Param("example") ShippingPackingScaleDOExample example);

    int updateByExample(@Param("record") ShippingPackingScaleDO record, @Param("example") ShippingPackingScaleDOExample example);

    int updateByPrimaryKeySelective(ShippingPackingScaleDO record);

    int updateByPrimaryKey(ShippingPackingScaleDO record);
}