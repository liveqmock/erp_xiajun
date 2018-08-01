package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.bean.dataVo.SettlementQueryVO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.CommissionSumarySettlementDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.CommissionSumarySettlementDOMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Create by 777 on 2018/7/31
 */
public interface CommissionSumarySettlementDOMapperExt extends CommissionSumarySettlementDOMapper {
	List<CommissionSumarySettlementDO> searchByUserNo(@Param("userNo") String userNo);

	List<CommissionSumarySettlementDO> searchPageList(SettlementQueryVO queryVO);
}
