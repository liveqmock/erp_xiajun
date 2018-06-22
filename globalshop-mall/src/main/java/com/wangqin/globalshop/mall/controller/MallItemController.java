package com.wangqin.globalshop.mall.controller;

import com.wangqin.globalshop.biz1.app.dto.ItemDTO;
import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import com.wangqin.globalshop.mall.service.MallItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Create by 777 on 2018/6/19
 */


@Controller
@RequestMapping("/mall/item")
public class MallItemController {


	@Autowired
	private MallItemService mallItemService;


	@RequestMapping("/queryshare")
	@ResponseBody
	public Object queryshare(String itemCode, String companyNo, String userNo) {
		JsonResult<ItemDTO> result = new JsonResult<>();

		ItemDTO itemDTO = mallItemService.itemqueryshare(itemCode,companyNo);

		if(itemDTO == null){
			return result.buildIsSuccess(false).buildMsg("未找到该商品");
		}

		//处理分享问题


		result.setData(itemDTO);
		result.buildIsSuccess(true).buildMsg("成功");

		return result;
	}


	/**
	 * 查一天新增的商品
	 * @param companyNo
	 * @return
	 */
	@RequestMapping("/queryOneday")
	@ResponseBody
	public Object queryOneDay(String companyNo) {
		JsonResult<ItemDTO> result = new JsonResult<>();

		List<ItemDTO> itemDOS = mallItemService.queryOneDay(companyNo);

		if(itemDOS == null || itemDOS.size() < 1){
			return result.buildIsSuccess(false).buildMsg("未找符合条件的商品");
		}

		result.setData(itemDOS.get(0));
		result.buildIsSuccess(true).buildMsg("成功");

		return result;
	}


}
