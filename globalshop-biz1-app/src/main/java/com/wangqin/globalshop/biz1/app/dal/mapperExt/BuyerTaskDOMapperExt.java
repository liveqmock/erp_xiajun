package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerTaskDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.TaskPurchaseVO;
import com.wangqin.globalshop.biz1.app.dal.mapper.BuyerTaskDOMapper;

import java.util.Date;
import java.util.List;

public interface BuyerTaskDOMapperExt extends BuyerTaskDOMapper {
    int todayPurchasedItemNumber(Date today);
    int updateBatchById(List<BuyerTaskDO> taskList);
    List<BuyerTaskDO> queryTaskPurchaseList(TaskPurchaseVO tpVO);

}
