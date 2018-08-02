package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallCommisionApplyDO;
import org.apache.ibatis.annotations.Param;


import com.wangqin.globalshop.biz1.app.dal.mapper.MallCommisionApplyDOMapper;

import java.util.Date;

import java.util.List;

public interface MallCommisionApplyDOMapperExt extends MallCommisionApplyDOMapper{


    void updateStatusBySubOrderNo(@Param("status")String status,
                                  @Param("subOrderNo")String subOrderNo, @Param("receiveDate")Date receiveDate,
                                  @Param("receiveTime")String receiveTime);


	List<MallCommisionApplyDO> selectByStatusAndNotSync(@Param("status") String status);
}

