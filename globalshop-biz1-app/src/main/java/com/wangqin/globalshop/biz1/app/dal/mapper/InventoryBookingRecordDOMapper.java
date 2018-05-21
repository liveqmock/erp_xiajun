package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryBookingRecordDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryBookingRecordDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InventoryBookingRecordDOMapper {
    int countByExample(InventoryBookingRecordDOExample example);

    int deleteByExample(InventoryBookingRecordDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InventoryBookingRecordDO record);

    int insertSelective(InventoryBookingRecordDO record);

    List<InventoryBookingRecordDO> selectByExample(InventoryBookingRecordDOExample example);

    InventoryBookingRecordDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") InventoryBookingRecordDO record, @Param("example") InventoryBookingRecordDOExample example);

    int updateByExample(@Param("record") InventoryBookingRecordDO record, @Param("example") InventoryBookingRecordDOExample example);

    int updateByPrimaryKeySelective(InventoryBookingRecordDO record);

    int updateByPrimaryKey(InventoryBookingRecordDO record);
}