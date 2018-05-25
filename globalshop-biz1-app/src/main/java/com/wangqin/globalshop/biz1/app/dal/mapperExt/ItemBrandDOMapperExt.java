package com.wangqin.globalshop.biz1.app.dal.mapperExt;


import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemBrandDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ItemBrandDOMapper;
import com.wangqin.globalshop.biz1.app.vo.ItemBrandQueryVO;


/**
 * Created by Patrick on 2018/5/15.
 */

public interface ItemBrandDOMapperExt  extends ItemBrandDOMapper {
	    //获取条数
		Integer queryItemBrandCount(ItemBrandQueryVO brandQueryVO);
		
		//分页查询
		List<ItemBrandDO> queryItemBrands(ItemBrandQueryVO brandQueryVO);

		//总查询
		List<ItemBrandDO> queryAllItemBrand();

	    ItemBrandDO selectByName(String name);
}
