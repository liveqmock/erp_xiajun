package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOnWareHouseDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOnWareHouseDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InventoryOnWareHouseDOMapper {
    int countByExample(InventoryOnWareHouseDOExample example);

    int deleteByExample(InventoryOnWareHouseDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InventoryOnWareHouseDO record);

    int insertSelective(InventoryOnWareHouseDO record);

    List<InventoryOnWareHouseDO> selectByExample(InventoryOnWareHouseDOExample example);

    InventoryOnWareHouseDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") InventoryOnWareHouseDO record, @Param("example") InventoryOnWareHouseDOExample example);

    int updateByExample(@Param("record") InventoryOnWareHouseDO record, @Param("example") InventoryOnWareHouseDOExample example);

    int updateByPrimaryKeySelective(InventoryOnWareHouseDO record);

    int updateByPrimaryKey(InventoryOnWareHouseDO record);
}