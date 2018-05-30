package com.wangqin.globalshop.item.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangqin.globalshop.item.app.service.IItemPackageScaleService;

@Controller
public class PackingController {

	@Autowired
	private IItemPackageScaleService shippingPackingScaleService;
	
	@RequestMapping("/freight/getPackageScaleList")
	@ResponseBody
	public Object getPackageScaleList() {
		return shippingPackingScaleService.queryPackageScaleTree().buildIsSuccess(true);
	}
}
