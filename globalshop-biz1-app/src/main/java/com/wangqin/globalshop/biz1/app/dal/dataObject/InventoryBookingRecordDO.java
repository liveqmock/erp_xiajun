package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.util.Date;

public class InventoryBookingRecordDO extends BaseModel {
    private Long id;

    private String companyNo;

    private String orderNo;

    private String subOrderNo;

    private String itemCode;

    private String skuCode;

    private Long quantity;

    private Long bookedQuantity;

    private Long inventory;

    private String inventoryType;

    private String operatorType;

    private String inventoryOnWarehouseNo;

    private String warehouseNo;

    private String positionNo;

    private Date gmtCreate;

    private Date gmtModify;

    private Boolean isDel;

    private String creator;

    private String modifier;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getCompanyNo() {
        return companyNo;
    }

    @Override
    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSubOrderNo() {
        return subOrderNo;
    }

    public void setSubOrderNo(String subOrderNo) {
        this.subOrderNo = subOrderNo;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getBookedQuantity() {
        return bookedQuantity;
    }

    public void setBookedQuantity(Long bookedQuantity) {
        this.bookedQuantity = bookedQuantity;
    }

    public Long getInventory() {
        return inventory;
    }

    public void setInventory(Long inventory) {
        this.inventory = inventory;
    }

    public String getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(String inventoryType) {
        this.inventoryType = inventoryType;
    }

    public String getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
    }

    public String getInventoryOnWarehouseNo() {
        return inventoryOnWarehouseNo;
    }

    public void setInventoryOnWarehouseNo(String inventoryOnWarehouseNo) {
        this.inventoryOnWarehouseNo = inventoryOnWarehouseNo;
    }

    public String getWarehouseNo() {
        return warehouseNo;
    }

    public void setWarehouseNo(String warehouseNo) {
        this.warehouseNo = warehouseNo;
    }

    public String getPositionNo() {
        return positionNo;
    }

    public void setPositionNo(String positionNo) {
        this.positionNo = positionNo;
    }

    @Override
    public Date getGmtCreate() {
        return gmtCreate;
    }

    @Override
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Override
    public Date getGmtModify() {
        return gmtModify;
    }

    @Override
    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }


    @Override
    public String getCreator() {
        return creator;
    }

    @Override
    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public String getModifier() {
        return modifier;
    }

    @Override
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
    @Override
    public Boolean getIsDel() {
        return isDel;
    }
    @Override
    public void setIsDel(Boolean del) {
        isDel = del;
    }
}