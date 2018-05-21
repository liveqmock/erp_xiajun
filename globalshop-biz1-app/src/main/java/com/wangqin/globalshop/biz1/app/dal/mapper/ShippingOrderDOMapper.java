package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingOrderDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShippingOrderDOMapper {
    int countByExample(ShippingOrderDOExample example);

    int deleteByExample(ShippingOrderDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShippingOrderDO record);

    int insertSelective(ShippingOrderDO record);

    List<ShippingOrderDO> selectByExample(ShippingOrderDOExample example);

    ShippingOrderDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShippingOrderDO record, @Param("example") ShippingOrderDOExample example);

    int updateByExample(@Param("record") ShippingOrderDO record, @Param("example") ShippingOrderDOExample example);

    int updateByPrimaryKeySelective(ShippingOrderDO record);

    int updateByPrimaryKey(ShippingOrderDO record);
}