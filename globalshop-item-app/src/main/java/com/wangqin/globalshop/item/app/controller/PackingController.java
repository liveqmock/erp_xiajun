package com.wangqin.globalshop.item.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.bean.dataVo.PackageLevelQueryVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ShippingPackingScaleQueryVO;
import com.wangqin.globalshop.biz1.app.bean.dto.ItemPackagePatternDTO;
import com.wangqin.globalshop.biz1.app.bean.dto.ItemPackageScaleDTO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingPackingPatternDO;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.JsonPageResult;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.common.utils.RandomUtils;
import com.wangqin.globalshop.common.utils.StringUtils;
import com.wangqin.globalshop.item.app.service.IItemPackagePatternService;
import com.wangqin.globalshop.item.app.service.IItemPackageScaleService;


/**
 * 包装以及包装规格控制器
 * shipping_packing_scale和shipping_packing_pattern两张表
 * @author admin
 *
 */
@Controller
@Authenticated
public class PackingController {

	//旧的service
	@Autowired
	private IItemPackageScaleService shippingPackingScaleService;
	
	@Autowired
	private IItemPackagePatternService iPackageLevelService;
	
	//新的service
//	@Autowired
//	private ItemPackageScaleFeignService shippingPackingScaleService;
//	
//	@Autowired
//	private ItemPackagePatternFeignService iPackageLevelService;
	
	@RequestMapping("/freight/getPackageScaleList")
	@ResponseBody
	public Object getPackageScaleList() {
		return shippingPackingScaleService.queryPackageScaleTree().buildIsSuccess(true);
	}
	
	/**
	 * 包装规格类别列表，商品管理第五项
	 * @return
	 */
	@RequestMapping("/packageScale/queryPackageScaleList")
	@ResponseBody
	public Object queryPackageScaleList() {
		JsonPageResult<List<ItemPackageScaleDTO>> result = shippingPackingScaleService.queryPackageScaleList();
		
		return result.buildIsSuccess(true);
	}
	
	/**
	 * 查单个规格
	 * @return
	 */
	@RequestMapping("/packageScale/query")
	@ResponseBody
	public Object queryScale(Long id) {
		JsonResult<ItemPackageScaleDTO> result = new JsonResult<>();
		ShippingPackingScaleQueryVO shippingPackingScaleQueryVO = new ShippingPackingScaleQueryVO();
		shippingPackingScaleQueryVO.setId(id);
		result.setData(shippingPackingScaleService.queryScaleList(shippingPackingScaleQueryVO).get(0));
		
		
		return result.buildIsSuccess(true);
	}
	
	/**
	 * 新增包装规格
	 * @param packageScale
	 * @return
	 */
	@RequestMapping("/packageScale/add")
	@ResponseBody
	public Object add(ItemPackageScaleDTO packageScale) {
		JsonResult<String> result = new JsonResult<>();
		if (null == packageScale.getId()) {
			if ((StringUtils.isNotBlank(packageScale.getName())) 
					&& (StringUtils.isNotBlank(packageScale.getEnName()))) {

				//Check Name or En_Name is unique
				ShippingPackingScaleQueryVO scaleQueryNameVO = new ShippingPackingScaleQueryVO();
				scaleQueryNameVO.setName(packageScale.getName());
				Integer count1 = shippingPackingScaleService.queryScaleListSelective(scaleQueryNameVO).size();
				
				ShippingPackingScaleQueryVO scaleQueryNameEnVO = new ShippingPackingScaleQueryVO();
				scaleQueryNameEnVO.setNameEn(packageScale.getEnName());
				Integer count2 = shippingPackingScaleService.queryScaleListSelective(scaleQueryNameEnVO).size();
				if ((count1 > 0) || (count2 > 0)) {
					result.buildData("包装规格名称或英文名称不可以重复").buildIsSuccess(false);
				} else {
					packageScale.setCreator(AppUtil.getLoginUserId());
					packageScale.setModifier(AppUtil.getLoginUserId());
					packageScale.setPackagingScaleNo(RandomUtils.getTimeRandom());
					shippingPackingScaleService.insertPackageScale(packageScale);
					result.buildIsSuccess(true);
				}
			} else {
				result.buildData("包装规格名称或英文名称不可以为空").buildIsSuccess(false);
			}
		} else {
			result.buildData("错误数据").buildIsSuccess(false);
		}
		return result;
	}
	
	/**
	 * 更新包装规格
	 * @param packageScale
	 * @return
	 */
	@RequestMapping("/packageScale/update")
	@ResponseBody
	public Object updateScale(ItemPackageScaleDTO packageScale) {
		JsonResult<String> result = new JsonResult<>();
		

		packageScale.setModifier(AppUtil.getLoginUserId());
		shippingPackingScaleService.updateScaleSelectiveById(packageScale);
		result.buildIsSuccess(true);
		return result;
	}
	
	/**
	 * 删除包装规格类别
	 * @return
	 */
	@RequestMapping("/packageScale/delete")
	@ResponseBody
	@Transactional(rollbackFor = ErpCommonException.class)
	public Object deleteById(Long id) {
		JsonResult<String> result = new JsonResult<>();
		//通过包装规格类别的id查到一个包装规格类别
		ItemPackageScaleDTO itemPackageSacle = shippingPackingScaleService.selectById(id);
		String packagingScaleNo = itemPackageSacle.getPackagingScaleNo();
		//通过打包规格查询是否存在这个包装规格
		int count = iPackageLevelService.countPatternsByScaleNo(packagingScaleNo);
		//如果返回的记录数超过0条就不能删除
		if(0 < count) {
			return result.buildIsSuccess(false).buildMsg("错误！此包装规格类别下有包装规格不能删除");
		}
		shippingPackingScaleService.delete(id);
		result.buildMsg("删除成功");
		return result.buildIsSuccess(true);
	}
	
