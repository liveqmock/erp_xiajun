package com.wangqin.globalshop.biz1.app.bean.dataVo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 商品多渠道结果
 */
public class ItemSkuPriceVO {


    private Long id;
    private String skuCode;
    private String itemCode;
    private Double skuRate;
    private String itemName;
    private String companyNo;
    private String categoryCode;
    private String categoryName;
    private String upc;
    private Double weight;
    private String skuPic;
    private String packageLevelId;
    private Double salePrice;
    private Integer isSale;
    private Integer saleOnYouzan;
    private Integer saleOnHaihu;
    private Integer saleOnXcx;
    private Integer status;
    private Integer saleable;
    private Double originSalePrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Double getSkuRate() {
        return skuRate;
    }

    public void setSkuRate(Double skuRate) {
        this.skuRate = skuRate;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getSkuPic() {
        return skuPic;
    }

    public void setSkuPic(String skuPic) {
        this.skuPic = skuPic;
    }

    public String getPackageLevelId() {
        return packageLevelId;
    }

    public void setPackageLevelId(String packageLevelId) {
        this.packageLevelId = packageLevelId;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getIsSale() {
        return isSale;
    }

    public void setIsSale(Integer isSale) {
        this.isSale = isSale;
    }

    public Integer getSaleOnYouzan() {
        return saleOnYouzan;
    }

    public void setSaleOnYouzan(Integer saleOnYouzan) {
        this.saleOnYouzan = saleOnYouzan;
    }

    public Integer getSaleOnHaihu() {
        return saleOnHaihu;
    }

    public void setSaleOnHaihu(Integer saleOnHaihu) {
        this.saleOnHaihu = saleOnHaihu;
    }

    public Integer getSaleOnXcx() {
        return saleOnXcx;
    }

    public void setSaleOnXcx(Integer saleOnXcx) {
        this.saleOnXcx = saleOnXcx;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSaleable() {
        return saleable;
    }

    public void setSaleable(Integer saleable) {
        this.saleable = saleable;
    }

    public Double getOriginSalePrice() {
        return originSalePrice;
    }

    public void setOriginSalePrice(Double originSalePrice) {
        this.originSalePrice = originSalePrice;
    }
}
