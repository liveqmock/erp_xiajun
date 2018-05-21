package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemDOMapper {
    int countByExample(ItemDOExample example);

    int deleteByExample(ItemDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ItemDO record);

    int insertSelective(ItemDO record);

    List<ItemDO> selectByExampleWithBLOBs(ItemDOExample example);

    List<ItemDO> selectByExample(ItemDOExample example);

    ItemDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ItemDO record, @Param("example") ItemDOExample example);

    int updateByExampleWithBLOBs(@Param("record") ItemDO record, @Param("example") ItemDOExample example);

    int updateByExample(@Param("record") ItemDO record, @Param("example") ItemDOExample example);

    int updateByPrimaryKeySelective(ItemDO record);

    int updateByPrimaryKeyWithBLOBs(ItemDO record);

    int updateByPrimaryKey(ItemDO record);
}