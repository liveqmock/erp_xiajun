package com.wangqin.globalshop.deal.app.controller;

import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.dal.dataObject.DealerDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.DealerTypeDO;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.EasyUtil;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.common.utils.StringUtil;
import com.wangqin.globalshop.deal.app.service.IDealerService;
import com.wangqin.globalshop.deal.app.service.IDealerTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 
 * Title: DealerController.java
 * Description: Dealer Controller
 *
 * @author jc
 * Mar 18, 2017
 *
 */

@Controller
@Authenticated
@RequestMapping("/seller")
public class DealerController{

	@Autowired
	private IDealerService iDealerService;
	@Autowired
	private IDealerTypeService iDealerTypeService;
	@RequestMapping("/add")
	@ResponseBody
	public Object add(DealerDO seller) {
		 
		JsonResult<String> result = new JsonResult<>();
		
		List<Map<String, String>> dealerList = iDealerService.dealerList(seller);
		 
		 if(EasyUtil.isStringEmpty(seller.getCode())) {
	        	return result.buildIsSuccess(false).buildMsg("类别代码必填");
	        }else {
	        	if(EasyUtil.isStringEmpty(seller.getName())) {
	            	return result.buildIsSuccess(false).buildMsg("类别名称必填");	
	            }
	        	for(int i = 0; i < dealerList.size(); i ++) {
	        		if(seller.getCode().equals(dealerList.get(i).get("code"))) {
	        			return result.buildIsSuccess(false).buildMsg("类别代码已存在");
	        		}
	        	}
		 }
		 
		iDealerService.insert(seller);
		return result.buildIsSuccess(true);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Object update(DealerDO seller) {
		
		JsonResult<DealerTypeDO> result = new JsonResult<>();
    	
    	if(EasyUtil.isStringEmpty(seller.getCode())) {
    		return result.buildIsSuccess(false).buildMsg("销售代码必填");
    	}
    	if(EasyUtil.isStringEmpty(seller.getName())) {
    		return result.buildIsSuccess(false).buildMsg("用户名称必填必填");
    	}
        
    	iDealerService.updateByDealer(seller);
    	
        return result.buildIsSuccess(true);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(DealerDO seller) {

		JsonResult<DealerDO> result = new JsonResult<>();

		iDealerService.deleteByDealer(seller);
		return result.buildIsSuccess(true);
		
		
		
	
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public Object query(Long id) {
		
		JsonResult<DealerDO> result = new JsonResult<>();
		DealerDO dealerDo = iDealerService.selectByPrimaryKey(id);
		
		return result.buildData(dealerDo).buildIsSuccess(true);
	
	}
	
	@RequestMapping("/querySellerList")
	@ResponseBody
	public Object queryDealerList(DealerDO seller) {
		JsonResult<List<Map<String, String>>> result = new JsonResult<>();
		seller.setCompanyNo(AppUtil.getLoginUserCompanyNo());
		String name = seller.getName();
		if(!StringUtil.isBlank(name)) {
			seller.setName(name);
		}
		List<Map<String, String>> list = iDealerService.dealerList(seller);
		
		result.setData(list);
		
		return result.buildIsSuccess(true);
	}
}
