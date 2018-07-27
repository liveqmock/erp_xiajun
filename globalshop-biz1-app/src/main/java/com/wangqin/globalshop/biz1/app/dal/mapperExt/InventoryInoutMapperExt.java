package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryInoutDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.InventoryQueryVO;
import com.wangqin.globalshop.biz1.app.dal.mapper.InventoryInoutDOMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface InventoryInoutMapperExt extends InventoryInoutDOMapper {

Integer queryInventoryInoutCount(InventoryQueryVO inventoryQueryVO);
	
	List<InventoryInoutDO> queryInventoryInouts(InventoryQueryVO inventoryQueryVO);
	
	Integer countTodayInInventoryItemNum(@Param("typeList")List<Integer> typeList,@Param("today")Date today);

	ItemSkuDO selectItemBySkuCode(String skuCode);
	
	List<InventoryQueryVO> queryInventoryInoutsVo(InventoryQueryVO inventoryQueryVO);
}
