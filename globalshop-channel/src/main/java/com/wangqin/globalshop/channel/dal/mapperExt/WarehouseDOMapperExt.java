package com.wangqin.globalshop.channel.dal.mapperExt;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.WarehouseDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.WarehouseDOMapper;

/**
 * Create by 777 on 2018/5/30
 */
public interface WarehouseDOMapperExt extends WarehouseDOMapper {

	public Integer queryPoCount(WarehouseDO warehouseDO);

	public WarehouseDO queryPo(WarehouseDO warehouseDO);

	public List<WarehouseDO> queryPoList(WarehouseDO warehouseDO);


}
