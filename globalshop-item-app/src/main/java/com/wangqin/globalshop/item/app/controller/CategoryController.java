package com.wangqin.globalshop.item.app.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemCategoryDO;
import com.wangqin.globalshop.biz1.app.dto.ItemCategoryDTO;
import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import com.wangqin.globalshop.common.utils.RandomUtils;
import com.wangqin.globalshop.item.app.service.IItemCategoryService;


@Controller
@RequestMapping("/category")
public class CategoryController  {

	@Autowired
	private IItemCategoryService categoryService;

	/**
	 * 添加类目（fin)，提供name和pcode
	 *
	 * @param
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Object add(ItemCategoryDO category) {
		//category.setCategoryCode("0000000");//XiaJun，在确定了category_code的生成方式之后，再重写这一句
		//ShiroUser shiroUser = this.getShiroUser();
		JsonResult<ItemCategoryDO> result = new JsonResult<>();
		if (category.getpCode() == null) {//添加一级类目
			category.setpCode("00000000");
			category.setRootCode("00000000");
			category.setLevel(1);			
		} else {
			ItemCategoryDO categoryP = categoryService.queryByCategoryCode(category.getpCode());
			if (categoryP == null) {
				return result.buildIsSuccess(false).buildMsg("not find parent category!");
			} else {
				category.setLevel(categoryP.getLevel()+1);
				if(category.getLevel() > 3){
					return result.buildIsSuccess(false).buildMsg("不支持新增4级及以上的类目");
				}
				if(StringUtils.isNotEmpty(categoryP.getAllPath())){
					category.setAllPath(categoryP.getAllPath()+"/"+category.getName());
				}else{
					category.setAllPath(categoryP.getName()+"/"+category.getName());
				}
				if(category.getLevel() == 2){
					category.setRootCode(categoryP.getCategoryCode());
				}else{
					category.setRootCode(categoryP.getRootCode());
				}
			}
		}
		
		category.setCategoryCode(RandomUtils.getTimeRandom());
		category.setCreator("admin");
		category.setModifier("admin");
		category.setStatus(1);//设置为有效状态
		categoryService.insertCategorySelective(category);
		return result.buildIsSuccess(true);
	}

	/**
	 * 修改类目(fin)，需要传入pCode
	 * 
	 * @param category
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Object update(ItemCategoryDO category) {
		//ShiroUser shiroUser = this.getShiroUser();
		JsonResult<ItemCategoryDO> result = new JsonResult<>();
		Long id = category.getId();
		if (id == null) {
			return result.buildIsSuccess(false).buildMsg("category id is null!");
		}
		if(category.getpCode()!=null){
			ItemCategoryDO categoryP = categoryService.queryByCategoryCode(category.getpCode());
			if (categoryP == null) {
				return result.buildIsSuccess(false).buildMsg("not find parent category!");
			} else {
				category.setLevel(categoryP.getLevel() + 1);
				if(StringUtils.isNotEmpty(categoryP.getAllPath())){
					category.setAllPath(categoryP.getAllPath()+"/"+category.getName());
				}else{
					category.setAllPath(categoryP.getName()+"/"+category.getName());
				}
				if(category.getLevel()==2){
					category.setRootCode(categoryP.getCategoryCode());
				}else{
					category.setRootCode(categoryP.getRootCode());
				}
			}
		}

		category.setModifier("admin");
		categoryService.update(category);
		return result.buildIsSuccess(true);
	}

	/**
	 * 删除类目，软删除(fin)
	 * 
	 * @param category
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(ItemCategoryDO category) {
		JsonResult<ItemCategoryDO> result = new JsonResult<>();
		Long id = category.getId();
		if (id == null) {
			return result.buildIsSuccess(false).buildMsg("category id is null!");
		}
		ItemCategoryDO categoryP = categoryService.findCategory(id);
		if (categoryP == null) {
			return result.buildIsSuccess(false).buildMsg("not find category!");
		}
		// TODO give session and set modify
//		category.setGmtModify(new Date());
		// category.setUserModify(userModify);
//		category.setStatus(1);// 状态设置为1，假删除
//		categoryService.updateSelectiveById(category);
		categoryService.deleteById(category);
		return result.buildIsSuccess(true);
	}
	
	

	/**
	 * 查询类目(fin)
	 * 
	 * @param category
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public Object query(ItemCategoryDO category) {
		JsonResult<ItemCategoryDO> result = new JsonResult<>();
		Long id = category.getId();
		if (id == null) {
			return result.buildIsSuccess(false).buildMsg("category id is null!");
		}
		ItemCategoryDO categoryP = categoryService.findCategory(id);
		if (categoryP == null) {
			return result.buildIsSuccess(false).buildMsg("not find category!");
		}
		return result.buildData(categoryP).buildIsSuccess(true);
	}
	
	
	/**
	 * 查询所有类目(fin)
	 *
	 * @return
	 */
	@RequestMapping("/queryList")
	@ResponseBody
	public Object queryList() {
		JsonResult<List<ItemCategoryDO>> result = new JsonResult<>();
		result.setData(categoryService.selectAll());
		result.setSuccess(true);
		return result;
	}
	
	/**
	 * 类目管理列表（fin)s
	 *
	 * @return
	 */
	@RequestMapping("/tree")
	@ResponseBody
	public Object tree() {
		JsonResult<List<ItemCategoryDTO>> result = new JsonResult<>();
		result.setData(categoryService.tree());
		result.setSuccess(true);
		return result;
	}
	

}
