package com.wangqin.globalshop.mall.controller;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemSkuDOMapperExt;
import com.wangqin.globalshop.biz1.app.dto.ItemDTO;
import com.wangqin.globalshop.biz1.app.vo.ItemQueryVO;
import com.wangqin.globalshop.biz1.app.vo.JsonResult;
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
	private ItemDOMapperExt itemDOMapperExt;

	@Autowired
	private ItemSkuDOMapperExt itemSkuDOMapperExt;

	@RequestMapping("/queryshare")
	@ResponseBody
	public Object queryshare(String itemCode, String companyNo, String userNo) {
		JsonResult<ItemDTO> result = new JsonResult<>();
		ItemQueryVO itemQueryVO = new ItemQueryVO();

		itemQueryVO.setItemCode(itemCode);
		itemQueryVO.setCompanyNo(companyNo);

		List<ItemDTO> itemDOS = itemDOMapperExt.queryItems(itemQueryVO);

		if(itemDOS == null || itemDOS.size() < 1){
			return result.buildIsSuccess(false).buildMsg("未找到该商品");
		}


		//处理分享问题


		result.setData(itemDOS.get(0));
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
		ItemQueryVO itemQueryVO = new ItemQueryVO();

		itemQueryVO.setItemCode(itemCode);
		itemQueryVO.setCompanyNo(companyNo);

		List<ItemDTO> itemDOS = itemDOMapperExt.queryItems(itemQueryVO);

		if(itemDOS == null || itemDOS.size() < 1){
			return result.buildIsSuccess(false).buildMsg("未找到该商品");
		}


		//处理分享问题


		result.setData(itemDOS.get(0));
		result.buildIsSuccess(true).buildMsg("成功");

		return result;
	}


}
