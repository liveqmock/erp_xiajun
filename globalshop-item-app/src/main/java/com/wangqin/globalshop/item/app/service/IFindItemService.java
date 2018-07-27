package com.wangqin.globalshop.item.app.service;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemFindDO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemQueryVO;
import com.wangqin.globalshop.common.utils.JsonPageResult;


public interface IFindItemService  {

	
	/**s
	 * 查询采购商品
	 * @param itemQueryVO
	 * @return
	 */
	JsonPageResult<List<ItemFindDO>> queryFindItems(ItemQueryVO itemQueryVO);
	
	/**
	 * 采购商品审核
	 * @param item
	 * @return
	 */
	//JsonResult<String> passOrRefuse(FindItem item);
	
}
