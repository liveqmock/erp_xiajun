package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdschema.impl.PublicImpl;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemCategoryDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ItemCategoryDOMapper;


/**
 * catagory 数据控制层
 * 
 * @author zhulu
 */
public interface ItemCategoryMapperExt extends ItemCategoryDOMapper{

    List<ItemCategoryDO> selectByName(String categoryName);
    
 ItemCategoryDO queryByCategoryCode(String categoryCode);
 
 List<ItemCategoryDO> selectAll();

}