	/**
	 * 包装规格list
	 * @param packageLevelQueryVO
	 * @return
	 */
	@RequestMapping("/packageLevel/queryPackageLevelList")
	@ResponseBody
	public Object queryPackageLevelList(PackageLevelQueryVO packageLevelQueryVO) {
		JsonPageResult<List<ItemPackagePatternDTO>> result = iPackageLevelService.queryPackageLevelList(packageLevelQueryVO);
		
		return result.buildIsSuccess(true);
	}
	
	/**
	 * 查询单个packagelevel
	 * @param packageLevelQueryVO
	 * @return
	 */
	@RequestMapping("/packageLevel/query")
	@ResponseBody
	public Object queryPackageLevelList(Long id) {
		JsonResult<ItemPackagePatternDTO> result = new JsonResult<>();
		PackageLevelQueryVO packageLevelQueryVO = new PackageLevelQueryVO();
		packageLevelQueryVO.setId(id);
		JsonPageResult<List<ItemPackagePatternDTO>> scale = iPackageLevelService.queryPackageLevelList(packageLevelQueryVO);
		result.setData(scale.getData().get(0));
		return result.buildIsSuccess(true);
	}
	
	/**
	 * 更新level
	 * @param packageScale
	 * @return
	 */
	@RequestMapping("/packageLevel/update")
	@ResponseBody
	@Transactional(rollbackFor = ErpCommonException.class)
	public Object update(ItemPackagePatternDTO packageLevel) {
		JsonResult<String> result = new JsonResult<>();
		/*
		if (null != packageLevel.getId()) {

			if ((StringUtils.isNotBlank(packageLevel.getName())) 
					&& (StringUtils.isNotBlank(packageLevel.getPackageEn()))) {
				if (!checkUniqueness(packageLevel)) {
					result.buildData("相同包装规格，包装级别名称不可以重复").buildIsSuccess(false);
				} else {
					result.buildIsSuccess(iPackageLevelService.updateSelectiveById(packageLevel));
				}
			} else {
				result.buildData("包装规格名称和包装规格类别英文名称不可以为空").buildIsSuccess(false);
			}
		} else {
			result.buildData("错误数据").buildIsSuccess(false);
		}
		*/
		String packagingScaleNo = shippingPackingScaleService.queryNoById(packageLevel.getPackageId());
		packageLevel.setPackagingScaleNo(packagingScaleNo);
		packageLevel.setModifier(AppUtil.getLoginUserId());
		iPackageLevelService.updateLevelSelectiveById(packageLevel);
		result.buildIsSuccess(true);
		return result;
	}
	
	/**
	 * 新增包装规格
	 * @param packageLevel
	 * @return
	 */
	@RequestMapping("/packageLevel/add")
	@ResponseBody
	@Transactional(rollbackFor = ErpCommonException.class)
	public Object add(ItemPackagePatternDTO packageLevel) {
		JsonResult<String> result = new JsonResult<>();
		
		if (null == packageLevel.getId()) {
			if ((StringUtils.isNotBlank(packageLevel.getName())) 
					&& (StringUtils.isNotBlank(packageLevel.getPackageEn()))) {
				/*if (!checkUniqueness(packageLevel)) {
					result.buildData("相同包装规格，包装级别名称不可以重复").buildIsSuccess(false);
				} else {
					result.buildIsSuccess(iPackageLevelService.insert(packageLevel));
				}*/
				/**老版本的前端传来的数据进行处理**/
				ShippingPackingPatternDO shippingPackingPatternDO = new ShippingPackingPatternDO();
				shippingPackingPatternDO.setCreator("admin");
				shippingPackingPatternDO.setModifier("admin");
				shippingPackingPatternDO.setName(packageLevel.getName());
				shippingPackingPatternDO.setNameEn(packageLevel.getPackageEn());
				shippingPackingPatternDO.setPatternNo(RandomUtils.getTimeRandom());
				shippingPackingPatternDO.setPackageLevel(packageLevel.getPackageLevel());
				shippingPackingPatternDO.setWeight(packageLevel.getWeight());
				//shippingPackingPatternDO.setPackagingScaleNo(packageLevel.getPackagingScaleNo());
				String packagingScaleNo = shippingPackingScaleService.queryNoById(packageLevel.getPackageId());
				shippingPackingPatternDO.setPackagingScaleNo(packagingScaleNo);
				shippingPackingPatternDO.setCreator(AppUtil.getLoginUserId());
				shippingPackingPatternDO.setModifier(AppUtil.getLoginUserId());
				iPackageLevelService.insertPattern(shippingPackingPatternDO);
			} else {
				result.buildData("包装规格名称和包装规格类别英文名称不可以为空").buildIsSuccess(false);
			}
		} else {
			result.buildData("错误数据").buildIsSuccess(false);
		}
		result.buildIsSuccess(true);
		return result;
	}
	
	@RequestMapping("/packageLevel/delete")
	@ResponseBody
	public Object delete(ItemPackagePatternDTO packageLevel) {
		JsonResult<String> result = new JsonResult<>();

		if (null != packageLevel.getId()) {
			iPackageLevelService.deleteById(packageLevel.getId());
			result.buildIsSuccess(true);
		} else {
			result.buildData("错误数据").buildIsSuccess(false);
		}
		
		return result;
	}
}
