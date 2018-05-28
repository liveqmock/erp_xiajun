package com.wangqin.globalshop.item.app.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.BuyerStorageDetailMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.BuyerTaskDetailMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.InventoryMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.InventoryOnWarehouseMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallOrderMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallSubOrderMapperExt;
import com.wangqin.globalshop.biz1.app.vo.JsonPageResult;
import com.wangqin.globalshop.common.utils.HaiJsonUtils;
import com.wangqin.globalshop.item.app.service.IInventoryService;
import com.wangqin.globalshop.item.app.service.IItemSkuService;
import com.wangqin.globalshop.item.app.service.IMallOrderService;
import com.wangqinauth.commons.shiro.ShiroUser;

@Service
public class MallOrderServiceImpl  implements IMallOrderService {
	

	@Autowired
	private MallOrderMapperExt erpOrderMapper;
	@Autowired
	private IItemSkuService itemSkuService;
	@Autowired
	private IInventoryService inventoryService;
	
	@Autowired
	private MallSubOrderMapperExt outerOrderDetailMapper;
	@Autowired
	private InventoryMapperExt inventoryMapperExt;
	@Autowired
	private InventoryOnWarehouseMapperExt inventoryAreaMapper;
	@Autowired
	private BuyerStorageDetailMapperExt purchaseStorageDetailMapper;
	@Autowired
	private BuyerTaskDetailMapperExt taskDailyDetailMapper;

	

	
	@Override
	public void updateUpcForOrder(MallOrderDO erpOrder) {
		erpOrderMapper.updateUpcForErpOrder(erpOrder);
		outerOrderDetailMapper.updateUpcForOuterOrderDetail(erpOrder);
		inventoryMapperExt.updateUpcForInventory(erpOrder);
		inventoryAreaMapper.updateUpcForInventoryArea(erpOrder);
		purchaseStorageDetailMapper.updateUpcForPurchaseStorageDetail(erpOrder);
		taskDailyDetailMapper.updateUpcForTaskDailyDetail(erpOrder);
		
	}
	
	@Override
	public void updateWeightForOrder(MallOrderDO erpOrder) {
		erpOrderMapper.updateWeightForErpOrder(erpOrder);
		outerOrderDetailMapper.updateWeightForOuterOrderDetail(erpOrder);
	}



}
