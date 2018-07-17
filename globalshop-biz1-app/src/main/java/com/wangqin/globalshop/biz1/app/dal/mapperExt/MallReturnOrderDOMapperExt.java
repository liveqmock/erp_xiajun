package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallReturnOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallReturnOrderDOMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MallReturnOrderDOMapperExt extends MallReturnOrderDOMapper {
    List<MallReturnOrderDO> list();

    List<MallReturnOrderDO> selectByCondition(@Param("orderNo") String orderNo,
                                              @Param("startGmtCreateDate") String startGmtCreateDate,
                                              @Param("endGmtCreateDate") String endGmtCreateDate);
}