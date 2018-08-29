package com.wangqin.globalshop.item.app.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemCategoryScaleDO;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.item.app.service.IItemCategoryScaleService;


/**
 * 
 * @author xiajun
 * 关于规格的所有controller层
 * 这个没有用到
 *
 */
@Controller
@RequestMapping(value = "/scale")
public class ScaleController {

	@Autowired
	private IItemCategoryScaleService itemCategoryScaleService;


	/**
	 * 根据三级类目查询规格项
	 *
	 * @param
	 * @return
	 */
	@RequestMapping("/queryScalesByCategory")
	@ResponseBody
	public Object queryScalesByCategory(String categoryCode) {
		JsonResult<List<ItemCategoryScaleDO>> result= new JsonResult<>();
		if(null == categoryCode) {
			result.buildIsSuccess(false);
			result.buildMsg("类目编码不能为空");
			return result;
		}
		List<ItemCategoryScaleDO> scaleList = itemCategoryScaleService.selectCategoryScaleByCategoryCode(categoryCode);
		result.buildData(scaleList);
		result.buildIsSuccess(true);
		result.buildMsg("查找成功");
		return result;
	}
	
}
