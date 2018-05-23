package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 渠道上的商品信息
 *
 */
@TableName("channel_listing_item")
public class ChannelListingItem implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/** 商品ID */
	@TableField(value = "item_code")
	private String itemCode;

	/**  */
	@TableField(value = "channel_no")
	private String channelNo;

	/**  */
	@TableField(value = "company_no")
	private String companyNo;

	/** 外部平台商品id */
	@TableField(value = "channel_item_code")
	private String channelItemCode;

	/**  */
	@TableField(value = "shop_code")
	private Long shopCode;

	/** 外部平台商品别名 */
	@TableField(value = "channel_item_alias")
	private String channelItemAlias;

	/** //0新档 1上架 2下架 -1删除 */
	private Integer status;

	/**  */
	@TableField(value = "is_del")
	private Integer isDel;

	/**  */
	@TableField(value = "gmt_create")
	private Date gmtCreate;

	/**  */
	@TableField(value = "gmt_modify")
	private Date gmtModify;

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

	public String getChannelNo() {
		return this.channelNo;
	}

	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}

	public String getCompanyNo() {
		return this.companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public String getChannelItemCode() {
		return this.channelItemCode;
	}

	public void setChannelItemCode(String channelItemCode) {
		this.channelItemCode = channelItemCode;
	}

	public Long getShopCode() {
		return this.shopCode;
	}

	public void setShopCode(Long shopCode) {
		this.shopCode = shopCode;
	}

	public String getChannelItemAlias() {
		return this.channelItemAlias;
	}

	public void setChannelItemAlias(String channelItemAlias) {
		this.channelItemAlias = channelItemAlias;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
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
