package com.wangqin.globalshop.schedule.service.impl;

import com.mchange.v2.codegen.CodegenUtils;
import com.wangqin.globalshop.biz1.app.bean.dataVo.SettlementQueryVO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.CommissionSumarySettlementDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.CommissionSumarySettlementDOMapper;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.CommissionSumaryDetailDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.CommissionSumarySettlementDOMapperExt;
import com.wangqin.globalshop.biz1.app.enums.SettlementStatus;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.CodeGenUtil;
import com.wangqin.globalshop.common.utils.EasyUtil;
import com.wangqin.globalshop.schedule.service.CommissionSumarySettlementService;
import net.sourceforge.htmlunit.corejs.javascript.optimizer.Codegen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Create by 777 on 2018/7/31
 */


@Service
public class CommissionSumarySettlementServiceImpl implements CommissionSumarySettlementService {

	@Autowired
	private CommissionSumarySettlementDOMapperExt settlementDOMapperExt;

	@Autowired
	private CommissionSumaryDetailDOMapperExt detailDOMapperExt;

	public List<CommissionSumarySettlementDO> searchByUserNo(String searchByShareId){

		return settlementDOMapperExt.searchByUserNo(searchByShareId);
	}

	public void add(CommissionSumarySettlementDO settlementDO){

		//todo 需要校验一些字段的合法性
		settlementDOMapperExt.insert(settlementDO);

	}

	public List<CommissionSumarySettlementDO> searchPageList(SettlementQueryVO queryVO){
           List<CommissionSumarySettlementDO> resultList = new ArrayList<>();
		   List<CommissionSumarySettlementDO> pageList = settlementDOMapperExt.searchPageList(queryVO);
		   resultList.addAll(pageList);
		   return resultList;
	}

	public void doSettlement(List<Long> idList, String shareUserId){

		if(EasyUtil.isStringEmpty(shareUserId)){
			throw new ErpCommonException("shareUserId_empty","结算ID必填");
		}

		//优化一下
		if(EasyUtil.isListEmpty(idList)){
			idList = detailDOMapperExt.selectByShareId(shareUserId);
		}

		if(!EasyUtil.isListEmpty(idList)){
			CommissionSumarySettlementDO settlementDO = new CommissionSumarySettlementDO();
			settlementDO.setShareUserId(shareUserId);
			settlementDO.setCompanyNo(AppUtil.getLoginUserCompanyNo());
			settlementDO.setDetailCount(idList.size());
			//settlementDO.setPayType();
			settlementDO.setSettlementNo(CodeGenUtil.getSettlementNo());
			settlementDO.setSettlementTime(new Date());



			//Double settlement = detailDOMapperExt.sumSettlement(idList);

			Map<String,Double> priceMap = detailDOMapperExt.sumPriceByIdList(idList);

			Double settlement = priceMap.get("settlement");
			Double salePrice = priceMap.get("salePrice");

			settlementDO.setTotalPrice(BigDecimal.valueOf(salePrice).setScale(2,BigDecimal.ROUND_HALF_UP));

			settlementDO.setSettlement(BigDecimal.valueOf(settlement).setScale(2,BigDecimal.ROUND_HALF_UP));

			settlementDOMapperExt.insert(settlementDO);

			for(Long id : idList){
				//todo 结算单号未更新
				detailDOMapperExt.updateStatusById(id, SettlementStatus.SUCCESS.getCode(),settlementDO.getSettlementNo());
			}

		}else {
		      throw new ErpCommonException("can settlement error","当前无可结算订单");
		}

	}


}
