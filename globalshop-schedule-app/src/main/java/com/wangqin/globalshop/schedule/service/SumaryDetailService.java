package com.wangqin.globalshop.schedule.service;

import com.wangqin.globalshop.biz1.app.bean.dataVo.SettlementDetailVo;
import com.wangqin.globalshop.biz1.app.bean.dataVo.SumaryDetailQueryVO;

import java.util.List;

/**
 * Create by 777 on 2018/7/31
 */
public interface SumaryDetailService {
	List<SettlementDetailVo> searchPageList(SumaryDetailQueryVO queryVO);
}
