package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangqin.globalshop.biz1.app.dal.dataVo.InventoryInoutVO;
import com.wangqin.globalshop.biz1.app.vo.InventoryQueryVO;

public interface InventoryInoutMapperExt {

Integer queryInventoryInoutCount(InventoryQueryVO inventoryQueryVO);
	
	List<InventoryInoutVO> queryInventoryInouts(InventoryQueryVO inventoryQueryVO);
	
	Integer countTodayInInventoryItemNum(@Param("typeList")List<Integer> typeList,@Param("today")Date today);
}
