package com.wangqin.globalshop.inventory.app.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallSubOrderMapperExt;
import com.wangqin.globalshop.common.exception.InventoryException;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.inventory.app.service.InventoryIMallSubOrderService;
import com.wangqin.globalshop.inventory.app.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author biscuit
 * @data 2018/05/28
 */
@Service
public class InventoryMallSubOrderServiceImpl implements InventoryIMallSubOrderService {
    @Autowired
    private MallSubOrderMapperExt mallSubOrderDOMapper;
    @Autowired
    private InventoryService inventoryService;
    @Override
    public MallSubOrderDO selectById(Long id) {
        return mallSubOrderDOMapper.selectByPrimaryKey(id);
    }



    @Override
    public MallSubOrderDO queryBySkuCode(String skuCode) {
        return mallSubOrderDOMapper.selectBySkuCode(skuCode);
    }
//
    @Override
    public JsonResult lockErpOrderBySkuId(String skuCode) throws InventoryException {
        MallSubOrderDO erpOrder = mallSubOrderDOMapper.selectBySkuCode(skuCode);
        inventoryService.order(erpOrder);
//        if(erpOrder.getStatus()!= OrderStatus.INIT.getCode()){
//            return JsonResult.buildFailed("订单状态错误");
//        }
//        //未备货订单
//        if(erpOrder.getStockStatus()== StockUpStatus.INIT.getCode()||erpOrder.getStockStatus()==StockUpStatus.RELEASED.getCode()){
//            if(erpOrder.getWarehouseNo()==null){
//                List<MallSubOrderDO> erpOrders = Lists.newArrayList();
//                erpOrders.add(erpOrder);
//                List<InventoryServiceImpl.WarehouseCollector> wcs = inventoryService.selectWarehousesByErpOrders(erpOrders);
//                if(CollectionUtils.isNotEmpty(wcs)){
//                    inventoryService.lockedInventroy(wcs.get(0));
//                }else{
//                    return JsonResult.buildFailed("没有库存备货失败");
//                }
//            }else{
//                return JsonResult.buildFailed("没有仓库信息");
//            }
//        }else{
//            if(erpOrder.getStockStatus()!=StockUpStatus.STOCKUP.getCode()&&erpOrder.getStockStatus()!=StockUpStatus.MIX_STOCKUP.getCode()&&erpOrder.getWarehouseNo()!=null){
//                InventoryServiceImpl.WarehouseCollector wc = inventoryService.selectWarehousesByErpOrder(erpOrder);
//                if(wc!=null){
//                    inventoryService.lockedInventroy(wc);
//                }else{
//                    return JsonResult.buildFailed("没有库存备货失败");
//                }
//            }else{
//                return JsonResult.buildFailed("订单备货状态错误");
//            }
//
//        }
        return JsonResult.buildSuccess(true);
    }

}
