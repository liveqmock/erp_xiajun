package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import org.apache.ibatis.annotations.Param;


import com.wangqin.globalshop.biz1.app.dal.mapper.MallCommisionApplyDOMapper;

import java.util.Date;

public interface MallCommisionApplyDOMapperExt extends MallCommisionApplyDOMapper{


    void updateStatusBySubOrderNoAndCompanyNo(@Param("status")String status,
                                              @Param("companyNo")String companyNo, @Param("subOrderNo")String subOrderNo,
                                              @Param("receiveDate")Date receiveDate, @Param("receiveTime")String receiveTime);
}