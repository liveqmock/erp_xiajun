package com.wangqin.globalshop.order.app.agent.service;

import com.wangqin.globalshop.biz1.app.bean.dataVo.AgentOrderVO;

public interface ICommissionSumaryService {

	AgentOrderVO queryOrderInfoBySubOrderNo(String subOrderNo);
}
