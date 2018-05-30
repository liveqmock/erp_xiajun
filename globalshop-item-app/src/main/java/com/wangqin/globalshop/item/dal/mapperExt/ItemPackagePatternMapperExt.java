package com.wangqin.globalshop.item.dal.mapperExt;

import java.util.List;

import com.wangqin.globalshop.item.app.dal.dto.ItemPackagePatternDTO;


public interface ItemPackagePatternMapperExt  {

	List<ItemPackagePatternDTO> queryPatternsByScaleNo(String no);
}
