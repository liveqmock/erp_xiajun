package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemCategoryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemCategoryDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemCategoryDOMapper {
    int countByExample(ItemCategoryDOExample example);

    int deleteByExample(ItemCategoryDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ItemCategoryDO record);

    int insertSelective(ItemCategoryDO record);

    List<ItemCategoryDO> selectByExample(ItemCategoryDOExample example);

    ItemCategoryDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ItemCategoryDO record, @Param("example") ItemCategoryDOExample example);

    int updateByExample(@Param("record") ItemCategoryDO record, @Param("example") ItemCategoryDOExample example);

    int updateByPrimaryKeySelective(ItemCategoryDO record);

    int updateByPrimaryKey(ItemCategoryDO record);
}