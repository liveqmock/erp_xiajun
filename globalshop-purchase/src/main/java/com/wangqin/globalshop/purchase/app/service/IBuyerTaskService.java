package com.wangqin.globalshop.purchase.app.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerTaskDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.BuyerTaskVO;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/06/12
 */
public interface IBuyerTaskService {

    List<BuyerTaskDO> list(BuyerTaskDO buyerTaskDO);

    void add(BuyerTaskVO buyerTaskDO);

    void importTask(List<List<Object>> list);



}
