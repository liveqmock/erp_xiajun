package com.wangqin.globalshop.biz1.app.dal.mapperExt;


import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemBrandDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ItemBrandDOMapper;
import com.wangqin.globalshop.biz1.app.vo.ItemBrandQueryVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * Created by Patrick on 2018/5/15.
 */

public interface ItemBrandDOMapperExt extends ItemBrandDOMapper {
    //获取条数
    Integer queryBrandCount(ItemBrandQueryVO brandQueryVO);

    //分页查询
    List<ItemBrandDO> queryBrands(ItemBrandQueryVO brandQueryVO);

    //总查询
    List<ItemBrandDO> queryItemBrand();

    //按条件查询品牌
    List<ItemBrandDO> queryBrandSelective(ItemBrandQueryVO brandQueryVO);

    String selectNoByName(String name);

    List<ItemBrandDO> queryAllBrand();

    Integer queryItemBrandCount(ItemBrandQueryVO brandQueryVO);

    //插入单个品牌
    int insertBrandSelective(ItemBrandDO brand);

    void deleteItemBrandById(Long id);

    List<ItemBrandDO> queryByEnNameAndCnName(@Param("enName") String enName, @Param("cnName") String cnName);
	
    //修改之前查询是不是已经有该英文名的品牌
    List<Long> queryIdListByBrandName(String name);
    
}
