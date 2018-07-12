package com.wangqin.globalshop.inventory.app.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BaseModel;
import com.wangqin.globalshop.biz1.app.dal.dataObject.WarehouseDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.WarehouseDOMapperExt;
import com.wangqin.globalshop.inventory.app.service.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WarehouseServiceImpl implements IWarehouseService {

	@Autowired
	private WarehouseDOMapperExt mapper;

	@Override
	public Map<String,Integer> getWarehousePropeties(String companyNo){

		Map<String,Integer> propetiesMap = new HashMap<>();

		WarehouseDO so = new WarehouseDO();
		so.setCompanyNo(companyNo);
		List<WarehouseDO> warehouseDOS =  mapper.queryPoList(so);

		for(WarehouseDO warehouseDO : warehouseDOS){
			propetiesMap.put(warehouseDO.getWarehouseNo(),warehouseDO.getDeliveryPriority());
		}

		return propetiesMap;

	}

	@Override
	public WarehouseDO getWarehouseById(Long warehouseId) {
		return mapper.selectByPrimaryKey(warehouseId);
	}

	@Override
	public void addWarehouse(String name) {
		WarehouseDO warehouse = new WarehouseDO();
		warehouse.init();
		warehouse.setName(name);
		warehouse.setWarehouseNo("WARE"+System.currentTimeMillis());
		warehouse.init();
		warehouse.setContactPerson("person");
		mapper.insertSelective(warehouse);
	}

	@Override
	public void updateWarehouse(WarehouseDO warehouse) {
		mapper.updateByPrimaryKeySelective(warehouse);
	}

	@Override
	public Object selectById(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WarehouseDO> queryWarehouses(String companyNo) {
		return mapper.selectByCompanyNo(companyNo);
	}

	@Override
	public List<WarehouseDO> list(WarehouseDO warehouseDO) {
		warehouseDO.init();
		return mapper.list(warehouseDO);
	}

	@Override
	public List<WarehouseDO> selectWhList(WarehouseDO warehouseDO){
		List<WarehouseDO> whList = new ArrayList<>();
		if(warehouseDO.getCompanyNo() == null){
			warehouseDO.initCompany();
		}
		whList = mapper.selectWhList(warehouseDO);
		return whList;
	}

	@Override
	public WarehouseDO selectByWarehouseNo(String warehouseNo) {
		// TODO Auto-generated method stub
		return mapper.selectByWarehouseNo(warehouseNo);
	}
}
