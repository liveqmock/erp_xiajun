package com.wangqin.shopkeeper.biz1.app.dal.mapper;

import com.wangqin.shopkeeper.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.shopkeeper.biz1.app.dal.dataObject.InventoryDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InventoryDOMapper {
    int countByExample(InventoryDOExample example);

    int deleteByExample(InventoryDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InventoryDO record);

    int insertSelective(InventoryDO record);

    List<InventoryDO> selectByExample(InventoryDOExample example);

    InventoryDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") InventoryDO record, @Param("example") InventoryDOExample example);

    int updateByExample(@Param("record") InventoryDO record, @Param("example") InventoryDOExample example);

    int updateByPrimaryKeySelective(InventoryDO record);

    int updateByPrimaryKey(InventoryDO record);
}