package com.wangqin.globalshop.channel.dal.haihu;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@TableName("`outer_order`")
public class OuterOrder implements Serializable {

	// `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
	// `taget_no` varchar(64) NOT NULL COMMENT '外部订单编号',
	// `outer_no` varchar(64) NOT NULL COMMENT '内部系统生成的外部订单编号',
	// `sales_id` varchar(64) DEFAULT '0' COMMENT '销售ID',
	// `sales_name` varchar(64) DEFAULT NULL COMMENT '销售名称',
	// `outerOrder_time` datetime NOT NULL COMMENT '销售时间',
	// `status` tinyint(2) DEFAULT '0' COMMENT '订单状态',
	// `stock_status` tinyint(2) DEFAULT '0' COMMENT '备货状态',
	// `receiver` varchar(64) DEFAULT NULL COMMENT '收件人',
	// `address` varchar(500) DEFAULT NULL COMMENT '收件地址',
	// `telephone` varchar(32) DEFAULT NULL COMMENT '联系电话',
	// `postcode` varchar(18) DEFAULT NULL COMMENT '邮编',
	// `remark` varchar(256) DEFAULT NULL COMMENT '备注',
	// `gmt_create` datetime NOT NULL COMMENT '创建时间',
	// `gmt_modify` datetime NOT NULL COMMENT '操作时间',

	@TableId(type = IdType.AUTO)
	private Long id;
	@TableField(value = "target_no")
	private String targetNo;
	@TableField(value = "order_no")
	private String orderNo;
	@TableField(value = "sales_id")
	private Long salesId;// 销售ID
	@TableField(value = "sales_name")
	private String salesName;// 销售名称
	@TableField(value = "order_time")
	private Date orderTime;// 销售时间
	private Integer status;// 订单状态
	private String receiver;
	@TableField(value = "address_detail")
	private String addressDetail;// 地址详情
	private String telephone;
	private String postcode;
	private String remark;
	
	@TableField(value = "receiver_state")
	private String receiverState;// 省
	@TableField(value = "receiver_city")
	private String receiverCity;// 市
	@TableField(value = "receiver_district")
	private String receiverDistrict;// 区
	@TableField(value = "platform_type")
	private Integer platformType;// 销售来源
	@TableField(value = "pay_type")
	private Integer payType;// 支付方式
	@TableField(value = "id_card")
	private String idCard;// 身份证号码

	@TableField(exist = false)
	private String outerOrderDetailList;

	@TableField(value = "gmt_create")
	private Date gmtCreate;
	@TableField(value = "gmt_modify")
	private Date gmtModify;

	@TableField(value = "user_create")
	private String userCreate;
	@TableField(value = "user_modify")
	private String userModify;
	@TableField(exist = false)
	private List<OuterOrderDetail> outerOrderDetails;
	
	@TableField(exist = false)
	private Double totalSalePrice;
	
	@TableField(value = "wx_pay_trade_no")
	private String wxPayTradeNo;	// 微信支付商户订单号
	
	@TableField(value = "company_id")
	private Long companyId;
	
	@TableField(exist = false)
	private Integer countOfSubOrder; // 子订单数
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTargetNo() {
		return targetNo;
	}

	public void setTargetNo(String targetNo) {
		this.targetNo = targetNo;
	}

	public Long getSalesId() {
		return salesId;
	}

	public void setSalesId(Long salesId) {
		this.salesId = salesId;
	}

	public String getSalesName() {
		return salesName;
	}

	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}

	

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}



	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getOrderDetailList() {
		return outerOrderDetailList;
	}

	public void setOrderDetailList(String outerOrderDetailList) {
		this.outerOrderDetailList = outerOrderDetailList;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getOuterOrderDetailList() {
		return outerOrderDetailList;
	}

	public void setOuterOrderDetailList(String outerOrderDetailList) {
		this.outerOrderDetailList = outerOrderDetailList;
	}

	public List<OuterOrderDetail> getOuterOrderDetails() {
		return outerOrderDetails;
	}

	public void setOuterOrderDetails(List<OuterOrderDetail> outerOrderDetails) {
		this.outerOrderDetails = outerOrderDetails;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getReceiverState() {
		return receiverState;
	}

	public void setReceiverState(String receiverState) {
		this.receiverState = receiverState;
	}

	public String getReceiverCity() {
		return receiverCity;
	}

	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}

	public String getReceiverDistrict() {
		return receiverDistrict;
	}

	public void setReceiverDistrict(String receiverDistrict) {
		this.receiverDistrict = receiverDistrict;
	}

	public Integer getPlatformType() {
		return platformType;
	}

	public void setPlatformType(Integer platformType) {
		this.platformType = platformType;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Double getTotalSalePrice() {
		return totalSalePrice;
	}

	public void setTotalSalePrice(Double totalSalePrice) {
		this.totalSalePrice = totalSalePrice;
	}

	public String getWxPayTradeNo() {
		return wxPayTradeNo;
	}

	public void setWxPayTradeNo(String wxPayTradeNo) {
		this.wxPayTradeNo = wxPayTradeNo;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Integer getCountOfSubOrder() {
		return countOfSubOrder;
	}

	public void setCountOfSubOrder(Integer countOfSubOrder) {
		this.countOfSubOrder = countOfSubOrder;
	}

}
