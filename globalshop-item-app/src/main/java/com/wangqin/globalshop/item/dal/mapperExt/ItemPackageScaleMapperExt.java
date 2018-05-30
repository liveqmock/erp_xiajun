package com.wangqin.globalshop.item.dal.mapperExt;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangqin.globalshop.item.app.dal.dto.ItemPackageScaleDTO;





public interface ItemPackageScaleMapperExt {
	
	List<ItemPackageScaleDTO> queryAllPackageScale();
}
