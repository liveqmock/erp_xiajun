package com.wangqin.globalshop.order.app.service.mall.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.MallOrderVO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallOrderMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallSubOrderMapperExt;
import com.wangqin.globalshop.inventory.app.service.InventoryService;
import com.wangqin.globalshop.order.app.service.item.OrderItemSkuService;
import com.wangqin.globalshop.order.app.service.mall.IMallOrderService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
    private MallSubOrderMapperExt mallSubOrderDOMapper;
    @Autowired
    private IMallOrderService mallSubOrderService;

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
    public void addOuterOrder(MallOrderVO outerOrder) {
        List<MallSubOrderDO> outerOrderDetails = outerOrder.getOuterOrderDetails();
        Double totalPrice = 0.0;
        int i = 0;
        for (MallSubOrderDO outerOrderDetail : outerOrderDetails) {
            totalPrice+=outerOrderDetail.getSalePrice()*outerOrderDetail.getQuantity();
            outerOrderDetail.setOrderNo(outerOrder.getOrderNo());
            outerOrderDetail.setShopCode("O" + outerOrder.getOrderNo().substring(1) + String.format("%0" + 4 + "d", i));
            i++;
            outerOrderDetail.setGmtCreate(new Date());
            outerOrderDetail.setGmtModify(new Date());
            outerOrderDetail.setCompanyNo(outerOrder.getCompanyNo());
            // 计算运费和销售价格
            ItemSkuDO sku = itemSkuService.selectBySkuCode(outerOrderDetail.getSkuCode());
            if (sku != null) {
                outerOrderDetail.setItemName(sku.getItemName());
                outerOrderDetail.setScale(sku.getScale());
                outerOrderDetail.setSkuCode(sku.getSkuCode());
//                    outerOrderDetail.setChannelSkuCode(sku.getSaleable());
                outerOrderDetail.setUpc(sku.getUpc());
                outerOrderDetail.setItemCode(sku.getItemCode());
                outerOrderDetail.setSkuPic(sku.getSkuPic());
                int logisticType = sku.getLogisticType();
                outerOrderDetail.setLogisticType(logisticType);
                outerOrderDetail.setWeight(sku.getWeight());
                inventoryService.order(outerOrderDetail);
//                //如果有虚拟库存就扣减虚拟库存
//                InventoryDO inventory = inventoryService.queryInventoryBySkuCode(sku.getItemCode(), sku.getSkuCode());
//                if (inventory.getVirtualInv() > 0 || inventory.getLockedVirtualInv() > 0) {
//                    Long virtualInv = inventory.getVirtualInv() - outerOrderDetail.getQuantity();
//                    virtualInv = virtualInv > 0 ? virtualInv : 0;
//                    //可售库存 = 现货库存 - 现货占用 + 在途库存- 在途占用
//                    Long totalAvailableInv = inventory.getInv() - inventory.getLockedInv() + inventory.getTransInv() - inventory.getLockedTransInv();
//                    //如果虚拟库存小于等于可售库存，虚拟库存清零
//                    virtualInv = virtualInv > totalAvailableInv ? virtualInv : 0;
//
//                    //如果虚拟占用库存大于零
//                    if (inventory.getLockedVirtualInv() > 0) {
//                        Long lockedVirtualInv = inventory.getLockedVirtualInv() - outerOrderDetail.getQuantity();
//                        lockedVirtualInv = lockedVirtualInv > 0 ? lockedVirtualInv : 0;
//                        inventory.setLockedVirtualInv(lockedVirtualInv);
//                    }
//                    inventory.setVirtualInv(virtualInv);
//                    inventory.setGmtModify(new Date());
//                    inventoryService.update(inventory);
//                }
            }
            mallSubOrderDOMapper.insert(outerOrderDetail);
        }
        outerOrder.setTotalAmount(totalPrice);
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
        outerOrderQueryVO.setCompanyNo("MallOrderServiceImplYYYYYY");
//        outerOrderQueryVO.setCompanyNo(ShiroUtil.getShiroUser().getCompanyNo());
        // 1、查询总的记录数量
        Integer totalCount = mallOrderDOMapper.queryOuterOrdersCount(outerOrderQueryVO);
        // 2、查询分页记录
        if (totalCount != null && totalCount != 0L) {
            outerOrders = mallOrderDOMapper.queryOuterOrders(outerOrderQueryVO);
            if (CollectionUtils.isNotEmpty(outerOrders)) {
                for (MallOrderDO order : outerOrders) {
                    MallSubOrderDO erpOrder = new MallSubOrderDO();
                    erpOrder.setOrderNo(order.getOrderNo());
                    //设置子订单数
//                    order.setCountOfSubOrder(mallSubOrderDOMapper.selectCount(erpOrder));
                }
            }
        } else {
            outerOrders = new ArrayList<>();
        }
        return outerOrders;
    }

    @Override
    public void delete(MallOrderDO outerOrder) {
        mallOrderDOMapper.deleteByPrimaryKey(outerOrder.getId());
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

}
