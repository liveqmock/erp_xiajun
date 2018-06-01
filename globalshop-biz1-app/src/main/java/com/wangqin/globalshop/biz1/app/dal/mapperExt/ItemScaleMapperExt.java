package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.Scale;

public interface ItemScaleMapperExt {

	List<Scale> queryScalesByTypeId(Long id);
}
