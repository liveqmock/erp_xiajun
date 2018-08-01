package com.wangqin.globalshop.schedule.job;

import com.wangqin.globalshop.biz1.app.bean.dataVo.SettlementDetailVo;
import com.wangqin.globalshop.biz1.app.dal.dataObject.CommissionSumaryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.CommissionSumaryDetailDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallCommisionApplyDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallCommisionApplyDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallSubOrderMapperExt;
import com.wangqin.globalshop.biz1.app.enums.OrderStatus;
import com.wangqin.globalshop.biz1.app.enums.SettlementStatus;
import com.wangqin.globalshop.common.base.BaseDto;
import com.wangqin.globalshop.common.enums.MallCommisionApplyStatus;
import com.wangqin.globalshop.common.utils.EasyUtil;
import com.wangqin.globalshop.common.utils.StringUtils;
import com.wangqin.globalshop.schedule.dto.AgencyModel;
import com.wangqin.globalshop.schedule.dto.CommisionModel;
import com.wangqin.globalshop.schedule.service.MallCommisionApplyService;
import com.wangqin.globalshop.schedule.service.SumaryDetailService;
import com.wangqin.globalshop.schedule.service.impl.CommissionSumaryServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sun.awt.geom.AreaOp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Create by 777 on 2018/7/31
 */

@Component
public class CommissionSumaryJob {

	protected static Logger log  = LoggerFactory.getLogger("CommissionSumaryJob");


	@Autowired
	private MallCommisionApplyService applyService;

	@Autowired
	private MallSubOrderMapperExt subOrderMapperExt;

	@Autowired
	private CommissionSumaryServiceImpl sumaryService;

	@Autowired
	private SumaryDetailService sumaryDetailService;


	/***
	 * 查看结算申请表mall_commision_apply
	 * 1、先查：已签收，且未生成佣金的记录
	 * 2、计算总佣金
	 * 3、生成 佣金记录，默认状态待结算
	 */
	@Scheduled(cron = "0 0 1,3,5 * * ? ")
	public void createCommissionSumary(){
		List<MallCommisionApplyDO> applyDOList =  applyService.selectByStatusAndNotSync(MallCommisionApplyStatus.RECEIVE.getCode());
		if(!EasyUtil.isListEmpty(applyDOList)){
			for(MallCommisionApplyDO applyDO : applyDOList){
				doCreateCommissionSumary(applyDO);
			}
		}
	}

	@Transactional
	public void doCreateCommissionSumary(MallCommisionApplyDO applyDO){
		//创建sumary
		MallSubOrderDO subOrderDO = subOrderMapperExt.selectBySubOrderNo(applyDO.getSubOrderNo());
		if(subOrderDO == null){
			return;
		}
		CommissionSumaryDO sumaryDO = new CommissionSumaryDO();
		sumaryDO.setCompanyNo(subOrderDO.getCompanyNo());
		sumaryDO.setItemCode(subOrderDO.getItemCode());
		sumaryDO.setItemName(subOrderDO.getItemName());
		sumaryDO.setOrderNo(subOrderDO.getOrderNo());
		sumaryDO.setOrderStatus(subOrderDO.getStatus().toString());
		sumaryDO.setOrderDesc(OrderStatus.of(subOrderDO.getStatus()).getDescription());

		sumaryDO.setQuantity(subOrderDO.getQuantity());
		sumaryDO.setOrderTime(subOrderDO.getOrderTime());
		sumaryDO.setReceiverInfo(subOrderDO.getReceiver()+" "
				+subOrderDO.getReceiverState()
				+subOrderDO.getReceiverCity()
				+subOrderDO.getReceiverCountry()
				+subOrderDO.getReceiverDistrict()
				+subOrderDO.getReceiverAddress());
		sumaryDO.setScale(subOrderDO.getScale());
		sumaryDO.setSkuCode(subOrderDO.getSkuCode());
		sumaryDO.setUpc(subOrderDO.getUpc());
		sumaryDO.setSkuPic(subOrderDO.getSkuPic());
		sumaryDO.setStatus(SettlementStatus.wait.getCode());
		sumaryService.insert(sumaryDO);

		//创建detail:1、查询代理，是否二级等
		String shareUserId = applyDO.getShareUserId();

		String commission = applyDO.getCommision();
		if (StringUtils.isBlank(commission)){

		}else{
			handleCommission(applyDO, commission);
		}

		//更新applyDO is_sync = 1
		applyDO.setIsSync(1);
		applyService.updateByPrimaryKeySelective(applyDO);
	}

