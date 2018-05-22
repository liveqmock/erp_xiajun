package com.wangqin.globalshop.web.controller;

import java.util.Date;

import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemBrandDO;
import com.wangqin.globalshop.biz1.app.service.IItemBrandService;
import com.wangqin.globalshop.common.base.BaseController;



@Controller
@RequestMapping(value = "/brand")
public class ItemBrandController extends BaseController{

	@Autowired
    private IItemBrandService itemBrandService;
	
	@RequestMapping(value = "/add")
	@ResponseBody
    public Object addBrand(ItemBrandDO itemBrandDO){
		JsonResult<ItemBrandDO> result = new JsonResult<>();
        itemBrandDO.setBrandNo("12");
        itemBrandDO.setCreator("xiajun");
        itemBrandDO.setModifier("xiaJun");
        itemBrandDO.setGmtCreate(new Date());
        itemBrandDO.setIsDel(false);
        itemBrandDO.setName("小米");
        itemBrandDO.setNameAlias("mi");
        itemBrandDO.setNameChina("xiaomi");
        itemBrandDO.setSeq(null);
        itemBrandDO.setGmtModify(new Date());
        itemBrandService.add(itemBrandDO);
        
       
		if (brand.getId() != null) {
			return result.buildMsg("新增品牌ID有值").buildIsSuccess(false);
		}
		if(StringUtil.isBlank(brand.getName())) {
			return result.buildMsg("英文品牌不能为空").buildIsSuccess(false);
		}
		/**
		 * @author XiaJun，禁止重复品牌提交，英文名重复即视为重复
		 */
		if(brandService.selectByName(brand.getName()) != null) {
			return result.buildMsg("添加失败，品牌已存在").buildIsSuccess(false);
		}
		brandService.addBrand(brand);
		return result.buildIsSuccess(true);
	}
        System.out.println("添加品牌成功");
    }
	
}
