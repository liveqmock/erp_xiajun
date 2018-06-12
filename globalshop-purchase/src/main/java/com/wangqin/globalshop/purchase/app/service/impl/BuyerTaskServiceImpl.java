package com.wangqin.globalshop.purchase.app.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerTaskDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.BuyerTaskDOMapperExt;
import com.wangqin.globalshop.purchase.app.service.IBuyerService;
import com.wangqin.globalshop.purchase.app.service.IBuyerTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/06/12
 */
@Service
public class BuyerTaskServiceImpl implements IBuyerTaskService {
    @Autowired
    private BuyerTaskDOMapperExt mapper;
    @Override
    public List<BuyerTaskDO> list(BuyerTaskDO buyerTask) {
        buyerTask.initCompany();
        return mapper.list(buyerTask);
    }
}
