package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 渠道商品的SKU信息
 *
 */
@TableName("channel_listing_item_sku")
public class ChannelListingItemSku implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/** 商品ID */
	@TableField(value = "item_code")
	private String itemCode;

	/** sku编码 */
	@TableField(value = "sku_code")
	private String skuCode;

	/** 外部平台类型 */
	@TableField(value = "platform_type")
	private Integer platformType;

	/** 外部平台SKU id，例如有赞SKU id */
	@TableField(value = "channel_item_sku_code")
	private String channelItemSkuCode;

	/** outer_item表里面的主键 */
	@TableField(value = "channel_item_code")
	private String channelItemCode;

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

	public String getSkuCode() {
		return this.skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public Integer getPlatformType() {
		return this.platformType;
	}

	public void setPlatformType(Integer platformType) {
		this.platformType = platformType;
	}

	public String getChannelItemSkuCode() {
		return this.channelItemSkuCode;
	}

	public void setChannelItemSkuCode(String channelItemSkuCode) {
		this.channelItemSkuCode = channelItemSkuCode;
	}

	public String getChannelItemCode() {
		return this.channelItemCode;
	}

	public void setChannelItemCode(String channelItemCode) {
		this.channelItemCode = channelItemCode;
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
