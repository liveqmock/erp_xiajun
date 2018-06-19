package com.wangqin.globalshop.mall.controller;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallShippingCartDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemSkuDOMapperExt;
import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by 777 on 2018/6/19
 */


@Controller
@RequestMapping("/wx/item")
public class MallItemController {


	@Autowired
	private ItemDOMapperExt itemDOMapperExt;

	@Autowired
	private ItemSkuDOMapperExt itemSkuDOMapperExt;

	@RequestMapping("/query")
	@ResponseBody
	public Object query(String itemCode, String userNo) {
		JsonResult<ItemDO> result = new JsonResult<>();
		ItemDO itemDO = itemDOMapperExt.queryItemByItemCode(itemCode);
		if (itemDO == null) {
			return result.buildData(itemDO).buildIsSuccess(false).buildMsg("未找到该商品");
		}else



		return result;
	}
}
