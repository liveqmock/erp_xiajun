package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSaleAgentDO;

public interface MallSaleAgentDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MallSaleAgentDO record);

    int insertSelective(MallSaleAgentDO record);

    MallSaleAgentDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallSaleAgentDO record);

    int updateByPrimaryKey(MallSaleAgentDO record);
}