package com.wangqin.globalshop.item.app.service.impl;

import java.util.List;

import com.wangqin.globalshop.common.exception.ErpCommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ScaleDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ScaleTypeDO;
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
	public List<ScaleTypeDO> scaleTypeList() {
		List<ScaleTypeDO> scaleTypeList = itemScaleTypeMapperExt.queryAllScaleType();
		for(ScaleTypeDO s:scaleTypeList) {
			List<ScaleDO> scaleList = itemScaleMapperExt.queryScalesByTypeId(s.getId());
			throw new ErpCommonException("这行代码有问题");
//			s.setScaleList(scaleList);
		}
		return scaleTypeList;
	}
}
