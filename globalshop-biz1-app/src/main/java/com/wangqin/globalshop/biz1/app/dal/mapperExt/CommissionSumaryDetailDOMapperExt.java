package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.mapper.CommissionSumaryDetailDOMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * Create by 777 on 2018/7/31
 */
public interface CommissionSumaryDetailDOMapperExt extends CommissionSumaryDetailDOMapper {
	void updateStatusById(@Param("id") Long id, @Param("code") int code);


	void updateStatusByUserId(@Param("userId") String userId, @Param("code") int code);


	Double sumSettlement(@Param("list") List<Long> list);

	Double sumSettlementByUserId(@Param("userId") String userId);
}
