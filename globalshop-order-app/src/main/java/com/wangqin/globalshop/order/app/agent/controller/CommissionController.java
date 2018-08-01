package com.wangqin.globalshop.order.app.agent.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.bean.dataVo.AgentCommissionVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.AgentOrderVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.CommissionQueryVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonResult;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSaleAgentDO;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.IsEmptyUtil;
import com.wangqin.globalshop.order.app.agent.service.ICommissionSumaryDetailService;
import com.wangqin.globalshop.order.app.agent.service.ICommissionSumaryService;
import com.wangqin.globalshop.order.app.agent.service.MallSaleAgentService;

@Authenticated
@RequestMapping("/agentInfo")
public class CommissionController {
	  @Autowired
	    private MallSaleAgentService mallSaleAgentService;
	  @Autowired
	    private ICommissionSumaryService sumaryService;
	  @Autowired
	    private ICommissionSumaryDetailService sumaryDetailService;
	  
	  /**
	     * 查询代理的订单列表
	     *
	     * @param mallSaleAgentDO
	     * @return
	     */
	    @PostMapping("/orderList")
	    public Object getOrderList(CommissionQueryVO qv) {
	    	JsonResult<AgentCommissionVO> result = new JsonResult<AgentCommissionVO>();
	    	if(!loginCheck()) {
	    		return result.buildFailed("请先登录");
	    	}
	    	//计算分页参数
	    	Integer pageIndex = qv.getPageIndex();
	    	Integer pageSize = qv.getPageSize();
	    	qv.setFirstStart((pageIndex-1)*pageSize);
	    	//查询代理的信息
	    	if(IsEmptyUtil.isStringEmpty(qv.getUserNo())) {
	    		return result.buildFailed("请提供代理的编号");
	    	}
	    	String agentNo = qv.getUserNo();
	    	MallSaleAgentDO agentDO = mallSaleAgentService.queryAgentInfoByUserNo(agentNo);
	    	if(null == agentDO) {
	    		return result.buildFailed("代理不存在");
	    	}
	    	AgentCommissionVO agentCommissionVO = new AgentCommissionVO();
	    	agentCommissionVO.setUserNo(agentNo);
	    	agentCommissionVO.setProfile(agentDO.getHeadProtraitUrl());
	    	agentCommissionVO.setName(agentDO.getAgentName());
	    	agentCommissionVO.setCommission(sumaryDetailService.sumSettlementAbleByUserNo(agentNo));
	    	//查询订单信息
	    	List<AgentOrderVO> orderInfoList = new ArrayList<AgentOrderVO>();
	    	List<String> orderNoList = sumaryDetailService.querySubOrderNoListByUserNo(qv);
	    	if(IsEmptyUtil.isCollectionNotEmpty(orderNoList)) {
	    		for(String orderNo:orderNoList) {
	    			AgentOrderVO orderVO = sumaryService.queryOrderInfoBySubOrderNo(orderNo);
	    			if(null != orderVO) {
	    				orderInfoList.add(orderVO);
	    			}	    			
	    		}
	    		agentCommissionVO.setOrderInfo(orderInfoList);
	    	}
	    	result.buildData(agentCommissionVO).buildMsg("查找成功");
	    	return result;	    		    	
	    }
	    
	    

	    /**
	     * 工具类
	     * 用户登录判断
	     * @param itemCode
	     * @return
	     */
	    public Boolean loginCheck() {
	    	if(IsEmptyUtil.isStringEmpty(AppUtil.getLoginUserCompanyNo()) || IsEmptyUtil.isStringEmpty(AppUtil.getLoginUserId())) {
	         	return false;
	        }  
	    	return true;
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
}
