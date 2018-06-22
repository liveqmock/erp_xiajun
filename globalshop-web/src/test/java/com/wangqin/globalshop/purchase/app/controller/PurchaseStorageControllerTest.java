package com.wangqin.globalshop.purchase.app.controller;

import com.alibaba.fastjson.JSON;
import com.wangqin.globalshop.biz1.app.dal.dataVo.BuyerStorageDetailVo;
import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Create by 777 on 2018/6/22
 */
public class PurchaseStorageControllerTest {


	@Test public void searchByOpenId() {


		JsonResult<List<BuyerStorageDetailVo>> result = new JsonResult<>();

		List<BuyerStorageDetailVo> list = new ArrayList<>();
		BuyerStorageDetailVo dataVo = new BuyerStorageDetailVo();


		dataVo.setId(1L);
		dataVo.setStorageNo("PKG001入库单号");
		dataVo.setSkuName("商品名称");
		dataVo.setSkuCode("skucode");
		dataVo.setUpc("UPC");
		dataVo.setSpecifications("规格");
		dataVo.setQuantity(3);//"实际入库数，客户手工填入的数据"
		dataVo.setTransQuantity(4);//"预入库数"
		dataVo.setBuyerOpenId(000L);//"微信ID"
		dataVo.setBuyerName("买手名字");
		dataVo.setGmtModify(new Date());
		dataVo.setGmtCreate(new Date());
		dataVo.setWarehouseName("仓库名");
		dataVo.setWarehouseNo("仓库编码");
		dataVo.setShelfNo("货架号");
		dataVo.setItemCode("itemcode");
		dataVo.setStatusName("状态名称-预入库，确认，成功，取消，入库中");
		dataVo.setStatus(1);

		list.add(dataVo);
		result.setData(list);

		result.buildIsSuccess(true).buildMsg("成功");
		System.out.println(JSON.toJSONString(result));


	}


	@Test public void searchByopenidAndUpc() {
	}



	@Test public void searchAll() {
	}



	@Test public void comfirm() {
	}


	@Test public void queryHasComfirm() {
	}
}
