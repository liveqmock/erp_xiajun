package com.wangqin.globalshop.item.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.Scale;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ScaleType;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemScaleMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemScaleTypeMapperExt;
import com.wangqin.globalshop.item.app.service.IScaleTypeService;

@Service
public class ScaleTypeService implements IScaleTypeService{

	@Autowired
	private ItemScaleTypeMapperExt itemScaleTypeMapperExt;
	
	@Autowired
	private ItemScaleMapperExt itemScaleMapperExt;
	//这里暂时作废
	@Override
	public List<ScaleType> scaleTypeList() {
		List<ScaleType> scaleTypeList = itemScaleTypeMapperExt.queryAllScaleType();
		for(ScaleType s:scaleTypeList) {
			List<Scale> scaleList = itemScaleMapperExt.queryScalesByTypeId(s.getId());
			s.setScaleList(scaleList);
		} 
		return scaleTypeList;
	}
}
