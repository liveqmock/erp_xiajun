package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.util.Date;

/**
 * @author angus
 * @date 2018/7/16
 */
public class InventoryOutManifestDetailDO {
    private Long id;
    /**
     * 父 ID
     */
    private String inventoryOutNo;
    /**
     * item ID
     */
    private String itemCode;
    /**
     * 数量
     */
    private Long quantity;
    /**
     * 商品名称
     */
    private String itemName;
    /**
     * 规格
     */
    private String scale;
    /**
     * 商品条码
     */
    private String upc;
    /**
     * SKU CODE
     */
    private String skuCode;
    /**
     * SKU 图片
     */
    private String skuPic;
    /**
     * 货架号
     */
    private String shelfNo;

    private String companyNo;
    /**
     * 操作时间
     */
    private Date gmtModify;
    /**
     * 创建时间
     */
    private Date gmtCreate;

    private String modifier;

    private String creator;

    private Boolean isDel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInventoryOutNo() {
        return inventoryOutNo;
    }

    public void setInventoryOutNo(String inventoryOutNo) {
        this.inventoryOutNo = inventoryOutNo;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getSkuPic() {
        return skuPic;
    }

    public void setSkuPic(String skuPic) {
        this.skuPic = skuPic;
    }

    public String getShelfNo() {
        return shelfNo;
    }

    public void setShelfNo(String shelfNo) {
        this.shelfNo = shelfNo;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean del) {
        isDel = del;
    }
}