package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallWxPayBillDO;

public interface MallWxPayBillDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MallWxPayBillDO record);

    int insertSelective(MallWxPayBillDO record);

    MallWxPayBillDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallWxPayBillDO record);

    int updateByPrimaryKey(MallWxPayBillDO record);
}