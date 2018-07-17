package com.wangqin.globalshop.order.app.service.mall.impl;

import com.wangqin.globalshop.biz1.app.constants.enums.OrderReturnStatus;
import com.wangqin.globalshop.biz1.app.constants.enums.OrderStatus;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallReturnOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.MallReturnOrderVO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallReturnOrderDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallSubOrderMapperExt;
import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.CodeGenUtil;
import com.wangqin.globalshop.common.utils.StringUtils;
import com.wangqin.globalshop.inventory.app.service.InventoryService;
import com.wangqin.globalshop.order.app.comm.Constant;
import com.wangqin.globalshop.order.app.service.mall.IMallReturnOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


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
    @Transactional(rollbackFor = ErpCommonException.class)
    public void add(MallReturnOrderVO erpReturnOrder) {

        // TODO: 前端没有传 subOrderNo 字段，导致添加时 sub_order_no 字段为空

        if (erpReturnOrder.getErpOrderId() == null) {
            new ErpCommonException("ErpCommonException");
        }
        //todo
        erpReturnOrder.setReturnRefer(0);

        erpReturnOrder.init();

        erpReturnOrder.setMallReturnOrderNo(CodeGenUtil.getMallReturnOrderNo());

        //1新增一个退款单

        MallSubOrderDO orderDO = mallSubOrderMapper.selectByPrimaryKey(erpReturnOrder.getErpOrderId());
        if (orderDO == null) {
            throw new ErpCommonException("找不到对应订单,请联系网站管理员");
        }
        //更新了子订单状态
        orderDO.update();
        if (erpReturnOrder.getStatus() == null) {
            throw new ErpCommonException("退单状态不能为空");
        }
        if (OrderReturnStatus.CLOSE.getCode() == erpReturnOrder.getStatus()) {
            orderDO.setStatus(OrderStatus.RETURNDONE.getCode());
        } else {
            orderDO.setStatus(OrderStatus.RETURNING.getCode());
        }

        mallSubOrderMapper.updateByPrimaryKeySelective(orderDO);
        if (orderDO.getQuantity() < erpReturnOrder.getReturnQuantity()) {
            throw new ErpCommonException("退货的数目大于订单数目,请确认后重新操作");
        }
        mapper.insertSelective(erpReturnOrder);
//        //判断是否要入库
//        if (erpReturnOrder.getIsCheckin() == 1) {
//            //退货
//            inventoryService.returns(orderDO, Long.valueOf(erpReturnOrder.getReturnQuantity()));
//        }
    }

    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public List<MallReturnOrderDO> selectByCondition(String orderNo, String startGmtCreate, String endGmtCreate) {

        List<MallReturnOrderDO> list = mapper.selectByCondition(orderNo, startGmtCreate, endGmtCreate, AppUtil.getLoginUserCompanyNo());
        return list;
    }
}
