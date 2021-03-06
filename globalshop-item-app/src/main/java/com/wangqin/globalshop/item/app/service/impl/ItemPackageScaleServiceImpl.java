package com.wangqin.globalshop.item.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingPackingScaleDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemPackagePatternMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemPackageScaleMapperExt;
import com.wangqin.globalshop.biz1.app.bean.dto.ItemPackageScaleDTO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ShippingPackingScaleQueryVO;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.JsonPageResult;
import com.wangqin.globalshop.item.app.service.IItemPackageScaleService;

@Service
public class ItemPackageScaleServiceImpl implements IItemPackageScaleService{

	@Autowired
	private ItemPackageScaleMapperExt packageScaleMapperExt;
	
	@Autowired
	private ItemPackagePatternMapperExt shippingPackingPatternDOMapperExt;
	
	
	@Override
	@Transactional(rollbackFor = ErpCommonException.class)
	public JsonPageResult<List<ItemPackageScaleDTO>>  queryPackageScaleTree() {
		JsonPageResult<List<ItemPackageScaleDTO>> result = new JsonPageResult<>();
		
		List<ItemPackageScaleDTO> packageScales = packageScaleMapperExt.queryAllPackageScale();
		
		for (ItemPackageScaleDTO packageScale : packageScales) {
			packageScale.setPackageLevels(shippingPackingPatternDOMapperExt.queryPatternsByScaleNo(packageScale.getPackagingScaleNo()));
		}
		
		result.setData(packageScales);
		
		return result;
	}
	
	@Override
	public JsonPageResult<List<ItemPackageScaleDTO>> queryPackageScaleList() {
		JsonPageResult<List<ItemPackageScaleDTO>> result = new JsonPageResult<>();
		
		List<ItemPackageScaleDTO> packageScales = packageScaleMapperExt.queryAllPackageScale();
		result.setData(packageScales);
		
		return result;
	}
	
	@Override
	public void insertPackageScale(ItemPackageScaleDTO itemPackageScaleDTO) {
		packageScaleMapperExt.insertPackageScale(itemPackageScaleDTO);
	}
	
	@Override
	public void delete(Long id) {
		packageScaleMapperExt.delete(id);
	}
	
	@Override
	public void updateScaleSelectiveById(ItemPackageScaleDTO itemPackageScaleDTO) {
		packageScaleMapperExt.updateScaleSelectiveById(itemPackageScaleDTO);
	}
	
	@Override
	public List<ItemPackageScaleDTO> queryScaleList(ShippingPackingScaleQueryVO scaleVO) {
		return packageScaleMapperExt.queryScaleList(scaleVO);
	}
	
	@Override
	public List<ShippingPackingScaleDO> queryScaleListSelective(ShippingPackingScaleQueryVO shippingPackingScaleQueryVO) {
		return packageScaleMapperExt.queryScaleListSelective(shippingPackingScaleQueryVO);
	}
	
	@Override
	public String queryNoById(Long id) {
		return packageScaleMapperExt.queryNoById(id);
	}

	@Override
	public ItemPackageScaleDTO selectById(Long id) {
		return packageScaleMapperExt.selectById(id);
	}
}
