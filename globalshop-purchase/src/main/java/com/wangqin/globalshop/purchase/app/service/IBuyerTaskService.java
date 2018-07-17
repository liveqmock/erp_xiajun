package com.wangqin.globalshop.purchase.app.service;

import com.wangqin.globalshop.biz1.app.vo.BuyerTaskVO;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/06/12
 */
public interface IBuyerTaskService {

    List<BuyerTaskVO> list(BuyerTaskVO buyerTaskVO);

    void add(BuyerTaskVO buyerTaskDO);

    void importTask(List<List<Object>> list) throws Exception;

    void updateTaskStatus(Integer status,List<Long> taskDailyIdList);

	public BuyerTaskVO selectVoById(Long id);


	void update(BuyerTaskVO buyerTaskDO);
}
