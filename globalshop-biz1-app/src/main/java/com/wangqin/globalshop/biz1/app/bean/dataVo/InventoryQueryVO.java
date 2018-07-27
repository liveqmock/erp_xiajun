package com.wangqin.globalshop.biz1.app.bean.dataVo;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;

/**
 * 库存分页查询参数
 *
 * @author liuhui
 */
public class InventoryQueryVO extends InventoryDO {
    private String warehouseNo;
    private String companyNo;
    private String skuCode;
    private String upc;
    private String itemName;
    private String itemCode;
    private String buySite;
    private Integer pageIndex;
    private String skuPic;
    private String warehouseName;
    private String inventoryOnWarehouseNo;
    private String shelfNo;
    private String creator;
    private String operatorType;
    private String remark;
    private Long quantity;

    // 2018/7/27 合并
    private Long warehouseId;
    private String positionNo;
    private String orderBy;
    private String sort;
    private Long companyId;

    @Override
    public String getItemCode() {
        return itemCode;
    }

    @Override
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    @Override
    public String getCompanyNo() {
        return companyNo;
    }

    @Override
    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getWarehouseNo() {
        return warehouseNo;
    }

    public void setWarehouseNo(String warehouseNo) {
        this.warehouseNo = warehouseNo;
    }


    @Override
    public String getSkuCode() {
        return skuCode;
    }

    @Override
    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    @Override
    public String getUpc() {
        return upc;
    }

    @Override
    public void setUpc(String upc) {
        this.upc = upc;
    }

    @Override
    public String getItemName() {
        return itemName;
    }

    @Override
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }


    public String getBuySite() {
        return buySite;
    }

    public void setBuySite(String buySite) {
        this.buySite = buySite;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getSkuPic() {
        return skuPic;
    }

    public void setSkuPic(String skuPic) {
        this.skuPic = skuPic;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getInventoryOnWarehouseNo() {
        return inventoryOnWarehouseNo;
    }

    public void setInventoryOnWarehouseNo(String inventoryOnWarehouseNo) {
        this.inventoryOnWarehouseNo = inventoryOnWarehouseNo;
    }

    public String getShelfNo() {
        return shelfNo;
    }

    public void setShelfNo(String shelfNo) {
        this.shelfNo = shelfNo;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getPositionNo() {
        return positionNo;
    }

    public void setPositionNo(String positionNo) {
        this.positionNo = positionNo;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
