package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.util.Date;

public class InventoryOnWareHouseDO {
    private Long id;
    
    private String inventoryOnWarehouseNo;
    
    private String companyNo;

	private Long itemCode;

    private String itemName;

    private String upc;

    private String scale;

    private String skuCode;

    private String skuPic;

    private Long inventory;

    private Long lockedInv;

    private Long transInv;

    private Long lockedTransInv;

    private Long warehouseNo;

    private String warehouseName;

    private String shelfNo;

    private String batchNo;

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

    public String getInventoryOnWarehouseNo() {
  		return inventoryOnWarehouseNo;
  	}

  	public void setInventoryOnWarehouseNo(String inventoryOnWarehouseNo) {
  		this.inventoryOnWarehouseNo = inventoryOnWarehouseNo;
  	}

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo == null ? null : companyNo.trim();
    }

    public Long getItemCode() {
        return itemCode;
    }

    public void setItemCode(Long itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc == null ? null : upc.trim();
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale == null ? null : scale.trim();
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode == null ? null : skuCode.trim();
    }

    public String getSkuPic() {
        return skuPic;
    }

    public void setSkuPic(String skuPic) {
        this.skuPic = skuPic == null ? null : skuPic.trim();
    }

    public Long getInventory() {
        return inventory;
    }

    public void setInventory(Long inventory) {
        this.inventory = inventory;
    }

    public Long getLockedInv() {
        return lockedInv;
    }

    public void setLockedInv(Long lockedInv) {
        this.lockedInv = lockedInv;
    }

    public Long getTransInv() {
        return transInv;
    }

    public void setTransInv(Long transInv) {
        this.transInv = transInv;
    }

    public Long getLockedTransInv() {
        return lockedTransInv;
    }

    public void setLockedTransInv(Long lockedTransInv) {
        this.lockedTransInv = lockedTransInv;
    }

    public Long getWarehouseNo() {
        return warehouseNo;
    }

    public void setWarehouseNo(Long warehouseNo) {
        this.warehouseNo = warehouseNo;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName == null ? null : warehouseName.trim();
    }

    public String getShelfNo() {
        return shelfNo;
    }

    public void setShelfNo(String shelfNo) {
        this.shelfNo = shelfNo == null ? null : shelfNo.trim();
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo == null ? null : batchNo.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}