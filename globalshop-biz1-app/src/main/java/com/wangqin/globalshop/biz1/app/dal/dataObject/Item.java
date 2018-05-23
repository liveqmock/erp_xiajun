package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;

/**
 *
 * 商品
 *
 */
public class Item implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/** 商品编码 */
	@TableField(value = "item_code")
	private String itemCode;

	/** 所属公司编号 */
	@TableField(value = "company_no")
	private String companyNo;

	/** 所属类目code */
	@TableField(value = "category_code")
	private String categoryCode;

	/** 商品在小程序中的二维码 */
	@TableField(value = "qr_code_url")
	private String qrCodeUrl;

	/** 商品描述信息 */
	private String desc;

	/** 商品展示视频 */
	private String video;

	/**  */
	private String subtitle;

	/** 商品名称 */
	@TableField(value = "item_name")
	private String itemName;

	/** 英文名称 */
	@TableField(value = "en_name")
	private String enName;

	/** 商品简称 */
	@TableField(value = "item_short")
	private String itemShort;

	/** 类目名称 */
	@TableField(value = "category_name")
	private String categoryName;

	/** 销售类型 */
	@TableField(value = "sale_type")
	private Integer saleType;

	/** 商品主图 */
	@TableField(value = "main_pic")
	private String mainPic;

	/** 品牌编号 */
	@TableField(value = "brand_no")
	private String brandNo;

	/** 品牌名字 */
	@TableField(value = "brand_name")
	private String brandName;

	/** 国家 */
	private Integer country;

	/** 币种 */
	private Integer currency;

	/** 产地 */
	private String origin;

	/** 运费,对外展示用 */
	private Double freight;

	/** 重量 */
	private Double weight;

	/** sku物流方式：0,直邮;1,拼邮 */
	@TableField(value = "logistic_type")
	private Integer logisticType;

	/** 商品价格区间 */
	@TableField(value = "price_range")
	private String priceRange;

	/** 包装单位 */
	private String unit;

	/** 商品来源 */
	private String source;

	/** 促销 */
	private String promotion;

	/** 是否身份证 */
	@TableField(value = "id_card")
	private Integer idCard;

	/** 销售开始日期 */
	@TableField(value = "start_date")
	private Date startDate;

	/** 销售结束日期 */
	@TableField(value = "end_date")
	private Date endDate;

	/** 预售时间 */
	@TableField(value = "booking_date")
	private Date bookingDate;

	/** 是否可售，1可售0不可销售 */
	@TableField(value = "is_sale")
	private Integer isSale;

	/** 小程序是否可售 (1:小程序可售 0:小程序不可售) */
	@TableField(value = "wxis_sale")
	private Integer wxisSale;

	/** 是否为小程序发现，0否 1是 */
	@TableField(value = "is_find")
	private Integer isFind;

	/** //0新档 1上架 2下架 -1删除 */
	private Integer status;

	/** 规格 */
	private String spec;

	/** 型号 */
	private String model;

	/** 商品详情 */
	private String detail;

	/**  */
	@TableField(value = "gmt_modify")
	private Date gmtModify;

	/**  */
	@TableField(value = "gmt_create")
	private Date gmtCreate;

	/**  */
	@TableField(value = "is_del")
	private Integer isDel;

	/**  */
	private String creator;

	/**  */
	private String modifier;


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
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

	public String getQrCodeUrl() {
		return this.qrCodeUrl;
	}

	public void setQrCodeUrl(String qrCodeUrl) {
		this.qrCodeUrl = qrCodeUrl;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getVideo() {
		return this.video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getSubtitle() {
		return this.subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getEnName() {
		return this.enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getItemShort() {
		return this.itemShort;
	}

	public void setItemShort(String itemShort) {
		this.itemShort = itemShort;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getSaleType() {
		return this.saleType;
	}

	public void setSaleType(Integer saleType) {
		this.saleType = saleType;
	}

	public String getMainPic() {
		return this.mainPic;
	}

	public void setMainPic(String mainPic) {
		this.mainPic = mainPic;
	}

	public String getBrandNo() {
		return this.brandNo;
	}

	public void setBrandNo(String brandNo) {
		this.brandNo = brandNo;
	}

	public String getBrandName() {
		return this.brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Integer getCountry() {
		return this.country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	public Integer getCurrency() {
		return this.currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
	}

	public String getOrigin() {
		return this.origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public Double getFreight() {
		return this.freight;
	}

	public void setFreight(Double freight) {
		this.freight = freight;
	}

	public Double getWeight() {
		return this.weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Integer getLogisticType() {
		return this.logisticType;
	}

	public void setLogisticType(Integer logisticType) {
		this.logisticType = logisticType;
	}

	public String getPriceRange() {
		return this.priceRange;
	}

	public void setPriceRange(String priceRange) {
		this.priceRange = priceRange;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getPromotion() {
		return this.promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	public Integer getIdCard() {
		return this.idCard;
	}

	public void setIdCard(Integer idCard) {
		this.idCard = idCard;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getBookingDate() {
		return this.bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Integer getIsSale() {
		return this.isSale;
	}

	public void setIsSale(Integer isSale) {
		this.isSale = isSale;
	}

	public Integer getWxisSale() {
		return this.wxisSale;
	}

	public void setWxisSale(Integer wxisSale) {
		this.wxisSale = wxisSale;
	}

	public Integer getIsFind() {
		return this.isFind;
	}

	public void setIsFind(Integer isFind) {
		this.isFind = isFind;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSpec() {
		return this.spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Date getGmtModify() {
		return this.gmtModify;
	}

	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}

	public Date getGmtCreate() {
		return this.gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
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

}
