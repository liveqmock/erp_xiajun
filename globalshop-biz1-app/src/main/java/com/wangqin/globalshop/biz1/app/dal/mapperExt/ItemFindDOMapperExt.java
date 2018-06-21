package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemFindDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ItemFindDOMapper;

public interface ItemFindDOMapperExt extends ItemFindDOMapper {

	Integer queryItemsCount();
	
	List<ItemFindDO> queryFindItems();

}
