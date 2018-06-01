package com.wangqin.globalshop.order.app.service.mall.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallOrderDOMapperExt;
import com.wangqin.globalshop.order.app.service.mall.IMallOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/05/28
 */
@Service
public class MallOrderServiceImpl   implements IMallOrderService {
    @Autowired
    private MallOrderDOMapperExt mallOrderDOMapper;
    @Override
    public MallOrderDO selectById(Long id) {
        return mallOrderDOMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateById(MallOrderDO outerOrder) {
        mallOrderDOMapper.updateByPrimaryKey(outerOrder);
    }

    @Override
    public MallOrderDO selectByOrderNo(String orderNo) {
        return mallOrderDOMapper.selectByOrderNo(orderNo);
    }

    @Override
    public List<MallOrderDO> queryByStatus(byte status) {
        return mallOrderDOMapper.queryByStatus(status);
    }
}
