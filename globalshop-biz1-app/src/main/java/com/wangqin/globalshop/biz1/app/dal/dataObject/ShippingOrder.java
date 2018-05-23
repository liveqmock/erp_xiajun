package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 物流订单
 *
 */
@TableName("shipping_order")
public class ShippingOrder implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/** 发货单号(包裹号),erp系统自动生产 */
	@TableField(value = "shipping_no")
	private String shippingNo;

	/** 物流运单号,外部物流公司的运单号 */
	@TableField(value = "logistic_no")
	private String logisticNo;

	/** 外部物流公司名称 */
	@TableField(value = "logistic_company")
	private String logisticCompany;

	/** 快递渠道：包税线，身份证线，BC线 */
	private Integer type;

	/** 运单状态 */
	private Integer status;

	/** 转运状态。0: 未预报；1: 预报失败；10：预报成功；20：创建转运单成功 */
	@TableField(value = "transfer_status")
	private Integer transferStatus;

	/** 是否同步到第三方渠道。0: 未同步；1: 已同步 */
	@TableField(value = "sync_send_status")
	private Integer syncSendStatus;

	/** 第三方物流的包裹入库状态 */
	@TableField(value = "tpl_pkg_status")
	private String tplPkgStatus;

	/** 实际运费 */
	private Double freight;

	/** 包裹里面的商品总重量 */
	@TableField(value = "sku_weight")
	private Double skuWeight;

	/** ERP订单编号多个用,分割 */
	@TableField(value = "mall_orders")
	private String mallOrders;

	/** PDF打印者 */
	@TableField(value = "shipping_printer")
	private String shippingPrinter;

	/** gls返回xml完整保留 */
	@TableField(value = "gls_return_back")
	private String glsReturnBack;

	/** 收件人 */
	private String receiver;

	/** 省 */
	@TableField(value = "receiver_state")
	private String receiverState;

	/** 市 */
	@TableField(value = "receiver_city")
	private String receiverCity;

	/** 区 */
	@TableField(value = "receiver_district")
	private String receiverDistrict;

	/** 详细地址 */
	private String address;

	/** 联系电话 */
	private String telephone;

	/** 邮编 */
	private String postcode;

	/** 备注 */
	private String memo;

	/** 身份证号码 */
	@TableField(value = "id_card")
	private String idCard;

	/** 身份证背面url */
	@TableField(value = "id_card_back")
	private String idCardBack;

	/** 身份证正面url */
	@TableField(value = "id_card_front")
	private String idCardFront;

	/** 创建时间 */
	@TableField(value = "gmt_create")
	private Date gmtCreate;

	/** 修改时间 */
	@TableField(value = "gmt_modify")
	private Date gmtModify;

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

	public String getShippingNo() {
		return this.shippingNo;
	}

	public void setShippingNo(String shippingNo) {
		this.shippingNo = shippingNo;
	}

	public String getLogisticNo() {
		return this.logisticNo;
	}

	public void setLogisticNo(String logisticNo) {
		this.logisticNo = logisticNo;
	}

	public String getLogisticCompany() {
		return this.logisticCompany;
	}

	public void setLogisticCompany(String logisticCompany) {
		this.logisticCompany = logisticCompany;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTransferStatus() {
		return this.transferStatus;
	}

	public void setTransferStatus(Integer transferStatus) {
		this.transferStatus = transferStatus;
	}

	public Integer getSyncSendStatus() {
		return this.syncSendStatus;
	}

	public void setSyncSendStatus(Integer syncSendStatus) {
		this.syncSendStatus = syncSendStatus;
	}

	public String getTplPkgStatus() {
		return this.tplPkgStatus;
	}

	public void setTplPkgStatus(String tplPkgStatus) {
		this.tplPkgStatus = tplPkgStatus;
	}

	public Double getFreight() {
		return this.freight;
	}

	public void setFreight(Double freight) {
		this.freight = freight;
	}

	public Double getSkuWeight() {
		return this.skuWeight;
	}

	public void setSkuWeight(Double skuWeight) {
		this.skuWeight = skuWeight;
	}

	public String getMallOrders() {
		return this.mallOrders;
	}

	public void setMallOrders(String mallOrders) {
		this.mallOrders = mallOrders;
	}

	public String getShippingPrinter() {
		return this.shippingPrinter;
	}

	public void setShippingPrinter(String shippingPrinter) {
		this.shippingPrinter = shippingPrinter;
	}

	public String getGlsReturnBack() {
		return this.glsReturnBack;
	}

	public void setGlsReturnBack(String glsReturnBack) {
		this.glsReturnBack = glsReturnBack;
	}

	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getReceiverState() {
		return this.receiverState;
	}

	public void setReceiverState(String receiverState) {
		this.receiverState = receiverState;
	}

	public String getReceiverCity() {
		return this.receiverCity;
	}

	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}

	public String getReceiverDistrict() {
		return this.receiverDistrict;
	}

	public void setReceiverDistrict(String receiverDistrict) {
		this.receiverDistrict = receiverDistrict;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getIdCard() {
		return this.idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getIdCardBack() {
		return this.idCardBack;
	}

	public void setIdCardBack(String idCardBack) {
		this.idCardBack = idCardBack;
	}

	public String getIdCardFront() {
		return this.idCardFront;
	}

	public void setIdCardFront(String idCardFront) {
		this.idCardFront = idCardFront;
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
