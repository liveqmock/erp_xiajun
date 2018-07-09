package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerTaskDetailDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.BuyerTaskDetailDOMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BuyerTaskDetailDOMapperExt extends BuyerTaskDetailDOMapper {
    void inserBatch(List<BuyerTaskDetailDO> detailList);

    void updateTaskDetailDailyStatus(@Param("status") Integer status, @Param("buyerTaskNo") String buyerTaskNo);
}
