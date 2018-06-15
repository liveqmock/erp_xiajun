package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ScaleDO;

public interface ItemScaleMapperExt {

	List<ScaleDO> queryScalesByTypeId(Long id);
}
