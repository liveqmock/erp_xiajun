package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerTaskDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.DealerDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.TaskPurchaseVO;
import com.wangqin.globalshop.biz1.app.dal.mapper.BuyerTaskDOMapper;
import com.wangqin.globalshop.biz1.app.vo.BuyerTaskVO;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BuyerTaskDOMapperExt extends BuyerTaskDOMapper {
    int todayPurchasedItemNumber(Date today);
    int updateBatchById(List<BuyerTaskDO> taskList);
    List<BuyerTaskDO> queryTaskPurchaseList(TaskPurchaseVO tpVO);

    List<BuyerTaskVO> list(BuyerTaskVO buyerTask);

    void insertBatch(List<BuyerTaskDO> taskList);
    
//    List<Map<String, String>> dealerList(DealerDO seller);
}
