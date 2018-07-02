package com.wangqin.globalshop.purchase.app.service;



import java.util.List;

import com.wangqin.globalshop.biz1.app.vo.BuyerTaskVO;

/**
 * @author biscuit
 * @data 2018/06/12
 */
public interface IBuyerTaskService {

    List<BuyerTaskVO> list(BuyerTaskVO buyerTaskVO);

    void add(BuyerTaskVO buyerTaskDO);

    void importTask(List<List<Object>> list);



}
