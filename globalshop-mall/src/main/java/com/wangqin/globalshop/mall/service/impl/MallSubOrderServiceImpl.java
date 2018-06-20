package com.wangqin.globalshop.mall.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallSubOrderMapperExt;
import com.wangqin.globalshop.mall.service.IMallSubOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MallSubOrderServiceImpl implements IMallSubOrderService{

    @Autowired
    private MallSubOrderMapperExt mallSubOrderDOMapperExt;

    @Override
    public List<MallSubOrderDO> queryOrdersByShareUserId(String shareUserId) {
        return mallSubOrderDOMapperExt.queryOrdersByShareUserId(shareUserId);
    }
}
