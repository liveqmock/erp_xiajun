package com.wangqin.globalshop.item.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangqin.globalshop.biz1.app.dal.dataObject.CountryDO;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.item.app.service.ICountryService;
import com.wangqin.globalshop.item.app.service.IItemSkuScaleService;

@Controller
public class ScaleController {

	@Autowired
	private IItemSkuScaleService itemSkuScaleService;

	
	
}
