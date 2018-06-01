package com.wangqin.globalshop.order.app.service.mall.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallCustomerDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallCustomerDOMapperExt;
import com.wangqin.globalshop.order.app.service.mall.OrderMallCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/05/28
 */
@Service
public class OrderMallCustomerServiceImpl implements OrderMallCustomerService {
    @Autowired
    private MallCustomerDOMapperExt mallCustomerDOMapper;
    @Override
    public List<MallCustomerDO> selectByRole(Integer role) {
        return mallCustomerDOMapper.selectByRole(role);
    }
}
