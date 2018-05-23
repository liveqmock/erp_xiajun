package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 买手公司渠道账号
 *
 */
@TableName("channel_account")
public class ChannelAccount implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**  */
	@TableField(value = "channel_id")
	private Long channelId;

	/**  */
	@TableField(value = "channel_no")
	private String channelNo;

	/** 渠道类型；1：有赞，2：海狐，3：淘宝 */
	private Integer type;

	/** 渠道名称 */
	@TableField(value = "channel_name")
	private String channelName;

	/** 渠道账号所属买手公司id */
	@TableField(value = "company_no")
	private String companyNo;

	/**  */
	@TableField(value = "shop_code")
	private String shopCode;

	/**  */
	@TableField(value = "shop_name")
	private String shopName;

	/** 状态 0:正常，1:关闭 */
	private Integer status;

	/** 渠道品台分配给买手公司的账号appkey/clientId等 */
	@TableField(value = "app_key")
	private String appKey;

	/** 渠道品台分配给买手公司的账号secret */
	@TableField(value = "app_secret")
	private String appSecret;

	/** 授权后，获取到的token */
	@TableField(value = "access_token")
	private String accessToken;

	/** 刷新access_token的token */
	@TableField(value = "refresh_token")
	private String refreshToken;

	/** 平台地址 */
	@TableField(value = "server_url")
	private String serverUrl;

	/** 获取token的URL */
	@TableField(value = "token_url")
	private String tokenUrl;

	/** 接入码 */
	@TableField(value = "access_key")
	private String accessKey;

	/** 授权密码 */
	@TableField(value = "secrete_key")
	private String secreteKey;

	/** 淘宝cookie，较长 */
	private String cookie;

	/** 渠道品台分配给买手公司的其他值value1 */
	@TableField(value = "app_value1")
	private String appValue1;

	/** app_value1描述 */
	@TableField(value = "value1_desc")
	private String value1Desc;

	/** 渠道品台分配给买手公司的其他值value1 */
	@TableField(value = "app_value2")
	private String appValue2;

	/** app_value2描述 */
	@TableField(value = "value2_desc")
	private String value2Desc;

	/** 渠道品台分配给买手公司的其他值value1 */
	@TableField(value = "app_value3")
	private String appValue3;

	/** app_value3描述 */
	@TableField(value = "value3_desc")
	private String value3Desc;

	/**  */
	private String creator;

	/**  */
	private String modifier;

	/** 创建时间 */
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

	public Long getChannelId() {
		return this.channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public String getChannelNo() {
		return this.channelNo;
	}

	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getChannelName() {
		return this.channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getCompanyNo() {
		return this.companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public String getShopCode() {
		return this.shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getShopName() {
		return this.shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAppKey() {
		return this.appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return this.appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getAccessToken() {
		return this.accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return this.refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getServerUrl() {
		return this.serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public String getTokenUrl() {
		return this.tokenUrl;
	}

	public void setTokenUrl(String tokenUrl) {
		this.tokenUrl = tokenUrl;
	}

	public String getAccessKey() {
		return this.accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecreteKey() {
		return this.secreteKey;
	}

	public void setSecreteKey(String secreteKey) {
		this.secreteKey = secreteKey;
	}

	public String getCookie() {
		return this.cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public String getAppValue1() {
		return this.appValue1;
	}

	public void setAppValue1(String appValue1) {
		this.appValue1 = appValue1;
	}

	public String getValue1Desc() {
		return this.value1Desc;
	}

	public void setValue1Desc(String value1Desc) {
		this.value1Desc = value1Desc;
	}

	public String getAppValue2() {
		return this.appValue2;
	}

	public void setAppValue2(String appValue2) {
		this.appValue2 = appValue2;
	}

	public String getValue2Desc() {
		return this.value2Desc;
	}

	public void setValue2Desc(String value2Desc) {
		this.value2Desc = value2Desc;
	}

	public String getAppValue3() {
		return this.appValue3;
	}

	public void setAppValue3(String appValue3) {
		this.appValue3 = appValue3;
	}

	public String getValue3Desc() {
		return this.value3Desc;
	}

	public void setValue3Desc(String value3Desc) {
		this.value3Desc = value3Desc;
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
