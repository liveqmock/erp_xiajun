package com.wangqin.globalshop.schedule.service.impl;

import com.wangqin.globalshop.biz1.app.bean.dataVo.SettlementDetailVo;
import com.wangqin.globalshop.biz1.app.bean.dataVo.SumaryDetailQueryVO;
import com.wangqin.globalshop.biz1.app.dal.mapper.CommissionSumaryDetailDOMapper;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.CommissionSumaryDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.CommissionSumarySettlementDOMapperExt;
import com.wangqin.globalshop.biz1.app.enums.SettlementStatus;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.schedule.service.SumaryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by 777 on 2018/7/31
 */
@Service
public class SumaryDetailServiceImpl implements SumaryDetailService {


	@Autowired
	private CommissionSumaryDOMapperExt sumaryDOMapperExt;

	public List<SettlementDetailVo> searchPageList(SumaryDetailQueryVO queryVO){

		List<SettlementDetailVo> detailVoList = new ArrayList<>();



		//把必填项弄好，
		//代理shareUserId必填
		//status必填

		if(queryVO.getShareUserId() == null){
               throw new ErpCommonException("share_user_id_error","代理人必填");
		}
		if(queryVO.getStatus() == null){
			queryVO.setStatus(SettlementStatus.wait.getCode());
		}

		//先查detail  join sumary

		List<SettlementDetailVo> pageResult = sumaryDOMapperExt.searchPageList(queryVO);

		//出来后再查user表

		//再计算佣金总数


	}

}
