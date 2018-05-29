package com.wangqin.globalshop.order.app.service.mall.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallSubOrderDOMapper;
import com.wangqin.globalshop.biz1.app.vo.ShippingOrderVO;
import com.wangqin.globalshop.order.app.service.mall.IMallSubOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/05/28
 */
@Service
public class MallSubOrderServiceImpl implements IMallSubOrderService {
    @Autowired
    private MallSubOrderDOMapper mallSubOrderDOMapper;
    @Override
    public List<MallSubOrderDO> selectList(MallSubOrderDO tjErpOrder) {
        return mallSubOrderDOMapper.selectList(tjErpOrder);
    }

    @Override
    public List<MallSubOrderDO> selectByOrderNo(String mainId) {
        MallSubOrderDO mallSubOrderDO = new MallSubOrderDO();
        mallSubOrderDO.setOrderNo(mainId);
        return mallSubOrderDOMapper.selectList(mallSubOrderDO);
    }

    @Override
    public List<MallSubOrderDO> selectBatchIds(List<Long> erpOrderIdList) {
        return mallSubOrderDOMapper.selectBatchIds(erpOrderIdList);
    }

    @Override
    public void updateBatchById(List<MallSubOrderDO> erpOrderList) {
        for (MallSubOrderDO mallSubOrderDO : erpOrderList) {
            mallSubOrderDOMapper.updateByPrimaryKey(mallSubOrderDO);
        }
    }

    @Override
    public List<MallSubOrderDO> queryByShippingOrder(ShippingOrderVO shippingOrderQueryVO) {
        return mallSubOrderDOMapper.queryByShippingOrder(shippingOrderQueryVO);
    }
}
