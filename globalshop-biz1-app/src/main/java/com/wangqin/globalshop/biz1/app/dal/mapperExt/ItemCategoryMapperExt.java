package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemCategoryDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ItemCategoryDOMapper;
import com.wangqin.globalshop.biz1.app.dto.ItemCategoryDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * catagory 数据控制层
 * item 模块使用
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
    
    //根据categoryCode和is_del来判断是否删除这个类目——张子阳
    int countRelativeItem(String categoryCode);
    
    int queryChildCategoryCountByCategoryCode(String categoryCode);
    
    //根据id删除类目
    void deleteItemCategoryById(Long id);

    List<ItemCategoryDO> selectByPcodeAndName(@Param("list") List<ItemCategoryDO> list, @Param("name")String name);

    List<ItemCategoryDO> queryItemCategoryByPcode(String pCode);

    //通过code查询id
    ItemCategoryDO queryCategoryIdByCategoryCode(String categoryCode);

}
