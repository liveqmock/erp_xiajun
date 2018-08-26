package com.wangqin.globalshop.item.app.controller;

import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemBrandQueryVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonPageResult;
import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonResult;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemBrandDO;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.RandomUtils;
import com.wangqin.globalshop.item.api.itembrand.ItemBrandFeignService;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import java.util.List;


@Controller
@RequestMapping(value = "/item/brand")
@Authenticated
public class ItemBrandController {



	// 旧方法
//	@Autowired
//	private IItemBrandService itemBrandService;  记住用，最小该变量ItemBrandFeignService代替即可

	//新方法
	@Autowired
	private ItemBrandFeignService itemBrandService;//feign声明式服务的高级版


	@Autowired RestTemplate restTemplate;  //第二种方案


	/**
	 * 添加品牌(fin)
	 *
	 * @param
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	@Transactional(rollbackFor = ErpCommonException.class)
	public Object add(ItemBrandDO brand) {
		JsonResult<ItemBrandDO> result = new JsonResult<>();
		brand.setBrandNo("b" + RandomUtils.getTimeRandom());
		brand.setCreator(AppUtil.getLoginUserId());
		brand.setModifier(AppUtil.getLoginUserId());
		if (itemBrandService.selectBrandNoByName(brand.getName()) != null) {
			return result.buildMsg("添加失败，品牌已存在").buildIsSuccess(false);
		}
		itemBrandService.insertBrandSelective(brand);
		return result.buildIsSuccess(true);
	}

	/**
	 * 根据id查找品牌(fin)
	 *
	 * @param
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public Object query(Long id) {
		JsonResult<ItemBrandDO> result = new JsonResult<>();
		ItemBrandDO brand = itemBrandService.selectByPrimaryKey(id);
		result.setData(brand);
		return result.buildIsSuccess(true);
	}

	/**
	 * 修改品牌(fin)
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	@Transactional(rollbackFor = ErpCommonException.class)
	public Object update(ItemBrandDO brand) {
		JsonResult<ItemBrandDO> result = new JsonResult<>();
		if (StringUtil.isBlank(brand.getName())) {
			return result.buildMsg("皮牌英文名不能为空").buildIsSuccess(false);
		}

		List<Long> idList = itemBrandService.queryIdListByBrandName(brand.getName());
		for(Long id:idList) {
			if(!id.equals(brand.getId())) {
				return result.buildIsSuccess(false).buildMsg("品牌英文名不能和已有的品牌重合");
			}
		}
		brand.setModifier(AppUtil.getLoginUserId());
		itemBrandService.updateBrand(brand);
		return result.buildIsSuccess(true);
	}

	/**
	 * 删除品牌(fin)
	 *
	 * @param
	 * @return
	 */
	
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(Long id) {
		JsonResult<ItemBrandDO> result = new JsonResult<>();
		itemBrandService.deleteItemBrandById(id);
		return result.buildIsSuccess(true);
	}

	/**
	 * 分页查询(fin)，用在品牌管理部分
	 */
	@RequestMapping("/queryBrands")
	@ResponseBody
	public Object queryItemBrandDOs(ItemBrandQueryVO brandQueryVO) {
		JsonPageResult<List<ItemBrandDO>> result = new JsonPageResult<>();
		result = itemBrandService.queryBrands(brandQueryVO);
		return result.buildIsSuccess(true);
	}

	/**
	 * 总查询，用在商品列表(fin)
	 */
	
	@RequestMapping("/queryAllBrand")
	@ResponseBody
	public Object queryItemBrandDOpage(ItemBrandDO brand) {
		JsonResult<List<ItemBrandDO>> result = itemBrandService.queryAllBrand();
		return result.buildIsSuccess(true);
	}
	

}
