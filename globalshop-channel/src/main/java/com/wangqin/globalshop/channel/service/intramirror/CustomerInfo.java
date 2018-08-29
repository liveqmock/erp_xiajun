package com.wangqin.globalshop.channel.service.intramirror;

/**
 * Create by 777 on 2018/8/27
 */
public class CustomerInfo {

	private Long geography_id; //Long 国际区域id（固定：2 - 港澳地区；） Y
	private Long address_country_id;// Long 国家id（固定：3 - 中国香港；） Y
	private String consignee_country;// String 收货国家（固定："中国香港"） Y
	private String consignee_name;// String 收货人姓名 Y
	private String consignee_mobile;// String 收货人手机号 Y
	private String consignee_province;// String 收货人所在省份 Y
	private String consignee_city;//  String 收货人所在城市 Y
	private String consignee_area;//  String 收货人所在地区 Y
	private String consignee_address;//  String 收货人详细地址 Y
	private String consigner_name;//  String 寄件人姓名（固定：到Intramirror注册时使用的用户名） Y
	private String consigner_mobile;//  String 寄件人手机号码（固定：到Intramirror注册时使用的手机号码） Y


	public Long getGeography_id() {
		return geography_id;
	}
	public void setGeography_id(Long geography_id) {
		this.geography_id = geography_id;
	}
	public Long getAddress_country_id() {
		return address_country_id;
	}
	public void setAddress_country_id(Long address_country_id) {
		this.address_country_id = address_country_id;
	}
	public String getConsignee_country() {
		return consignee_country;
	}
	public void setConsignee_country(String consignee_country) {
		this.consignee_country = consignee_country;
	}
	public String getConsignee_name() {
		return consignee_name;
	}
	public void setConsignee_name(String consignee_name) {
		this.consignee_name = consignee_name;
	}
	public String getConsignee_mobile() {
		return consignee_mobile;
	}
	public void setConsignee_mobile(String consignee_mobile) {
		this.consignee_mobile = consignee_mobile;
	}
	public String getConsignee_province() {
		return consignee_province;
	}
	public void setConsignee_province(String consignee_province) {
		this.consignee_province = consignee_province;
	}
	public String getConsignee_city() {
		return consignee_city;
	}
	public void setConsignee_city(String consignee_city) {
		this.consignee_city = consignee_city;
	}
	public String getConsignee_area() {
		return consignee_area;
	}
	public void setConsignee_area(String consignee_area) {
		this.consignee_area = consignee_area;
	}
	public String getConsignee_address() {
		return consignee_address;
	}
	public void setConsignee_address(String consignee_address) {
		this.consignee_address = consignee_address;
	}
	public String getConsigner_name() {
		return consigner_name;
	}
	public void setConsigner_name(String consigner_name) {
		this.consigner_name = consigner_name;
	}
	public String getConsigner_mobile() {
		return consigner_mobile;
	}
	public void setConsigner_mobile(String consigner_mobile) {
		this.consigner_mobile = consigner_mobile;
	}
}
