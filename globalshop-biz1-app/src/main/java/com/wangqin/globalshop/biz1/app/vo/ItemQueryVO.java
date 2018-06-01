package com.wangqin.globalshop.biz1.app.vo;

import com.wangqin.globalshop.biz1.app.constants.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerDO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 商品
 * @author zhulu
 *
 */
public class ItemQueryVO extends PageQueryVO{

	private Integer hasInventory;
	
    private Integer hasVirtualInventory;
    
	private String startDate;
	
	private String endDate;

	private Long id;
	
	private String startGmt;
	
	private String endGmt;
	
	private Long companyId;
	
	private String name;
	private Date    gmtModify;
	private Long categoryId;

	private String categoryName;

	private  String itemCode;

	private Integer isNew;

	private Integer saleType;

	private String mainPic;

	private String itemShort;

	private String enName;
	private String brand;
	private Integer country;
	private Integer currency;

	private String buySite;
	private String  origin;
	private Double  freight;
	private Double  weight;

	private String priceRange;
	private String  unit;
	private String  source;
	private String promotion;

	private String contactPerson;

	private String contactTel;

	private Integer idCard;



	private Integer isSale;



	private String userCreate;

	private String userModify;

	private  List<ItemSkuAddVO> itemSkus = new ArrayList<ItemSkuAddVO>();

	private   String skuList;
	//商品状态 0新档 1上架 2下架 3删除
	private Integer status;
	
	private String remark; //商品描述信息
	
	private String detail;//商品详情，同步到有赞
	
	private String spec;//规格
	
	private String model;//型号

	private Integer logisticType;
	

	private String outerAlias;	//有赞商品链接
	

	private String sexStyle;	//男女款式
	

	private String dimensionCodePic;
	

	private String bookingDate;

	private Integer wxisSale;

	private String refuseReason;
	

	private Long buyerId;

	private String buyerName;
	

	private String imageIds;	//男女款式
	

	private Integer thirdSale;
	

	private Integer saleOnYouzan; // 在有赞售卖
	

	private String skuCode;	//skucode
	

	private String salePrice;	
	

	private String skuColor;
	

	private Integer itemQuantity;
	

	private List<BuyerDO> wxList;
	

	private List<Integer> saleOnChannels;
	

	private String owners;
	
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

//	public Date getStartDate() {
//		return startDate;
//	}
//	public void setStartDate(Date startDate) {
//		this.startDate = startDate;
//	}
//
//	public Date getEndDate() {
//		return endDate;
//	}
//	
//	public void setEndDate(Date endDate) {
//		this.endDate = endDate;
//	}
	
	public Integer getIsSale() {
		return isSale;
	}
	public void setIsSale(Integer isSale) {
		this.isSale = isSale;
	}
	/*
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
	}*/
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

	public Integer getCountry() {
		return country;
	}
	public void setCountry(Integer country) {
		this.country = country;
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
	
//	public String getStartDateStr() {
//		if(startDate!=null){
//		 return DateFormatUtils.format(startDate, DateFormatUtils.ISO_DATE_FORMAT.getPattern());
//		}
//		return "";
//	}
//	
//	
//	public String getEndDateStr() {
//		if(endDate!=null){
//		  return DateFormatUtils.format(endDate, DateFormatUtils.ISO_DATE_FORMAT.getPattern());
//		}
//		return "";
//	}

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
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Integer getSaleOnYouzan() {
		return saleOnYouzan;
	}
	public void setSaleOnYouzan(Integer saleOnYouzan) {
		this.saleOnYouzan = saleOnYouzan;
	}
	public List<Integer> getSaleOnChannels() {
		return saleOnChannels;
	}
	public void setSaleOnChannels(List<Integer> saleOnChannels) {
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
	
	
}
