package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOutManifestDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOutManifestDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InventoryOutManifestDOMapper {
    int countByExample(InventoryOutManifestDOExample example);

    int deleteByExample(InventoryOutManifestDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InventoryOutManifestDO record);

    int insertSelective(InventoryOutManifestDO record);

    List<InventoryOutManifestDO> selectByExample(InventoryOutManifestDOExample example);

    InventoryOutManifestDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") InventoryOutManifestDO record, @Param("example") InventoryOutManifestDOExample example);

    int updateByExample(@Param("record") InventoryOutManifestDO record, @Param("example") InventoryOutManifestDOExample example);

    int updateByPrimaryKeySelective(InventoryOutManifestDO record);

    int updateByPrimaryKey(InventoryOutManifestDO record);
}