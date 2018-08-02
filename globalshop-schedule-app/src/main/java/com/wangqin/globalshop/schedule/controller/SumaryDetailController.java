package com.wangqin.globalshop.schedule.controller;

import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonResult;
import com.wangqin.globalshop.biz1.app.bean.dataVo.SettlementDetailVo;
import com.wangqin.globalshop.biz1.app.bean.dataVo.SettlementQueryVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.SumaryDetailQueryVO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.CommissionSumarySettlementDO;
import com.wangqin.globalshop.common.base.BaseController;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.schedule.service.CommissionSumaryDetailService;
import com.wangqin.globalshop.schedule.service.SumaryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Create by 777 on 2018/7/31
 */

@RequestMapping("/sumarydetail")
@ResponseBody
@Controller
@Authenticated
public class SumaryDetailController extends BaseController {

	   @Autowired
	   private SumaryDetailService sumaryDetailService;


	   @Autowired
	   private CommissionSumaryDetailService detailService;

//		/**
//		 * 一般查询的结算时间，代理人姓名，结算单号
//		 * @param queryVO
//		 * @return
//		 */
//		@RequestMapping("/searchPageList")
//		public Object searchPageList(SumaryDetailQueryVO queryVO){
//			JsonResult<List<SettlementDetailVo>> result = new JsonResult<>();
//			try {
//				queryVO.setCompanyNo(AppUtil.getLoginUserCompanyNo());
//				List<SettlementDetailVo> list = sumaryDetailService.searchPageList(queryVO);
//				result.buildData(list);
//			} catch (Exception e) {
//				return result.buildIsSuccess(false).buildMsg(e.getMessage());
//			}
//			return result.buildIsSuccess(true);
//		}


	/**
	 *
	 * @param
	 * @return
	 */
	@RequestMapping("/sumSettlePageList")
	public Object sumSettlePageList(SumaryDetailQueryVO queryVO){
		JsonResult<List<Map<String,Object>>> result = new JsonResult<>();
		try {
			queryVO.setCompanyNo(AppUtil.getLoginUserCompanyNo());
			List<Map<String,Object>> list = detailService.sumSettlePageList(queryVO);
			result.buildData(list);
		} catch (Exception e) {
			logger.error("",e);
			return result.buildIsSuccess(false).buildMsg(e.getMessage());
		}
		return result.buildIsSuccess(true);

	}

}
