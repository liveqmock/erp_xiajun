package com.wangqin.globalshop.biz1.app.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelSalePriceDO;

/**
 * 商品对象查询
 * @author zhulu
 *
 */
public class ItemSkuQueryVO extends PageQueryVO{
	
	private Integer saleMode;

	private List<ChannelSalePriceVO> priceList = new ArrayList<ChannelSalePriceVO>();

	private List<ChannelSalePriceVO> channelSalePriceList = new ArrayList<ChannelSalePriceVO>();
	
	private String thirdSkuCode;
	
	private String companyNo;
	
	private Long id;
	
	private Long itemId;
	
	private Long virtualInv;
	
	private Long lockedVirtualInv;
	/**
	 * 商品编码
	 */
	private String itemCode;
	
	private String packageLevelId;
	
	/**
	 * SKU编码
	 */
	private String skuCode;
	
	/**
	 * upc 条码
	 */
	private String upc;
	
	/**
	 * 商品名称
	 */
	private String itemName;
	
	
	/**
	 * 英文名称
	 */
	private String enName;
	
	/**
	 * 商品类目ID
	 * 
	 */
	private Long categoryId;
	
	private String categoryCode;
	
	private String categoryName;
	
	/**
	 * 品牌
	 */
	private String brand;
	/**
	 * 颜色
	 */
	private String color;
	
	/**
	 * 创建时间开始
	 */
	private Date startGmt;
	/**
	 * 创建时间end
	 */
	private Date endGmt;
	
	/**
	 * 创建时间开始
	 */
	private Date startOrderTime;
	/**
	 * 创建时间end
	 */
	private Date endOrderTime;
	
	/**
	 * 是否是采购单查询商品列表, 1是
	 */
	private Integer isPurchase;
	/**
	 * 当是采购单查询商品列表时, 是否按订单搜索, 1是
	 */
	private Integer isOrderQuery;
	
	private String buySite;
	
	private Double salePrice;
	private String scale;

	private Double weight;

	private String skuPic;
	private String creator;
	private String modifier;

	public Integer getSaleMode() {
		return saleMode;
	}

	public void setSaleMode(Integer saleMode) {
		this.saleMode = saleMode;
	}

	public List<ChannelSalePriceVO> getChannelSalePriceList() {
		return channelSalePriceList;
	}

	public void setChannelSalePriceList(List<ChannelSalePriceVO> channelSalePriceList) {
		this.channelSalePriceList = channelSalePriceList;
	}
	
	

	public List<ChannelSalePriceVO> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<ChannelSalePriceVO> priceList) {
		this.priceList = priceList;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
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
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}


	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getUpc() {
		return upc;
	}

	public void setUpc(String upc) {
		this.upc = upc;
	}

	public Integer getIsPurchase() {
		return isPurchase;
	}

	public void setIsPurchase(Integer isPurchase) {
		this.isPurchase = isPurchase;
	}

	public Integer getIsOrderQuery() {
		return isOrderQuery;
	}

	public void setIsOrderQuery(Integer isOrderQuery) {
		this.isOrderQuery = isOrderQuery;
	}

	public Date getStartOrderTime() {
		return startOrderTime;
	}

	public void setStartOrderTime(Date startOrderTime) {
		this.startOrderTime = startOrderTime;
	}

	public Date getEndOrderTime() {
		return endOrderTime;
	}

	public void setEndOrderTime(Date endOrderTime) {
		this.endOrderTime = endOrderTime;
	}

	public String getBuySite() {
		return buySite;
	}

	public void setBuySite(String buySite) {
		this.buySite = buySite;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getLockedVirtualInv() {
		return lockedVirtualInv;
	}

	public void setLockedVirtualInv(Long lockedVirtualInv) {
		this.lockedVirtualInv = lockedVirtualInv;
	}


	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
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

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getPackageLevelId() {
		return packageLevelId;
	}

	public void setPackageLevelId(String packageLevelId) {
		this.packageLevelId = packageLevelId;
	}

	public String getThirdSkuCode() {
		return thirdSkuCode;
	}

	public void setThirdSkuCode(String thirdSkuCode) {
		this.thirdSkuCode = thirdSkuCode;
	}


	public void setVirtualInv(Long virtualInv) {
		this.virtualInv = virtualInv;
	}

	public Long getVirtualInv() {
		return virtualInv;
	}
	
	
}
