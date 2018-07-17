package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallReturnOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallReturnOrderDOMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallReturnOrderDOMapperExt extends MallReturnOrderDOMapper {

    List<MallReturnOrderDO> selectByCondition(@Param("orderNo") String orderNo,
                                              @Param("startGmtCreateDate") String startGmtCreateDate,
                                              @Param("endGmtCreateDate") String endGmtCreateDate,
                                              @Param("loginUserCompanyNo")String loginUserCompanyNo);
}