package com.wangqin.globalshop.item.app.controller;

import java.util.List;

import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemBrandDO;
import com.wangqin.globalshop.biz1.app.vo.ItemBrandQueryVO;
import com.wangqin.globalshop.biz1.app.vo.JsonPageResult;
import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import com.wangqin.globalshop.common.utils.RandomUtils;
import com.wangqin.globalshop.item.app.service.IItemBrandService;

@Controller
@RequestMapping(value = "/item/brand")
public class ItemBrandController {

	@Autowired
	private IItemBrandService itemBrandService;



	/**
	 * 添加品牌(fin)
	 *
	 * @param
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Object add(ItemBrandDO brand) {
		JsonResult<ItemBrandDO> result = new JsonResult<>();
		//brand.setName("mingzi");
		//brand.setNameChina("中文名");
		//brand.setNameAlias("别名");
		brand.setBrandNo("b"+RandomUtils.getTimeRandom());
		brand.setCreator("admin");
		brand.setModifier("admin");
		// if (brand.getId() != null) {
		// return result.buildMsg("新增品牌ID有值").buildIsSuccess(false);
		// }
		// if(StringUtil.isBlank(brand.getName())) {
		// return result.buildMsg("英文品牌不能为空").buildIsSuccess(false);
		// }
		/**
		 * @author XiaJun，禁止重复品牌提交，英文名重复即视为重复
		 */
		// if(itemBrandService.selectByName(brand.getName()) != null) {
		// return result.buildMsg("添加失败，品牌已存在").buildIsSuccess(false);
		// }
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
	 * @param category
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Object update(ItemBrandDO brand) {
		JsonResult<ItemBrandDO> result = new JsonResult<>();
		/*if (brand.getId() == null) {
			return result.buildMsg("新增品牌ID为空").buildIsSuccess(false);
		}
		if (StringUtil.isBlank(brand.getName())) {
			return result.buildMsg("英文品牌不能为空").buildIsSuccess(false);
		}*/
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
		itemBrandService.deleteByPrimaryKey(id);
		return result.buildIsSuccess(true);
	}

	/**
	 * 分页查询(fin)
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
