package com.wangqin.globalshop.order.app.kuaidi_bean._4px;

import java.util.List;

public class _4pxOrderData {

	
	private String ShipperOrderNo ;//接入商唯一订单编码，用于唯一标识某订单，并且在分箱与合箱时更新此编号查找需要分箱与合箱的包裹
	private String WarehouseCode ;//收货仓库代码,详见仓库代码定义字典表
	private String WarehouseOperateMode ;//仓库操作模式，如单票、合箱、分箱,详见仓库操作模式字义字典表
	private String CarrierDeliveryNo ;//发件承运商面单号码，国外物流派送公司的物流单号
	
	private String ServiceTypeCode ;//服务类型代码,详见服务类型定义字典表
	private String ItemDeclareCurrency ;//货物申报币种－使用国际货币标准码,详见币种字义字典表
	private String ConsigneeName ;//收件人姓名
	
	private String CountryOfDestination ;//目的地国家(国家3字码，参照5.9）
	private String ReceiptCountry ; //收货国家
	
	private String Province ;//收件省份
	private String City ;//收件城市
	private String District ;//收件区/县
	private String ConsigneeStreetDoorNo ;//收件街道及门牌号码
	private String ConsigneePostCode ;//收件邮编
	private String ConsigneeMobile ;//收件人手机号码
	private String ConsigneeIDNumber ;//收件人证件号码，如果使用的转运服务要求上传清关身份证信息，则此项必填
	private String ConsigneeIDFrontCopy ;//收件人证件正面扫描图片URL,如果使用的转运服务要求上传清关身份证信息，则此项必填
	private String ConsigneeIDBackCopy ;//收件人证件反面扫描图片URL,如果使用的转运服务要求上传清关身份证信息，则此项必填
	private String UserCode ;//会员编号，由会员注册后由转运四方提供
	
	private List<_4pxItem> ITEMS ;//商品明细

	public String getShipperOrderNo() {
		return ShipperOrderNo;
	}

	public void setShipperOrderNo(String shipperOrderNo) {
		ShipperOrderNo = shipperOrderNo;
	}

	public String getWarehouseCode() {
		return WarehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		WarehouseCode = warehouseCode;
	}

	public String getWarehouseOperateMode() {
		return WarehouseOperateMode;
	}

	public void setWarehouseOperateMode(String warehouseOperateMode) {
		WarehouseOperateMode = warehouseOperateMode;
	}

	public String getCarrierDeliveryNo() {
		return CarrierDeliveryNo;
	}

	public void setCarrierDeliveryNo(String carrierDeliveryNo) {
		CarrierDeliveryNo = carrierDeliveryNo;
	}

	public String getServiceTypeCode() {
		return ServiceTypeCode;
	}

	public void setServiceTypeCode(String serviceTypeCode) {
		ServiceTypeCode = serviceTypeCode;
	}

	public String getItemDeclareCurrency() {
		return ItemDeclareCurrency;
	}

	public void setItemDeclareCurrency(String itemDeclareCurrency) {
		ItemDeclareCurrency = itemDeclareCurrency;
	}

	public String getConsigneeName() {
		return ConsigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		ConsigneeName = consigneeName;
	}

	public String getProvince() {
		return Province;
	}

	public void setProvince(String province) {
		Province = province;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getDistrict() {
		return District;
	}

	public void setDistrict(String district) {
		District = district;
	}

	public String getConsigneeStreetDoorNo() {
		return ConsigneeStreetDoorNo;
	}

	public void setConsigneeStreetDoorNo(String consigneeStreetDoorNo) {
		ConsigneeStreetDoorNo = consigneeStreetDoorNo;
	}

	public String getConsigneePostCode() {
		return ConsigneePostCode;
	}

	public void setConsigneePostCode(String consigneePostCode) {
		ConsigneePostCode = consigneePostCode;
	}

	public String getConsigneeMobile() {
		return ConsigneeMobile;
	}

	public void setConsigneeMobile(String consigneeMobile) {
		ConsigneeMobile = consigneeMobile;
	}

	public String getConsigneeIDNumber() {
		return ConsigneeIDNumber;
	}

	public void setConsigneeIDNumber(String consigneeIDNumber) {
		ConsigneeIDNumber = consigneeIDNumber;
	}

	public String getConsigneeIDFrontCopy() {
		return ConsigneeIDFrontCopy;
	}

	public void setConsigneeIDFrontCopy(String consigneeIDFrontCopy) {
		ConsigneeIDFrontCopy = consigneeIDFrontCopy;
	}

	public String getConsigneeIDBackCopy() {
		return ConsigneeIDBackCopy;
	}

	public void setConsigneeIDBackCopy(String consigneeIDBackCopy) {
		ConsigneeIDBackCopy = consigneeIDBackCopy;
	}

	public String getUserCode() {
		return UserCode;
	}

	public void setUserCode(String userCode) {
		UserCode = userCode;
	}

	public List<_4pxItem> getITEMS() {
		return ITEMS;
	}

	public void setITEMS(List<_4pxItem> iTEMS) {
		ITEMS = iTEMS;
	}

	public String getCountryOfDestination() {
		return CountryOfDestination;
	}

	public void setCountryOfDestination(String countryOfDestination) {
		CountryOfDestination = countryOfDestination;
	}

	public String getReceiptCountry() {
		return ReceiptCountry;
	}

	public void setReceiptCountry(String receiptCountry) {
		ReceiptCountry = receiptCountry;
	}
	

}
