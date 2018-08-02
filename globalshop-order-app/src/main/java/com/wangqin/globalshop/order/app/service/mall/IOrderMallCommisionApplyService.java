package com.wangqin.globalshop.order.app.service.mall;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author xiajun
 *
 */
public interface IOrderMallCommisionApplyService {

	 void updateStatusBySubOrderNo(String subOrderNo);
}
