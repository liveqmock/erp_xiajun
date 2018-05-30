package com.wangqin.globalshop.channel.service.warehouse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.WarehouseDO;

/**
 *
 * Warehouse 表数据服务层接口实现类
 *
 */
@Service("wareHouseService")
public class WarehouseServiceImpl implements IWarehouseService {

	@Resource WarehouseDOMapperExt warehouseDOMapperExt;

	public Map<String,Integer> getWarehousePropeties(String companyNo){

		Map<String,Integer> propetiesMap = new HashMap<>();

		WarehouseDO so = new WarehouseDO();
		so.setCompanyNo(companyNo);
		List<WarehouseDO> warehouseDOS =  warehouseDOMapperExt.queryPoList(so);

		for(WarehouseDO warehouseDO : warehouseDOS){
			propetiesMap.put(warehouseDO.getWarehouseNo(),warehouseDO.getDeliveryPriority());
		}

		return propetiesMap;

	}


}
