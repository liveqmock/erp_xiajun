package com.wangqin.globalshop.item.app.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.InventoryMapperExt;
import com.wangqin.globalshop.item.app.service.IInventoryService;
import com.wangqinauth.commons.shiro.ShiroUser;

@Service
public class InventoryServiceImpl  implements IInventoryService {

	@Autowired
	private InventoryMapperExt inventoryMapper;
	
	@Override
	public InventoryDO queryInventoryBySkuId(Long itemId,Long skuId) {
		return inventoryMapper.getInventoryBySkuId(itemId, skuId);
	}
	
	@Override
	public void insertBatch(List<InventoryDO> invList) {
		 inventoryMapper.insertBatch(invList);
	}
	
	@Override
	public InventoryDO queryInventoryBySkuCode(String skuCode) {
		return inventoryMapper.queryInventoryBySkuCode(skuCode);
	}
}
