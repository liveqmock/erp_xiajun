package com.wangqin.globalshop.deal.app.controller;

import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.dal.dataObject.DealerDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.DealerTypeDO;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.deal.app.service.IDealerService;
import com.wangqin.globalshop.deal.app.service.IDealerTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
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
		
		List<DealerDO> list2 = iDealerService.list2();
		
		if("".equals(seller.getCode()) || seller.getCode() == null) {
			return result.buildIsSuccess(false).buildMsg("Dealer code is null");
		}else {
        	if("".equals(seller.getName()) || seller.getName() == null) {
            	return result.buildIsSuccess(false).buildMsg("DealerType name is null");	
            }
        	for(int i = 0; i < list2.size(); i ++) {
         		if(seller.getCode().equals(list2.get(i).getCode()) && !Objects.equals(seller.getId(), list2.get(i).getId())) {
         			return result.buildIsSuccess(false).buildMsg("错误！不能使用该销售代码");
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
 
    	List<DealerDO> list2 = iDealerService.list2();
    	
        if("".equals(seller.getCode()) || seller.getCode() == null) {
         	
         	return result.buildIsSuccess(false).buildMsg("DealerType code is null");
         	
         }else {
         	if("".equals(seller.getName()) || seller.getName() == null) {
             	return result.buildIsSuccess(false).buildMsg("DealerType name is null");	
             }
         	
         	
         	for(int i = 0; i < list2.size(); i ++) {
         		if(seller.getCode().equals(list2.get(i).getCode()) && !Objects.equals(seller.getId(), list2.get(i).getId())) {
         			return result.buildIsSuccess(false).buildMsg("错误！不能使用该销售代码");
         		}
         	}
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
	public Object queryDealerList() {
		JsonResult<List<DealerDO>> result = new JsonResult<>();
		List<DealerDO> list = iDealerService.list();
		
		result.setData(list);
		
		return result.buildIsSuccess(true);
	}
}
