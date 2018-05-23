package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 商品
 *
 */
@TableName("item_find")
public class ItemFind implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**  */
	@TableField(value = "item_find_no")
	private String itemFindNo;

	/** 商品名称 */
	@TableField(value = "item_name")
	private String itemName;

	/** 商品编码 */
	@TableField(value = "item_code")
	private String itemCode;

	/**  */
	@TableField(value = "sku_code")
	private String skuCode;

	/** 是否新品 */
	@TableField(value = "is_new")
	private Integer isNew;

	/** 销售类型 */
	@TableField(value = "sale_type")
	private Integer saleType;

	/** 币种 */
	private Integer currency;

	/** 采购站点 */
	@TableField(value = "buy_site")
	private String buySite;

	/** 产地 */
	private String origin;

	/** sku物流方式：0,直邮;1,拼邮 */
	@TableField(value = "logistic_type")
	private Integer logisticType;

	/** 联系人 */
	@TableField(value = "contact_person")
	private String contactPerson;

	/** 联系电话 */
	@TableField(value = "contact_tel")
	private String contactTel;

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

	/** 商品描述信息 */
	private String desc;

	/** 规格 */
	private String spec;

	/** 型号 */
	private String model;

	/** 商品详情 */
	private String detail;

	/** 买手id */
	@TableField(value = "buyer_open_id")
	private String buyerOpenId;

	/** 买手名称 */
	@TableField(value = "buyer_name")
	private String buyerName;

	/** 采购发现状态 0:待审核 1:已通过 -1:已拒绝 */
	@TableField(value = "purchase_status")
	private Integer purchaseStatus;

	/** 推荐原因 */
	private String reason;

	/** 商品发现地址 */
	@TableField(value = "find_address")
	private String findAddress;

	/** 拒绝原因 */
	@TableField(value = "refuse_reason")
	private String refuseReason;

	/**  */
	private String creator;

	/**  */
	private String modifier;

	/** 创建时间 */
	@TableField(value = "gmt_create")
	private Date gmtCreate;

	/** 操作时间 */
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

	public String getItemFindNo() {
		return this.itemFindNo;
	}

	public void setItemFindNo(String itemFindNo) {
		this.itemFindNo = itemFindNo;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getSkuCode() {
		return this.skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public Integer getIsNew() {
		return this.isNew;
	}

	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}

	public Integer getSaleType() {
		return this.saleType;
	}

	public void setSaleType(Integer saleType) {
		this.saleType = saleType;
	}

	public Integer getCurrency() {
		return this.currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
	}

	public String getBuySite() {
		return this.buySite;
	}

	public void setBuySite(String buySite) {
		this.buySite = buySite;
	}

	public String getOrigin() {
		return this.origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public Integer getLogisticType() {
		return this.logisticType;
	}

	public void setLogisticType(Integer logisticType) {
		this.logisticType = logisticType;
	}

	public String getContactPerson() {
		return this.contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactTel() {
		return this.contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
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

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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

	public String getBuyerOpenId() {
		return this.buyerOpenId;
	}

	public void setBuyerOpenId(String buyerOpenId) {
		this.buyerOpenId = buyerOpenId;
	}

	public String getBuyerName() {
		return this.buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public Integer getPurchaseStatus() {
		return this.purchaseStatus;
	}

	public void setPurchaseStatus(Integer purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getFindAddress() {
		return this.findAddress;
	}

	public void setFindAddress(String findAddress) {
		this.findAddress = findAddress;
	}

	public String getRefuseReason() {
		return this.refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
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
