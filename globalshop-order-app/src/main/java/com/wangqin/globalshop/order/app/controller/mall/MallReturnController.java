package com.wangqin.globalshop.order.app.controller.mall;

import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallReturnOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.MallReturnOrderVO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallReturnOrderDOMapperExt;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.DateUtils;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.common.utils.StringUtils;
import com.wangqin.globalshop.order.app.service.mall.IMallReturnOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @author liuyang
 *
 */
@Controller
@RequestMapping("/erpReturnOrder")
@Authenticated
public class MallReturnController {
	@Autowired
	private IMallReturnOrderService erpReturnOrderService;

	@Autowired
	private MallReturnOrderDOMapperExt mapper;

	
	@PostMapping("/index")
	@ResponseBody
	public Object index(@RequestParam(value = "orderNo",required = false) String orderNo,
						@RequestParam(value = "startGmtCreate",required = false) String startGmtCreate,
						@RequestParam(value = "endGmtCreate",required = false) String endGmtCreate
						) {
		if (StringUtils.isBlank(orderNo) || "null".equals(orderNo)){
			orderNo = null;
		}
		if (StringUtils.isBlank(startGmtCreate) || "null".equals(startGmtCreate)){
			startGmtCreate = null;
		}
		if (StringUtils.isBlank(endGmtCreate) || "null".equals(endGmtCreate)){
			endGmtCreate = null;
		}

		JsonResult<List<MallReturnOrderDO>> result= new JsonResult<>();

		Date startGmtCreateDate = null;
		Date endGmtCreateDate = null;
		if(StringUtils.isNotBlank(startGmtCreate)){
			startGmtCreateDate = DateUtils.dateBeginning(startGmtCreate);
		}
		if (StringUtils.isNotBlank(endGmtCreate)){
			endGmtCreateDate = DateUtils.dateEnd(endGmtCreate);
		}
		List<MallReturnOrderDO> list = mapper.selectByCondition(orderNo, startGmtCreateDate, endGmtCreateDate);
		result.setData(list);
		result.setSuccess(true);
		return result;
	}
	

	@PostMapping("/add")
	@ResponseBody
	public Object add(MallReturnOrderVO erpReturnOrder) {
		JsonResult<MallReturnOrderVO> result = new JsonResult<>();
		if(erpReturnOrder.getErpOrderId()==null) {
			return result.buildIsSuccess(false).buildMsg("参数异常");
		}
		erpReturnOrder.setReturnRefer(0);
		try {
			erpReturnOrderService.add(erpReturnOrder);
		}catch (ErpCommonException exception){
			return result.buildIsSuccess(false).buildMsg(exception.getErrorMsg());
		}
		result.setSuccess(true);
		return result;
	}




}
