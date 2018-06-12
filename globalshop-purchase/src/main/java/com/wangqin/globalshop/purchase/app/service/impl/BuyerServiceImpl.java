package com.wangqin.globalshop.purchase.app.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.BuyerMapperExt;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.purchase.app.service.IBuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/06/12
 */
@Service
public class BuyerServiceImpl implements IBuyerService {
    @Autowired
    private BuyerMapperExt mapper;
    @Override
    public List<BuyerDO> list() {
        return mapper.list(AppUtil.getLoginUserCompanyNo());
    }
}
