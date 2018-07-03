package com.wangqin.globalshop.order.app.service.mall.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.DealerDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.MallOrderVO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallOrderMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallSubOrderMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.SequenceUtilMapperExt;
import com.wangqin.globalshop.channelapi.dal.GlobalshopOrderVo;
import com.wangqin.globalshop.channelapi.dal.JdCommonParam;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.CodeGenUtil;
import com.wangqin.globalshop.common.utils.DateUtil;
import com.wangqin.globalshop.deal.app.service.IDealerService;
import com.wangqin.globalshop.inventory.app.service.InventoryService;
import com.wangqin.globalshop.order.app.service.item.OrderItemSkuService;
import com.wangqin.globalshop.order.app.service.mall.IMallOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/05/28
 */
@Service
public class MallOrderServiceImpl implements IMallOrderService {
    @Autowired
    private MallOrderMapperExt mallOrderDOMapper;
    @Autowired
    private OrderItemSkuService itemSkuService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private IDealerService iDealerService;
    @Autowired
    private MallSubOrderMapperExt mallSubOrderDOMapper;
    @Autowired
    private IMallOrderService mallSubOrderService;

    @Autowired
    private SequenceUtilMapperExt sequenceUtilMapperExt;

    @Override
    public MallOrderDO selectById(Long id) {
        return mallOrderDOMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateById(MallOrderDO outerOrder) {
        mallOrderDOMapper.updateByPrimaryKeySelective(outerOrder);
    }

    @Override
    public MallOrderDO selectByOrderNo(String orderNo) {
        return mallOrderDOMapper.selectByOrderNo(orderNo);
    }

    @Override
    public List<MallOrderDO> queryByStatus(byte status) {
        return mallOrderDOMapper.queryByStatus(status);
    }

    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void addOuterOrder(MallOrderVO outerOrder) {
        List<MallSubOrderDO> os = outerOrder.getOuterOrderDetails();
        Double totalPrice = 0.0;
        String shopCode = CodeGenUtil.getShopCode();
        DealerDO deal = iDealerService.selectByCode(outerOrder.getDealerCode());
        outerOrder.init();
        for (MallSubOrderDO o : os) {
            totalPrice += o.getSalePrice() * o.getQuantity();
            o.setOrderNo(outerOrder.getOrderNo());
            o.setShopCode(shopCode);
            o.init();
            /**商品相关Star*/
            ItemSkuDO itemSku = itemSkuService.selectBySkuCode(o.getSkuCode());
            o.setSalePrice(itemSku.getSalePrice());
            Long freight = itemSku.getFreight();
            o.setFreight(Double.valueOf(freight == null ? 0L : freight));
            o.setItemCode(itemSku.getItemCode());
            o.setItemName(itemSku.getItemName());
            o.setSkuPic(itemSku.getSkuPic());
            o.setUpc(itemSku.getUpc());
            o.setScale(itemSku.getScale());
            o.setLogisticType(Integer.valueOf(itemSku.getLogisticType()));
            o.setWeight(itemSku.getWeight());
            /**商品相关End*/
            o.setCompanyNo(outerOrder.getCompanyNo());
            o.setReceiver(outerOrder.getReceiver());
            o.setReceiverDistrict(outerOrder.getReceiverDistrict());
            o.setReceiverCity(outerOrder.getReceiverCity());
            o.setReceiverState(outerOrder.getReceiverState());
            o.setReceiverAddress(outerOrder.getAddressDetail());
            o.setTelephone(outerOrder.getTelephone());
            o.setStatus(0);
            inventoryService.order(o);
            mallSubOrderDOMapper.insert(o);
        }

        outerOrder.setDealerName(deal.getName());
        outerOrder.setShopCode(shopCode);
        outerOrder.setMemo(outerOrder.getRemark());
        outerOrder.setTotalAmount(totalPrice);
        outerOrder.setActualAmount(0.0);
        mallOrderDOMapper.insertSelective(outerOrder);

    }

    @Override
    public void review(String orderNo) {
//        // 1、审核导入
////        mallSubOrderDOMapper.reviewOuterOrder(orderNo);
//        // 2、锁定库存
//        List<MallSubOrderDO> erpOrders = mallSubOrderDOMapper.SelectByOrderNo(orderNo);
//        if (CollectionUtils.isNotEmpty(erpOrders)) {
//            try {
//                //同一个仓库分配库存
//                List<WarehouseCollector> wcs = inventoryService.selectWarehousesByErpOrders(erpOrders);
//                Set<String> erpOrderIds = Sets.newHashSet();
//                if (CollectionUtils.isNotEmpty(wcs)) {
//                    for (WarehouseCollector wc : wcs) {
//                        try {
//                            erpOrderIds.add(wc.getOrderNo());
//                            inventoryService.lockedInventroy(wc);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//                //落单的商品，其他的仓库随机分配库存
//                erpOrders.forEach(erpOrder -> {
//                    if (!erpOrderIds.contains(erpOrder.getId())) {
//                        try {
//                            mallSubOrderService.lockErpOrder(erpOrder);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//            } catch (InventoryException e) {
//                e.printStackTrace();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

    }

    @Override
    public List<MallOrderDO> queryOuterOrderList(MallOrderVO outerOrderQueryVO) {
        List<MallOrderDO> outerOrders;
        outerOrderQueryVO.setCompanyNo(AppUtil.getLoginUserCompanyNo());
        outerOrders = mallOrderDOMapper.queryOuterOrders(outerOrderQueryVO);
        return outerOrders;
    }

    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void delete(MallOrderDO outerOrder) {
        List<MallSubOrderDO> mallSubOrderDOList = mallSubOrderDOMapper.selectByOrderNo(outerOrder.getOrderNo());
        for (MallSubOrderDO mallSubOrderDO : mallSubOrderDOList) {
            mallSubOrderDO.setIsDel(true);
            mallSubOrderDOMapper.updateByPrimaryKeySelective(mallSubOrderDO);
        }
        outerOrder.setIsDel(true);
        mallOrderDOMapper.updateByPrimaryKey(outerOrder);
    }

//    @Override
//    public JsonResult<Object> lockErpOrder(MallSubOrderDO erpOrder) throws InventoryException {
//        if (erpOrder.getStatus() != OrderStatus.INIT.getCode()) {
//            return JsonResult.buildFailed("订单状态错误");
//        }
//        //未备货订单
//        if (erpOrder.getStockStatus() == StockUpStatus.INIT.getCode() || erpOrder.getStockStatus() == StockUpStatus.RELEASED.getCode()) {
//            if (erpOrder.getWarehouseNo() == null) {
//                List<MallSubOrderDO> erpOrders = Lists.newArrayList();
//                erpOrders.add(erpOrder);
//                List<WarehouseCollector> wcs = inventoryService.selectWarehousesByErpOrders(erpOrders);
//                if (CollectionUtils.isNotEmpty(wcs)) {
//                    inventoryService.lockedInventroy(wcs.get(0));
//                } else {
//                    return JsonResult.buildFailed("没有库存备货失败");
//                }
//            } else {
//                return JsonResult.buildFailed("没有仓库信息");
//            }
//        } else {
//            if (erpOrder.getStockStatus() != StockUpStatus.STOCKUP.getCode() && erpOrder.getStockStatus() != StockUpStatus.MIX_STOCKUP.getCode() && erpOrder.getWarehouseNo() != null) {
//                WarehouseCollector wc = inventoryService.selectWarehousesByErpOrder(erpOrder);
//                if (wc != null) {
//                    inventoryService.lockedInventroy(wc);
//                } else {
//                    return JsonResult.buildFailed("没有库存备货失败");
//                }
//            } else {
//                return JsonResult.buildFailed("订单备货状态错误");
//            }
//
//        }
//        return JsonResult.buildSuccess(true);
//    }

    @Override
    public List<MallOrderDO> queryOuterOrderForExcel(MallOrderVO outerOrderQueryVO) {
        return mallOrderDOMapper.queryOuterOrders(outerOrderQueryVO);
    }

    @Override
    public List<MallOrderDO> selectByStatus(byte b) {
        return mallOrderDOMapper.queryByStatus(b);
    }

    @Override
    public void addMallOrderDO(MallOrderDO mallOrderDO) {
        mallOrderDOMapper.insertSelective(mallOrderDO);

    }

    @Override
    public List<MallOrderVO> list(MallOrderVO vo) {
        vo.init();
        return mallOrderDOMapper.list(vo);
    }


    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void dealOrder(JdCommonParam jdCommonParam, GlobalshopOrderVo globalshopOrderVo) {

        MallOrderDO mallOrderDO = globalshopOrderVo.getMallOrderDO();

        mallOrderDO.setOrderNo(CodeGenUtil.getOrderNo());

        mallOrderDOMapper.insertSelective(mallOrderDO);

        List<MallSubOrderDO> mallSubOrderDOS = globalshopOrderVo.getMallSubOrderDOS();
        for (MallSubOrderDO mallSubOrderDO : mallSubOrderDOS) {


            mallSubOrderDOMapper.insert(mallSubOrderDO);
        }

    }

}
