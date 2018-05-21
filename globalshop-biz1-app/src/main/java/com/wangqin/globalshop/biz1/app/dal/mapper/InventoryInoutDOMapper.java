package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryInoutDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryInoutDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InventoryInoutDOMapper {
    int countByExample(InventoryInoutDOExample example);

    int deleteByExample(InventoryInoutDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InventoryInoutDO record);

    int insertSelective(InventoryInoutDO record);

    List<InventoryInoutDO> selectByExample(InventoryInoutDOExample example);

    InventoryInoutDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") InventoryInoutDO record, @Param("example") InventoryInoutDOExample example);

    int updateByExample(@Param("record") InventoryInoutDO record, @Param("example") InventoryInoutDOExample example);

    int updateByPrimaryKeySelective(InventoryInoutDO record);

    int updateByPrimaryKey(InventoryInoutDO record);
}