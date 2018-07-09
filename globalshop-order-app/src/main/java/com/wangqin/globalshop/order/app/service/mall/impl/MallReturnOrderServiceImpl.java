package com.wangqin.globalshop.order.app.service.mall.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallReturnOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.MallReturnOrderVO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallReturnOrderDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallSubOrderMapperExt;
import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.inventory.app.service.InventoryService;
import com.wangqin.globalshop.order.app.comm.Constant;
import com.wangqin.globalshop.order.app.service.mall.IMallReturnOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.wangqin.globalshop.order.app.comm.Constant.ORDER_SATUTS_RETURNDONE;

/**
 * @author biscuit
 * @data 2018/06/08
 */
@Service
public class MallReturnOrderServiceImpl implements IMallReturnOrderService {
    @Autowired
    private MallReturnOrderDOMapperExt mapper;
    @Autowired
    private MallSubOrderMapperExt mallSubOrderMapper;
    @Autowired
    private InventoryService inventoryService;

    @Override
    public List<MallReturnOrderDO> list() {

        return mapper.list();

    }

    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void add(MallReturnOrderVO erpReturnOrder) {

        erpReturnOrder.init();
        //1新增一个退款单
        mapper.insertSelective(erpReturnOrder);
        MallSubOrderDO orderDO = mallSubOrderMapper.selectByPrimaryKey(erpReturnOrder.getErpOrderId());
        if (orderDO == null){
            throw new ErpCommonException("找不到对应订单,请联系网站管理员");
        }
        //更新了子订单状态
        orderDO.update();
        orderDO.setStatus(ORDER_SATUTS_RETURNDONE);
        mallSubOrderMapper.updateByPrimaryKeySelective(orderDO);
        if (orderDO.getQuantity() < erpReturnOrder.getReturnQuantity()){
            throw new ErpCommonException("退货的数目大于订单数目,请确认后重新操作");
        }
        //判断是否要入库
        if (erpReturnOrder.getIsCheckin() == 1) {
            //退货
            inventoryService.returns(orderDO, Long.valueOf(erpReturnOrder.getReturnQuantity()));
        }
    }
}
