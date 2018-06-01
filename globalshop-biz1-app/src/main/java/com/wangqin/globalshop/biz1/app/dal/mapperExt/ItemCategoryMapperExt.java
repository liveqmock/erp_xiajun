package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemCategoryDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ItemCategoryDOMapper;
import com.wangqin.globalshop.biz1.app.dto.ItemCategoryDTO;


/**
 * catagory 数据控制层
 * 
 * @author zhulu
 */
public interface ItemCategoryMapperExt extends ItemCategoryDOMapper{

    List<ItemCategoryDO> selectByName(String categoryName);
    
    ItemCategoryDO queryByCategoryCode(String categoryCode);
 
    List<ItemCategoryDO> selectAll();
 
 
    List<ItemCategoryDTO> selectAllDTO();
    
    //插入类目
    void insertCategorySelective(ItemCategoryDO category);

}
