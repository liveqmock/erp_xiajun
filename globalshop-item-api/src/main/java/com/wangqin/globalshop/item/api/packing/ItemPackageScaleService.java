package com.wangqin.globalshop.item.api.packing;


import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wangqin.globalshop.biz1.app.bean.dataVo.ShippingPackingScaleQueryVO;
import com.wangqin.globalshop.biz1.app.bean.dto.ItemPackageScaleDTO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingPackingScaleDO;
import com.wangqin.globalshop.common.utils.JsonPageResult;




public interface ItemPackageScaleService {

	@RequestMapping(value = "/scale/queryPackageScaleTree", method = RequestMethod.POST)
	JsonPageResult<List<ItemPackageScaleDTO>>  queryPackageScaleTree();

	@RequestMapping(value = "/scale/queryPackageScaleList", method = RequestMethod.POST)
	JsonPageResult<List<ItemPackageScaleDTO>> queryPackageScaleList();

	@RequestMapping(value = "/scale/selectById", method = RequestMethod.POST)
	ItemPackageScaleDTO selectById(@RequestParam("id")Long id);

	@RequestMapping(value = "/scale/insertPackageScale", method = RequestMethod.POST)
	void insertPackageScale(@RequestBody ItemPackageScaleDTO itemPackageScaleDTO);

	@RequestMapping(value = "/scale/delete", method = RequestMethod.POST)
	void delete(@RequestParam("id")Long id);

	@RequestMapping(value = "/scale/updateScaleSelectiveById", method = RequestMethod.POST)
	void updateScaleSelectiveById(@RequestBody ItemPackageScaleDTO itemPackageScaleDTO);

	@RequestMapping(value = "/scale/queryScaleList", method = RequestMethod.POST)
	List<ItemPackageScaleDTO> queryScaleList(@RequestBody ShippingPackingScaleQueryVO scaleVO);

	@RequestMapping(value = "/scale/queryScaleListSelective", method = RequestMethod.POST)
	List<ShippingPackingScaleDO> queryScaleListSelective(@RequestBody ShippingPackingScaleQueryVO shippingPackingScaleQueryVO);

	@RequestMapping(value = "/scale/queryNoById", method = RequestMethod.POST)
	String queryNoById(@RequestParam("id")Long id);
}
