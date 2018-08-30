package com.wangqin.globalshop.item.app.service;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingPackingScaleDO;
import com.wangqin.globalshop.biz1.app.bean.dto.ItemPackageScaleDTO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ShippingPackingScaleQueryVO;
import com.wangqin.globalshop.common.utils.JsonPageResult;


public interface IItemPackageScaleService {
	
	JsonPageResult<List<ItemPackageScaleDTO>>  queryPackageScaleTree();
	
	JsonPageResult<List<ItemPackageScaleDTO>> queryPackageScaleList();
	
	ItemPackageScaleDTO selectById(Long id); 
	
	void insertPackageScale(ItemPackageScaleDTO itemPackageScaleDTO);
	
	void delete(Long id);
	
	void updateScaleSelectiveById(ItemPackageScaleDTO itemPackageScaleDTO);
	
	List<ItemPackageScaleDTO> queryScaleList(ShippingPackingScaleQueryVO scaleVO);
	
	List<ShippingPackingScaleDO> queryScaleListSelective(ShippingPackingScaleQueryVO shippingPackingScaleQueryVO);
	
	String queryNoById(Long id);
}
