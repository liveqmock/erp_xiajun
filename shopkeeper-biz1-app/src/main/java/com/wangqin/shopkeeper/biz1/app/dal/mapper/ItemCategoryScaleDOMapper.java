package com.wangqin.shopkeeper.biz1.app.dal.mapper;

import com.wangqin.shopkeeper.biz1.app.dal.dataObject.ItemCategoryScaleDO;
import com.wangqin.shopkeeper.biz1.app.dal.dataObject.ItemCategoryScaleDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemCategoryScaleDOMapper {
    int countByExample(ItemCategoryScaleDOExample example);

    int deleteByExample(ItemCategoryScaleDOExample example);

    int insert(ItemCategoryScaleDO record);

    int insertSelective(ItemCategoryScaleDO record);

    List<ItemCategoryScaleDO> selectByExample(ItemCategoryScaleDOExample example);

    int updateByExampleSelective(@Param("record") ItemCategoryScaleDO record, @Param("example") ItemCategoryScaleDOExample example);

    int updateByExample(@Param("record") ItemCategoryScaleDO record, @Param("example") ItemCategoryScaleDOExample example);
}