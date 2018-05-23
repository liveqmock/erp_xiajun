package com.wangqin.globalshop.biz1.app.service.impl;

import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.mapper.BuyerMapper;
import com.wangqin.globalshop.biz1.app.dal.dataObject.Buyer;
import com.wangqin.globalshop.biz1.app.service.IBuyerService;
import com.baomidou.framework.service.impl.SuperServiceImpl;

/**
 *
 * Buyer 表数据服务层接口实现类
 *
 */
@Service
public class BuyerServiceImpl extends SuperServiceImpl<BuyerMapper, Buyer> implements IBuyerService {


}