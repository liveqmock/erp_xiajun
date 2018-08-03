package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.bean.dataVo.AgentOrderVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.SettlementDetailVo;
import com.wangqin.globalshop.biz1.app.bean.dataVo.SumaryDetailQueryVO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.CommissionSumaryDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.CommissionSumaryDOMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Create by 777 on 2018/7/31
 */
public interface CommissionSumaryDOMapperExt extends CommissionSumaryDOMapper {
	List<SettlementDetailVo> searchPageList(SumaryDetailQueryVO queryVO);

	List<CommissionSumaryDO> selectMorethan15Day();

	AgentOrderVO queryOrderInfoBySubOrderNo(@Param("subOrderNo") String subOrderNo,@Param("userNo") String userNo);

}
