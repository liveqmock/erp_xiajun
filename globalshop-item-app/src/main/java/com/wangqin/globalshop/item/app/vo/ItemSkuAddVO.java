package com.wangqin.globalshop.item.app.vo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.wangqin.globalshop.common.utils.Money;


/**
 * 商品SKU
 * 
 * @author zhulu
 *
 */
public class ItemSkuAddVO implements Serializable {
	/** 主键id */
	@TableId(type = IdType.AUTO)
	private Long id;
	@TableField(value = "item_id")
	private Long itemId;
	@TableField(value = "item_name")
	private String itemName;
	@TableField(value = "category_id")
	private Long categoryId;
	@TableField(value = "category_name")
	private String categoryName;
	@TableField(value = "item_code")
	private String itemCode;
	@TableField(value = "sku_code")
	private String skuCode;
	@TableField(value = "company_id")
	private Long companyId;
	
	private String upc;
	
	private String brand;//品牌


	private Integer salable;

	private String color;

	private String scale;

	@TableField(value = "purchase_price")
	private Double purchasePrice;
	@TableField(value = "freight")
	private Long freight;
	@TableField(exist = false)
	private String freightStr;
	@TableField(value = "sale_price")
	private Double salePrice;

	private String remark;
//	@TableField(value = "main_pic")
//	private String mainPic; //商品主图
	
	@TableField(value = "sku_pic")
	private String skuPic;  //sku图片
	
	@TableField(value = "gmt_create")
	private Date gmtCreate;
	@TableField(value = "gmt_modify")
	private Date gmtModify;
	@TableField(value = "user_create")
	private String userCreate;
	@TableField(value = "user_modify")
	private String userModify;
	
	//销售类型
	@TableField(value = "sale_type")
	private Integer saleType;
	//物流类型
	@TableField(value = "logistic_type")
	private Integer logisticType;
	//采购站点
	@TableField(value = "buy_site")
	private String buySite;
	
	//商品重量
	private Double  weight;
	
	//包装重量
	@TableField(value = "package_weight")
	private Double  packageWeight;
	//包装id
	@TableField(value = "package_id")
	private Long packageId;
	
	@TableField(value = "package_level_id")
	private String packageLevelId;
	
	//规格英文
	@TableField(value = "package_en")
	private String packageEn;
	
	@TableField(value = "package_name")
	private String packageName;
	
	/**
	 * 可售库存
	 */
	@TableField(exist = false)
	private int totalAvailableInv;
	/**
	 * 实际占用库存
	 */
	@TableField(exist = false)
	private int lockedInv;
	/**
	 * 实际库存
	 */
	@TableField(exist = false)
	private int inventory;
	/**
	 * 虚拟库存
	 */
	@TableField(exist = false)
	private int virtualInv;
	/**
	 * 虚拟占用库存
	 */
	@TableField(exist = false)
	private int lockedVirtualInv;
	/**
	 * 在途库存
	 */
	@TableField(exist = false)
	private int transInv;
	
	/**
	 * 在途占用库存
	 */
	@TableField(exist = false)
	private int lockedTransInv;
	
	
	/**
	 * 采购导出excel相关
	 */
	@TableField(exist = false)
	private Integer count;
	@TableField(exist = false)
	private String taskTitle;
	@TableField(exist = false)
	private String  taskOrderNo;
	@TableField(exist = false)
	private Integer inCount;
	/**
	 * 采购商品查询相关
	 */
	@TableField(exist = false)
	private String taskDailyItemSkuCase;
	@TableField(exist = false)
	private int saleNeed;
	@TableField(exist = false)
	private int purchaseNeed;
	
	@TableField(value = "cost_price") // 原价
	private Double costPrice;
	
	@TableField(value = "discount") // 折扣率
	private Double discount;
	
	@TableField(value = "third_sku_code") // 第三方sku
	private String  thirdSkuCode;
	
	@TableField(exist = false)
	private Integer itemSkuQuantity;
	
