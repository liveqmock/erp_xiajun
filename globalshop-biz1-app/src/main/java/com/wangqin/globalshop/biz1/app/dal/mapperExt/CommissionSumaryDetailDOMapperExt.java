package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import java.math.BigDecimal;
import java.util.List;

import com.wangqin.globalshop.biz1.app.bean.dataVo.SumaryDetailQueryVO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.CommissionSumaryDetailDO;
import com.wangqin.globalshop.common.utils.BigDecimalHelper;
import org.apache.ibatis.annotations.Param;


import com.wangqin.globalshop.biz1.app.bean.dataVo.CommissionQueryVO;
import com.wangqin.globalshop.biz1.app.dal.mapper.CommissionSumaryDetailDOMapper;


import java.util.Map;


/**
 * Create by 777 on 2018/7/31
 */
public interface CommissionSumaryDetailDOMapperExt extends CommissionSumaryDetailDOMapper {
	void updateStatusById(@Param("id") Long id, @Param("status") int status, @Param("settlementNo") String settlementNo);


	void updateStatusByUserId(@Param("userId") String userId, @Param("code") int code);


	Double sumSettlement(@Param("list") List<Long> list);

	Double sumSettlementByUserId(@Param("userId") String userId);

	
	Double sumSettlementAbleByUserNo(@Param("userNo") String userNo,@Param("status")Integer status);
	
	List<String> querySubOrderNoListByUserNo(CommissionQueryVO qv);


	List<Long> selectByShareId(@Param("shareUserId") String shareUserId);

	Map<String,BigDecimal> sumPriceByIdList(@Param("list") List<Long> list);
	
	Map<String,Object> sumOrderNumSaleCommissionByUserNo(@Param("status")Integer status,@Param("userNo")String userNo);

	List<Map<String,Object>> sumSettlePageList(SumaryDetailQueryVO queryVO);

	List<CommissionSumaryDetailDO> selectBySubOrderNo(@Param("subOrderNo") String subOrderNo);
}
