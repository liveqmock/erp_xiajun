package com.wangqin.globalshop.inventory.app.controller;

import com.wangqin.globalshop.biz1.app.dal.dataObject.WarehouseDO;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.common.utils.ShiroUtil;
import com.wangqin.globalshop.inventory.app.service.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/warehouse")
public class WarehouseController{

	@Autowired
	private IWarehouseService warehouseService;

	/**
	 * 添加仓库
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(String name) {
		if(name == null){
			return JsonResult.buildFailed("新增仓库对象不能为空");
		}
		warehouseService.addWarehouse(name);
		return JsonResult.buildSuccess(true);
	}

	/**
	 * 修改仓库信息
	 * 
	 * @param category
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Object update(WarehouseDO warehouse) {
		if (warehouse.getId() == null) {
			return JsonResult.buildFailed("修改仓库ID为空");
		} 
		warehouseService.updateWarehouse(warehouse);
		return JsonResult.buildSuccess(null);
	}
	
	/**
	 * 根据id查询仓库信息
	 * 
	 * @param category
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public Object query(Long id) {
		if (id == null) {
			return JsonResult.buildFailed("仓库ID为空");
		} 
		return JsonResult.buildSuccess(warehouseService.selectById(id));
	}

	
	/**
	 * 查询仓库列表
	 * 
	 * @return
	 */
	@GetMapping("/queryWarehouses")
	@ResponseBody
	public Object queryWarehouses(WarehouseDO warehouseDO) {
//		List<WarehouseDO> list = warehouseService.queryWarehouses(ShiroUtil.getShiroUser().getCompanyNo());
		List<WarehouseDO> list = warehouseService.list(warehouseDO);
		return JsonResult.buildSuccess(list);
	}
}
