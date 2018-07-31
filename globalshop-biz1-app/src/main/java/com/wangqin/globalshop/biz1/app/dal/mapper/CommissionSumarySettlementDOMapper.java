package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.CommissionSumarySettlementDO;

public interface CommissionSumarySettlementDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CommissionSumarySettlementDO record);

    int insertSelective(CommissionSumarySettlementDO record);

    CommissionSumarySettlementDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CommissionSumarySettlementDO record);

    int updateByPrimaryKey(CommissionSumarySettlementDO record);
}