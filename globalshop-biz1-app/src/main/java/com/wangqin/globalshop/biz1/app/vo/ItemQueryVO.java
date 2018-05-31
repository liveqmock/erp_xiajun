package com.wangqin.globalshop.biz1.app.vo;

import java.util.Date;

/**
 * 商品对象查询
 * 
 * @author zhulu
 */
public class ItemQueryVO extends PageQueryVO {

    /**
     * 公司id
     */
    private Long    companyId;

    /**
     * 商品编码
     */
    private String  itemCode;

    /**
     * 商品名称
     */
    private String  name;

    /**
     * 英文名称
     */
    private String  enName;

    /**
     * 商品类目ID
     */
    private Long    categoryId;

    /**
     * 品牌
     */
    private String  brand;

    /**
     * 采购站点
     */
    private String  buySite;

    /**
     * 创建时间开始
     */
    private String    startGmt;
    /**
     * 创建时间end
     */
    private String    endGmt;

    /**
     * 销售开始日期
     */
    private String    startDate;

    /**
     * 销售结束日期
     */
    private String    endDate;

    /**
     * 商品状态
     */
    private Integer purchaseStatus;
    private String    gmtModify;

    private Integer hasInventory;
    private Integer hasVirtualInventory;

    public Integer getHasInventory() {
        return hasInventory;
    }

    public void setHasInventory(Integer hasInventory) {
        this.hasInventory = hasInventory;
    }

    public Integer getHasVirtualInventory() {
        return hasVirtualInventory;
    }

    public void setHasVirtualInventory(Integer hasVirtualInventory) {
        this.hasVirtualInventory = hasVirtualInventory;
    }

    private String owners;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBuySite() {
        return buySite;
    }

    public void setBuySite(String buySite) {
        this.buySite = buySite;
    }

   

    public String getStartGmt() {
		return startGmt;
	}

	public void setStartGmt(String startGmt) {
		this.startGmt = startGmt;
	}

	public String getEndGmt() {
		return endGmt;
	}

	public void setEndGmt(String endGmt) {
		this.endGmt = endGmt;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getPurchaseStatus() {
		return purchaseStatus;
	}

	public void setPurchaseStatus(Integer purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}

	public String getGmtModify() {
		return gmtModify;
	}

	public void setGmtModify(String gmtModify) {
		this.gmtModify = gmtModify;
	}

	public String getOwners() {
        return owners;
    }

    public void setOwners(String owners) {
        this.owners = owners;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

}
