package com.wangqin.globalshop.inventory.app.vo;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 与出库单明细交互的 VO 类
 *
 * @author angus
 * @date 2018/7/13
 */
public class InventoryOutDetailVO {

    /**
     * 出库商品的 inventoryOnWarehouseNo
     */
    private String inventoryOnWarehouseNo;
    /**
     * 出库数量
     */
    private Long quantity;

    public InventoryOutDetailVO(){

    }

    public InventoryOutDetailVO(String inventoryOnWarehouseNo, Long quantity){
        this.inventoryOnWarehouseNo = inventoryOnWarehouseNo;
        this.quantity = quantity;
    }

    public String getInventoryOnWarehouseNo() {
        return inventoryOnWarehouseNo;
    }

    public void setInventoryOnWarehouseNo(String inventoryOnWarehouseNo) {
        this.inventoryOnWarehouseNo = inventoryOnWarehouseNo;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
