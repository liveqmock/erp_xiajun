package com.wangqin.globalshop.inventory.app.service.impl;

import com.wangqin.globalshop.biz1.app.Exception.ErpCommonException;
import com.wangqin.globalshop.biz1.app.dal.dataObject.*;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.*;
import com.wangqin.globalshop.inventory.app.service.IInventoryOnWarehouseService;
import com.wangqin.globalshop.inventory.app.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author biscuit
 * @data 2018/06/04
 */
@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private ItemSkuMapperExt itemSkuMapper;
    @Autowired
    private InventoryMapperExt mapper;
    @Autowired
    private MallSubOrderMapperExt mallSubOrderMapper;
    @Autowired
    private InventoryInoutMapperExt inoutMapper;
    @Autowired
    private IInventoryOnWarehouseService invOnWarehouseService;
    @Autowired
    private InventoryOutManifestMapperExt outManifestMapper;
    @Autowired
    private InventoryOutManifestDetailDOMapperExt outManifestDetailDOMapper;
    @Autowired
    private InventoryOnWarehouseMapperExt invOnWarehouseMapperExt;


    /**
     * 入库
     *
     * @param inventory
     * @param warehouseNo
     */
    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void outbound(InventoryDO inventory, String warehouseNo,String positionNo) {

        /**1增加库存库存*/
        insertInv(inventory, inventory.getInv());
        InventoryOnWareHouseDO wareHouseDO = invOnWarehouseService.insertInventory(inventory, warehouseNo,positionNo);
        if (wareHouseDO == null){
            throw new ErpCommonException("找不到对应的商品,入库失败");
        }
        /**2根据InventoryOnWareHouseDO和InventoryDO生成流水*/
        Integer opeatory = 101;
        saveInventoryInOut(inventory, wareHouseDO, opeatory, inventory.getInv(), "采购入库");


    }


    /**
     * 下单
     *
     * @param mallOrderDO
     */
    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void order(MallOrderDO mallOrderDO) {
        /**判断可售库存是否满足*/
        List<MallSubOrderDO> list = mallSubOrderMapper.selectByOrderNo(mallOrderDO.getOrderNo());
        for (MallSubOrderDO orderDO : list) {
            order(orderDO);
        }
    }

    /**
     * 下单
     *
     * @param mallSubOrderDO
     */
    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void order(MallSubOrderDO mallSubOrderDO) {
        /**判断可售库存是否满足*/
        InventoryDO inventoryDO = mapper.queryBySkuCodeAndItemCode(mallSubOrderDO.getSkuCode(), mallSubOrderDO.getItemCode());
        if (inventoryDO == null) {
            throw new ErpCommonException("不存在该商品，下单失败");
        }

        if (inventoryDO.getInv() + inventoryDO.getLockedTransInv() - inventoryDO.getLockedInv() >= mallSubOrderDO.getQuantity()) {
            /**修改库存占用*/
            inventoryDO.setLockedInv(inventoryDO.getLockedInv() + mallSubOrderDO.getQuantity());
            mapper.updateByPrimaryKeySelective(inventoryDO);
        } else {
            throw new ErpCommonException("库存不足，下单失败");
        }


    }

    /**
     * 取消订单
     *
     * @param mallSubOrderDO
     */
    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void release(MallSubOrderDO mallSubOrderDO) {
        /**判断可售库存是否满足*/
        InventoryDO inventoryDO = mapper.queryBySkuCodeAndItemCode(mallSubOrderDO.getSkuCode(), mallSubOrderDO.getItemCode());
        /**修改库存占用*/
        inventoryDO.setLockedInv(inventoryDO.getLockedInv() - mallSubOrderDO.getQuantity());
        mapper.updateByPrimaryKeySelective(inventoryDO);

    }

    /**
     * 盘点增加
     */
    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void checkIn(String skuCode, Long warehouseId, String positionNo, Long quantity) {
        /**增加实际库存*/
        InventoryDO inventoryDO = mapper.queryBySkuCode(skuCode);
        insertInv(inventoryDO, quantity);
        /**增加仓库库存*/
        InventoryOnWareHouseDO houseDO = invOnWarehouseMapperExt.selectByPrimaryKey(warehouseId);
        houseDO.setInventory(houseDO.getLockedInv() + quantity);
        invOnWarehouseMapperExt.updateByPrimaryKeySelective(houseDO);
        /**新增流水*/
        Integer opeatory = 103;
        saveInventoryInOut(inventoryDO, houseDO, opeatory, quantity, "盘点入库");

    }

    /**
     * 盘点减少
     */
    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void inventoryCheckOut(Long inventoryAreaId, Long quantity) {
        /**减少仓库库存*/
        InventoryOnWareHouseDO houseDO = invOnWarehouseMapperExt.selectByPrimaryKey(inventoryAreaId);
        houseDO.setInventory(houseDO.getLockedInv() + quantity);
        invOnWarehouseMapperExt.updateByPrimaryKeySelective(houseDO);
        /**减少实际库存*/
        InventoryDO inventoryDO = mapper.queryBySkuCodeAndItemCode(houseDO.getSkuCode(), houseDO.getItemCode());
        insertInv(inventoryDO, quantity);

        /**新增流水*/
        Integer opeatory = 202;
        saveInventoryInOut(inventoryDO, houseDO, opeatory, quantity, "盘点出库");
    }

    /**
     * 发货
     *
     * @param orderDO
     */
    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void ship(MallSubOrderDO orderDO) {
        /**修改库存  和  库存占用*/
        InventoryDO inventoryDO = mapper.queryBySkuCodeAndItemCode(orderDO.getSkuCode(), orderDO.getItemCode());
        inventoryDO.setLockedInv(inventoryDO.getLockedInv() - orderDO.getQuantity());
        inventoryDO.setInv(inventoryDO.getInv() - orderDO.getQuantity());
        mapper.updateByPrimaryKeySelective(inventoryDO);
        /**更新相关InventoryOnWareHouse*/
        // TODO: 18.6.4
        InventoryOnWareHouseDO warehouseDO = null;
        /**生成流水*/
        Integer opeatory = 201;
        saveInventoryInOut(inventoryDO, warehouseDO, opeatory, inventoryDO.getInv(), "发货出库");
        /**绑定shipping_order*/
        // TODO: 18.6.4


    }

    /**
     * 退货
     *
     * @param mallReturnOrderDO
     */
    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void returns(MallReturnOrderDO mallReturnOrderDO) {
        /**修改库存*/
        /**更新相关Inventory*/
        /**修改流水*/
    }


    /**
     * 出库单出库
     *
     * @param outManifestDO
     */
    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void outOfStorehouse(InventoryOutManifestDO outManifestDO) {

    }

    /**
     * 通过itemcode和skuCode来查找
     *
     * @param itemCode
     * @param skuCode
     * @return
     */
    @Override
    public InventoryDO selectByItemCodeAndSkuCode(String itemCode, String skuCode) {
        return mapper.queryBySkuCodeAndItemCode(skuCode, itemCode);
    }


    /**
     * 增加库存   如果存在记录  则更新  如果不存在记录  则新增
     *
     * @param inventoryDO 包含增加的实体信息
     * @param inv         新增的数目
     */
    private void insertInv(InventoryDO inventoryDO, Long inv) {
        InventoryDO inventory = mapper.queryBySkuCodeAndItemCode(inventoryDO.getSkuCode(), inventoryDO.getItemCode());
        if (inventory == null) {
            ItemSkuDO itemSkuDO = itemSkuMapper.queryItemBySkuCode(inventoryDO.getSkuCode());
            inventoryDO.setItemName(itemSkuDO.getItemName());
            inventoryDO.setUpc(itemSkuDO.getUpc());
            inventoryDO.setCompanyNo("InventoryServiceImpl4545");
            inventoryDO.setCreator("qweqweqweqwe");
            inventoryDO.setModifier("zzcxzxczxc");
            mapper.insertSelective(inventoryDO);
        } else {
            inventory.setInv(inventory.getInv() + inv);
            inventoryDO.setCreator("qweqweqweqwe");
            inventoryDO.setModifier("zzcxzxczxc");
            inventoryDO.setCompanyNo("InventoryServiceImpl4545");
            mapper.updateByPrimaryKeySelective(inventory);
        }

    }

    /**
     * 生成并保存流水记录
     *
     * @param inventoryDO 相关的inventory记录
     * @param wareHouseDO 相关的inventory_on_warehouse记录
     * @param opeatory    流水类型 101 采购入库   102  退货入库  103 盘点入库  201发货出库  202盘点出库  203出库单出库
     * @param quantity    流水设计到的数目
     * @param remark      备注
     */
    private void saveInventoryInOut(InventoryDO inventoryDO, InventoryOnWareHouseDO wareHouseDO, Integer opeatory, Long quantity, String remark) {
        InventoryInoutDO inoutDO = new InventoryInoutDO();

        inoutDO.setCreator("当前用户");
        inoutDO.setModifier("qwewqeqwew");
        /****inventory***/
        inoutDO.setSkuCode(inventoryDO.getSkuCode());
        inoutDO.setItemCode(inventoryDO.getItemCode());
        inoutDO.setCompanyNo(inventoryDO.getCompanyNo());
        /****warehouse***/
        inoutDO.setInventoryOnWarehouseNo(wareHouseDO.getInventoryOnWarehouseNo());
        inoutDO.setWarehouseNo(wareHouseDO.getWarehouseNo());
        inoutDO.setShelfNo(wareHouseDO.getShelfNo());
        inoutDO.setInventoryOnWarehouseNo(wareHouseDO.getInventoryOnWarehouseNo());
        /*******/
        inoutDO.setGmtCreate(new Date());
        inoutDO.setGmtModify(new Date());
        inoutDO.setOperatorType(opeatory);
        inoutDO.setQuantity(quantity);
        inoutDO.setRemark(remark);
        inoutMapper.insertSelective(inoutDO);
    }

}
