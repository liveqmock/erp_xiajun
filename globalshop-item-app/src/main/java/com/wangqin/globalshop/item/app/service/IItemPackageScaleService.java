package com.wangqin.globalshop.item.app.service;

import java.util.List;

import com.wangqin.globalshop.common.utils.JsonPageResult;
import com.wangqin.globalshop.item.app.dal.dto.ItemPackageScaleDTO;


public interface IItemPackageScaleService {

	//List<ItemPackageScaleDTO> queryPackageScales();
	
	JsonPageResult<List<ItemPackageScaleDTO>>  queryPackageScaleTree();
	
	JsonPageResult<List<ItemPackageScaleDTO>> queryPackageScaleList();
	

	void insertPackageScale(ItemPackageScaleDTO itemPackageScaleDTO);
	
	void delete(Long id);
}
