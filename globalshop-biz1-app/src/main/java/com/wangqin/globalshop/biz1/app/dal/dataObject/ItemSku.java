package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 商品sku
 *
 */
@TableName("item_sku")
public class ItemSku implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/** sku编码 */
	@TableField(value = "sku_code")
	private String skuCode;

	/** 商品编码 */
	@TableField(value = "item_code")
	private String itemCode;

	/** 名称 */
	@TableField(value = "item_name")
	private String itemName;

	/** 所属公司id */
	@TableField(value = "company_no")
	private String companyNo;

	/** 所属类目编码 */
	@TableField(value = "category_code")
	private String categoryCode;

	/** 所属类目名字 */
	@TableField(value = "category_name")
	private String categoryName;

	/** 商品条码 */
	private String upc;

	/** 是否可售 */
	private Integer saleable;

	/** 采购价 */
	@TableField(value = "purchase_price")
	private Double purchasePrice;

	/** 运费 */
	private Long freight;

	/** 折扣率 */
	private Double discount;

	/** 备注 */
	private String desc;

	/** sku物流方式：0,直邮;1,拼邮 */
	@TableField(value = "logistic_type")
	private Integer logisticType;

	/** 原价 */
	@TableField(value = "cost_price")
	private Double costPrice;

	/** 品牌名字 */
	@TableField(value = "brand_name")
	private String brandName;

	/** 商品重量 */
	private Double weight;

	/** sku图片 */
	@TableField(value = "sku_pic")
	private String skuPic;

	/** 包装ID（运费计算） */
	@TableField(value = "package_code")
	private String packageCode;

	/** 包装名称，运费计算 */
	@TableField(value = "package_name")
	private String packageName;

	/** 规格英文,运费计算 */
	@TableField(value = "package_en")
	private String packageEn;

	/** 包装重量，运费计算，单位g */
	@TableField(value = "package_weight")
	private Double packageWeight;

	/**  */
	@TableField(value = "package_level_id")
	private String packageLevelId;

	/** 尺寸 */
	private String scale;

	/** 商品型号 */
	private String model;

	/** 销售类型:现货,代购 */
	@TableField(value = "sale_type")
	private Integer saleType;

	/** 销售价 */
	@TableField(value = "sale_price")
	private Double salePrice;

	/**  */
	private String creator;

	/**  */
	private String modifier;

	/**  */
	@TableField(value = "gmt_create")
	private Date gmtCreate;

	/**  */
	@TableField(value = "gmt_modify")
	private Date gmtModify;

	/**  */
	@TableField(value = "is_del")
	private Integer isDel;


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSkuCode() {
		return this.skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getCompanyNo() {
		return this.companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public String getCategoryCode() {
		return this.categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getUpc() {
		return this.upc;
	}

	public void setUpc(String upc) {
		this.upc = upc;
	}

	public Integer getSaleable() {
		return this.saleable;
	}

	public void setSaleable(Integer saleable) {
		this.saleable = saleable;
	}

	public Double getPurchasePrice() {
		return this.purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Long getFreight() {
		return this.freight;
	}

	public void setFreight(Long freight) {
		this.freight = freight;
	}

	public Double getDiscount() {
		return this.discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getLogisticType() {
		return this.logisticType;
	}

	public void setLogisticType(Integer logisticType) {
		this.logisticType = logisticType;
	}

	public Double getCostPrice() {
		return this.costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public String getBrandName() {
		return this.brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Double getWeight() {
		return this.weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getSkuPic() {
		return this.skuPic;
	}

	public void setSkuPic(String skuPic) {
		this.skuPic = skuPic;
	}

	public String getPackageCode() {
		return this.packageCode;
	}

	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}

	public String getPackageName() {
		return this.packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getPackageEn() {
		return this.packageEn;
	}

	public void setPackageEn(String packageEn) {
		this.packageEn = packageEn;
	}

	public Double getPackageWeight() {
		return this.packageWeight;
	}

	public void setPackageWeight(Double packageWeight) {
		this.packageWeight = packageWeight;
	}

	public String getPackageLevelId() {
		return this.packageLevelId;
	}

	public void setPackageLevelId(String packageLevelId) {
		this.packageLevelId = packageLevelId;
	}

	public String getScale() {
		return this.scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getSaleType() {
		return this.saleType;
	}

	public void setSaleType(Integer saleType) {
		this.saleType = saleType;
	}

	public Double getSalePrice() {
		return this.salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getModifier() {
		return this.modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getGmtCreate() {
		return this.gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModify() {
		return this.gmtModify;
	}

	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

}
