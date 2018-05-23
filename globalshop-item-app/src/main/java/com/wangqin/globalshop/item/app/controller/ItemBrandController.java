package com.wangqin.globalshop.item.app.controller;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemBrandDO;
import com.wangqin.globalshop.item.app.service.IItemBrandService;



@Controller
@RequestMapping(value = "/brand")
public class ItemBrandController{
	
	@Autowired
    private IItemBrandService itemBrandService;
	
	@RequestMapping(value = "/add")
	@ResponseBody
    public Object addBrand(ItemBrandDO itemBrandDO){
		JsonResult<ItemBrandDO> result = new JsonResult<>();
		/******for test******/
        itemBrandDO.setBrandNo("12");
        itemBrandDO.setCreator("xiajun");
        itemBrandDO.setModifier("xiaJun");
        itemBrandDO.setGmtCreate(new Date());
        itemBrandDO.setIsDel(false);
        itemBrandDO.setName("ASML");
        itemBrandDO.setNameAlias("mi");
        itemBrandDO.setNameChina("光科技");
        itemBrandDO.setSeq(null);
        itemBrandDO.setGmtModify(new Date());
        itemBrandService.add(itemBrandDO);
        System.out.println("添加品牌成功");
		/******for test******/
	    return result.buildMsg("添加品牌成功").buildIsSuccess(true);
   
    }
	
}
