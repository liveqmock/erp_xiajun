package com.wangqin.globalshop.channel.service.warehouse;

import com.wangqin.globalshop.biz1.app.dal.dataObject.WarehouseDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.WarehouseDOMapperExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Warehouse 表数据服务层接口实现类
 *
 */
@Service("wareHouseService")
public class WarehouseServiceImpl implements IWarehouseService {

	@Autowired
	private WarehouseDOMapperExt warehouseDOMapperExt;

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
