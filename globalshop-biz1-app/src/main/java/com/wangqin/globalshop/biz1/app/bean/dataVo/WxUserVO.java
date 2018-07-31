package com.wangqin.globalshop.biz1.app.bean.dataVo;

public class WxUserVO {

	private String openId;   //当前程序ID

	private String unionId;   //唯一性ID

	private String nickName;  //简称

	private Integer gender; //1为男性，2为女性

	private String city;  //城市

	private String province; //市

	private String country; //国家

	private String avatarUrl;

	private String headimgurl; //头像地址

	private String referer; //来源

	private Long firstLoginDevice;

	private Long lastLoginDevice;


	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getUnionId() {
		return unionId;
	}
	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getReferer() {
		return referer;
	}
	public void setReferer(String referer) {
		this.referer = referer;
	}
	public Long getFirstLoginDevice() {
		return firstLoginDevice;
	}
	public void setFirstLoginDevice(Long firstLoginDevice) {
		this.firstLoginDevice = firstLoginDevice;
	}
	public Long getLastLoginDevice() {
		return lastLoginDevice;
	}
	public void setLastLoginDevice(Long lastLoginDevice) {
		this.lastLoginDevice = lastLoginDevice;
	}
}
