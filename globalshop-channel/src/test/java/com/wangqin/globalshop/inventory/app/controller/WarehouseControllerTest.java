package com.wangqin.globalshop.inventory.app.controller;

import com.alibaba.fastjson.JSON;
import com.wangqin.globalshop.biz1.app.dal.dataObject.WarehouseDO;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.inventory.app.service.IWarehouseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Create by 777 on 2018/6/22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-common.xml"})
public class WarehouseControllerTest {

	@Autowired
	private IWarehouseService warehouseService;

	@Test public void selectWhList() {

		WarehouseDO warehouse = new WarehouseDO();
		warehouse.initCompany();
		List<WarehouseDO> list = warehouseService.selectWhList(warehouse);
		JsonResult<Object>  result =  JsonResult.buildSuccess(list);
		System.out.println(JSON.toJSONString(result));

	}
}
