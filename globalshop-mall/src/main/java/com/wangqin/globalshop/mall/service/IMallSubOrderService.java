package com.wangqin.globalshop.mall.service;


import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dto.MyOrderDTO;



/**
 * 
 */


public interface IMallSubOrderService {


	//一键分享，我的订单
    List<MyOrderDTO> queryOrderByShareUserId(String shareUserId,int start,String pageSize);    
    
    //一键分享，订单详情
    List<MallSubOrderDO> queryOrderDetailByTime(String shareUserId,String shareTime,int start,String pageSize);
  
}
