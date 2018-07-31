package com.wangqin.globalshop.schedule.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.bean.dataVo.BuyerStorageDetailVo;
import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonResult;
import com.wangqin.globalshop.biz1.app.bean.dataVo.SettlementQueryVO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.CommissionSumarySettlementDO;
import com.wangqin.globalshop.common.base.BaseDto;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.schedule.service.CommissionSumarySettlementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Create by 777 on 2018/7/31
 */

@RequestMapping("/settlement")
@ResponseBody
@Controller
@Authenticated
public class SettlementController {

	@Autowired
	private CommissionSumarySettlementService settlementService;

	/**
	 * 暂时不开放
	 * @param searchByShareId
	 * @return
	 */
	@RequestMapping("/searchByShareId")
	public Object searchByOpenId(String searchByShareId){
		JsonResult<List<CommissionSumarySettlementDO>> result = new JsonResult<>();
		try {
			List<CommissionSumarySettlementDO> list = settlementService.searchByUserNo(searchByShareId);
			result.buildData(list);
		} catch (Exception e) {
			return result.buildIsSuccess(false).buildMsg(e.getMessage());
		}
		return result.buildIsSuccess(true);

	}


	@RequestMapping("/add")
	public Object add(CommissionSumarySettlementDO settlementDO){
		JsonResult<Object> result = new JsonResult<>();
		try {
			settlementService.add(settlementDO);
		} catch (Exception e) {
			return result.buildIsSuccess(false).buildMsg(e.getMessage());
		}
		return result.buildIsSuccess(true);

	}

	/**
	 * 结算选中的明细
	 * @param
	 * @return
	 */
	@RequestMapping("/do")
	public Object add(String idListStr,String shareUserId){
		JsonResult<Object> result = new JsonResult<>();
		try {
			List<Long> idList = BaseDto.fromJson(idListStr,new TypeReference<List<Long>>(){});
			settlementService.doSettlement(idList,shareUserId);
		} catch (Exception e) {
			return result.buildIsSuccess(false).buildMsg(e.getMessage());
		}
		return result.buildIsSuccess(true);

	}








}
