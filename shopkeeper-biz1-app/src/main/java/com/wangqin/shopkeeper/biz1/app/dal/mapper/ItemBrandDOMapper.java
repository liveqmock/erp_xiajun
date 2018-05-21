package com.wangqin.shopkeeper.biz1.app.dal.mapper;

import com.wangqin.shopkeeper.biz1.app.dal.dataObject.ItemBrandDO;
import com.wangqin.shopkeeper.biz1.app.dal.dataObject.ItemBrandDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemBrandDOMapper {
    int countByExample(ItemBrandDOExample example);

    int deleteByExample(ItemBrandDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ItemBrandDO record);

    int insertSelective(ItemBrandDO record);

    List<ItemBrandDO> selectByExample(ItemBrandDOExample example);

    ItemBrandDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ItemBrandDO record, @Param("example") ItemBrandDOExample example);

    int updateByExample(@Param("record") ItemBrandDO record, @Param("example") ItemBrandDOExample example);

    int updateByPrimaryKeySelective(ItemBrandDO record);

    int updateByPrimaryKey(ItemBrandDO record);
}