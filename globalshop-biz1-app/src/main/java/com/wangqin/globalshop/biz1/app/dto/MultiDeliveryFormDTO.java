package com.wangqin.globalshop.biz1.app.dto;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;



public class MultiDeliveryFormDTO {
	private String erpOrderId;
	private String receiver;
	private String telephone;
	private String postcode;
	private String remark;
	private String addressDetail;
	//子订单收货地址如果不相同，进行预警
	private String info;
	
	private String receiverState;// 省
	private String receiverCity;// 市
	private String receiverDistrict;// 区
	private String idCard;// 身份证号码
	private Double skuWeight;
	private Double totalSalePrice;
	
	private List<MallOrderDO> erpOrderList;

	public String getErpOrderId() {
		return erpOrderId;
	}

	public void setErpOrderId(String erpOrderId) {
		this.erpOrderId = erpOrderId;
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

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public List<MallOrderDO> getErpOrderList() {
		return erpOrderList;
	}

	public void setErpOrderList(List<MallOrderDO> erpOrderList) {
		this.erpOrderList = erpOrderList;
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

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Double getSkuWeight() {
		return skuWeight;
	}

	public void setSkuWeight(Double skuWeight) {
		this.skuWeight = skuWeight;
	}

	public Double getTotalSalePrice() {
		return totalSalePrice;
	}

	public void setTotalSalePrice(Double totalSalePrice) {
		this.totalSalePrice = totalSalePrice;
	}
}