package com.wangqin.globalshop.inventory.app.service.impl;

import com.google.gson.JsonObject;
import com.wangqin.globalshop.biz1.app.dal.dataObject.*;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.*;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.StringUtils;
import com.wangqin.globalshop.inventory.app.service.IInventoryOnWarehouseService;
import com.wangqin.globalshop.inventory.app.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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
     * 采购入库
     *
     * @param inventory   封装库存信息的对象
     * @param warehouseNo 仓库号
     * @param positionNo  货架号
     */
    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void outbound(InventoryDO inventory, String warehouseNo, String positionNo) {
        Long inv = inventory.getInv();
        /**更新具体仓库的库存*/
        InventoryOnWareHouseDO wareHouseDO = invOnWarehouseService.insertInventory(inventory, inv, warehouseNo, positionNo);
        InventoryDO exitInventory = mapper.queryBySkuCodeAndCompanyNo(inventory.getSkuCode(), AppUtil.getLoginUserCompanyNo());
        /**如果有虚拟库存,表示已有库存记录  需要更新
         * 反之  则没有库存记录  需要新增
         * */
        if (exitInventory != null) {
            Long virtualInv = exitInventory.getVirtualInv();
            /**1增加实际库存*/
            exitInventory.setInv(exitInventory.getInv() + inv);
            exitInventory.update();
            /**减少虚拟库存  保证可售不变*/
            if (virtualInv > inv) {
                exitInventory.setVirtualInv(virtualInv - inv);
            } else {
                exitInventory.setVirtualInv(0L);
            }
            mapper.updateByPrimaryKeySelective(exitInventory);
        } else {
            inventory.init();
            mapper.insertSelective(inventory);
        }
        if (wareHouseDO == null) {
            throw new ErpCommonException("找不到对应的商品,入库失败");
        }

        /**2根据InventoryOnWareHouseDO和InventoryDO生成流水*/
        Integer opeatory = 101;
        saveInventoryInOut(inventory, wareHouseDO, opeatory, inv, "采购入库");


    }

    /**
     * 退货
     *
     * @param orderDO
     */
    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void returns(MallSubOrderDO orderDO, Long inv) {
        /**修改库存*/
        InventoryDO inventory = mapper.queryBySkuCodeAndCompanyNo(orderDO.getSkuCode(), AppUtil.getLoginUserCompanyNo());
        if (inventory == null) {
            throw new ErpCommonException("找不到相关库存");
        }
        inventory.setInv(inventory.getInv() + inv);
        inventory.update();
        mapper.updateByPrimaryKeySelective(inventory);
        /**更新相关Inventory*/
        InventoryOnWareHouseDO wareHouseDO = invOnWarehouseMapperExt.selectByCompanyNoAndSkuCodeAndWarehouseNo(orderDO.getCompanyNo(), orderDO.getSkuCode(), orderDO.getWarehouseNo());
        if (wareHouseDO == null) {
            throw new ErpCommonException("找不到相关库存");
        }
        wareHouseDO.update();
        invOnWarehouseMapperExt.updateByPrimaryKeySelective(wareHouseDO);
        /**修改流水*/
        Integer opeatory = 102;
        saveInventoryInOut(inventory, wareHouseDO, opeatory, inv, "退货入库");
    }


    /**
     * 预售入库
     *
     * @param list
     */
    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void outbound(List<InventoryDO> list) {
        for (InventoryDO aDo : list) {
            /**1增加库存库存*/
            aDo.init();
            mapper.insertSelective(aDo);
        }

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
        InventoryDO inventoryDO = mapper.queryBySkuCodeAndCompanyNo(mallSubOrderDO.getSkuCode(), AppUtil.getLoginUserCompanyNo());
        if (inventoryDO == null) {
            throw new ErpCommonException("库存不足，下单失败");
        }

        if (inventoryDO.getInv() + inventoryDO.getVirtualInv() - inventoryDO.getLockedInv() >= mallSubOrderDO.getQuantity()) {
            inventoryDO.setLockedInv(inventoryDO.getLockedInv() + mallSubOrderDO.getQuantity());
            mapper.updateByPrimaryKeySelective(inventoryDO);
        } else {
            throw new ErpCommonException("库存不足，下单失败");
        }


    }

    /**
     * 下单
     *
     * @param outerOrderDetails
     */
    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void order(List<MallSubOrderDO> outerOrderDetails) {
        for (MallSubOrderDO detail : outerOrderDetails) {
            order(detail);
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
        InventoryDO inventoryDO = mapper.queryBySkuCodeAndCompanyNo(mallSubOrderDO.getSkuCode(), AppUtil.getLoginUserCompanyNo());
        if (inventoryDO == null) {
            throw new ErpCommonException("找不到对应的库存");
        }
        /**修改库存占用*/
        inventoryDO.setLockedInv(inventoryDO.getLockedInv() - mallSubOrderDO.getQuantity());
        mapper.updateByPrimaryKeySelective(inventoryDO);

    }

    /**
     * 库存盘入
     *
     * @param inventoryOnWarehouseNo inventoryOnWarehouseNo
     * @param skuCode                skuCode
     * @param quantity               盘入数量
     */
    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void inventoryCheckIn(String inventoryOnWarehouseNo, String skuCode, Long quantity) {
        /**增加校验*/
        if (StringUtils.isBlank(inventoryOnWarehouseNo) || StringUtils.isBlank(skuCode) || quantity == null) {
            throw new ErpCommonException("有空数据");
        }
        if (quantity <= 0) {
            throw new ErpCommonException("盘入库存要为正数");
        }
        /**增加实际库存*/
        InventoryDO inventoryDO = mapper.queryBySkuCodeAndCompanyNo(skuCode, AppUtil.getLoginUserCompanyNo());
        insertInv(inventoryDO, quantity);
        // /**增加仓库库存*/
        // InventoryOnWareHouseDO houseDO = invOnWarehouseMapperExt.selectByCompanyNoAndSkuCodeAndWarehouseNo(AppUtil.getLoginUserCompanyNo(),skuCode,warehouseNo);
        /**增加仓库对应货架库存*/
        InventoryOnWareHouseDO houseDO = invOnWarehouseMapperExt.getByInventoryOnWarehouseNo(inventoryOnWarehouseNo);
        /**检查仓库及对应货架是否存在*/
        if (houseDO == null) {
            throw new ErpCommonException("该商品对应仓库货架不存在");
        }
        houseDO.setInventory(houseDO.getInventory() + quantity);
        invOnWarehouseMapperExt.updateByPrimaryKeySelective(houseDO);
        /**新增流水*/
        Integer opeatory = 103;
        saveInventoryInOut(inventoryDO, houseDO, opeatory, quantity, "盘点入库");
    }

    /**
     * 库存盘入带货架修改
     *
     * @param inventoryOnWarehouseNo inventoryOnWarehouseNo
     * @param skuCode                skuCode
     * @param quantity               盘入数量
     * @param shelfNo                货架号
     */
    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void inventoryCheckIn(String inventoryOnWarehouseNo, String skuCode, Long quantity, String shelfNo) {
        // 库存盘入
        inventoryCheckIn(inventoryOnWarehouseNo, skuCode, quantity);
        // 修改货架
        updateSelfNo(inventoryOnWarehouseNo, shelfNo);
    }

    @Override
    public void inventoryCheckOut(Long inventoryAreaId, Long quantity) {

    }

    /**
     * 库存盘出
     *
     * @param inventoryOnWarehouseNo inventoryOnWarehouseNo
     * @param skuCode                skuCode
     * @param quantity               盘出数量
     */
    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void inventoryCheckOut(String inventoryOnWarehouseNo, String skuCode, Long quantity) {
        // 增加校验
        if (StringUtils.isBlank(inventoryOnWarehouseNo) || StringUtils.isBlank(skuCode) || quantity == null) {
            throw new ErpCommonException("有空数据");
        }
        if (quantity <= 0) {
            throw new ErpCommonException("盘出库存要为正数");
        }
        /**减少仓库库存*/
        InventoryOnWareHouseDO houseDO = invOnWarehouseMapperExt.getByInventoryOnWarehouseNo(inventoryOnWarehouseNo);
        if (houseDO.getInventory() - quantity < 0) {
            throw new ErpCommonException("盘出库存不能大于实际库存");
        }
        houseDO.setInventory(houseDO.getInventory() - quantity);
        invOnWarehouseMapperExt.updateByPrimaryKeySelective(houseDO);
        /**减少实际库存*/
        InventoryDO inventoryDO = mapper.queryBySkuCodeAndCompanyNo(skuCode, AppUtil.getLoginUserCompanyNo());
        if (inventoryDO == null){
            throw new ErpCommonException("找不到对应库存");
        }
        if (inventoryDO.getInv() - quantity < 0) {
            throw new ErpCommonException("盘出库存不能大于实际库存");
        }
        inventoryDO.setInv(inventoryDO.getInv() - quantity);
        mapper.updateByPrimaryKeySelective(inventoryDO);

        /**新增流水*/
        Integer opeatory = 202;
        saveInventoryInOut(inventoryDO, houseDO, opeatory, quantity, "盘点出库");
    }

    /**
     * 出库单确认出库
     *
     * @param inventoryOutDetailListStr
     * @param desc
     */
    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void inventoryOutConfirm(JsonObject[] inventoryOutDetailListStr, String desc) {
        // 增加校验
        if (inventoryOutDetailListStr == null || inventoryOutDetailListStr.length == 0 || StringUtils.isBlank(desc)) {
            throw new ErpCommonException("有空数据");
        }
        for (JsonObject inventoryOutDetail : inventoryOutDetailListStr) {
            // TODO
        }
    }

    /**
     * 修改货架号
     *
     * @param inventoryOnWarehouseNo inventoryOnWarehouseNo
     * @param shelfNo                货架号
     */
    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void updateSelfNo(String inventoryOnWarehouseNo, String shelfNo) {
        // 增加校验
        if (StringUtils.isBlank(inventoryOnWarehouseNo) || StringUtils.isBlank(shelfNo)) {
            throw new ErpCommonException("有空数据");
        }
        InventoryOnWareHouseDO houseDO = invOnWarehouseMapperExt.getByInventoryOnWarehouseNo(inventoryOnWarehouseNo);
        if (!houseDO.getShelfNo().trim().equals(shelfNo.trim())) {
            houseDO.setShelfNo(shelfNo);
            invOnWarehouseMapperExt.updateByPrimaryKeySelective(houseDO);
        }
    }


    @Override
    public InventoryDO selectBySkuCodeAndCompanyNo(String skuCode, String companyNo) {
        return mapper.queryBySkuCodeAndCompanyNo(skuCode, companyNo);
    }

    /**
     * 发货
     *
     * @param orderDO
     */
    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public Map<InventoryOnWareHouseDO, Long> ship(MallSubOrderDO orderDO) throws ErpCommonException {
        /**修改库存  和  库存占用*/
        InventoryDO inventoryDO = mapper.queryBySkuCodeAndCompanyNo(orderDO.getSkuCode(), AppUtil.getLoginUserCompanyNo());
        /**
         * 库存逻辑
         * */
        Integer quantity = orderDO.getQuantity();
        inventoryDO.setLockedInv(inventoryDO.getLockedInv() - quantity);
        long inv = inventoryDO.getInv() - quantity;
        if (inv < 0) {
            throw new ErpCommonException("库存不足,不允许发货");
        }
        inventoryDO.setInv(inv);
        mapper.updateByPrimaryKeySelective(inventoryDO);
        /**更新相关InventoryOnWareHouse*/
        /**发货*/
        Map<InventoryOnWareHouseDO, Long> map = invOnWarehouseService.ship(inventoryDO, Long.valueOf(quantity));
        /**生成流水*/
        Integer opeatory = 201;
        for (InventoryOnWareHouseDO houseDO : map.keySet()) {
            orderDO.setWarehouseNo(houseDO.getWarehouseNo());
            orderDO.update();
            mallSubOrderMapper.updateByPrimaryKeySelective(orderDO);
            saveInventoryInOut(inventoryDO, houseDO, opeatory, map.get(houseDO), "发货出库");
        }
        return map;
    }


    /**
     * 出库单出库
     *
     * @param outManifestDO
     */
    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void outOfStorehouse(InventoryOutManifestDO outManifestDO) {
//        outManifestMapper
        List<InventoryOutManifestDetailDO> list = outManifestDetailDOMapper.selectByOutNo(outManifestDO.getInventoryOutNo());
        outOfWarehouse(list);
    }

    /**
     * 提供给sku修改的时候修改虚拟库存
     */
    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void updateVirtualInv(String skuCode, Long virInv, String companyNo) {
        /**如果virInv 为负数  不允许修改*/
        if (virInv < 0) {
            throw new ErpCommonException("虚拟库存必须大于0");

        }
        /**查出对应库存的仓库记录*/
        InventoryDO inventory = mapper.queryBySkuCodeAndCompanyNo(skuCode, companyNo);
        if (inventory.getInv() - inventory.getLockedInv() + virInv < 0) {
            throw new ErpCommonException("当前允许虚拟库存最小值为" + (inventory.getLockedInv() - inventory.getInv()));
        }
        inventory.setVirtualInv(virInv);
        mapper.updateByPrimaryKey(inventory);

    }

    //todo
    private void outOfWarehouse(List<InventoryOutManifestDetailDO> list) {
        for (InventoryOutManifestDetailDO aDo : list) {
            /**修改库存*/
            InventoryDO inventory = mapper.queryBySkuCodeAndCompanyNo(aDo.getSkuCode(), AppUtil.getLoginUserCompanyNo());
            long inv = inventory.getInv() - aDo.getQuantity();
            if (inv < 0) {
                throw new ErpCommonException("库存不足，出库失败");
            }
            inventory.setInv(inv);
            mapper.updateByPrimaryKeySelective(inventory);
            /**分库存*/
            Map<InventoryOnWareHouseDO, Long> map = invOnWarehouseService.ship(inventory, Long.valueOf(aDo.getQuantity()));
            /**生成流水*/
            Integer opeatory = 201;
            for (InventoryOnWareHouseDO houseDO : map.keySet()) {
                saveInventoryInOut(inventory, houseDO, opeatory, map.get(houseDO), "出库单出库");
            }
        }
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
        return mapper.queryBySkuCodeAndCompanyNo(skuCode, AppUtil.getLoginUserCompanyNo());
    }


    /**
     * 增加库存   如果存在记录  则更新  如果不存在记录  则新增
     *
     * @param inventoryDO 包含增加的实体信息
     * @param inv         新增的数目
     */
    private void insertInv(InventoryDO inventoryDO, Long inv) {
        InventoryDO inventory = mapper.queryBySkuCodeAndCompanyNo(inventoryDO.getSkuCode(), AppUtil.getLoginUserCompanyNo());
        if (inventory == null) {
            ItemSkuDO itemSkuDO = itemSkuMapper.queryItemBySkuCode(inventoryDO.getSkuCode());
            inventoryDO.setItemName(itemSkuDO.getItemName());
            inventoryDO.setUpc(itemSkuDO.getUpc());
            inventoryDO.init();
            mapper.insertSelective(inventoryDO);
        } else {
            inventory.setInv(inventory.getInv() + inv);
            inventory.update();
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
        /****inventory***/
        inoutDO.setSkuCode(inventoryDO.getSkuCode());
        inoutDO.setItemCode(inventoryDO.getItemCode());
        /****warehouse***/
        inoutDO.setInventoryOnWarehouseNo(wareHouseDO.getInventoryOnWarehouseNo());
        inoutDO.setWarehouseNo(wareHouseDO.getWarehouseNo());
        inoutDO.setShelfNo(wareHouseDO.getShelfNo());
        /*******/
        inoutDO.init();
        inoutDO.setOperatorType(opeatory);
        inoutDO.setQuantity(quantity);
        inoutDO.setRemark(remark);
        inoutMapper.insertSelective(inoutDO);
    }

}
