package com.wangqin.shopkeeper.biz1.app.dal.mapper;

import com.wangqin.shopkeeper.biz1.app.dal.dataObject.ItemFindDO;
import com.wangqin.shopkeeper.biz1.app.dal.dataObject.ItemFindDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemFindDOMapper {
    int countByExample(ItemFindDOExample example);

    int deleteByExample(ItemFindDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ItemFindDO record);

    int insertSelective(ItemFindDO record);

    List<ItemFindDO> selectByExampleWithBLOBs(ItemFindDOExample example);

    List<ItemFindDO> selectByExample(ItemFindDOExample example);

    ItemFindDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ItemFindDO record, @Param("example") ItemFindDOExample example);

    int updateByExampleWithBLOBs(@Param("record") ItemFindDO record, @Param("example") ItemFindDOExample example);

    int updateByExample(@Param("record") ItemFindDO record, @Param("example") ItemFindDOExample example);

    int updateByPrimaryKeySelective(ItemFindDO record);

    int updateByPrimaryKeyWithBLOBs(ItemFindDO record);

    int updateByPrimaryKey(ItemFindDO record);
}