	public Double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}
	public Integer getInCount() {
		return inCount;
	}
	public void setInCount(Integer inCount) {
		this.inCount = inCount;
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
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
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

	public Integer getSalable() {
		return salable;
	}
	public void setSalable(Integer salable) {
		this.salable = salable;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}

	public Long getFreight() {
		return freight;
	}
	public void setFreight(Long freight) {
		this.freight = freight;
	}

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
//	public String getMainPic() {
//		return mainPic;
//	}
//	public void setMainPic(String mainPic) {
//		this.mainPic = mainPic;
//	}
	public String getSkuCode() {
		return skuCode;
	}
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
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
	public Integer getSaleType() {
		return saleType;
	}
	public void setSaleType(Integer saleType) {
		this.saleType = saleType;
	}
	public Integer getLogisticType() {
		return logisticType;
	}
	public void setLogisticType(Integer logisticType) {
		this.logisticType = logisticType;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
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

	public Long getPackageId() {
		return packageId;
	}
	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}
	public String getPackageEn() {
		return packageEn;
	}
	public void setPackageEn(String packageEn) {
		this.packageEn = packageEn;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getPackageLevelId() {
		return packageLevelId;
	}
	public void setPackageLevelId(String packageLevelId) {
		this.packageLevelId = packageLevelId;
	}
	public int getVirtualInv() {
		return virtualInv;
	}
	
	public int getLockedVirtualInv() {
		return lockedVirtualInv;
	}
	public void setLockedVirtualInv(int lockedVirtualInv) {
		this.lockedVirtualInv = lockedVirtualInv;
	}
	public String getFreightStr() {
		String a = "0.0";
		if(freight!=null){
		Money money = new Money(freight);
		a= money.toString();
		}
		return a;
	}
	public void setFreightStr(String freightStr) {
		this.freightStr = freightStr;
	}
	public Double getPackageWeight() {
		return packageWeight;
	}
	public void setPackageWeight(Double packageWeight) {
		this.packageWeight = packageWeight;
	}
	public Double getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public Double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}
	public String getSkuPic() {
		return skuPic;
	}
	public void setSkuPic(String skuPic) {
		this.skuPic = skuPic;
	}

	public int getLockedInv() {
		return lockedInv;
	}
	public void setLockedInv(int lockedInv) {
		this.lockedInv = lockedInv;
	}
	public int getInventory() {
		return inventory;
	}
	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
	public int getTransInv() {
		return transInv;
	}
	public void setTransInv(int transInv) {
		this.transInv = transInv;
	}
	public void setVirtualInv(int virtualInv) {
		this.virtualInv = virtualInv;
	}
	public int getLockedTransInv() {
		return lockedTransInv;
	}
	public void setLockedTransInv(int lockedTransInv) {
		this.lockedTransInv = lockedTransInv;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getTaskTitle() {
		return taskTitle;
	}
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}
	public String getTaskOrderNo() {
		return taskOrderNo;
	}
	public void setTaskOrderNo(String taskOrderNo) {
		this.taskOrderNo = taskOrderNo;
	}
	public String getTaskDailyItemSkuCase() {
		return taskDailyItemSkuCase;
	}
	public void setTaskDailyItemSkuCase(String taskDailyItemSkuCase) {
		this.taskDailyItemSkuCase = taskDailyItemSkuCase;
	}
	public int getSaleNeed() {
		return saleNeed;
	}
	public void setSaleNeed(int saleNeed) {
		this.saleNeed = saleNeed;
	}
	public int getPurchaseNeed() {
		return purchaseNeed;
	}
	public void setPurchaseNeed(int purchaseNeed) {
		this.purchaseNeed = purchaseNeed;
	}
	public int getTotalAvailableInv() {
		return totalAvailableInv;
	}
	public void setTotalAvailableInv(int totalAvailableInv) {
		this.totalAvailableInv = totalAvailableInv;
	}
	public String getBuySite() {
		return buySite;
	}
	public void setBuySite(String buySite) {
		this.buySite = buySite;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public String getThirdSkuCode() {
		return thirdSkuCode;
	}
	public void setThirdSkuCode(String thirdSkuCode) {
		this.thirdSkuCode = thirdSkuCode;
	}
	public Integer getItemSkuQuantity() {
		return itemSkuQuantity;
	}
	public void setItemSkuQuantity(Integer itemSkuQuantity) {
		this.itemSkuQuantity = itemSkuQuantity;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

}
