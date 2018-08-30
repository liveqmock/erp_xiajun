package com.wangqin.globalshop.item.api.packing;


import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wangqin.globalshop.biz1.app.bean.dataVo.PackageLevelQueryVO;
import com.wangqin.globalshop.biz1.app.bean.dto.ItemPackagePatternDTO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingPackingPatternDO;
import com.wangqin.globalshop.common.utils.JsonPageResult;




/**
 * 
 */

public interface ItemPackagePatternService {

    @RequestMapping(value = "/pattern/countPatternsByScaleNo", method = RequestMethod.POST)
	Integer countPatternsByScaleNo(@RequestParam("packagingScaleNo")String packagingScaleNo);

    @RequestMapping(value = "/pattern/queryPackageLevelList", method = RequestMethod.POST)
    JsonPageResult<List<ItemPackagePatternDTO>> queryPackageLevelList(@RequestBody PackageLevelQueryVO packageLevelQueryVO);

    @RequestMapping(value = "/pattern/insertPattern", method = RequestMethod.POST)
    void insertPattern(@RequestBody ShippingPackingPatternDO shippingPackingPatternDO);

    @RequestMapping(value = "/pattern/deleteById", method = RequestMethod.POST)
	void deleteById(@RequestParam("id")Long id);

    @RequestMapping(value = "/pattern/updateLevelSelectiveById", method = RequestMethod.POST)
	void updateLevelSelectiveById(@RequestBody ItemPackagePatternDTO shippingPackingPatternDO);
}