	public void handleCommission(MallCommisionApplyDO applyDO, String commission) {
		CommisionModel model = BaseDto.fromJson(commission, CommisionModel.class);
		String strategy = model.getStrategy();
		if ("1".equals(strategy)){
			//二级代理模式
			String modelStr = model.getModel();
			AgencyModel agencyModel = BaseDto.fromJson(modelStr, AgencyModel.class);

			//1 判断是否有skuRate > 0
			BigDecimal totalAmount = BigDecimal.ZERO; //TODO
			String skuRateStr = agencyModel.getSkuRate();
			BigDecimal skuRate = BigDecimal.ZERO;
			if (StringUtils.isNotBlank(skuRateStr)){
				skuRate = new BigDecimal(skuRateStr);
			}
			BigDecimal levelOneRate = BigDecimal.ZERO;
			BigDecimal levelTwoRate = BigDecimal.ZERO;
			if (StringUtils.isNotBlank(agencyModel.getLevelOneRate())){
				levelOneRate = new BigDecimal(agencyModel.getLevelOneRate());
			}
			if (StringUtils.isNotBlank(agencyModel.getLevelTwoRate())){
				levelTwoRate = new BigDecimal(agencyModel.getLevelTwoRate());
			}

			BigDecimal levelOneAmount = BigDecimal.ZERO;
			BigDecimal levelTwoAmount  = BigDecimal.ZERO;
			BigDecimal shareMoney = BigDecimal.ZERO;
			if (BigDecimal.ZERO.compareTo(skuRate) < 0){
				//2.1 有
				shareMoney = totalAmount.multiply(skuRate).setScale(2, RoundingMode.HALF_UP);
				BigDecimal rateSum = levelOneRate;
				if (rateSum.compareTo(BigDecimal.ZERO) == 0){
					rateSum = BigDecimal.ONE;
				}
				levelTwoAmount = shareMoney.multiply(levelTwoRate).divide(rateSum).setScale(2, BigDecimal.ROUND_HALF_UP);
			}else{
				//2.2  没有
				shareMoney = totalAmount.multiply(levelOneRate).setScale(2, RoundingMode.HALF_UP);
				levelTwoAmount = shareMoney.multiply(levelTwoRate).setScale(2, BigDecimal.ROUND_HALF_UP);
			}
			levelOneAmount = shareMoney.subtract(levelTwoAmount);
			//3 插入detail表

			if(StringUtils.isNotBlank(agencyModel.getLevelOneUserId())){
				CommissionSumaryDetailDO detailDO = new CommissionSumaryDetailDO();
				detailDO.setCompanyNo(applyDO.getCompanyNo());
				detailDO.setSettlement(levelOneAmount);
				detailDO.setStatus(SettlementStatus.wait.getCode());
				detailDO.setShareUserId(agencyModel.getLevelOneUserId());
				detailDO.setSubOrderNo(applyDO.getSubOrderNo());
				sumaryDetailService.insert(detailDO);

			}
			if(StringUtils.isNotBlank(agencyModel.getLevelTwoUserId())){
				CommissionSumaryDetailDO detailDO = new CommissionSumaryDetailDO();
				detailDO.setCompanyNo(applyDO.getCompanyNo());
				detailDO.setSettlement(levelTwoAmount);
				detailDO.setStatus(SettlementStatus.wait.getCode());
				detailDO.setShareUserId(agencyModel.getLevelTwoUserId());
				detailDO.setSubOrderNo(applyDO.getSubOrderNo());
				sumaryDetailService.insert(detailDO);
			}
		}



	}
	/**
	 *  校验状态，检验是否已签收15天，是则可结算
	 */
	@Scheduled(cron = "0 0 1,3,5 * * ? ")
	public void checkStatusCommissionSumary(){
		List<CommissionSumaryDO> sumaryDOList = sumaryService.selectMorethan15Day();
		if(!EasyUtil.isListEmpty(sumaryDOList)){
			for(CommissionSumaryDO sumaryDO : sumaryDOList){

				//检查订单状态，如果是从已签收-->售后，则不应该进入可结算，增加关闭结算状态
				MallSubOrderDO subOrderDO = subOrderMapperExt.selectBySubOrderNo(sumaryDO.getSubOrderNo());

				if(subOrderDO == null){
					sumaryDO.setStatus(SettlementStatus.CLOSE.getCode());
				}else if(OrderStatus.SUCCESS.getCode() == subOrderDO.getStatus() || OrderStatus.COMFIRM.getCode() == subOrderDO.getStatus() ){
					//既不是已签收，也不是已完成，则关闭
					sumaryDO.setStatus(SettlementStatus.CLOSE.getCode());
				}else {
					sumaryDO.setStatus(SettlementStatus.can.getCode());
				}
				sumaryService.updateByPrimaryKeySelective(sumaryDO);
			}
		}
	}


}
