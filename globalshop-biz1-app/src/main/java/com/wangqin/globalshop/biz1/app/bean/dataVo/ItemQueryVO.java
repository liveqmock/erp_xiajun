package com.wangqin.globalshop.biz1.app.bean.dataVo;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerDO;
import com.wangqin.globalshop.biz1.app.enums.ChannelType;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 商品
 *
 * @author xiajun
 */
public class ItemQueryVO extends PageQueryVO {

	/**
	 * 商品列表：按条件查询
	 */
	private String itemCode;
    private String name;
    private String categoryCode;
    private String startTime;//前端传来的
    private String endTime;
    private Integer status;
    private Date startDate;//转化成数据库使用的
    private Date endDate;
    
    
    //0808新加字段
    private Integer isAbroad;
    private Integer shelfMethod;
    
    
    
    
    private Long id;

    private String companyNo;

    

    /**
     * 英文名称
     */
    private String enName;

    /**
     * 商品类目ID
     */
    private Long categoryId;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 采购站点
     */
    private String buySite;

    /**
     * 创建时间开始
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startGmt;
    /**
     * 创建时间end
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endGmt;




    /**
     * 商品状态
     */
    private Integer purchaseStatus;

    private Integer hasInventory;
    private Integer hasVirtualInventory;

    private String owners;

    


    private String categoryName;

    private Integer isNew;

    private Integer saleType;

    private String mainPic;

    private Integer mainPicNum;

    private String itemShort;

    private String country;
    private Integer currency;

    private String origin;
    private Double freight;
    private Double weight;

    private String priceRange;
    private String unit;
    private String source;
    private String promotion;

    private String contactPerson;

    private String contactTel;

    private Integer idCard;


    private Integer isSale;


    private String userCreate;

    private String userModify;

    private List<ItemSkuAddVO> itemSkus = new ArrayList<ItemSkuAddVO>();

    private String skuList;
    

    private String remark; //商品描述信息

    private String detail;//商品详情，同步到有赞

    private String spec;//规格

    private String model;//型号

    private Integer logisticType;


    private String outerAlias;    //有赞商品链接


    private String sexStyle;    //男女款式


    private String dimensionCodePic;


    private String bookingDate;

    private Integer wxisSale;

    private String refuseReason;


    private Long buyerId;

    private String buyerName;


    private String imageIds;    //男女款式


    private Integer thirdSale;


    private Integer saleOnYouzan; // 在有赞售卖


    private String skuCode;    //skucode


    private String salePrice;


    private String skuColor;


    private Integer itemQuantity;


    private List<BuyerDO> wxList;


    private String saleOnChannels;

    private Date gmtModify;

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

    public String getSkuColor() {
        return skuColor;
    }

    public void setSkuColor(String skuColor) {
        this.skuColor = skuColor;
    }

    public String getSkuWeight() {
        return skuWeight;
    }

    public void setSkuWeight(String skuWeight) {
        this.skuWeight = skuWeight;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    private String skuWeight;

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getSkuUpc() {
        return skuUpc;
    }

    public void setSkuUpc(String skuUpc) {
        this.skuUpc = skuUpc;
    }

    private String skuUpc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    public Integer getSaleType() {
        return saleType;
    }

    public void setSaleType(Integer saleType) {
        this.saleType = saleType;
    }

    public String getMainPic() {
        return mainPic;
    }

    public void setMainPic(String mainPic) {
        this.mainPic = mainPic;
    }

    public Integer getMainPicNum() {
        return mainPicNum;
    }

    public void setMainPicNum(Integer mainPicNum) {
        this.mainPicNum = mainPicNum;
    }

    public String getItemShort() {
        return itemShort;
    }

    public void setItemShort(String itemShort) {
        this.itemShort = itemShort;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getBuySite() {
        return buySite;
    }

    public void setBuySite(String buySite) {
        this.buySite = buySite;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public Integer getIdCard() {
        return idCard;
    }

    public void setIdCard(Integer idCard) {
        this.idCard = idCard;
    }

    public Integer getIsSale() {
        return isSale;
    }

    public void setIsSale(Integer isSale) {
        this.isSale = isSale;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public String getUserModify() {
        return userModify;
    }

    public void setUserModify(String userModify) {
        this.userModify = userModify;
    }

    public List<ItemSkuAddVO> getItemSkus() {
        return itemSkus;
    }

    public void setItemSkus(List<ItemSkuAddVO> itemSkus) {
        this.itemSkus = itemSkus;
    }


    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public Integer getCurrency() {
        return currency;
    }

    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getSkuList() {
        return skuList;
    }

    public void setSkuList(String skuList) {
        this.skuList = skuList;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getOuterAlias() {
        return outerAlias;
    }

    public void setOuterAlias(String outerAlias) {
        this.outerAlias = outerAlias;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getLogisticType() {
        return logisticType;
    }

    public void setLogisticType(Integer logisticType) {
        this.logisticType = logisticType;
    }

    public String getSexStyle() {
        return sexStyle;
    }

    public void setSexStyle(String sexStyle) {
        this.sexStyle = sexStyle;
    }

    public String getDimensionCodePic() {
        return dimensionCodePic;
    }

    public void setDimensionCodePic(String dimensionCodePic) {
        this.dimensionCodePic = dimensionCodePic;
    }

    public Integer getWxisSale() {
        return wxisSale;
    }

    public void setWxisSale(Integer wxisSale) {
        this.wxisSale = wxisSale;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getImageIds() {
        return imageIds;
    }

    public void setImageIds(String imageIds) {
        this.imageIds = imageIds;
    }

    public Integer getThirdSale() {
        return thirdSale;
    }

    public void setThirdSale(Integer thirdSale) {
        this.thirdSale = thirdSale;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public List<BuyerDO> getWxList() {
        return wxList;
    }

    public void setWxList(List<BuyerDO> wxList) {
        this.wxList = wxList;
    }

    public String getOwners() {
        return owners;
    }

    public void setOwners(String owners) {
        this.owners = owners;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public Integer getSaleOnYouzan() {
        return saleOnYouzan;
    }

    public void setSaleOnYouzan(Integer saleOnYouzan) {
        this.saleOnYouzan = saleOnYouzan;
    }

    public String getSaleOnChannels() {
        return saleOnChannels;
    }

    public void setSaleOnChannels(String saleOnChannels) {
        this.saleOnChannels = saleOnChannels;
    }


    public List<Integer> generateSaleOnChannels() {
        List<Integer> list = new ArrayList<Integer>();
        if (this.getSaleOnYouzan() == 1) {
            list.add(ChannelType.YouZan.getValue());
        }
        if (this.getThirdSale() == 1) {
            list.add(ChannelType.HaiHu.getValue());
        }
        return list;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public Date getStartGmt() {
        return startGmt;
    }

    public void setStartGmt(Date startGmt) {
        this.startGmt = startGmt;
    }

    public Date getEndGmt() {
        return endGmt;
    }

    public void setEndGmt(Date endGmt) {
        this.endGmt = endGmt;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(Integer purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getIsAbroad() {
		return isAbroad;
	}

	public void setIsAbroad(Integer isAbroad) {
		this.isAbroad = isAbroad;
	}

	public Integer getShelfMethod() {
		return shelfMethod;
	}

	public void setShelfMethod(Integer shelfMethod) {
		this.shelfMethod = shelfMethod;
	}
    
	
    
}
