package com.wangqin.globalshop.deal.app.controller;

import com.wangqin.globalshop.biz1.app.dal.dataObject.DealerTypeDO;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.EasyUtil;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.deal.app.service.IDealerService;
import com.wangqin.globalshop.deal.app.service.IDealerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/06/05
 */
@Controller
@RequestMapping("/sellerType")
public class DealerTypeController {

    @Autowired
    private IDealerTypeService iSellerTypeService;

    @Autowired
    private IDealerService isellerService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Object add(DealerTypeDO sellerType) {
    	
        JsonResult<DealerTypeDO> result = new JsonResult<>();
        
        List<DealerTypeDO> list = iSellerTypeService.list(AppUtil.getLoginUserCompanyNo());
        
        if(EasyUtil.isStringEmpty(sellerType.getCode())) {
        	return result.buildIsSuccess(false).buildMsg("类别代码必填");
        }else {
        	if(EasyUtil.isStringEmpty(sellerType.getName())) {
            	return result.buildIsSuccess(false).buildMsg("类别名称必填");	
            }
        	for(int i = 0; i < list.size(); i ++) {
        		if(sellerType.getCode().equals(list.get(i).getCode())) {
        			return result.buildIsSuccess(false).buildMsg("类别代码已存在");
        		}
        	}
		}
        
     
        iSellerTypeService.insert(sellerType);
       
        return result.buildIsSuccess(true);
    }

    @RequestMapping("/update")
    @ResponseBody
    public Object update(DealerTypeDO sellerType) {
    	
    	JsonResult<DealerTypeDO> result = new JsonResult<>();
        
    	List<DealerTypeDO> list = iSellerTypeService.list(AppUtil.getLoginUserCompanyNo());


    	if(EasyUtil.isStringEmpty(sellerType.getCode())){
			return result.buildIsSuccess(false).buildMsg("类别代码必填");
		}
		if(EasyUtil.isStringEmpty(sellerType.getName())){
			return result.buildIsSuccess(false).buildMsg("类别名称必填");
		}

    	iSellerTypeService.update(sellerType);
    	
        return result.buildIsSuccess(true);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(DealerTypeDO sellerType) {
   
    	JsonResult<DealerTypeDO> result = new JsonResult<>();
    	//需要判断该类别下有没有销售管理
    	DealerTypeDO sellerTypeCode = iSellerTypeService.selectByPrimaryKey(sellerType.getId());
    	String typeCode = sellerTypeCode.getCode();
    	
    	int count = iSellerTypeService.countRelativeDealerType(typeCode);
    	
    	if(count > 0) {
    		return result.buildIsSuccess(false).buildMsg("错误，该类别下存在有销售");
    	}
    	
    	iSellerTypeService.deleteById(sellerType);
        return result.buildIsSuccess(true);
    }

    @RequestMapping("/query")
    @ResponseBody
    public Object query(Long id) {
        JsonResult<DealerTypeDO> result = new JsonResult<>();
        DealerTypeDO dealerTypeDO = iSellerTypeService.selectByPrimaryKey(id);
    	return result.buildData(dealerTypeDO).buildIsSuccess(true);
    }

    @RequestMapping("/querySellerTypeList")
    @ResponseBody
    public Object querySellerTypeList() {
        JsonResult<List<DealerTypeDO>> result = new JsonResult<>();
        List<DealerTypeDO> list = iSellerTypeService.list(AppUtil.getLoginUserCompanyNo());
        result.setData(list);
        return result.buildIsSuccess(true);
    }
}

