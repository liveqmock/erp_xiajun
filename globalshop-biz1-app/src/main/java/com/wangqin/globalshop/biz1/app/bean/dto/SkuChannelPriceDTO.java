package com.wangqin.globalshop.biz1.app.bean.dto;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelSalePriceDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuScaleDO;
import com.wangqin.globalshop.common.utils.Money;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 商品SKU 多渠道销售价相关
 *
 *
 */
public class SkuChannelPriceDTO implements Serializable {

	
	private Long id;
	private String itemName;


	private String itemCode;

	private String skuCode;
	
	private String upc;
	
//	private String brand;//品牌

	private Double salePrice;

	private String skuPic;  //sku图片

	private Integer saleType;

	private Integer saleMode;


	//多渠道销售价相关
	private List<ChannelSalePriceDO> channelSalePriceList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getUpc() {
		return upc;
	}

	public void setUpc(String upc) {
		this.upc = upc;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public String getSkuPic() {
		return skuPic;
	}

	public void setSkuPic(String skuPic) {
		this.skuPic = skuPic;
	}

	public Integer getSaleType() {
		return saleType;
	}

	public void setSaleType(Integer saleType) {
		this.saleType = saleType;
	}

	public Integer getSaleMode() {
		return saleMode;
	}

	public void setSaleMode(Integer saleMode) {
		this.saleMode = saleMode;
	}

	public List<ChannelSalePriceDO> getChannelSalePriceList() {
		return channelSalePriceList;
	}

	public void setChannelSalePriceList(List<ChannelSalePriceDO> channelSalePriceList) {
		this.channelSalePriceList = channelSalePriceList;
	}
}
