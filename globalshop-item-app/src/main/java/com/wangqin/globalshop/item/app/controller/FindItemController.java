package com.wangqin.globalshop.item.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemFindDO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemQueryVO;
import com.wangqin.globalshop.common.utils.EasyuiJsonResult;
import com.wangqin.globalshop.common.utils.JsonPageResult;
import com.wangqin.globalshop.item.app.service.IFindItemService;


/**
 * 采购商品处理器
 * @author wangkan
 *
 */
@Controller
@RequestMapping("/item/finditem")
public class FindItemController {
	@Autowired
	private IFindItemService findItemService;
	/**
	 * 商品查询主列表
	 * @return
	 */
	@RequestMapping("/queryFindItemList")
	@ResponseBody
	public Object queryItemList(ItemQueryVO itemQueryVO) {
		JsonPageResult<List<ItemFindDO>> result = findItemService.queryFindItems(itemQueryVO);
		EasyuiJsonResult<List<ItemFindDO>> jsonResult = new EasyuiJsonResult<>();
		jsonResult.setTotal(result.getTotalCount());
		jsonResult.setRows(result.getData());
		return jsonResult.buildIsSuccess(true);
	}
	/**
	 * 单个采购商品通过或拒绝
	 * @param item
	 */
//	@RequestMapping("/passOrRefuse")
//	@ResponseBody
//	public Object passOrRefuse(FindItem item) {
//		return findItemService.passOrRefuse(item);
//	}
}
